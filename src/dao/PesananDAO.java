/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import koneksiDatabase.Connect;
/**
 *
 * @author David
 
 */
import model.PesananModel;

public class PesananDAO {
    public static ArrayList<PesananModel> searchPesananByKey(String keyword) {
        ArrayList<PesananModel> pesananList = new ArrayList<>();
        try {
            Connection conn = Connect.configDB();

            String query = "SELECT pesanan.id_pesanan, pesanan.id_customer, pesanan.id_layanan, pesanan.berat, " +
                           "pesanan.harga, pesanan.tanggalSelesai, pesanan.jamSelesai, " +
                           "customer.nama, layanan.nama " +
                           "FROM pesanan " +
                           "JOIN customer ON pesanan.id_customer = customer.id_customer " +
                           "JOIN layanan ON pesanan.id_layanan = layanan.id_layanan " +
                           "WHERE pesanan.id_pesanan LIKE ? " +
                           "OR customer.nama LIKE ? " +
                           "OR layanan.nama LIKE ? " +
                           "OR pesanan.berat LIKE ? " +
                           "OR pesanan.harga LIKE ? " +
                           "OR pesanan.tanggalSelesai LIKE ? " +
                           "OR pesanan.jamSelesai LIKE ?";

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

    public static ArrayList<PesananModel> searchPesananCustomer(String keyword, String idCustomer) {
        ArrayList<PesananModel> pesananList = new ArrayList<>();
        try {
            Connection conn = Connect.configDB();
            String query = "SELECT pesanan.id_pesanan, pesanan.id_customer, pesanan.id_layanan, pesanan.berat, " +
                           "pesanan.harga, pesanan.tanggalSelesai, pesanan.jamSelesai, " +
                           "customer.nama AS nama_customer, layanan.nama AS nama_layanan " +
                           "FROM pesanan " +
                           "JOIN customer ON pesanan.id_customer = customer.id_customer " +
                           "JOIN layanan ON pesanan.id_layanan = layanan.id_layanan " +
                           "WHERE pesanan.id_customer = ? " +
                           "AND (pesanan.id_pesanan LIKE ? " +
                           "OR customer.nama LIKE ? " +
                           "OR layanan.nama LIKE ? " +
                           "OR pesanan.berat LIKE ? " +
                           "OR pesanan.harga LIKE ? " +
                           "OR pesanan.tanggalSelesai LIKE ? " +
                           "OR pesanan.jamSelesai LIKE ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, idCustomer);
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            stmt.setString(4, "%" + keyword + "%");
            stmt.setString(5, "%" + keyword + "%");
            stmt.setString(6, "%" + keyword + "%");
            stmt.setString(7, "%" + keyword + "%");
            stmt.setString(8, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PesananModel pesanan = new PesananModel();
                pesanan.setIdPesanan(rs.getString("id_pesanan"));
                pesanan.setIdCustomer(rs.getString("id_customer"));
                pesanan.setIdLayanan(rs.getString("id_layanan"));
                pesanan.setBerat(rs.getInt("berat"));
                pesanan.setHarga(rs.getInt("harga"));

                String tanggalSelesai = rs.getString("tanggalSelesai");
                pesanan.setTanggalSelesai(tanggalSelesai != null ? tanggalSelesai : "");

                String jamSelesai = rs.getString("jamSelesai");
                pesanan.setJamSelesai(jamSelesai != null ? jamSelesai : "");

                pesananList.add(pesanan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pesananList;
    }

    public static ArrayList<PesananModel> getPesananByCustomerId(String idCustomer){
        try {
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

                // Cek apakah kolom tanggalSelesai tidak null
                String tanggalSelesai = rs.getString("tanggalSelesai");
                if (tanggalSelesai != null) {
                    pesanan.setTanggalSelesai(tanggalSelesai);
                }

                // Cek apakah kolom jamSelesai tidak null
                String jamSelesai = rs.getString("jamSelesai");
                if (jamSelesai != null) {
                    pesanan.setJamSelesai(jamSelesai);
                }

                pesananList.add(pesanan);
            }
            
            return pesananList;
        } catch (SQLException ex) {
            Logger.getLogger(PesananDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static PesananModel getPesananById(String idPesanan) {
        try {
            Connection conn = Connect.configDB();
            String query = "SELECT * FROM pesanan WHERE id_pesanan = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, idPesanan);
            ResultSet rs = stmt.executeQuery();
            PesananModel pesanan = new PesananModel();
            while (rs.next()) {
                pesanan.setIdPesanan(rs.getString("id_pesanan"));
                pesanan.setIdCustomer(rs.getString("id_customer"));
                pesanan.setIdLayanan(rs.getString("id_layanan"));
                pesanan.setBerat(rs.getInt("berat"));
                pesanan.setHarga(rs.getInt("harga"));
                pesanan.setTanggalSelesai(rs.getString("tanggalSelesai"));
                pesanan.setJamSelesai(rs.getString("jamSelesai"));
            }
            
            return pesanan;
        } catch (SQLException ex) {
            Logger.getLogger(PesananDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
    
    public static void insertPesananKasir(PesananModel pesanan) throws SQLException {
        Connection conn = Connect.configDB();
        String query = "INSERT INTO pesanan (id_pesanan, id_customer, id_layanan, berat, harga,tanggalSelesai,jamSelesai) VALUES (?, ?, ?, ?, ?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, pesanan.getIdPesanan());
        stmt.setString(2, pesanan.getIdCustomer());
        stmt.setString(3, pesanan.getIdLayanan());
        stmt.setInt(4, pesanan.getBerat());
        stmt.setInt(5, pesanan.getHarga());
        stmt.setString(6, pesanan.getTanggalSelesai());
        stmt.setString(7, pesanan.getJamSelesai());
        stmt.executeUpdate();
    }

    public static ArrayList<PesananModel> getAllPesanan(){
        try {
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
        } catch (SQLException ex) {
            Logger.getLogger(PesananDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

        public static String generateIdPesanan() {
        String tempID = "";
        try {
            Statement stm = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM pesanan ORDER BY id_pesanan DESC LIMIT 1";
            ResultSet rs = stm.executeQuery(query);

            if (rs.next()) {
                tempID = rs.getString("id_pesanan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (tempID.isEmpty()) {
            return "LDR001";
        }
        String ID = tempID.replace("LDR", "");
        int IDint = Integer.parseInt(ID);
        int newIDInt = IDint + 1;
        String newId = String.format("LDR%03d", newIDInt);
        return newId;
    }

    public static boolean deletePesananById(String idPesanan) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = Connect.configDB();
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
    
    public static boolean deletePesananByCustomerId(String idCustomer) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = Connect.configDB();
            String query = "DELETE FROM pesanan WHERE id_customer = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, idCustomer);

            int affectedRows = stmt.executeUpdate();
            // Jika ada baris yang terhapus, set success ke true
            if (affectedRows > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        
        }
        return success;
    }

}