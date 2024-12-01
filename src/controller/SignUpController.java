package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.AkunDAO;
import dao.CustomerDAO;
import model.AkunModel;
import model.CustomerModel;
import view.auth.signupPage;

/**
 *
 * @author dzikr
 */
public class SignUpController extends MouseAdapter implements ActionListener {
    private final signupPage view;
    private final AkunModel akun;
    private final CustomerModel cust;

    public SignUpController() {
        view = new signupPage();
        akun = new AkunModel();
        cust = new CustomerModel();
        view.setVisible(true);
        addEvents();
    }
    
    public void addEvents() {
        view.getBtnSignup().addActionListener(e -> addUser());
        view.getBackToLogin().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginController login = new LoginController();
                view.dispose();
            }
        });
        
        view.getTxtUsername().addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                try {
                    if (isUsernameExist(view.getTxtUsername().getText())) {
                        view.getTxtUsername().setText("");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        
        view.getTxtPhone().addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (!view.getTxtPhone().getText().matches("\\d+")) { // Hanya angka
                    JOptionPane.showMessageDialog(view, "Nomor telepon hanya boleh berisi angka!");
                    view.getTxtPhone().setText("");
                }
            }
        });
        view.getTxtPassword().addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (view.getTxtPassword().getText().length() < 6) {
                    JOptionPane.showMessageDialog(view, "Password harus memiliki minimal 6 karakter");
                    view.getTxtPassword().setText("");
                }
            }
        });
    }
    
    private void addUser() {
        try {
            if (!isUsernameExist(view.getTxtUsername().getText()) && isPasswordEquals() && isFilled()) {
                String generatedId = AkunDAO.generateIDAkun();
                akun.setIdAkun(generatedId);
                akun.setUsername(view.getTxtUsername().getText());
                akun.setPassword(view.getTxtPassword().getText());
                akun.setRole("member");

                cust.setIdCustomer(generatedId);
                cust.setIdAkun(generatedId);
                cust.setName(view.getTxtField().getText());
                cust.setPhone(view.getTxtPhone().getText());
                cust.setAddress(view.getTxtAddress().getText());

                AkunDAO.addAkun(akun);
                CustomerDAO.addCustomer(cust);

                JOptionPane.showMessageDialog(view, "Registrasi berhasil, Silahkan Login");
                LoginController login = new LoginController();
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Registrasi gagal, silahkan coba lagi");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isPasswordEquals() {
        if (!view.getTxtPassword().getText().equals(view.getTxtConfrimPassword().getText())) {
            JOptionPane.showMessageDialog(view, "Password tidak sama");
            view.getTxtPassword().setText("");
            view.getTxtConfrimPassword().setText("");
            return false;
        } else {
            return true;
        }
    }

    private boolean isUsernameExist(String username) throws SQLException {
        if (AkunDAO.IsUsernameExist(username)) {
            JOptionPane.showMessageDialog(view, "Username sudah digunakan");
            return true;
        }
        return false;
    }

    private boolean isFilled() {
        if (!view.getTxtField().getText().isEmpty() &&
            !view.getTxtPhone().getText().isEmpty() &&
            !view.getTxtUsername().getText().isEmpty() &&
            !view.getTxtAddress().getText().isEmpty() &&
            !view.getTxtPassword().getText().isEmpty() &&
            !view.getTxtConfrimPassword().getText().isEmpty()) {

            return true;
        } else {
            JOptionPane.showMessageDialog(view, "Tolong Isi Semua Fieldnya");
            return false;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Action performed handling, if needed in your context
    }
}