package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import dao.LayananDAO;
import dao.PesananDAO;
import model.LayananModel;
import model.PesananModel;
import util.PesananUtil;
import util.SpinerTimeModel;
import view.kasir.updateOrder;

public class UpdateOrderController extends MouseAdapter implements ActionListener {
    private final updateOrder view;
    private final PesananModel pesanan;

    public UpdateOrderController(PesananModel pesanan) {
        this.pesanan = pesanan;
        view = new updateOrder();
        fillForm();
        renderCbLayanan();
        renderSpinerJam();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        Events();
    }

    public void Events() {
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (((Integer) view.getSpnBerat().getValue()) == 0 && view.getCalTanggal().getDate() == null) {
            JOptionPane.showMessageDialog(view, "Please fill out the form");
            return;
        }
        try {
            LayananModel selectedLayanan = (LayananModel) view.getCmbLayanan().getSelectedItem();
            String idLayanan = selectedLayanan.getIdLayanan();
            String formattedDate = dateFormat.format(view.getCalTanggal().getDate());
            PesananModel newOrder = new PesananModel(
                    pesanan.getIdPesanan(),
                    pesanan.getIdPesanan(),
                    idLayanan,
                    (Integer) view.getSpnBerat().getValue(),
                    hitungHarga((Integer) view.getSpnBerat().getValue()),
                    formattedDate,
                    PesananUtil.convertTo24HourFormat((String) view.getSpnJam().getValue()));
            PesananDAO.updatePesanan(newOrder);
            JOptionPane.showMessageDialog(view, "Order successfully updated");
            view.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int hitungHarga(int berat) {
        LayananModel selectedLayanan = (LayananModel) view.getCmbLayanan().getSelectedItem();
        String idLayanan = selectedLayanan.getIdLayanan();
        int harga = LayananDAO.getHargaById(idLayanan);
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

    private void renderSpinerJam() {
        view.getSpnJam().setModel(new SpinerTimeModel());

        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) view.getSpnJam().getEditor();
        editor.getTextField().setHorizontalAlignment(JTextField.CENTER);
    }

    private void renderHarga() {
        int berat = Integer.parseInt(view.getSpnBerat().getValue().toString());
        int HargaTotal = hitungHarga(berat);
        String displayHarga = "Rp. " + PesananUtil.formatCurrency(HargaTotal) + " -,";
        view.getLabelHarga().setText(displayHarga);
    }
}
