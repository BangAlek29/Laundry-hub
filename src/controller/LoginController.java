package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import dao.AkunDAO;
import dao.CustomerDAO;
import model.AkunModel;
import model.CustomerModel;
import view.auth.loginPage;

public class LoginController extends MouseAdapter {
    private final loginPage view;
    private AkunModel akun;
    private CustomerModel cust;

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

                // Validasi input kosong
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Tolong isi semua field.", "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validasi login
                akun = AkunDAO.validateLogin(username, password);

                if (akun != null) {
                    // Login berhasil, dapatkan data customer jika perlu
                    cust = CustomerDAO.getCustomerByIdAkun(akun.getIdAkun());

                    // Arahkan ke dashboard sesuai role
                    switch (akun.getRole()) {
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
                            JOptionPane.showMessageDialog(view, "Role tidak valid.", "Login Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                    }

                    // Tutup tampilan login setelah berhasil
                    view.dispose();
                } else {
                    // Login gagal
                    JOptionPane.showMessageDialog(view, "Username atau password salah.", "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                // Kesalahan database
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Database Error", ex);
                JOptionPane.showMessageDialog(view, "Kesalahan database: " + ex.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Kesalahan umum
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "General Error", ex);
                JOptionPane.showMessageDialog(view, "Terjadi kesalahan: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });


        view.getSignButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    SignUpController signup = new SignUpController();
                    view.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE,
                            "Error saat membuka halaman sign-up.", ex);

                    JOptionPane.showMessageDialog(
                            view,
                            "Gagal membuka halaman sign-up. Silakan coba lagi.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
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
        
        view.getPassword().addKeyListener(enterKeyListener);
        view.getUsername().addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent evt) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                view.getPassword().requestFocus();
            }
        }
    });
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
            UserController user = new UserController(cust);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(view, "Gagal membuka Kasir Dashboard.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}