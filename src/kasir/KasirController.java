/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kasir;

import Auth.loginPage;
import dao.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import koneksiDatabase.Connect;
import model.*;

/**
 *
 * @author David
 */
public class KasirController extends MouseAdapter implements ActionListener{
    private final kasirDashboard view;
    private final CustomerDAO customerDAO;
    private final LayananDAO layananDAO;
    private final PesananDAO pesananDAO;
    
    public KasirController(kasirDashboard view){
        this.view = view;
        this.customerDAO = new CustomerDAO();
        this.layananDAO = new LayananDAO();
        this.pesananDAO = new PesananDAO();
        view.setVisible(true);
        view.addActionListener(this);
        showTabelLayanan();
    }
    
@Override
public void actionPerformed(ActionEvent ae) {
    Object source = ae.getSource();

    if (source.equals(view.getorderButton())) {
        handleOrder();
    } else if (source.equals(view.getBtnAddLayanan())) {
        handleAddLayanan();
    } else if (source.equals(view.getBtnDeleteLayanan())) {
        handleDeleteLayanan();
    } else if (source.equals(view.getBtnDeletePesanan())) {
        handleDeletePesanan();
    } else if (source.equals(view.getBtnEditLayanan())) {
        handleEditLayanan();
    } else if (source.equals(view.getBtnLayanan())) {
        handleLayanan();
    } else if (source.equals(view.getBtnLogout())) {
        handleLogout();
    } else if (source.equals(view.getBtnOrderList())) {
        handleOrderList();
    } else if (source.equals(view.getBtnRefreshLayanan())) {
        handleRefreshLayanan();
    } else if (source.equals(view.getBtnRefreshPesanan())) {
        handleRefreshPesanan();
    } else if (source.equals(view.getBtnSearchLayanan())) {
        handleSearchLayanan();
    } else if (source.equals(view.getBtnSearchPesanan())) {
        handleSearchPesanan();
    } else if (source.equals(view.getBtnUpdateLayanan())) {
        handleUpdateLayanan();
    } else if (source.equals(view.getBntOrderRequest())) {
        handleOrderRequest();
    }
}

@Override
public void mouseClicked(MouseEvent me) {
    Object source = me.getSource();

    if (source.equals(view.getTabelPesanan())){
        int row = view.getTabelPesanan().getSelectedRow();
        TableModel model = view.getTabelPesanan().getModel();
        view.id_pesanan = model.getValueAt(row, 1).toString();
        view.berat = model.getValueAt(row, 3).toString();
        view.hargaTotal = model.getValueAt(row,4).toString();

    }

    if (source.equals(view.getTbLayanan())){
        int row = view.getTbLayanan().getSelectedRow();
        TableModel model = view.getTabelPesanan().getModel();
        view.id_pesanan = model.getValueAt(row, 1).toString();
        view.berat = model.getValueAt(row, 3).toString();
        view.hargaTotal = model.getValueAt(row,4).toString();

    }
}
    
    
private String getIdUser(){
    String id_customer = "";
    String name = view.getCmbCustomer().getSelectedItem().toString();
    try {
        Statement stmt = (Statement) Connect.configDB().createStatement();
        String query = "SELECT id_customer FROM customer WHERE nama = '"+name+"'";
        ResultSet rs = stmt.executeQuery(query);

        if(rs.next()){
            id_customer = rs.getString("id_customer");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return id_customer;
}
// Fungsi Handler

private void handleOrder() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String id_cust = customerDAO.generateIDCustomer();
            System.out.println(id_cust);
        if (view.getTxtNama().getText().equals("") && view.getTxtTelepon().getText().equals("") && view.getTxtAlamat().getText().equals("") && ((Integer) view.getSpnBerat().getValue()) == 0 && view.getcalTanggal().getDate() == null) {
            JOptionPane.showMessageDialog(view, "Please fill out the form");
            return;
        }
        try {
            String formattedDate = dateFormat.format(view.getcalTanggal().getDate());
        
            Statement stmt = (Statement) Connect.configDB().createStatement();
            if(view.getRbCustomerBaru().isSelected()){

                String query1 = "INSERT INTO pesanan VALUE ('"+PesananDAO.generateIdPesanan()+"','"+id_cust+"','"+getIdLayanan()+"','"+view.getSpnBerat().getValue()+"','"+hitungHarga(Integer.parseInt(view.getSpnBerat().getValue().toString()))+"','"+formattedDate+"','"+generateJam()+"')";
                String query2 = "INSERT INTO customer (id_customer,nama,telpon,alamat) VALUE ('"+id_cust+"','"+view.getTxtNama().getText()+"','"+view.getTxtTelepon().getText()+"','"+view.getTxtAlamat().getText()+"')";
                stmt.executeUpdate(query2);
                stmt.executeUpdate(query1);
                
            } else {
                String query = "INSERT INTO pesanan VALUE ('"+PesananDAO.generateIdPesanan()+"','"+getIdUser()+"','"+getIdLayanan()+"','"+view.getSpnBerat().getValue()+"','"+hitungHarga(Integer.parseInt(view.getSpnBerat().getValue().toString()))+"','"+formattedDate+"','"+generateJam()+"')";
                stmt.executeUpdate(query);
            }
            JOptionPane.showMessageDialog(view, "Order successfully placed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    // Logika untuk menangani request order
    System.out.println("Request Order button clicked");
    // Tambahkan logika yang sesuai, seperti membuka form pemesanan, atau memproses data
}

private String getIdLayanan(){
    String idLayanan = null;
    String namaLayanan = view.getCmbLayanan().getSelectedItem().toString();
    try {
        Statement stmt = (Statement) Connect.configDB().createStatement();
        String query = "SELECT * FROM layanan WHERE nama = '"+ namaLayanan+ "'";
        ResultSet rs = stmt.executeQuery(query);

        if(rs.next()){
            idLayanan = rs.getString("id_layanan");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return idLayanan;
}

private void handleOrderRequest() {
    view.getMainPanel().removeAll();
    view.getMainPanel().repaint();
    view.getMainPanel().revalidate();
    view.getMainPanel().add(view.getOrderPanel());
    view.getMainPanel().repaint();
    view.getMainPanel().revalidate();
}


private void handleAddLayanan() {
    // Logika untuk menangani penambahan layanan
    System.out.println("Add Layanan button clicked");
    try {
            tambahLayanan lyn = new tambahLayanan();
            lyn.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
    }
}

private void handleDeleteLayanan() {
    // Logika untuk menangani penghapusan layanan
    System.out.println("Delete Layanan button clicked");
    try {
            if (!view.id_layanan.equals(null) ) {
                int response =JOptionPane.showConfirmDialog(view, "Do you really want to delete ?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        try {
                            Statement stmt = (Statement) Connect.configDB().createStatement();
                            //delete record from login table
                            stmt.executeUpdate("DELETE FROM layanan WHERE id_layanan = '" + view.id_layanan+"';");

                            JOptionPane.showMessageDialog(view, "The information was successfully updated");
                            showTabelLayanan();
                        } catch (Exception e) {
                        }
                    }else{
                        return;
                    }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Please choose an account");
    }
}

private void handleDeletePesanan() {
    // Logika untuk menangani penghapusan pesanan
    System.out.println("Delete Pesanan button clicked");
    try {
            if (!view.id_pesanan.equals(null)) {
                int response =JOptionPane.showConfirmDialog(view, "Do you really want to delete ?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        try {
                            Statement stmt = (Statement) Connect.configDB().createStatement();
                            //delete record from login table
                            stmt.executeUpdate("DELETE FROM pesanan WHERE id_pesanan = '" + view.id_pesanan+"';");

                            JOptionPane.showMessageDialog(view, "The information was successfully updated");
                            showTabelPesanan();
                        } catch (Exception e) {
                        }
                    }else{
                        return;
                    }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Please choose an account");
    }
}

private void handleEditLayanan() {
    // Logika untuk menangani pengeditan layanan
    System.out.println("Edit Layanan button clicked");
    try {
            if (!view.id_layanan.equals(null)){
                insertIntoEditLayanan(view.id_layanan,view.nama,view.harga);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Layanan Belum Di pilih");
    }
}

private void insertIntoEditLayanan(String id_layanan,String nama, String harga){
    editLayanan edtLayanan = new editLayanan();
    edtLayanan.txtIdLayanan.setText(id_layanan);
    edtLayanan.namaField.setText(nama);
    edtLayanan.txtHarga.setText(harga);
    edtLayanan.txtDeskripsi.setText(getDeskripsi(id_layanan));
    edtLayanan.setVisible(true);
}
private String getDeskripsi(String id_layanan){
    String deskripsi ="";
    try {
        Statement stmt = (Statement) Connect.configDB().createStatement();
        String query = "SELECT * FROM layanan WHERE id_layanan = '" + id_layanan + "'";
        ResultSet rs = stmt.executeQuery(query);
        if(rs.next()){
            deskripsi = rs.getString("deskripsi");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return deskripsi;
}
private void handleLayanan() {
    // Logika untuk menangani tindakan terkait layanan
    System.out.println("Layanan button clicked");
    view.getMainPanel().removeAll();
    view.getMainPanel().repaint();
    view.getMainPanel().revalidate();

    view.getMainPanel().add(view.getLayananPanel());
    view.getMainPanel().repaint();
    view.getMainPanel().revalidate();

}

private void handleLogout() {
// Logika untuk menangani logout
System.out.println("Logout button clicked");
int response =JOptionPane.showConfirmDialog(view, "Are you sure ?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.YES_OPTION) {
        loginPage logout = null;
        try {
            logout = new loginPage();
        } catch (Exception ex) {
            //Logger.getLogger(memberDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        logout.setVisible(true);
        view.dispose();
    }else{
        return;
    }
}

private void handleOrderList() {
    // Logika untuk menangani tampilan daftar pesanan
    System.out.println("Order List button clicked");
    view.getMainPanel().removeAll();
    view.getMainPanel().repaint();
    view.getMainPanel().revalidate();

    view.getMainPanel().add(view.getOrderList());
    view.getMainPanel().repaint();
    view.getMainPanel().revalidate();
}

private void handleRefreshLayanan() {
    System.out.println("Refresh Layanan button clicked");
    showTabelLayanan();
}

private void handleRefreshPesanan() {
    System.out.println("Refresh Pesanan button clicked");
    showTabelPesanan();
}

private void handleSearchLayanan() {
    // Logika untuk menangani pencarian layanan
    System.out.println("Search Layanan button clicked");
    // Bisa membuka form pencarian atau filter layanan
}

private void handleSearchPesanan() {
    // Logika untuk menangani pencarian pesanan
    System.out.println("Search Pesanan button clicked");
    // Bisa membuka form pencarian atau filter pesanan
}

private void handleUpdateLayanan() {
    // Logika untuk menangani pembaruan layanan
    System.out.println("Update Layanan button clicked");
    try {
        if (!view.berat.equals(null)){
            insertIntoUpdatePesanan(view.id_pesanan,view.berat , view.hargaTotal);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(view, "Layanan Belum Di pilih");
    }
}
    
private void insertIntoUpdatePesanan (String id_pesanan,String berat, String harga){
    updateOrder updt = new updateOrder();

    updt.idPesananField.setText(id_pesanan);
    updt.countBerat.setValue(Integer.parseInt(berat));
    updt.labelHarga.setText("RP" + harga);

    updt.setVisible(true);
}
private int hitungHarga(int berat){
    int harga = 0;
    try {
        Statement stmt = (Statement) Connect.configDB().createStatement();
        String query = "SELECT * FROM layanan where id_layanan = '"+getIdLayanan()+"'";

        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()){
            harga = rs.getInt("harga");
        }

        while (rs.next()){

        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return berat * harga;
}

private String generateJam() {
    String jam = (String) view.getSpnJam().getValue();

    String[] parts = jam.split(" ");
    String time = parts[0];
    String period = parts[1];

    String[] timeParts = time.split(":");
    int hour = Integer.parseInt(timeParts[0]);
    String minute = timeParts[1];


    if (period.equals("PM") && hour != 12) {
        hour += 12;
    } else if (period.equals("AM") && hour == 12) {
        hour = 0;
    }

    return String.format("%02d:%s:00", hour, minute);
}
public void showTabelLayanan(){
    int n = 0;
    String [] kolom = {"NO", "Id_layanan" ,"Nama", "Harga"};
    DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
    try {
        Statement stmt = (Statement) Connect.configDB().createStatement();
        String query = "SELECT * FROM layanan";
        ResultSet rs = stmt.executeQuery(query);


        while(rs.next()){
            n++;
            String id_layanan = rs.getString("id_layanan");
            String nama = rs.getString("nama");
            int harga = rs.getInt("harga");
            tb1.addRow(new String[] {String.valueOf(n), id_layanan, nama, String.valueOf(harga)});
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    view.getTbLayanan().setModel(tb1);
}
    
public void showTabelPesanan(){
    int n = 0;
    String [] kolom = {"NO", "id_pesanan" ,"id_customer", "berat" ,"Harga", "Tanggal Ambil", "Jam Ambil"};
    DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
    try {
            Statement stmt = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM pesanan";
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                n++;
                String id_pesanan = rs.getString("id_pesanan");
                String id_customer = rs.getString("id_customer");
                int berat = rs.getInt("berat");
                int harga = rs.getInt("harga");
                String tanggalAmbil = rs.getString("tanggalSelesai");
                String jamAmbil = rs.getString("jamSelesai");
                tb1.addRow(new String[] {String.valueOf(n), id_pesanan, id_customer , String.valueOf(berat) , String.valueOf(harga), tanggalAmbil, jamAmbil});
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
    view.getTabelPesanan().setModel(tb1);
    }

}
