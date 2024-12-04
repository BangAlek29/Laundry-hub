package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import dao.LayananDAO;
import static dao.PesananDAO.updatePesanan;
import javax.swing.JComboBox;
import model.LayananModel;
import model.PesananModel;
import util.PesananUtil;
import static util.PesananUtil.convertDate;
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
        renderCbLayanan();
        fillForm();
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
        try{
            LayananModel selectedLayanan = (LayananModel) view.getCmbLayanan().getSelectedItem();
            String idLayanan = selectedLayanan.getIdLayanan();
            String jam = convertTo24HourFormat(view.getTxtJam().getText());
            String formattedDate = null;
            if (view.getTxtTanggal().getText().equals("--/--/----")) {
                formattedDate = null;
            }else{
               formattedDate = convertDate(view.getTxtTanggal().getText());
            }
            if (view.getTxtJam().getText().equals("--:-- --")) {
                jam = null;
            }
            PesananModel newOrder = new PesananModel(
                    pesanan.getIdPesanan(),
                    pesanan.getIdCustomer(),
                    idLayanan,
                    (Integer) view.getSpnBerat().getValue(),
                    hitungHarga((Integer) view.getSpnBerat().getValue()),
                    formattedDate,
                    jam);
            updatePesanan(newOrder);
            kasir.showTabelPesanan();
            JOptionPane.showMessageDialog(view, "Order successfully updated");
            view.dispose();
        }catch (SQLException e) {
            e.printStackTrace();
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
        setSelectedLayanan(pesanan.getIdLayanan());
        view.getTxtTanggal().setText(PesananUtil.convertDateReverse(pesanan.getTanggalSelesai()));
        view.getTxtJam().setText(PesananUtil.convertTo12HourFormat(pesanan.getJamSelesai()));
        view.getLabelHarga().setText("Rp. " + PesananUtil.formatCurrency(pesanan.getHarga()) + " -,");
    }
    
    public void setSelectedLayanan(String idLayanan) {
        JComboBox<LayananModel> cmbLayanan = view.getCmbLayanan();
        for (int i = 0; i < cmbLayanan.getItemCount(); i++) {
            LayananModel layanan = cmbLayanan.getItemAt(i);
            if (layanan.getIdLayanan().equals(idLayanan)) {
                cmbLayanan.setSelectedItem(layanan); // Pilih item yang sesuai
                break;
            }
        }
    }

    private void renderHarga() {
        int berat = Integer.parseInt(view.getSpnBerat().getValue().toString());
        int HargaTotal = hitungHarga(berat);
        String displayHarga = "Rp. " + PesananUtil.formatCurrency(HargaTotal) + " -,";
        view.getLabelHarga().setText(displayHarga);
    }
}
