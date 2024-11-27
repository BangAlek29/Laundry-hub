package User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import dao.PesananDAO;
import koneksiDatabase.Connect;
import model.PesananModel;

public class UserController {
    private final userDashboard view;
    private final PesananDAO pesananDAO;

    public UserController(userDashboard view) {
        this.view = view;
        this.pesananDAO = new PesananDAO();

    }

    public void refreshTable(String idCustomer) {
        try {
            // Memanggil DAO untuk mendapatkan daftar pesanan berdasarkan id_customer
            ArrayList<PesananModel> pesananList = pesananDAO.getPesananByCustomerId(idCustomer);
            DefaultTableModel model = new DefaultTableModel(
                    new String[] { "NO", "ID Pesanan", "Berat", "Harga", "Tanggal Selesai", "Jam Selesai" }, 0);

            int n = 0;
            for (PesananModel pesanan : pesananList) {
                n++;
                // Menambahkan baris data pesanan ke tabel
                model.addRow(new Object[] { n, pesanan.getIdPesanan(), pesanan.getBerat(), pesanan.getHarga(),
                        pesanan.getTanggalSelesai(), pesanan.getJamSelesai() });
            }

            // Mengupdate model tabel di view
            view.jTable2.setModel(model);
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
}
