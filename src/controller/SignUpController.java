package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.AkunDAO;
import dao.CustomerDAO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    
    private void addEvents() {
        view.getBtnSignup().addActionListener(e -> register());
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
        
        addNavigation();
    }

    private void addNavigation() {
        view.getTxtName().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    view.getTxtPhone().requestFocus();
                }
            }
        });

        view.getTxtPhone().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    view.getTxtEmail().requestFocus();
                } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
                    view.getTxtName().requestFocus();
                }
            }
        });
        
        view.getTxtEmail().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    view.getTxtUsername().requestFocus();
                } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
                    view.getTxtPhone().requestFocus();
                }
            }
        });

        view.getTxtUsername().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    view.getTxtPassword().requestFocus();
                } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
                    view.getTxtEmail().requestFocus();
                }
            }
        });

        view.getTxtPassword().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    view.getTxtConfrimPassword().requestFocus();
                } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
                    view.getTxtUsername().requestFocus();
                }
            }
        });

        view.getTxtConfrimPassword().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    view.getTxtAddress().requestFocus();
                } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
                    view.getTxtPassword().requestFocus();
                }
            }
        });

        view.getTxtAddress().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_UP) {
                    view.getTxtConfrimPassword().requestFocus();
                } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    view.getBtnSignup().doClick(); // Trigger button sign up
                }
            }
        });
    }

    
    private void register() {
        try {
            if (!isUsernameExist(view.getTxtUsername().getText()) && isPasswordEquals() && isFilled()) {
                String IdAkun = AkunDAO.generateIDAkun();
                akun.setIdAkun(IdAkun);
                akun.setEmail(view.getTxtEmail().getText());
                akun.setUsername(view.getTxtUsername().getText());
                akun.setPassword(view.getTxtPassword().getText());
                akun.setRole("member");

                cust.setIdCustomer(CustomerDAO.generateIDCustomer());
                cust.setIdAkun(IdAkun);
                cust.setName(view.getTxtName().getText());
                cust.setPhone(view.getTxtPhone().getText());
                cust.setAddress(view.getTxtAddress().getText());

                AkunDAO.addAkun(akun);
                CustomerDAO.addCustomer(cust);

                JOptionPane.showMessageDialog(view, "Registrasi berhasil, Silahkan Aktivasi email anda");
                EmailVerificationController emailVerif = new EmailVerificationController(akun);
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
        if (AkunDAO.isUsernameExist(username)) {
            JOptionPane.showMessageDialog(view, "Username sudah digunakan");
            return true;
        }
        return false;
    }

    private boolean isFilled() {
        if (!view.getTxtName().getText().isEmpty() &&
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