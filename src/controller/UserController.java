package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.Component;

import dao.CustomerDAO;
import dao.LayananDAO;
import dao.PesananDAO;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import model.CustomerModel;
import model.LayananModel;
import model.PesananModel;
import util.PanelUtils;
import util.PesananUtil;
import view.user.userDashboard;



public class UserController extends MouseAdapter implements ActionListener {
    private final userDashboard view;
    private CustomerModel cust;

    public UserController(CustomerModel cust) {
        this.cust = cust;
        this.view = new userDashboard();
        view.setVisible(true);
        addEvents();
        renderCbLayanan();
        refreshTable();
    }

    public void addEvents() {
        view.getBtnRequestOrder()
                .addActionListener(e -> PanelUtils.switchPanel(view.getMainPanel(), view.getOrderPanel()));
        view.getBtnOrderList()
                .addActionListener(e -> PanelUtils.switchPanel(view.getMainPanel(), view.getRiwayatPemesananPanel()));
        view.getRbCustomerBaru()
                .addActionListener(e -> PanelUtils.switchPanel(view.getPnlCustomer(), view.getCustomerBaruPanel()));
        view.getRbCustomerLama().addActionListener(e -> {
            PanelUtils.switchPanel(view.getPnlCustomer(), view.getCustomerLamaPanel());
            fillForm();
        });
        view.getOrderButton().addActionListener(e -> requestOrder());
        view.getBtnLogout().addActionListener(e -> Logout());
        view.getSearchPesananButton().addActionListener(e -> searchPesanan());
        view.getCmbLayanan().addActionListener(e -> renderHarga());
        view.getSpnBerat().addChangeListener(e -> renderHarga());
        view.getBtnRefresh().addChangeListener(e -> refreshTable());
    }

    public void fillForm() {
        view.getTxtNama1().setText(cust.getName());
        view.getTxtAlamat2().setText(cust.getAddress());
        view.getTxtTelepon2().setText(cust.getPhone());
    }

    private void searchPesanan() {
        int n = 0;
        String[] kolom = { "NO", "id_pesanan", "berat", "Harga", "Tanggal Ambil", "Jam Ambil" };
        DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
        String search = view.getTxtSearchPesanan().getText();

        ArrayList<PesananModel> pesananList = PesananDAO.searchPesananCustomer(search, cust.getIdCustomer());

        for (PesananModel pesanan : pesananList) {
            n++;
            String id_pesanan = pesanan.getIdPesanan();
            int berat = pesanan.getBerat();
            int harga = pesanan.getHarga();
            String tanggalAmbil = pesanan.getTanggalSelesai();
            String jamAmbil = pesanan.getJamSelesai();
            tb1.addRow(new String[] { String.valueOf(n), id_pesanan, String.valueOf(berat), String.valueOf(harga),
                    tanggalAmbil, jamAmbil });
        }
        view.getTabelPesanan().setModel(tb1);
    }

    private int hitungHarga(int berat) {
        LayananModel selectedLayanan = (LayananModel) view.getCmbLayanan().getSelectedItem();
        String idLayanan = selectedLayanan.getIdLayanan();
        int harga = LayananDAO.getLayananById(idLayanan).getHarga();
        return berat * harga;
    }

    private void requestOrder() {
        try {
            LayananModel selectedLayanan = (LayananModel) view.getCmbLayanan().getSelectedItem();
            String idLayanan = selectedLayanan.getIdLayanan();
            String idPesanan = PesananDAO.generateIdPesanan();

            if (view.getRbCustomerBaru().isSelected()) {
                String idCust = CustomerDAO.generateIDCustomer();

                CustomerModel newCustomer = new CustomerModel(
                        idCust,
                        null,
                        view.getTxtNama().getText().trim(),
                        view.getTxtTelepon().getText().trim(),
                        view.getTxtAlamat().getText().trim());

                CustomerDAO.addCustomer(newCustomer);

                PesananModel newOrder = new PesananModel(
                        idPesanan,
                        idCust,
                        idLayanan,
                        (Integer) view.getSpnBerat().getValue(),
                        hitungHarga((Integer) view.getSpnBerat().getValue()));

                PesananDAO.insertPesanan(newOrder);

            } else {
                String idCust = cust.getIdCustomer();

                PesananModel newOrder = new PesananModel(
                        idPesanan,
                        idCust,
                        idLayanan,
                        (Integer) view.getSpnBerat().getValue(),
                        hitungHarga((Integer) view.getSpnBerat().getValue()));

                PesananDAO.insertPesanan(newOrder);
            }

            JOptionPane.showMessageDialog(view, "Order successfully placed");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        refreshTable();
    }

    private void Logout() {
        view.dispose();
        LoginController login = new LoginController();
    }

    public void refreshTable() {
        try {
            ArrayList<PesananModel> pesananList = PesananDAO.getPesananByCustomerId(cust.getIdCustomer());
            DefaultTableModel model = new DefaultTableModel(
                    new String[] { "NO", "ID Pesanan", "Berat", "Harga", "Tanggal Selesai", "Jam Selesai" }, 0);
            int n = 0;
            for (PesananModel pesanan : pesananList) {
                n++;
                model.addRow(new Object[] { n, pesanan.getIdPesanan(), pesanan.getBerat(), pesanan.getHarga(),
                        pesanan.getTanggalSelesai(), pesanan.getJamSelesai() });
            }

            view.tabelPesanan.setModel(model);
            setColumnAlignment(view.tabelPesanan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setColumnAlignment(JTable table) {
        // Mendapatkan model kolom tabel
        TableColumnModel columnModel = table.getColumnModel();

        // Set alignment untuk semua kolom menjadi rata tengah
        for (int i = 0; i < table.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setHorizontalAlignment(SwingConstants.CENTER);  // Set semua kolom rata tengah
                    return comp;
                }
            });
        }
    }
    private void renderCbLayanan() {
        List<LayananModel> listLayanan = LayananDAO.getAllLayanan();
        DefaultComboBoxModel<LayananModel> model = new DefaultComboBoxModel<>();

        for (LayananModel layanan : listLayanan) {
            model.addElement(layanan);
        }

        view.getCmbLayanan().setModel(model);
    }

    private void renderHarga() {
        int berat = Integer.parseInt(view.getSpnBerat().getValue().toString());
        int HargaTotal = hitungHarga(berat);
        String displayHarga = "Rp. " + PesananUtil.formatCurrency(HargaTotal) + " -,";
        view.getLabelHarga().setText(displayHarga);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}