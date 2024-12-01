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
    private final KasirController kasir;

    public TambahLayananController(KasirController kasir) {
        view = new tambahLayanan();
        this.kasir = kasir;
        addEvent();
        view.setVisible(true);
    }

    private void addEvent(){
        view.getBtnAdd().addActionListener(e ->{addLayanan();view.dispose();});
        view.getBtnAdds().addActionListener(e ->{addLayanan();clear();});
    }
    
    private void addLayanan(){
        System.out.println("controller.TambahLayananController.addLayanan()");
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
            kasir.showTabelLayanan();
            JOptionPane.showMessageDialog(view, "Layanan berhasil ditambahkan.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Harga harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clear(){
        view.getTxtNama().setText("");
        view.getTxtHarga().setText("");
        view.getTxtDeskripsi().setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
