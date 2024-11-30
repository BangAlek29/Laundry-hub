/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author David
 
 */
import model.PesananModel;
import java.sql.*;
import java.util.ArrayList;
import koneksiDatabase.Connect;

public class PesananDAO {
    public static ArrayList<PesananModel> searchPesanan(String keyword) {
        ArrayList<PesananModel> pesananList = new ArrayList<>();
        try {
            Connection conn = Connect.configDB();
            String query = "SELECT * FROM pesanan WHERE id_pesanan LIKE ? OR id_customer LIKE ? OR id_layanan LIKE ? OR berat LIKE ? OR harga LIKE ? OR tanggalSelesai LIKE ? OR jamSelesai LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            stmt.setString(4, "%" + keyword + "%");
            stmt.setString(5, "%" + keyword + "%");
            stmt.setString(6, "%" + keyword + "%");
            stmt.setString(7, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PesananModel pesanan = new PesananModel();
                pesanan.setIdPesanan(rs.getString("id_pesanan"));
                pesanan.setIdCustomer(rs.getString("id_customer"));
                pesanan.setIdLayanan(rs.getString("id_layanan"));
                pesanan.setBerat(rs.getInt("berat"));
                pesanan.setHarga(rs.getInt("harga"));
                pesanan.setTanggalSelesai(rs.getString("tanggalSelesai"));
                pesanan.setJamSelesai(rs.getString("jamSelesai"));
                pesananList.add(pesanan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pesananList;
    }
    
    public static ArrayList<PesananModel> getPesananByCustomerId(String idCustomer) throws SQLException {
        ArrayList<PesananModel> pesananList = new ArrayList<>();
        Connection conn = Connect.configDB();
        String query = "SELECT * FROM pesanan WHERE id_customer = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, idCustomer);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            PesananModel pesanan = new PesananModel();
            pesanan.setIdPesanan(rs.getString("id_pesanan"));
            pesanan.setIdCustomer(rs.getString("id_customer"));
            pesanan.setIdLayanan(rs.getString("id_layanan"));
            pesanan.setBerat(rs.getInt("berat"));
            pesanan.setHarga(rs.getInt("harga"));
            pesanan.setTanggalSelesai("tanggalSelesai");
            pesanan.setJamSelesai("jamSelesai");
            pesananList.add(pesanan);
        }

        return pesananList;
    }

    public static void insertPesanan(PesananModel pesanan) throws SQLException {
        Connection conn = Connect.configDB();
        String query = "INSERT INTO pesanan (id_pesanan, id_customer, id_layanan, berat, harga) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, pesanan.getIdPesanan());
        stmt.setString(2, pesanan.getIdCustomer());
        stmt.setString(3, pesanan.getIdLayanan());
        stmt.setInt(4, pesanan.getBerat());
        stmt.setInt(5, pesanan.getHarga());
        stmt.executeUpdate();
    }
    
    public static ArrayList<PesananModel> getAllPesanan() throws SQLException {
        ArrayList<PesananModel> pesananList = new ArrayList<>();
        Connection conn = Connect.configDB();
        String query = "SELECT * FROM pesanan";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            PesananModel pesanan = new PesananModel();
            pesanan.setIdPesanan(rs.getString("id_pesanan"));
            pesanan.setIdCustomer(rs.getString("id_customer"));
            pesanan.setIdLayanan(rs.getString("id_layanan"));
            pesanan.setBerat(rs.getInt("berat"));
            pesanan.setHarga(rs.getInt("harga"));
            pesanan.setTanggalSelesai(rs.getString("tanggalSelesai")); // Sesuaikan dengan nama kolom yang benar
            pesanan.setJamSelesai(rs.getString("jamSelesai")); // Sesuaikan dengan nama kolom yang benar
            pesananList.add(pesanan);
        }

        return pesananList;
    }

    
    public static String generateIdPesanan(){
        String tempID = "";
        try {
            Statement stm = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM pesanan ORDER BY id_pesanan DESC LIMIT 1";
            ResultSet rs = stm.executeQuery(query);

            if(rs.next()){
                tempID = rs.getString("id_pesanan");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (tempID.isEmpty()) {
            return "LDR1";
        }

        String ID = tempID.replace("LDR", "");
        int IDint = Integer.parseInt(ID);

        return "LDR" + (IDint + 1);
    }
    
    public static boolean deletePesananById(String idPesanan) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = Connect.configDB(); // Pastikan koneksi berhasil
            stmt = conn.prepareStatement("DELETE FROM pesanan WHERE id_pesanan = ?");
            stmt.setString(1, idPesanan);

            stmt.executeUpdate();
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
    
    
    public static boolean updatePesanan(PesananModel pesanan) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = Connect.configDB();

            String query = "UPDATE pesanan SET id_layanan = ?, berat = ?, harga = ?, tanggalSelesai = ?, jamSelesai = ? WHERE id_pesanan = ?";

            stmt = conn.prepareStatement(query);

            stmt.setString(1, pesanan.getIdLayanan());
            stmt.setInt(2, pesanan.getBerat());
            stmt.setInt(3, pesanan.getHarga());
            stmt.setString(4, pesanan.getTanggalSelesai());
            stmt.setString(5, pesanan.getJamSelesai());
            stmt.setString(6, pesanan.getIdPesanan());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

}