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

    public static AkunModel validateLogin(String username, String password) {
        try {
            Statement stmt = Connect.configDB().createStatement();
            String query = "SELECT * FROM akun WHERE username = '" + username + "';";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (storedPassword.equals(password)) {
                    String role = rs.getString("role");
                    String idAkun = rs.getString("id_akun");
                    String email = rs.getString("email");
                    return new AkunModel(idAkun, email, username, password, role);
                } else {
                    // Return objek khusus untuk password salah
                    return new AkunModel("INVALID_PASSWORD", null, username, null, null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Username tidak ditemukan
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
                akun.setEmail(rs.getString("email"));
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
                akun.setEmail(rs.getString("email"));
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
            String query = "INSERT INTO akun (id_akun,email, username, password, role, is_active) VALUES (?,?, ?, ?, ?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, akun.getIdAkun());
            stmt.setString(2, akun.getEmail());
            stmt.setString(3, akun.getUsername());
            stmt.setString(4, akun.getPassword());
            stmt.setString(5, akun.getRole());
            stmt.setString(6, "1");
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean isVerificationCodeValid(String idAkun, String kodeVerifikasi) {
        String query = "SELECT * FROM akun WHERE id_akun = ? AND activation_code = ?";
        try {
            Connection conn = Connect.configDB();
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setString(1, idAkun);
            ps.setString(2, kodeVerifikasi);
            ResultSet rs = ps.executeQuery();

            boolean isValid = rs.next(); // True jika data ditemukan
            return isValid;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateVerificationStatus(String idAkun) {
        String updateQuery = "UPDATE akun SET is_active = 1 WHERE id_akun = ?";
        try {
            Connection conn = Connect.configDB();
            PreparedStatement ps = conn.prepareStatement(updateQuery);

            ps.setString(1, idAkun);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // True jika ada baris yang diperbarui

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public static boolean checkIsActive(String idAkun) {
        String checkQuery = "SELECT is_active FROM akun WHERE id_akun = ?";
        try {
            Connection conn = Connect.configDB();
            PreparedStatement ps = conn.prepareStatement(checkQuery);

            ps.setString(1, idAkun);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // Jika data ditemukan
                int isActive = rs.getInt("is_active");
                return isActive == 1; // True jika is_active bernilai 1
            } else {
                System.out.println("ID akun tidak ditemukan.");
                return false; // Jika ID tidak ditemukan
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false jika terjadi kesalahan
        }
    }
    
    public static boolean updateVerificationCode(String idAkun, String code) {
        String updateQuery = "UPDATE akun SET activation_code = ? WHERE id_akun = ?";
        try {
            Connection conn = Connect.configDB();
            PreparedStatement ps = conn.prepareStatement(updateQuery);

            ps.setString(1, code);
            ps.setString(2, idAkun);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // True jika ada baris yang diperbarui

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String generateIDAkun() {
        String lastIdAkun = null;
        String newIdAkun = null;
        try {
            Connection conn = Connect.configDB();
            String query = "SELECT id_akun FROM akun ORDER BY id_akun DESC LIMIT 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                lastIdAkun = rs.getString("id_akun");
            }

            if (lastIdAkun == null) {
                newIdAkun = "AKN001";
            } else {
                int number = Integer.parseInt(lastIdAkun.replace("AKN", ""));
                newIdAkun = String.format("AKN%03d", number + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newIdAkun;
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
            String query = "UPDATE akun SET email = ?, username = ?, password = ?, role = ? WHERE id_akun = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, akun.getEmail());
            stmt.setString(2, akun.getUsername());
            stmt.setString(3, akun.getPassword());
            stmt.setString(4, akun.getRole());
            stmt.setString(5, akun.getIdAkun());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
