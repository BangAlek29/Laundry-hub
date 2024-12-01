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

    public static ArrayList<LayananModel> searchLayanan(String keyword) {
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

    public static String getDeskripsi(String id_layanan) {
        String deskripsi = "";
        try {
            Statement stmt = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM layanan WHERE id_layanan = '" + id_layanan + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                deskripsi = rs.getString("deskripsi");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deskripsi;
    }

    public static int getHargaById(String IdLayanan) {
        int harga = 0;
        try {
            Statement stmt = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM layanan where id_layanan = '" + IdLayanan + "'";

            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                harga = rs.getInt("harga");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return harga;
    }

    public static String generateIdLayanan() {
        String tempID = "";
        try {
            Statement stm = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM layanan ORDER BY id_layanan ASC";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                tempID = rs.getString("id_layanan");
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        String ID = tempID.replace("LYN", "");
        int IDint = Integer.parseInt(ID);

        return "LYN" + (IDint + 1);
    }

}
