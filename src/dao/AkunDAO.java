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
import java.util.List;

import koneksiDatabase.Connect;
import model.AkunModel;

/**
 *
 * @author David
 */
public class AkunDAO {

    public AkunModel getAkunByID(String idAkun) throws SQLException {
        Connection conn = Connect.configDB();
        String query = "SELECT * FROM akun WHERE id_akun = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, idAkun);
        ResultSet rs = stmt.executeQuery();

        AkunModel akun = null;

        if (rs.next()) {
            akun = new AkunModel();
            akun.setIdAkun(idAkun);
            akun.setUsername(rs.getString("username"));
            akun.setPassword(rs.getString("password"));
            akun.setRole(rs.getString("role"));
        }

        return akun;
    }

    public boolean deleteAkun(String idAkun) throws SQLException {
        Connection conn = Connect.configDB();
        String query = "DELETE FROM akun WHERE id_akun = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, idAkun);

        boolean isSuccess = stmt.executeUpdate() > 0;

        return isSuccess;
    }

    public List<AkunModel> getAllAkun() throws SQLException {
        Connection conn = Connect.configDB();
        String query = "SELECT * FROM akun";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<AkunModel> listAkun = new ArrayList<>();
        while (rs.next()) {
            AkunModel akun = new AkunModel();
            akun.setIdAkun(rs.getString("id_akun"));
            akun.setUsername(rs.getString("username"));
            akun.setPassword(rs.getString("password"));
            akun.setRole(rs.getString("role"));
            listAkun.add(akun);
        }

        return listAkun;
    }
}
