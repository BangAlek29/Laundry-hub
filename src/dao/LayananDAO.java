package dao;

import java.sql.*;
import java.util.ArrayList;
import koneksiDatabase.Connect;
import model.LayananModel;

public class LayananDAO {
    public ArrayList<LayananModel> getAllLayanan() {
        ArrayList<LayananModel> layananList = new ArrayList<>();
        try (Connection conn = Connect.configDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM layanan")) {
            while (rs.next()) {
                layananList.add(new LayananModel(
                    rs.getString("id_layanan"),
                    rs.getString("nama"),
                    rs.getInt("harga"),
                    rs.getString("deskripsi")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return layananList;
    }

    public void updateLayanan(LayananModel layanan) throws SQLException {
        try {
            Connection conn = Connect.configDB();
            PreparedStatement stmt = conn.prepareStatement("UPDATE layanan SET nama = ?, harga = ?, deskripsi = ? WHERE id_layanan = ?");
            stmt.setString(1, layanan.getNama());
            stmt.setInt(2, layanan.getHarga());
            stmt.setString(3, layanan.getDeskripsi());
            stmt.setString(4, layanan.getIdLayanan());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLayanan(LayananModel layanan) throws SQLException {
        try (Connection conn = Connect.configDB();
             PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO layanan (id_layanan, nama, harga, deskripsi) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, layanan.getIdLayanan());
            stmt.setString(2, layanan.getNama());
            stmt.setInt(3, layanan.getHarga());
            stmt.setString(4, layanan.getDeskripsi());
            stmt.executeUpdate();
        }
    }
}
