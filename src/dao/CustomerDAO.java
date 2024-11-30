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
import model.CustomerModel;

/**
 *
 * @author David
 */
public class CustomerDAO {

    public static CustomerModel getCustomerByIdAkun(String idAkun) throws SQLException {
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

    public static List<CustomerModel> getAllCustomers() throws SQLException {
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
    }

    public static String generateIDCustomer() {
        String lastId_cust = null;
        try {
            Statement stmt = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM customer ORDER BY id_customer ASC";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                lastId_cust = rs.getString("id_customer");
            }

            if (lastId_cust == null) {
                lastId_cust = "CST1";
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        int number = Integer.parseInt(lastId_cust.replace("CST", ""));
        return "CST" + (number + 1);
    }

    public static List<CustomerModel> searchCustomer(String keyword) throws SQLException {
        Connection conn = Connect.configDB();
        String query = "SELECT * FROM customer WHERE nama LIKE ? OR id_customer LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, "%" + keyword + "%");
        stmt.setString(2, "%" + keyword + "%");
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
    }

    public static CustomerModel getCustomerByIdCustomer(String idCustomer) throws SQLException {
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
    }

}
