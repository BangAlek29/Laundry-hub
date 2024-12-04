package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.LayananDAO;
import model.LayananModel;
import view.kasir.editLayanan;

public class EditLayananController implements ActionListener {

    private final editLayanan view;
    private LayananModel layanan;
    private final KasirController kasir;

    public EditLayananController(LayananModel layanan, KasirController kasir) {
        this.layanan = layanan;
        this.kasir = kasir;
        view = new editLayanan();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        loadLayananData();
        addEvents();
    }

    public void addEvents() {
        view.getBtnEdit().addActionListener(e -> {
            try {
                layanan.setIdLayanan(view.getTxtIdLayanan().getText());
                layanan.setNama(view.getTxtNama().getText());
                layanan.setHarga(Integer.parseInt(view.getTxtHarga().getText()));
                layanan.setDeskripsi(view.getTxtDeskripsi().getText());

                LayananDAO.updateLayanan(layanan);
                kasir.showTabelLayanan();
                JOptionPane.showMessageDialog(view, "Layanan berhasil diperbarui.");
                view.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
            }
        });
    }

    
    private void loadLayananData() {
        view.getTxtIdLayanan().setText(layanan.getIdLayanan());
        view.getTxtNama().setText(layanan.getNama());
        view.getTxtHarga().setText(String.valueOf(layanan.getHarga()));
        view.getTxtDeskripsi().setText(layanan.getDeskripsi());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
