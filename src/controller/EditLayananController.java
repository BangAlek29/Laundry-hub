package controller;

import dao.LayananDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.LayananModel;
import javax.swing.JOptionPane;
import view.kasir.editLayanan;

public class EditLayananController implements ActionListener{

    private final editLayanan view;
    private LayananModel layanan;
    public EditLayananController(LayananModel layanan) {
        this.layanan = layanan;
        view = new editLayanan();
        view.setVisible(true);
        loadLayananData();
    }
    
    private void loadLayananData() {
        view.getTxtIdLayanan().setText(layanan.getIdLayanan());
        view.getTxtNama().setText(layanan.getNama());
        view.getTxtHarga().setText(String.valueOf(layanan.getHarga()));
        view.getTxtDeskripsi().setText(layanan.getDeskripsi());
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source.equals(view.getBtnEdit())){
            try {
                layanan.setIdLayanan(view.getTxtIdLayanan().getText());
                layanan.setNama(view.getTxtNama().getText());
                layanan.setHarga(Integer.parseInt(view.getTxtHarga().getText()));
                layanan.setDeskripsi(view.getTxtDeskripsi().getText());

                LayananDAO.updateLayanan(layanan);
                JOptionPane.showMessageDialog(view, "Layanan berhasil diperbarui.");
                view.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
            }
        
        }
    }
}
