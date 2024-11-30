package controller;

import dao.CustomerDAO;
import dao.LayananDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import dao.PesananDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import koneksiDatabase.Connect;
import model.PesananModel;
import view.user.userDashboard;
import model.CustomerModel;
import model.LayananModel;
import util.PanelUtils;
import util.PesananUtil;

public class UserController extends MouseAdapter implements ActionListener{
    private final userDashboard view;
    private CustomerModel cust;
    public UserController() {
        this.view = new userDashboard();
        view.setVisible(true);
        addEvents();
        refreshTable(cust.getIdCustomer());
    }
    public void addEvents() {
        // Tombol untuk memproses pemesanan
        view.getBtnRequestOrder().addActionListener(e -> handleRequestOrder());

        // Tombol untuk logout
        view.getBtnLogout().addActionListener(e -> handleLogout());

        // Tombol untuk melihat daftar pesanan
        view.getBtnOrderList().addActionListener(e -> showTable());
        // Tombol untuk membuka panel pelanggan baru
        view.getRbCustomerBaru().addActionListener(e -> switchPanel(view.getPnlCustomer(), view.getCustomerBaruPanel()));

        // Tombol untuk membuka panel pelanggan lama
        view.getRbCustomerLama().addActionListener(e -> switchPanel(view.getPnlCustomer(), view.getCustomerLamaPanel()));

        // Tombol untuk membuka panel order baru
        view.getOrderButton().addActionListener(e -> openOrderPanel());

        // Tombol untuk membuka panel riwayat pemesanan
        view.getBtnOrderList().addActionListener(e -> showRiwayatPemesananPanel());

        // Tombol untuk logout dari aplikasi
        view.getBtnLogout().addActionListener(e -> handleLogout());

        // Tombol untuk mencari berdasarkan layanan
        view.getSearchPesananButton().addActionListener(e -> handleSearchPesanan());

    }
    
    
    private void handleSearchPesanan() {
        int n = 0;
        String [] kolom = {"NO", "id_pesanan" ,"id_customer", "berat" ,"Harga", "Tanggal Ambil", "Jam Ambil"};
        DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
        String search = view.getTxtSearchPesanan().getText();
        
        ArrayList<PesananModel> pesananList = PesananDAO.searchPesanan(search);
        
        for (PesananModel pesanan : pesananList) {
            n++;
            String id_pesanan = pesanan.getIdPesanan();
            String id_customer = pesanan.getIdCustomer();
            int berat = pesanan.getBerat();
            int harga = pesanan.getHarga();
            String tanggalAmbil = pesanan.getTanggalSelesai();
            String jamAmbil = pesanan.getJamSelesai();
            tb1.addRow(new String[] {String.valueOf(n), id_pesanan, id_customer , String.valueOf(berat) , String.valueOf(harga), tanggalAmbil, jamAmbil});
        }
        view.getTabelPesanan().setModel(tb1);
    }
    private void switchPanel(JPanel mainPanel, JPanel newPanel) {
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        mainPanel.add(newPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
    private int hitungHarga(int berat){
        LayananModel selectedLayanan = (LayananModel) view.getCmbLayanan().getSelectedItem();
        String idLayanan = selectedLayanan.getIdLayanan();
        int harga = LayananDAO.getHargaById(idLayanan);
        return berat * harga;
    }
    private void handleRequestOrder() {
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
                    view.getTxtAlamat().getText().trim()
                );

                CustomerDAO.addCustomer(newCustomer);

                PesananModel newOrder = new PesananModel(
                    idPesanan,
                    idCust,
                    idLayanan,
                    (Integer) view.getSpnBerat().getValue(),
                    hitungHarga((Integer) view.getSpnBerat().getValue())
                );

                PesananDAO.insertPesanan(newOrder);

            } else {
                String idCust = cust.getIdCustomer();

                PesananModel newOrder = new PesananModel(
                    idPesanan,
                    idCust,
                    idLayanan,
                    (Integer) view.getSpnBerat().getValue(),
                    hitungHarga((Integer) view.getSpnBerat().getValue())
                );

                PesananDAO.insertPesanan(newOrder);
            }

            JOptionPane.showMessageDialog(view, "Order successfully placed");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        refreshTable(cust.getIdCustomer());
    }
    
    private void openOrderPanel(){
        PanelUtils.switchPanel(view.getMainPanel(), view.getOrderPanel());
    }
    private void showRiwayatPemesananPanel(){
        PanelUtils.switchPanel(view.getMainPanel(), view.getRiwayatPemesananPanel());
    }
    private void handleLogout() {
        view.dispose();
        LoginController login = new LoginController();
    }
    private void showTable(){
        refreshTable(cust.getIdCustomer());
    }
    public void refreshTable(String idCustomer) {
        try {
            ArrayList<PesananModel> pesananList = PesananDAO.getPesananByCustomerId(idCustomer);
            DefaultTableModel model = new DefaultTableModel(
            new String[] { "NO", "ID Pesanan", "Berat", "Harga", "Tanggal Selesai", "Jam Selesai" }, 0);
            int n = 0;
            for (PesananModel pesanan : pesananList) {
                n++;
                
                model.addRow(new Object[] { n, pesanan.getIdPesanan(), pesanan.getBerat(), pesanan.getHarga(),
                        pesanan.getTanggalSelesai(), pesanan.getJamSelesai() });
            }

            view.tabelPesanan.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateCustomerId() {
        String tempID = "";
        try {
            Statement stm = Connect.configDB().createStatement();
            String query = "SELECT * FROM customer ORDER BY id_customer DESC LIMIT 1";
            ResultSet rs = stm.executeQuery(query);

            if (rs.next()) {
                tempID = rs.getString("id_customer");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (tempID.isEmpty()) {
            return "CST1";
        }

        String id = tempID.replace("CST", "");
        int newId = Integer.parseInt(id) + 1;
        return "CST" + newId;
    }

    public static String generateOrderId() {
        String tempID = "";
        try {
            Statement stm = Connect.configDB().createStatement();
            String query = "SELECT * FROM pesanan ORDER BY id_pesanan DESC LIMIT 1";
            ResultSet rs = stm.executeQuery(query);

            if (rs.next()) {
                tempID = rs.getString("id_pesanan");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (tempID.isEmpty()) {
            return "ORD1";
        }

        String id = tempID.replace("ORD", "");
        int newId = Integer.parseInt(id) + 1;
        return "ORD" + newId;
    }

    public static int calculatePrice(int weight, String serviceId) {
        int pricePerKg = 0;
        try {
            Statement stmt = Connect.configDB().createStatement();
            String query = "SELECT harga FROM layanan WHERE id_layanan = '" + serviceId + "';";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                pricePerKg = rs.getInt("harga");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weight * pricePerKg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
