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

    public EditLoginController(AkunModel akun) {
        this.akun = akun;
        view = new editLoginForm();
        view.addActionListener(this);
        view.setVisible(true);
        fillForm();
    }

    private void fillForm() {
        view.getTxtUsername().setText(akun.getUsername());
        view.getTxtPassword().setText(akun.getPassword());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnSave())) {
            handleEditAkun();
        }

        if (source.equals(view.getBtnBack())) {
            view.dispose();
        }
    }

    public void handleEditAkun() {
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

}
