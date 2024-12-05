/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import dao.AkunDAO;
import dao.CustomerDAO;
import java.awt.event.KeyAdapter;
import koneksiDatabase.Connect;
import model.AkunModel;
import model.CustomerModel;
import view.admin.addUserForm;

/**
 *
 * @author dzikr
 */
public class AddUserController extends MouseAdapter implements ActionListener {
    private addUserForm view;
    private AkunModel akun;
    private CustomerModel cust;
    private final AdminController admin;

    public AddUserController(AdminController admin) {
        view = new addUserForm();
        this.admin = admin;
        view.setVisible(true);
        addEvents();
    }

    public void addEvents() {
        view.getBtnAdd().addActionListener(e -> addUser());
        view.getBtnBack().addActionListener(e -> view.dispose());
    }

    public void addUser() {
        try {
            String id_akun = AkunDAO.generateIDAkun();

            if (AkunDAO.isUsernameExist(view.getTxtUsername().getText())) {
                JOptionPane.showMessageDialog(view, "Username sudah digunakan, silakan coba username lain.");
                return;
            }

            if (!isFilled()) {
                JOptionPane.showMessageDialog(view, "Semua field wajib diisi.");
                return;
            }

            akun = new AkunModel(
                    id_akun,
                    view.getTxtEmail().getText(),
                    view.getTxtUsername().getText(),
                    view.getTxtPassword().getText(),
                    view.getCmbRole().getSelectedItem().toString());

            cust = new CustomerModel(
                    CustomerDAO.generateIDCustomer(),
                    id_akun,
                    view.getTxtName().getText(),
                    view.getTxtPhoneNumber().getText(),
                    view.getTxtAddress().getText());

            try (Statement stm = (Statement) Connect.configDB().createStatement()) {
                AkunDAO.addAkun(akun);
                CustomerDAO.addCustomer(cust);
                admin.showAkunTable();
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

    private boolean isFilled() {
        if (!view.getTxtName().getText().isEmpty() && !view.getTxtPhoneNumber().getText().isEmpty()
                && !view.getTxtUsername().getText().isEmpty() && !view.getTxtAddress().getText().isEmpty()
                && !String.valueOf(view.getTxtPassword().getPassword()).isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(view, "Harap isi semua Field. ");
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
