/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AkunDAO;
import dao.CustomerDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.AkunModel;
import model.CustomerModel;
import view.auth.signupPage;

/**
 *
 * @author dzikr
 */
public class SignUpController extends MouseAdapter implements ActionListener{
    private final signupPage view;
    private final AkunModel akun;
    private final CustomerModel cust;
    
    public SignUpController(){
        view = new signupPage();
        akun = new AkunModel();
        cust = new CustomerModel();
        view.addActionListener(this);
        view.addMouseListener(this);
        view.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source.equals(view.getSignUpButton())) {
            try {

            String id_akun = AkunDAO.generateIDAkun();
            if (!isUsernameExist(view.getUsernameField()) && isPasswordEquals() && isFilled()) {
                String generatedId = AkunDAO.generateIDAkun();
                AkunModel akun = new AkunModel(generatedId, view.getUsernameField(), view.getPasswordField(), "member");
                CustomerModel cust = new CustomerModel(generatedId, generatedId, view.getNameField(), view.getPhoneField(), view.getAddressField());

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
    }
    
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        Object source = evt.getSource();
        
        if (source.equals(view.getBackToLogin())) {
            LoginController login = new LoginController();
            view.dispose();
        }
    }
    
    private boolean isPasswordEquals() {
        if (!view.getPasswordField().equals(view.getConfrimPasswordField())) {
            JOptionPane.showMessageDialog(view, "Password tidak sama");
            view.setPasswordField("");
            view.setConfirmPasswordField("");
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
        if (!view.getNameField().isEmpty() && 
            !view.getPhoneField().isEmpty() && 
            !view.getUsernameField().isEmpty() && 
            !view.getAddressField().isEmpty() && 
            !view.getPasswordField().isEmpty() && 
            !view.getConfrimPasswordField().isEmpty()) {

            return true;
        } else {
            JOptionPane.showMessageDialog(view, "Please fill out the form");
            return false;
        }
    }

}
