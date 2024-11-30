package controller;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import dao.CustomerDAO;
import dao.AkunDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import model.CustomerModel;
import model.AkunModel;
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
        view.getBtnUserManagement().addActionListener(e -> showUserManagementPanel());
        view.getBtnUserInformation().addActionListener(e -> showUserInformationPanel());
        view.getBtnLogOut().addActionListener(e -> Logout());
        view.getBtnEditInfoUser().addActionListener(e -> showEditInfoUserPanel());
        view.getBtnRefreshInfo().addActionListener(e -> refreshTable());
        view.getTxtSearchUsername().addActionListener(e -> searchUser());
        view.getTxtSeachInfo().addActionListener(e -> searchInfoUser());
        view.getTableLogin().addMouseListener(this);
        view.getTableInformationUser().addMouseListener(this);
    }
    
    private void showAddUserPanel() {
        AddUserController addUser = new AddUserController();
    }

    private void showEditUserPanel() {
        try {
            if (akun != null) {
                EditLoginController editAkun = new EditLoginController(akun);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Please choose an account");
        }
    }

    private void refreshTable() {
        showAkunTable();
        showCustomerTable();
    }

    private void deleteAkun() {
        try {
            if (akun != null) {
                int response =JOptionPane.showConfirmDialog(view, "Do you really want to delete ?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        try {
                            CustomerDAO.deleteCustomer(cust.getIdCustomer());
                            AkunDAO.deleteAkun(akun.getIdAkun());
                            showAkunTable();
                            showCustomerTable();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }else{
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
                EditInfoUserController editInfo = new EditInfoUserController(cust);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Please choose an account");
        }
    }
    
    private void searchUser(){
        if (!view.getTxtSearchUsername().equals("")) {
            String[] kolom = { "NO", "ID Akun", "Username", "Password", "Role" };
            DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        view.getTableLogin().setModel(tb1);
            
        } else {
            JOptionPane.showMessageDialog(view, "Please fill out the form");
        }
    }
    
    private void searchInfoUser(){
        if (!view.getTxtSeachInfo().getText().equals("")) {
            int n = 0;
            String[] kolom = { "NO", "ID Customer", "ID Akun", "Nama", "Telepon", "Alamat" };
            DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
            String keyword = view.getTxtSeachInfo().getText();
            
            try {
                List<CustomerModel> customers = CustomerDAO.searchCustomer(keyword);
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
                if (n == 0) {
                    JOptionPane.showMessageDialog(view, "No data found for keyword: " + keyword);
                }
            } catch (SQLException e) {
                System.err.println(e);
                JOptionPane.showMessageDialog(view, "Error: Unable to fetch data!");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please fill out the search field");
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource().equals(view.getTableLogin())) {
            int row = view.getTableLogin().getSelectedRow();
                TableModel model = view.getTableLogin().getModel();
                try {
                    this.akun = AkunDAO.getAkunByID(model.getValueAt(row, 1).toString());
                    this.cust = CustomerDAO.getCustomerByIdAkun(model.getValueAt(row, 1).toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (me.getSource().equals(view.getTableInformationUser())) {
                int row = view.getTableInformationUser().getSelectedRow();
                TableModel model = view.getTableInformationUser().getModel();
                try {
                    this.akun = AkunDAO.getAkunByID(model.getValueAt(row, 2).toString());
                    this.cust = CustomerDAO.getCustomerByIdAkun(model.getValueAt(row, 2).toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
