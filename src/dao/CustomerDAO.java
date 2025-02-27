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
import java.util.logging.Level;
import java.util.logging.Logger;

import koneksiDatabase.Connect;
import model.CustomerModel;

/**
 *
 * @author David
 */
public class CustomerDAO {

    public static CustomerModel getCustomerByIdAkun(String idAkun) {
        try {
            Connection conn = Connect.configDB();
            String query = "SELECT * FROM customer WHERE id_akun = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, idAkun);
            ResultSet rs = stmt.executeQuery();
            
            CustomerModel customer = null;
            
            if (rs.next()) {
                customer = new CustomerModel(
                        rs.getString("id_customer"),
                        rs.getString("id_akun"),
                        rs.getString("nama"),
                        rs.getString("telpon"),
                        rs.getString("alamat"));
            }
            
            return customer;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void addCustomer(CustomerModel customer) throws SQLException {
        Connection conn = Connect.configDB();
        String query = "INSERT INTO customer (id_customer, nama, telpon, alamat, id_akun) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, customer.getIdCustomer());
        stmt.setString(2, customer.getName());
        stmt.setString(3, customer.getPhone());
        stmt.setString(4, customer.getAddress());
        stmt.setString(5, customer.getIdAkun());

        boolean isSuccess = stmt.executeUpdate() > 0;
    }

    public static boolean updateCustomer(CustomerModel customer) throws SQLException {
        Connection conn = Connect.configDB();
        String query = "UPDATE customer SET nama = ?, telpon = ?, alamat = ?, id_akun = ? WHERE id_customer = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, customer.getName());
        stmt.setString(2, customer.getPhone());
        stmt.setString(3, customer.getAddress());
        stmt.setString(4, customer.getIdAkun());
        stmt.setString(5, customer.getIdCustomer());

        boolean isSuccess = stmt.executeUpdate() > 0;

        return isSuccess;
    }

    public static boolean deleteCustomer(String idCustomer) throws SQLException {
        Connection conn = Connect.configDB();
        String query = "DELETE FROM customer WHERE id_customer = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, idCustomer);

        boolean isSuccess = stmt.executeUpdate() > 0;

        return isSuccess;
    }

    public static List<CustomerModel> getAllCustomers() {
        try {
            Connection conn = Connect.configDB();
            String query = "SELECT * FROM customer";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            List<CustomerModel> customers = new ArrayList<>();
            while (rs.next()) {
                CustomerModel customer = new CustomerModel(
                        rs.getString("id_customer"),
                        rs.getString("id_akun"),
                        rs.getString("nama"),
                        rs.getString("telpon"),
                        rs.getString("alamat"));
                customers.add(customer);
            }
            
            return customers;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String generateIDCustomer() {
        String lastIdCustomer = null;
        String newIdCustomer = null;
        try {
            Connection conn = Connect.configDB();
            String query = "SELECT id_customer FROM customer ORDER BY id_customer DESC LIMIT 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                lastIdCustomer = rs.getString("id_customer");
            }

            if (lastIdCustomer == null) {
                newIdCustomer = "CST001";
            } else {
                int number = Integer.parseInt(lastIdCustomer.replace("CST", ""));
                newIdCustomer = String.format("CST%03d", number + 1);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return newIdCustomer;
    }

    public static List<CustomerModel> searchCustomer(String keyword){
        try {
            Connection conn = Connect.configDB();
            String query = "SELECT * FROM customer WHERE id_customer LIKE ? OR id_akun LIKE ? OR nama LIKE ? OR telpon LIKE ? OR alamat LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            stmt.setString(4, "%" + keyword + "%");
            stmt.setString(5, "%" + keyword + "%");
            
            ResultSet rs = stmt.executeQuery();
            
            List<CustomerModel> customers = new ArrayList<>();
            while (rs.next()) {
                CustomerModel customer = new CustomerModel(
                        rs.getString("id_customer"),
                        rs.getString("id_akun"),
                        rs.getString("nama"),
                        rs.getString("telpon"),
                        rs.getString("alamat"));
                customers.add(customer);
            }
            
            return customers;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static CustomerModel getCustomerByIdCustomer(String idCustomer){
        try {
            Connection conn = Connect.configDB();
            String query = "SELECT * FROM customer WHERE id_customer = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, idCustomer);
            ResultSet rs = stmt.executeQuery();
            
            CustomerModel customer = null;
            
            if (rs.next()) {
                customer = new CustomerModel(
                        rs.getString("id_customer"),
                        rs.getString("id_akun"),
                        rs.getString("nama"),
                        rs.getString("telpon"),
                        rs.getString("alamat"));
            }
            return customer;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
