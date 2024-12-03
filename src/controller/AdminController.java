package controller;

import util.*;
import model.*;
import dao.*;

import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import koneksiDatabase.Connect;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import view.admin.adminDashboard;

public class AdminController extends MouseAdapter implements ActionListener {
    private final adminDashboard view;
    private AkunModel akun;
    private CustomerModel cust;

    public AdminController() {
        this.view = new adminDashboard();
        view.setVisible(true);
        showAkunTable();
        showCustomerTable();
        addEvents();
    }

    public void addEvents() {
        view.getBtnAddUser().addActionListener(e -> showAddUserPanel());
        view.getBtnEditUser().addActionListener(e -> showEditUserPanel());
        view.getBtnRefreshUser().addActionListener(e -> showAkunTable());
        view.getBtnDeleteUser().addActionListener(e -> deleteAkun());
        view.getTxtSearchUsername().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchUser();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchUser();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Biasanya tidak diperlukan untuk JTextField
            }
        });
        view.getBtnUserManagement().addActionListener(e -> showUserManagementPanel());
        view.getBtnUserInformation().addActionListener(e -> showUserInformationPanel());
        view.getBtnLogOut().addActionListener(e -> Logout());
        view.getBtnCetakLaporan().addActionListener((ActionEvent e) -> {
            try {
                String reportPath = "src/laporan/Laporan_Laundry.jasper";
                Connection conn = Connect.configDB();
                JasperPrint print = JasperFillManager.fillReport(reportPath, null,conn );
                JasperViewer viewer = new JasperViewer(print, false, null);
                viewer.setVisible(true);
            } catch (JRException ex) {
                ex.printStackTrace();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        
        view.getBtnEditInfoUser().addActionListener(e -> showEditInfoUserPanel());
        view.getBtnRefreshInfo().addActionListener(e -> showCustomerTable());
        view.getTxtSeachInfo().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchInfoUser();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchInfoUser();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Biasanya tidak diperlukan untuk JTextField
            }
        });
        view.getTableLogin().getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = view.getTableLogin().getSelectedRow();
                if (selectedRow == -1) {
                    view.getBtnEditUser().setEnabled(false);
                } else {
                    TableModel model = view.getTableLogin().getModel();
                    akun = AkunDAO.getAkunByID(model.getValueAt(selectedRow, 1).toString());
                    view.getBtnEditUser().setEnabled(true);
                }
            }
        });

        view.getTableInformationUser().getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = view.getTableInformationUser().getSelectedRow();
                if (selectedRow == -1) {
                    view.getBtnEditInfoUser().setEnabled(false);
                } else {
                    TableModel model = view.getTableInformationUser().getModel();
                    cust = CustomerDAO.getCustomerByIdCustomer(model.getValueAt(selectedRow, 1).toString());
                    view.getBtnEditInfoUser().setEnabled(true);
                }
            }
        }

    );
    }

    private void showAddUserPanel() {
        try {
            AddUserController addUser = new AddUserController(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showEditUserPanel() {
        try {
            if (akun != null) {
                EditLoginController editAkun = new EditLoginController(akun,this);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Tolong pilih akun");
        }
    }

    private void deleteAkun() {
        try {
            if (akun != null) {
                int response = JOptionPane.showConfirmDialog(view, "Do you really want to delete ?", "Confirm",
                        JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    try {
                        CustomerDAO.deleteCustomer(cust.getIdCustomer());
                        AkunDAO.deleteAkun(akun.getIdAkun());
                        showAkunTable();
                        showCustomerTable();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Tolong pilih akun");
        }
    }
    
    private void showUserManagementPanel() {
        PanelUtils.switchPanel(view.getMainPanel(), view.getUserManagementPanel());
    }

    private void showUserInformationPanel() {
        PanelUtils.switchPanel(view.getMainPanel(), view.getUserInformationPanel());
    }

    private void Logout() {
        view.dispose();
        LoginController login = new LoginController();
    }

    private void showEditInfoUserPanel() {
        try {
            if (!cust.equals(null)) {
                EditInfoUserController editInfo = new EditInfoUserController(cust,this);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Please choose an account");
        }
    }

    private void searchUser() {
        if (!view.getTxtSearchUsername().equals("")) {
            String keyword = view.getTxtSearchUsername().getText();
            List<AkunModel> akunList = AkunDAO.SearchAkun(view.getTxtSearchUsername().getText());
            DefaultTableModel tb1 = TableModelFactory.createAkunTableModel(akunList);
            view.getTableLogin().setModel(tb1);
            TableUtils.setColumnAlignment(view.getTableLogin(), SwingConstants.CENTER);
        } else {
            JOptionPane.showMessageDialog(view, "Harap isi kolom pencarian");
        }
    }
    
    public void showAkunTable() {
        List<AkunModel> akunList = AkunDAO.getAllAkun();
        DefaultTableModel tb1 = TableModelFactory.createAkunTableModel(akunList);
        view.getTableLogin().setModel(tb1);
        TableUtils.setColumnAlignment(view.getTableLogin(), SwingConstants.CENTER);
    }

    private void searchInfoUser() {
        String keyword = view.getTxtSeachInfo().getText();
        List<CustomerModel> customers = CustomerDAO.searchCustomer(keyword);
        DefaultTableModel tb1 = TableModelFactory.createCustomerTableModel(customers);
        view.getTableInformationUser().setModel(tb1);
        TableUtils.setColumnAlignment(view.getTableInformationUser(), SwingConstants.CENTER);
    }

    public void showCustomerTable() {
        List<CustomerModel> customerList = CustomerDAO.getAllCustomers();
        DefaultTableModel tb1 = TableModelFactory.createCustomerTableModel(customerList);
        view.getTableInformationUser().setModel(tb1);
        TableUtils.setColumnAlignment(view.getTableInformationUser(), SwingConstants.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
