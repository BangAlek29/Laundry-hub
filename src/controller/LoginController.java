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

import java.util.prefs.Preferences;

public class LoginController extends MouseAdapter {
    private final loginPage view;
    private AkunModel akun;
    private CustomerModel cust;

    private final Preferences prefs;

    public LoginController() {
        view = new loginPage();
        akun = new AkunModel();
        cust = new CustomerModel();

        prefs = Preferences.userNodeForPackage(LoginController.class);

        view.setVisible(true);
        loadRememberedLogin();
        addEvents();
    }

    public void addEvents() {
        view.getBtnLogin().addActionListener(e -> autentifikasi());
        view.getCbRememberMe().addActionListener(e -> toggleRememberMe());
        view.getLbSign().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openSignUpPanel();
            }
        });

        view.getTxtPassword().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    view.getBtnLogin().doClick();
                } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
                    view.getTxtUsername().requestFocus();
                }
            }
        });

        view.getTxtUsername().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    view.getTxtPassword().requestFocus();
                }
            }
        });
    }

    private void autentifikasi() {
        try {
            String username = view.getTxtUsername().getText();
            String password = view.getTxtPassword().getText();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Tolong isi semua field.", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            akun = AkunDAO.validateLogin(username, password);

            if (akun != null) {
            if ("INVALID_PASSWORD".equals(akun.getIdAkun())) {
                JOptionPane.showMessageDialog(view, "Password salah.", "Login Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                cust = CustomerDAO.getCustomerByIdAkun(akun.getIdAkun());

                if (view.getCbRememberMe().isSelected()) {
                    saveLogin(username, password);
                } else {
                    clearSavedLogin();
                }

                switch (akun.getRole()) {
                    case "Admin":
                        openAdminDashboard();
                        break;
                    case "Kasir":
                        openKasirDashboard();
                        break;
                    case "Member":
                        openUserDashboard();
                        break;
                    default:
                        JOptionPane.showMessageDialog(view, "Role tidak valid.", "Login Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                }

                view.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Username tidak ditemukan.", "Login Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "General Error", ex);
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void toggleRememberMe() {
        if (!view.getCbRememberMe().isSelected()) {
            clearSavedLogin();
        }
    }

    private void saveLogin(String username, String password) {
        prefs.put("username", username);
        prefs.put("password", password);
    }

    private void clearSavedLogin() {
        prefs.remove("username");
        prefs.remove("password");
    }

    private void loadRememberedLogin() {
        String savedUsername = prefs.get("username", "");
        String savedPassword = prefs.get("password", "");

        if (!savedUsername.isEmpty() && !savedPassword.isEmpty()) {
            view.getTxtUsername().setText(savedUsername);
            view.getTxtPassword().setText(savedPassword);
            view.getCbRememberMe().setSelected(true);
        }
    }

    private void openSignUpPanel() {
        try {
            new SignUpController();
            view.dispose();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(
                    Level.SEVERE, "Error saat membuka halaman sign-up.", ex);

            JOptionPane.showMessageDialog(
                    view,
                    "Gagal membuka halaman sign-up. Silakan coba lagi.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
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
            JOptionPane.showMessageDialog(view, "Gagal membuka User Dashboard.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
