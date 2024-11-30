package controller;

import view.user.userDashboard;
import dao.AkunDAO;
import dao.CustomerDAO;
import java.awt.event.*;
import model.AkunModel;
import model.CustomerModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.auth.loginPage;

public class LoginController extends MouseAdapter {
    private final loginPage view;
    private final AkunModel akun;
    private final CustomerModel cust;

    public LoginController() {
        view = new loginPage();
        akun = new AkunModel();
        cust = new CustomerModel();
        view.setVisible(true);
        Events();
    }

    public void Events() {
        view.getLoginbtn().addActionListener(e -> {
            try {
                String username = view.getUsername().getText();
                String password = view.getPassword().getText();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Tolong isi semua field.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                AkunModel user = AkunDAO.validateLogin(username, password);

                if (user != null) {
                    switch (user.getRole()) {
                        case "admin":
                            openAdminDashboard();
                            break;
                        case "kasir":
                            openKasirDashboard();
                            break;
                        case "member":
                            openUserDashboard();
                            break;
                        default:
                            JOptionPane.showMessageDialog(view, "Role tidak valid.", "Login Error", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                    view.dispose();
                } else {
                    JOptionPane.showMessageDialog(view, "Username atau password salah.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(view, "Kesalahan database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(view, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        view.getSignButton().addActionListener(e -> {
            try {
                SignUpController signup = new SignUpController();
                view.dispose();
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(view, "Gagal membuka halaman sign-up.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        KeyAdapter enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    view.getLoginbtn().doClick();
                }
            }
        };
        view.getUsername().addKeyListener(enterKeyListener);
        view.getPassword().addKeyListener(enterKeyListener);
    }

    private void openAdminDashboard() {
        try {
            AdminController admin = new AdminController();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(view, "Gagal membuka Admin Dashboard.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openKasirDashboard() {
        try {
            KasirController kasir = new KasirController();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(view, "Gagal membuka Kasir Dashboard.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openUserDashboard() {
        try {
            UserController user = new UserController();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(view, "Gagal membuka Kasir Dashboard.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}