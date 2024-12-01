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

    public static AkunModel validateLogin(String username, String password) throws SQLException {
        try {
            Statement stmt = Connect.configDB().createStatement();
            String query = "SELECT * FROM akun WHERE username = '" + username + "';";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (storedPassword.equals(password)) {
                    String role = rs.getString("role");
                    String idAkun = getIdAkunByUsername(username);
                    return new AkunModel(idAkun, username, password, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<AkunModel> SearchAkun(String key) throws SQLException{
        List<AkunModel> listAkun = new ArrayList<>();
        try {
            Statement stmt = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM akun WHERE username LIKE '%" + key + "%' " +
                    "OR password LIKE '%" + key + "%' " +
                    "OR role LIKE '%" + key + "%';";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                AkunModel akun = new AkunModel();
                akun.setIdAkun(rs.getString("id_akun"));
                akun.setUsername(rs.getString("username"));
                akun.setPassword(rs.getString("password"));
                akun.setRole(rs.getString("role"));
                listAkun.add(akun);
            }
            return listAkun;
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    private static String getIdAkunByUsername(String username) {
        String idAkun = "";
        try {
            Statement stmt = Connect.configDB().createStatement();
            String query = "SELECT id_akun FROM akun WHERE username = '" + username + "';";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                idAkun = rs.getString("id_akun");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idAkun;
    }

    public static AkunModel getAkunByID(String idAkun) throws SQLException {
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

    public static boolean deleteAkun(String idAkun) throws SQLException {
        Connection conn = Connect.configDB();
        String query = "DELETE FROM akun WHERE id_akun = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, idAkun);
        boolean isSuccess = stmt.executeUpdate() > 0;
        return isSuccess;
    }

    public static List<AkunModel> getAllAkun() throws SQLException {
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

    public static void addAkun(AkunModel akun) throws SQLException {
        Connection conn = Connect.configDB();
        String query = "INSERT INTO akun (id_akun, username, password, role) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, akun.getIdAkun());
        stmt.setString(2, akun.getUsername());
        stmt.setString(3, akun.getPassword());
        stmt.setString(4, akun.getRole());

        boolean isSuccess = stmt.executeUpdate() > 0;
    }

    public static String generateIDAkun() {
        String lastId_akun = null;
        try {
            Statement stmt = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM akun ORDER BY id_akun ASC";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                lastId_akun = rs.getString("id_akun");
            }

            if (lastId_akun == null) {
                lastId_akun = "AKN1";
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        int number = Integer.parseInt(lastId_akun.replace("AKN", ""));
        return "AKN" + (number + 1);
    }

    public static boolean IsUsernameExist(String username) {
        boolean result = false;
        try {
            Connection conn = Connect.configDB();
            String query = "SELECT * FROM akun WHERE username = ?"; // Gunakan PreparedStatement untuk keamanan
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = true; // Username ditemukan
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean updateAkun(AkunModel akun) throws SQLException {
        Connection conn = Connect.configDB();
        String query = "UPDATE akun SET username = ?, password = ?, role = ? WHERE id_akun = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, akun.getUsername());
        stmt.setString(2, akun.getPassword());
        stmt.setString(3, akun.getRole());
        stmt.setString(4, akun.getIdAkun());

        return stmt.executeUpdate() > 0;
    }

}
