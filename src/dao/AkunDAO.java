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

    public static AkunModel validateLogin(String username, String password){
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

    public static List<AkunModel> SearchAkun(String key){
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

    public static AkunModel getAkunByID(String idAkun){
        try{
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
                    } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }

    public static boolean deleteAkun(String idAkun) {
        try {
            Connection conn = Connect.configDB();
            String query = "DELETE FROM akun WHERE id_akun = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, idAkun);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<AkunModel> getAllAkun() {
        List<AkunModel> listAkun = new ArrayList<>();
        try {
            Connection conn = Connect.configDB();
            String query = "SELECT * FROM akun";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                AkunModel akun = new AkunModel();
                akun.setIdAkun(rs.getString("id_akun"));
                akun.setUsername(rs.getString("username"));
                akun.setPassword(rs.getString("password"));
                akun.setRole(rs.getString("role"));
                listAkun.add(akun);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listAkun;
    }

    public static boolean addAkun(AkunModel akun) {
        try {
            Connection conn = Connect.configDB();
            String query = "INSERT INTO akun (id_akun, username, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, akun.getIdAkun());
            stmt.setString(2, akun.getUsername());
            stmt.setString(3, akun.getPassword());
            stmt.setString(4, akun.getRole());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String generateIDAkun() {
        String lastIdAkun = null;
        try {
            Connection conn = Connect.configDB();
            String query = "SELECT * FROM akun ORDER BY id_akun ASC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                lastIdAkun = rs.getString("id_akun");
            }

            if (lastIdAkun == null) {
                lastIdAkun = "AKN1";
            } else {
                int number = Integer.parseInt(lastIdAkun.replace("AKN", ""));
                lastIdAkun = "AKN" + (number + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastIdAkun;
    }

    public static boolean isUsernameExist(String username) {
        boolean result = false;
        try {
            Connection conn = Connect.configDB();
            String query = "SELECT * FROM akun WHERE username = ?";
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

    public static boolean updateAkun(AkunModel akun) {
        try {
            Connection conn = Connect.configDB();
            String query = "UPDATE akun SET username = ?, password = ?, role = ? WHERE id_akun = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, akun.getUsername());
            stmt.setString(2, akun.getPassword());
            stmt.setString(3, akun.getRole());
            stmt.setString(4, akun.getIdAkun());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
