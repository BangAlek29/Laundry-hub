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
    public ArrayList<PesananModel> getPesananByCustomerId(String idCustomer) throws SQLException {
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

    public void insertPesanan(PesananModel pesanan) throws SQLException {
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
}
