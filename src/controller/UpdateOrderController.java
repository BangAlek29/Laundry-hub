package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import dao.LayananDAO;
import static dao.PesananDAO.updatePesanan;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LayananModel;
import model.PesananModel;
import util.PesananUtil;
import static util.PesananUtil.convertTo24HourFormat;
import view.kasir.updateOrder;

public class UpdateOrderController extends MouseAdapter implements ActionListener {
    private final updateOrder view;
    private final PesananModel pesanan;
    private final KasirController kasir;

    public UpdateOrderController(PesananModel pesanan,KasirController kasir) {
        this.kasir = kasir;
        this.pesanan = pesanan;
        view = new updateOrder();
        fillForm();
        renderCbLayanan();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        addEvents();
    }

    public void addEvents() {
        view.getOrderButton().addActionListener(e -> orderButtonActionPerformed());
        view.getCmbLayanan().addActionListener(e -> renderHarga());
        view.getSpnBerat().addChangeListener(e -> renderHarga());
    }

    private void renderCbLayanan() {
        List<LayananModel> listLayanan = LayananDAO.getAllLayanan();
        DefaultComboBoxModel<LayananModel> model = new DefaultComboBoxModel<>();

        for (LayananModel layanan : listLayanan) {
            model.addElement(layanan);
        }

        view.getCmbLayanan().setModel(model);
    }

    private void orderButtonActionPerformed() {
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (((Integer) view.getSpnBerat().getValue()) == 0 && view.getTxtTanggal() == null) {
                JOptionPane.showMessageDialog(view, "Please fill out the form");
                return;
            }
            Date date = originalFormat.parse(view.getTxtTanggal().getText());
            try {
                LayananModel selectedLayanan = (LayananModel) view.getCmbLayanan().getSelectedItem();
                String idLayanan = selectedLayanan.getIdLayanan();
                String formattedDate = newFormat.format(date);
                PesananModel newOrder = new PesananModel(
                        pesanan.getIdPesanan(),
                        pesanan.getIdPesanan(),
                        idLayanan,
                        (Integer) view.getSpnBerat().getValue(),
                        hitungHarga((Integer) view.getSpnBerat().getValue()),
                        formattedDate,
                        convertTo24HourFormat(view.getTxtJam().getText()));
                updatePesanan(newOrder);
                kasir.showTabelPesanan();
                JOptionPane.showMessageDialog(view, "Order successfully updated");
                view.dispose();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ParseException ex) {
            Logger.getLogger(UpdateOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int hitungHarga(int berat) {
        LayananModel selectedLayanan = (LayananModel) view.getCmbLayanan().getSelectedItem();
        String idLayanan = selectedLayanan.getIdLayanan();
        int harga = LayananDAO.getLayananById(idLayanan).getHarga();
        return berat * harga;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void fillForm() {
        view.getIdPesananField().setText(pesanan.getIdPesanan());
        view.getSpnBerat().setValue(pesanan.getBerat());
        view.getLabelHarga().setText("Rp. " + PesananUtil.formatCurrency(pesanan.getHarga()) + " -,");
    }

    private void renderHarga() {
        int berat = Integer.parseInt(view.getSpnBerat().getValue().toString());
        int HargaTotal = hitungHarga(berat);
        String displayHarga = "Rp. " + PesananUtil.formatCurrency(HargaTotal) + " -,";
        view.getLabelHarga().setText(displayHarga);
    }
}
