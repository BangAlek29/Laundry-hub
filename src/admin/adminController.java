package admin;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import dao.CustomerDAO;
import dao.AkunDAO;
import model.CustomerModel;
import model.AkunModel;

public class adminController {
    private final adminDashboard view; // Jika view tidak digunakan, bisa dihapus.
    private final CustomerDAO customerDAO;
    private final AkunDAO akunDAO;

    // Constructor menerima parameter adminDashboard
    public adminController(adminDashboard view) {
        this.view = view;
        this.customerDAO = new CustomerDAO();
        this.akunDAO = new AkunDAO();
    }

    public DefaultTableModel getCustomerTableModel() {
        // Definisi kolom tabel
        String[] kolom = { "NO", "ID Customer", "ID Akun", "Nama", "Telepon", "Alamat" };
        DefaultTableModel tb1 = new DefaultTableModel(null, kolom);

        try {
            // Mendapatkan daftar pelanggan dari DAO
            List<CustomerModel> customerList = customerDAO.getAllCustomers();

            if (customerList != null) {
                int n = 0;
                for (CustomerModel customer : customerList) {
                    n++;
                    tb1.addRow(new String[] {
                            String.valueOf(n),
                            customer.getIdCustomer(),
                            customer.getIdAkun(),
                            customer.getName(),
                            customer.getPhone(),
                            customer.getAddress()
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tb1;
    }

    public DefaultTableModel getAkunTableModel() {
        String[] kolom = { "NO", "ID Akun", "Username", "Password", "Role" };
        DefaultTableModel tb1 = new DefaultTableModel(null, kolom);

        try {
            List<AkunModel> akunList = akunDAO.getAllAkun();
            int n = 0;
            for (AkunModel akun : akunList) {
                n++;
                tb1.addRow(new String[] {
                        String.valueOf(n),
                        akun.getIdAkun(),
                        akun.getUsername(),
                        akun.getPassword(),
                        akun.getRole() });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tb1;
    }

    public void deleteAkun(String idAkun){
        try {
            akunDAO.deleteAkun(idAkun);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

}
