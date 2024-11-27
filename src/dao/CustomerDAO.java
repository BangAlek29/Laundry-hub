/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koneksiDatabase.Connect;
import model.CustomerModel;

/**
 *
 * @author David
 */
public class CustomerDAO {

    public CustomerModel getCustomerByIdAkun(String idAkun) throws SQLException {
        Connection conn = Connect.configDB();
        String query = "SELECT * FROM customer WHERE id_akun = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, idAkun);
        ResultSet rs = stmt.executeQuery();

        CustomerModel customer = null;

        if (rs.next()) {
            customer = new CustomerModel(
                    rs.getString("id_customer"),
                    rs.getString("nama"),
                    rs.getString("telpon"),
                    rs.getString("alamat"));
            customer.setIdAkun(rs.getString("id_akun"));
        }

        rs.close();
        stmt.close();
        conn.close();

        return customer;
    }

    public boolean addCustomer(CustomerModel customer) throws SQLException {
        Connection conn = Connect.configDB();
        String query = "INSERT INTO customer (id_customer, nama, telpon, alamat, id_akun) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, customer.getIdCustomer());
        stmt.setString(2, customer.getName());
        stmt.setString(3, customer.getPhone());
        stmt.setString(4, customer.getAddress());
        stmt.setString(5, customer.getIdAkun());

        boolean isSuccess = stmt.executeUpdate() > 0;

        return isSuccess;
    }

    public boolean updateCustomer(CustomerModel customer) throws SQLException {
        Connection conn = Connect.configDB();
        String query = "UPDATE customer SET nama = ?, telpon = ?, alamat = ?, id_akun = ? WHERE id_customer = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, customer.getName());
        stmt.setString(2, customer.getPhone());
        stmt.setString(3, customer.getAddress());
        stmt.setString(4, customer.getIdAkun());
        stmt.setString(5, customer.getIdCustomer());

        boolean isSuccess = stmt.executeUpdate() > 0;

        stmt.close();
        conn.close();

        return isSuccess;
    }

    public boolean deleteCustomer(String idCustomer) throws SQLException {
        Connection conn = Connect.configDB();
        String query = "DELETE FROM customer WHERE id_customer = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, idCustomer);

        boolean isSuccess = stmt.executeUpdate() > 0;

        return isSuccess;
    }

    public List<CustomerModel> getAllCustomers() throws SQLException {
        Connection conn = Connect.configDB();
        String query = "SELECT * FROM customer";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        List<CustomerModel> customers = new ArrayList<>();
        while (rs.next()) {
            CustomerModel customer = new CustomerModel(
                    rs.getString("id_customer"),
                    rs.getString("nama"),
                    rs.getString("telpon"),
                    rs.getString("alamat"));
            customer.setIdAkun(rs.getString("id_akun"));
            customers.add(customer);
        }

        rs.close();
        stmt.close();
        conn.close();

        return customers;
    }

}
