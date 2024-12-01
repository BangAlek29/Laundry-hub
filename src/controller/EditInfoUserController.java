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

import dao.CustomerDAO;
import model.CustomerModel;
import view.admin.editInfoUserForm;

/**
 *
 * @author dzikr
 */
public class EditInfoUserController extends MouseAdapter implements ActionListener {
    private final editInfoUserForm view;
    private CustomerModel cust;
    private final AdminController admin;

    public EditInfoUserController(CustomerModel cust,AdminController admin) {
        this.cust = cust;
        this.admin = admin;
        this.view = new editInfoUserForm();
        view.setVisible(true);
        fillForm();
        addEvents();
    }

    public void addEvents() {
        view.getBtnSave().addActionListener(e -> EditInfoUser());
        view.getBtnBack().addActionListener(e -> view.dispose());
    }


    public void EditInfoUser() {
        if (view.getTxtName().getText().trim().isEmpty()
                || view.getTxtPhone().getText().trim().isEmpty()
                || view.getTxtAddres().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Harap isi semua fieldnya.");
            return;
        }

        int response = JOptionPane.showConfirmDialog(view, "Are you sure?", "Confirm", JOptionPane.YES_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            try {
                CustomerModel customerBaru = new CustomerModel(
                        cust.getIdCustomer(),
                        cust.getIdAkun(),
                        view.getTxtName().getText().trim(),
                        view.getTxtPhone().getText().trim(),
                        view.getTxtAddres().getText().trim());

                boolean isUpdated = CustomerDAO.updateCustomer(customerBaru);

                if (isUpdated) {
                    admin.showCustomerTable();
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

    private void fillForm() {
        view.getTxtID().setText(cust.getIdAkun());
        view.getTxtName().setText(cust.getName());
        view.getTxtPhone().setText(cust.getPhone());
        view.getTxtAddres().setText(cust.getAddress());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
