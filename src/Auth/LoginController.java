package Auth;

import User.userDashboard;
import admin.AdminController;
import admin.adminDashboard;
import dao.AkunDAO;
import dao.CustomerDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import model.AkunModel;
import model.CustomerModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import kasir.kasirDashboard;

public class LoginController extends MouseAdapter implements ActionListener {
    private final loginPage view;
    private final AkunModel akun;
    private final CustomerModel cust;
    
    public LoginController() {
        view = new loginPage();
        akun = new AkunModel();
        cust = new CustomerModel();
        view.addActionListener(this);
        view.addMouseListener(this);
        view.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source.equals(view.getLoginbtn())) {
            try {
                String username = view.getUsername();
                String password = view.getPassword();

                if (!username.isEmpty() && !password.isEmpty()) {
                    AkunModel user = AkunDAO.validateLogin(username, password);
                    System.out.println(user.getIdAkun());
                    if (user != null) {
                        switch (user.getRole()) {
                            case "admin":
                                openAdminDashboard();
                                break;
                            case "kasir":
                                openKasirDashboard();
                                break;
                            case "member":
                                openUserDashboard(user);
                                break;
                            default:
                                JOptionPane.showMessageDialog(view, "role tidak Valid.");
                                break;
                        }
                        view.dispose();
                    } else {
                        JOptionPane.showMessageDialog(view, "Username atau Password salah");
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Tolong Isi semua fieldnya");
                }
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (source.equals(view.getSignButton())) {
            SignUpController signup = new SignUpController();
            view.dispose();
        }
    }
    
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        Object source = evt.getSource();
        
        if (source.equals(view.getForgotPass())) {
            ForgotPasswordController forgot = new ForgotPasswordController();
            view.dispose();
        }
    }
    
    private void openAdminDashboard() {
        try {
            adminDashboard adm = new adminDashboard();
            new AdminController(adm);
            adm.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(view, "Failed to open Admin Dashboard.");
        }
    }

    private void openKasirDashboard() {
        try {
            kasirDashboard ksr = new kasirDashboard();
            ksr.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(view, "Failed to open Kasir Dashboard.");
        }
    }

    private void openUserDashboard(AkunModel user) {
        try {
            CustomerDAO customerDAO = new CustomerDAO();
            CustomerModel customer = customerDAO.getCustomerByIdAkun(user.getIdAkun());

            if (customer != null) {
                userDashboard usr = new userDashboard(customer);
                usr.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(view, "Customer data not found.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(view, "Failed to fetch customer data.");
        }
    }
}
