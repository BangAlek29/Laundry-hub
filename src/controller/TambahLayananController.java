/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.LayananDAO;
import model.LayananModel;
import view.kasir.tambahLayanan;

/**
 *
 * @author dzikr
 */
public class TambahLayananController implements ActionListener {
    private final tambahLayanan view;

    public TambahLayananController() {
        view = new tambahLayanan();
        view.setVisible(true);
        view.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

        try {
            if (view.getTxtNama().getText().isEmpty() || view.getTxtHarga().getText().isEmpty()
                    || view.getTxtDeskripsi().getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Semua field harus diisi.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LayananModel layanan = new LayananModel(
                    LayananDAO.generateIdLayanan(),
                    view.getTxtNama().getText(),
                    Integer.parseInt(view.getTxtHarga().getText()), // Harga layanan (konversi ke Integer)
                    view.getTxtDeskripsi().getText() // Deskripsi layanan
            );

            LayananDAO.addLayanan(layanan);

            JOptionPane.showMessageDialog(view, "Layanan berhasil ditambahkan.");
            view.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Harga harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
