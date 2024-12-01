/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.AkunDAO;
import model.AkunModel;
import view.admin.editLoginForm;

/**
 *
 * @author dzikr
 */
public class EditLoginController extends MouseAdapter implements ActionListener {
    private final editLoginForm view;
    private AkunModel akun;
    private final AdminController admin;

    public EditLoginController(AkunModel akun,AdminController admin) {
        this.akun = akun;
        this.admin = admin;
        view = new editLoginForm();
        view.setVisible(true);
        fillForm();
        addEvents();
        
    }

    public void addEvents() {
        view.getBtnSave().addActionListener(e -> EditAkun());
        view.getBtnBack().addActionListener(e -> view.dispose());
    }

    
    private void fillForm() {
        view.getTxtUsername().setText(akun.getUsername());
        view.getTxtPassword().setText(akun.getPassword());
        String role = akun.getRole();
        if (role.equals("Admin")){
            view.getJComboBoxRole().setSelectedIndex(0);
        } else if(role.equals("Kasir")){
            view.getJComboBoxRole().setSelectedIndex(1);
        } else if (role.equals("Member")){
            view.getJComboBoxRole().setSelectedIndex(2);
        }
    }

    public void EditAkun() {
        if (view.getTxtUsername().getText().trim().isEmpty() || view.getTxtPassword().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill out the form");
            return;
        }

        String selectedRole = (String) view.getJComboBoxRole().getSelectedItem();
        if (selectedRole == null || selectedRole.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please select a role");
            return;
        }

        int response = JOptionPane.showConfirmDialog(view, "Are you sure?", "Confirm", JOptionPane.YES_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            try {
                // Membuat model Akun baru dengan data yang diedit
                AkunModel akunBaru = new AkunModel(
                        akun.getIdAkun(),
                        view.getTxtUsername().getText().trim(),
                        view.getTxtPassword().getText().trim(),
                        selectedRole);

                // Memperbarui data akun di database
                boolean isUpdated = AkunDAO.updateAkun(akunBaru);

                if (isUpdated) {
                    admin.showAkunTable();
                    JOptionPane.showMessageDialog(view, "The information was successfully updated");
                    view.dispose();
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to update the information. Please try again.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(view, "An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
