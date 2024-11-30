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
import java.sql.Statement;
import javax.swing.JOptionPane;
import koneksiDatabase.Connect;
import model.AkunModel;
import model.CustomerModel;
import view.admin.addUserForm;

/**
 *
 * @author dzikr
 */
public class AddUserController extends MouseAdapter implements ActionListener{
    private addUserForm view;
    private AkunModel akun;
    private CustomerModel cust;
    
    public AddUserController(){
        view = new addUserForm();
        view.addActionListener(this);
        view.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source.equals(view.getBtnAdd())){
            handleAddUser();
        }
        
        if (source.equals(view.getBtnBack())){
            view.dispose();
        }
    }
    
    public void handleAddUser() {
        try {
            String id_akun = AkunDAO.generateIDAkun();

            if (AkunDAO.IsUsernameExist(view.getTxtUsername().getText())) {
                JOptionPane.showMessageDialog(view, "Username sudah digunakan, silakan coba username lain.");
                return;
            }

            if (!isPasswordEquals()) {
                JOptionPane.showMessageDialog(view, "Password tidak sesuai, silakan periksa kembali.");
                return;
            }

            if (!isFilled()) {
                JOptionPane.showMessageDialog(view, "Semua field wajib diisi.");
                return;
            }

            akun = new AkunModel(
                id_akun,
                view.getTxtUsername().getText(),
                view.getTxtPassword().getText(),
                view.getCmbRole().getSelectedItem().toString()
            );

            cust = new CustomerModel(
                CustomerDAO.generateIDCustomer(),
                id_akun,
                view.getTxtName().getText(),
                view.getTxtPhoneNumber().getText(),
                view.getTxtAddress().getText()
            );

            try (Statement stm = (Statement) Connect.configDB().createStatement()) {
                AkunDAO.addAkun(akun);
                CustomerDAO.addCustomer(cust);

                JOptionPane.showMessageDialog(view, "User berhasil ditambahkan.");
                view.dispose();
            } catch (SQLException dbError) {
                JOptionPane.showMessageDialog(view, "Terjadi kesalahan pada database: " + dbError.getMessage());
                dbError.printStackTrace();
            }

        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan: Data input tidak boleh kosong.");
            npe.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    
    private boolean isPasswordEquals () {
        if (!String.valueOf(view.getTxtPassword().getPassword()).equals(String.valueOf(view.getTxtConfirmPassword().getPassword()))) {
            JOptionPane.showMessageDialog(view, "Password Do Not Match");
            view.getTxtPassword().setText("");
            view.getTxtConfirmPassword().setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean isFilled () {
        if (!view.getTxtName().getText().isEmpty() && !view.getTxtPhoneNumber().getText().isEmpty() && !view.getTxtUsername().getText().isEmpty() && !view.getTxtAddress().getText().isEmpty() && !String.valueOf(view.getTxtPassword().getPassword()).isEmpty() && !String.valueOf(view.getTxtConfirmPassword().getPassword()).isEmpty()) {
            return true;
        }else{
            JOptionPane.showMessageDialog(view, "Please fill out the form");
            return false;
        }
    }
}
