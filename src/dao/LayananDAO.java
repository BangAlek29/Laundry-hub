package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import koneksiDatabase.Connect;
import model.LayananModel;

public class LayananDAO {
    public static ArrayList<LayananModel> getAllLayanan() {
        ArrayList<LayananModel> layananList = new ArrayList<>();
        try {
            Connection conn = Connect.configDB();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM layanan");
            while (rs.next()) {
                layananList.add(new LayananModel(
                        rs.getString("id_layanan"),
                        rs.getString("nama"),
                        rs.getInt("harga"),
                        rs.getString("deskripsi")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return layananList;
    }

    public static ArrayList<LayananModel> searchLayananByKey(String keyword) {
        ArrayList<LayananModel> layananList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Connect.configDB();
            String query = "SELECT * FROM layanan WHERE nama LIKE ? OR harga LIKE ? OR deskripsi LIKE ?";
            stmt = conn.prepareStatement(query);

            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                layananList.add(new LayananModel(
                        rs.getString("id_layanan"),
                        rs.getString("nama"),
                        rs.getInt("harga"),
                        rs.getString("deskripsi")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return layananList;
    }

    public static void updateLayanan(LayananModel layanan) throws SQLException {
        try {
            Connection conn = Connect.configDB();
            PreparedStatement stmt = conn
                    .prepareStatement("UPDATE layanan SET nama = ?, harga = ?, deskripsi = ? WHERE id_layanan = ?");
            stmt.setString(1, layanan.getNama());
            stmt.setInt(2, layanan.getHarga());
            stmt.setString(3, layanan.getDeskripsi());
            stmt.setString(4, layanan.getIdLayanan());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addLayanan(LayananModel layanan) throws SQLException {
        Connection conn = Connect.configDB();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO layanan (id_layanan, nama, harga, deskripsi) VALUES (?, ?, ?, ?)");
        stmt.setString(1, layanan.getIdLayanan());
        stmt.setString(2, layanan.getNama());
        stmt.setInt(3, layanan.getHarga());
        stmt.setString(4, layanan.getDeskripsi());
        stmt.executeUpdate();
    }

    public static void deleteLayananById(String idLayanan) throws SQLException {
        try {
            Connection conn = Connect.configDB();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM layanan WHERE id_layanan = ?");
            stmt.setString(1, idLayanan);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String generateIdLayanan() {
        String lastIdLayanan = null;
        String newIdLayanan = null;
        try {
            Connection conn = Connect.configDB();
            String query = "SELECT id_layanan FROM layanan ORDER BY id_layanan DESC LIMIT 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                lastIdLayanan = rs.getString("id_layanan");
            }

            if (lastIdLayanan == null) {
                newIdLayanan = "LYN01"; // ID pertama jika tabel kosong
            } else {
                int number = Integer.parseInt(lastIdLayanan.replace("LYN", ""));
                newIdLayanan = String.format("LYN%02d", number + 1); // Format dua digit
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return newIdLayanan;
    }
    
    public static LayananModel getLayananById(String idLayanan) {
        LayananModel layanan = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Connect.configDB();
            String query = "SELECT * FROM layanan WHERE id_layanan = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, idLayanan);
            rs = stmt.executeQuery();

            if (rs.next()) {
                layanan = new LayananModel(
                    rs.getString("id_layanan"),
                    rs.getString("nama"),
                    rs.getInt("harga"),
                    rs.getString("deskripsi")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return layanan;
    }


}
