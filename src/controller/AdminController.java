package controller;

import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.AkunDAO;
import dao.CustomerDAO;
import java.awt.Component;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import koneksiDatabase.Connect;
import model.AkunModel;
import model.CustomerModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import util.PanelUtils;
import view.admin.adminDashboard;

public class AdminController extends MouseAdapter implements ActionListener {
    private final adminDashboard view;
    private AkunModel akun;
    private CustomerModel cust;

    public AdminController() {
        this.view = new adminDashboard();
        view.setVisible(true);
        refreshTable();
        addEvents();
    }

    public void addEvents() {
        view.getBtnAddUser().addActionListener(e -> showAddUserPanel());
        view.getBtnEditUser().addActionListener(e -> showEditUserPanel());
        view.getBtnRefreshUser().addActionListener(e -> refreshTable());
        view.getBtnDeleteUser().addActionListener(e -> deleteAkun());
        view.getTxtSearchUsername().addActionListener(e -> searchUser());
        
        view.getBtnUserManagement().addActionListener(e -> showUserManagementPanel());
        view.getBtnUserInformation().addActionListener(e -> showUserInformationPanel());
        view.getBtnLogOut().addActionListener(e -> Logout());
        view.getBtnCetakLaporan().addActionListener((ActionEvent e) -> {
            try {
                String reportPath = "src/laporan/Laporan_Laundry.jasper";
                HashMap<String, Object> parameter = new HashMap<>();
                Connection conn = Connect.configDB();
                JasperPrint print = JasperFillManager.fillReport(reportPath, parameter,conn );
                JasperViewer viewer = new JasperViewer(print, false);
                viewer.setVisible(true);
            } catch (JRException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        view.getBtnEditInfoUser().addActionListener(e -> showEditInfoUserPanel());
        view.getBtnRefreshInfo().addActionListener(e -> refreshTable());
        view.getTxtSeachInfo().addActionListener(e -> searchInfoUser());
        
        view.getTableLogin().getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) { // Menghindari event duplikasi
                int selectedRow = view.getTableLogin().getSelectedRow();
                if (selectedRow == -1) { // Tidak ada baris yang dipilih
                    view.getBtnEditUser().setEnabled(false);
                } else { // Ada baris yang dipilih
                    TableModel model = view.getTableLogin().getModel();
                    try {
                        akun = AkunDAO.getAkunByID(model.getValueAt(selectedRow, 1).toString());
                        cust = CustomerDAO.getCustomerByIdAkun(model.getValueAt(selectedRow, 1).toString());
                        view.getBtnEditUser().setEnabled(true);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        view.getTableInformationUser().getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) { // Menghindari event ganda
                int selectedRow = view.getTableInformationUser().getSelectedRow();
                if (selectedRow == -1) { // Tidak ada baris yang dipilih
                    view.getBtnEditInfoUser().setEnabled(false);
                } else { // Ada baris yang dipilih
                    TableModel model = view.getTableInformationUser().getModel();
                    try {
                        akun = AkunDAO.getAkunByID(model.getValueAt(selectedRow, 2).toString());
                        cust = CustomerDAO.getCustomerByIdAkun(model.getValueAt(selectedRow, 2).toString());
                        view.getBtnEditInfoUser().setEnabled(true);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void showAddUserPanel() {
        AddUserController addUser = new AddUserController(this);
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

    private void refreshTable() {
        showAkunTable();
        showCustomerTable();
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
            JOptionPane.showMessageDialog(view, "Please choose an account");
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
            String[] kolom = { "NO", "ID Akun", "Username", "Password", "Role" };
            DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
            String keyword = view.getTxtSearchUsername().getText();
            
            try {
                List<AkunModel> akunList = AkunDAO.SearchAkun(view.getTxtSearchUsername().getText());
                int n = 0;
                for (AkunModel akun : akunList) {
                    n++;
                    tb1.addRow(new String[] {
                            String.valueOf(n),
                            akun.getIdAkun(),
                            akun.getUsername(),
                            akun.getPassword(),
                            akun.getRole() });
                }
                view.getTableLogin().setModel(tb1);
                setColumnAlignment(view.getTableLogin());
                if (n == 0) {
                    JOptionPane.showMessageDialog(view, "Pencarian tidak ditemukan keyword: " + keyword);
                }
            } catch (SQLException e) {
                System.err.println(e);
                JOptionPane.showMessageDialog(view, "Error: Unable to fetch data!");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Harap isi kolom pencarian");
        }
    }

    private void searchInfoUser() {
            String[] kolom = { "NO", "ID Customer", "ID Akun", "Nama", "Telepon", "Alamat" };
            DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
            String keyword = view.getTxtSeachInfo().getText();

            try {
                List<CustomerModel> customers = CustomerDAO.searchCustomer(keyword);
                int n = 0;
                for (CustomerModel customer : customers) {
                    n++;
                    String[] rowData = {
                            String.valueOf(n),
                            customer.getIdCustomer(),
                            customer.getIdAkun(),
                            customer.getName(),
                            customer.getPhone(),
                            customer.getAddress()
                    };
                    tb1.addRow(rowData);
                }
                view.getTableInformationUser().setModel(tb1);
                setColumnAlignment(view.getTableInformationUser());
                if (n == 0) {
                    JOptionPane.showMessageDialog(view, "Pencarian tidak ditemukan keyword: " + keyword);
                }
            } catch (SQLException e) {
                System.err.println(e);
                JOptionPane.showMessageDialog(view, "Error: Unable to fetch data!");
            }
    }

    public void showCustomerTable() {
        String[] kolom = { "NO", "ID Customer", "ID Akun", "Nama", "Telepon", "Alamat" };
        DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
        try {
            List<CustomerModel> customerList = CustomerDAO.getAllCustomers();
            if (customerList != null) {
                int n = 0;
                for (CustomerModel customer : customerList) {
                    n++;
                    tb1.addRow(new String[] {
                            String.valueOf(n),
                            customer.getIdCustomer(),
                            customer.getIdAkun(),
                            customer.getName(),
                            customer.getPhone(),
                            customer.getAddress()
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.getTableInformationUser().setModel(tb1);
        setColumnAlignment(view.getTableInformationUser());
        
    }
    private void setColumnAlignment(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();

        for (int i = 0; i < table.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setHorizontalAlignment(SwingConstants.CENTER);  // Set semua kolom rata tengah
                    return comp;
                }
            });
        }
    }
    public void showAkunTable() {
        String[] kolom = { "NO", "ID Akun", "Username", "Password", "Role" };
        DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
        try {
            List<AkunModel> akunList = AkunDAO.getAllAkun();
            int n = 0;
            for (AkunModel akun : akunList) {
                n++;
                tb1.addRow(new String[] {
                        String.valueOf(n),
                        akun.getIdAkun(),
                        akun.getUsername(),
                        akun.getPassword(),
                        akun.getRole() });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        view.getTableLogin().setModel(tb1);
        setColumnAlignment(view.getTableLogin());
        view.getTableLogin().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
