package admin;

import Auth.LoginController;
import Auth.SignUpController;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import dao.CustomerDAO;
import dao.AkunDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import koneksiDatabase.Connect;
import model.CustomerModel;
import model.AkunModel;

public class AdminController extends MouseAdapter implements ActionListener {
    private final adminDashboard view;
    private final CustomerDAO customerDAO;
    private final AkunDAO akunDAO;
    private AkunModel akun;
    private CustomerModel cust;

    public AdminController(adminDashboard view) {
        this.view = view;
        this.customerDAO = new CustomerDAO();
        this.akunDAO = new AkunDAO();
        view.setVisible(true);
        view.addActionListener(this);
        showAkunTable();
        showCustomerTable();
    }
    
 @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

        if (source.equals(view.getBtnAddUser())) {
            handleAddUser();
        } else if (source.equals(view.getBtnEditUser())) {
            handleEditUser();
        } else if (source.equals(view.getBtnRefreshUser())) {
            handleRefresh();
        } else if (source.equals(view.getBtnDeleteUser())) {
            handleDelete();
        } else if (source.equals(view.getBtnUserManagement())) {
            handleUserManagement();
        } else if (source.equals(view.getBtnUserInformation())) {
            handleUserInformation();
        } else if (source.equals(view.getBtnLogOut())) {
            handleLogout();
        } else if (source.equals(view.getBtnEditInfoUser())) {
            handleEditUserSecondary();
        } else if (source.equals(view.getBtnRefreshInfo())) {
            handleRefreshSecondary();
        } else if (source.equals(view.getTxtSearchUsername())) {
            handleSearchUser();
        } else if (source.equals(view.getTxtSeachInfo())){
            handleSearchInfo();
        }
    }
    
    private void handleAddUser() {
        SignUpController regist = new SignUpController();
    }

    private void handleEditUser() {
        try {
            if (!akun.equals(null)) {
                    editLoginForm edit = new editLoginForm();
                    edit.txtUsername.setText(akun.getUsername());
                    edit.txtPassword.setText(akun.getPassword());
                    edit.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Please choose an account");
        }
    }

    private void handleRefresh() {
        showAkunTable();
    }

    private void handleDelete() {
        try {
            if (!akun.equals(null)) {
                int response =JOptionPane.showConfirmDialog(view, "Do you really want to delete ?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        try {
                            customerDAO.deleteCustomer(cust.getIdCustomer());
                            akunDAO.deleteAkun(akun.getIdAkun());
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

    private void handleUserManagement() {
        view.getMainPanel().removeAll();
        view.getMainPanel().repaint();
        view.getMainPanel().revalidate();
        
        view.getMainPanel().add(view.getUserManagementPanel());
        view.getMainPanel().repaint();
        view.getMainPanel().revalidate();
    }

    private void handleUserInformation() {
        view.getMainPanel().removeAll();
        view.getMainPanel().repaint();
        view.getMainPanel().revalidate();
        
        view.getMainPanel().add(view.getUserInformationPanel());
        view.getMainPanel().repaint();
        view.getMainPanel().revalidate();
    }

    private void handleLogout() {
        view.dispose();
        LoginController login = new LoginController();
    }

    private void handleEditUserSecondary() {
        try {
            if (!cust.equals(null)) {
                editInfoUserForm edtInfoUser = new editInfoUserForm();
                edtInfoUser.txtID.setText(cust.getIdCustomer());
                edtInfoUser.txtName.setText(cust.getName());
                edtInfoUser.txtPhone.setText(cust.getPhone());
                edtInfoUser.txtAddrs.setText(cust.getAddress());
        edtInfoUser.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Please choose an account");
        }
    }

    private void handleRefreshSecondary() {
        showCustomerTable();
    }
    
    private void handleSearchUser(){
        System.out.println("admin.AdminController.handleSearchUser()");
        if (!view.getTxtSearchUsername().equals("")) {
            String[] kolom = { "NO", "ID Akun", "Username", "Password", "Kontol" };
            DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
            try {
                List<AkunModel> akunList = akunDAO.SearchAkun(view.getTxtSearchUsername().getText());
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
            return;
        }
    }
    
    private void handleSearchInfo(){
        if (!view.getTxtSeachInfo().getText().equals("")) {
            int n = 0;
            String[] kolom = { "NO", "ID Customer", "ID Akun", "Nama", "Telepon", "Alamat" };
            DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
            String keyword = view.getTxtSeachInfo().getText();
            
            try {
                List<CustomerModel> customers = customerDAO.searchCustomer(keyword);
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
            // Jika input kosong, tampilkan pesan
            JOptionPane.showMessageDialog(view, "Please fill out the search field");
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource().equals(view.getTableLogin())) {
            int row = view.getTableLogin().getSelectedRow();
                TableModel model = view.getTableLogin().getModel();
                try {
                    this.akun = akunDAO.getAkunByID(model.getValueAt(row, 1).toString());
                    this.cust = customerDAO.getCustomerByIdAkun(model.getValueAt(row, 1).toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (me.getSource().equals(view.getTableInformationUser())) {
                int row = view.getTableInformationUser().getSelectedRow();
                TableModel model = view.getTableInformationUser().getModel();
                try {
                    this.akun = akunDAO.getAkunByID(model.getValueAt(row, 2).toString());
                    this.cust = customerDAO.getCustomerByIdAkun(model.getValueAt(row, 2).toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void showCustomerTable() {
        String[] kolom = { "NO", "ID Customer", "ID Akun", "Nama", "Telepon", "Alamat" };
        DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
        try {
            List<CustomerModel> customerList = customerDAO.getAllCustomers();

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
            List<AkunModel> akunList = akunDAO.getAllAkun();
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

    public void deleteAkun(String idAkun){
        try {
            akunDAO.deleteAkun(idAkun);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
