/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static dao.CustomerDAO.*;
import static dao.LayananDAO.*;
import static dao.PesananDAO.*;
import static model.TableModelFactory.*;
import static util.PanelUtils.*;
import static util.PesananUtil.*;
import static util.TableUtils.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.*;
import view.kasir.kasirDashboard;

/**
 *
 * @author David
 */
public class KasirController extends MouseAdapter implements ActionListener, ChangeListener {
    private final kasirDashboard view;
    private PesananModel pesanan;
    private LayananModel layanan;

    public KasirController() {
        this.view = new kasirDashboard();
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        pesanan = new PesananModel();
        layanan = new LayananModel();
        initComponent();
    }

    public void initComponent() {
        showTabelLayanan();
        showTabelPesanan();
        AddEvents();
        view.getRbCustomerBaru().setSelected(true);
        view.getCmbCustomer().setModel(renderCbCustomer());
        view.getCmbLayanan().setModel(renderCbLayanan());
        view.getRbCustomerBaru().setSelected(true);
    }

    public void AddEvents() {
        view.getOrderButton().addActionListener(e -> order());
        view.getBtnDeletePesanan().addActionListener(e -> deletePesanan());
        view.getBtnUpdatePesanan().addActionListener(e -> ShowUpdatePesananPanel());
        view.getBtnRefreshPesanan().addActionListener(e -> showTabelPesanan());
        
        view.getBtnAddLayanan().addActionListener(e -> ShowAddLayananPanel());
        view.getBtnEditLayanan().addActionListener(e -> ShowEditLayananPanel());
        view.getBtnDeleteLayanan().addActionListener(e -> deleteLayanan());
        view.getBtnRefreshLayanan().addActionListener(e -> showTabelLayanan());

        view.getCmbLayanan().addActionListener(e -> renderHarga());
        view.getSpnBerat().addChangeListener(e -> renderHarga());
        view.getCmbCustomer().addActionListener(e -> fillCustomer());

        view.getBtnLogout().addActionListener(e -> logout());
        
        view.getBtnLayanan().addActionListener(e -> switchPanel(view.getMainPanel(), view.getLayananPanel()));
        view.getBtnOrderList().addActionListener(e -> switchPanel(view.getMainPanel(), view.getOrderList()));
        view.getBntOrderRequest().addActionListener(e -> switchPanel(view.getMainPanel(), view.getOrderPanel()));
        view.getRbCustomerBaru().addActionListener(e -> {switchPanel(view.getPnlCustomer(), view.getCustomerBaruPanel()); renderHarga();});
        view.getRbCustomerLama().addActionListener(e -> {switchPanel(view.getPnlCustomer(), view.getCustomerLamaPanel()); fillCustomer(); renderHarga();});
        
        view.getTabelPesanan().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int row = view.getTabelPesanan().getSelectedRow();
                TableModel model = view.getTabelPesanan().getModel();
                pesanan = getPesananById(model.getValueAt(row, 1).toString());
                
            }
        });
        
        view.getTbLayanan().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int row = view.getTbLayanan().getSelectedRow();
                TableModel model = view.getTbLayanan().getModel();
                layanan = getLayananById(model.getValueAt(row, 1).toString());
            }
        });
        
        view.getTxtSearchPesanan().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchPesanan();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchPesanan();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        
        view.getTxtSearchLayanan().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchLayanan();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchLayanan();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }
    

    private void fillCustomer() {
        CustomerModel selectedCustomer = (CustomerModel) view.getCmbCustomer().getSelectedItem();
        if (selectedCustomer != null) {
            String customerId = selectedCustomer.getIdCustomer();
            CustomerModel cust = getCustomerByIdCustomer(customerId);
            view.getTxtAlamat2().setText(cust.getAddress());
            view.getTxtTelepon2().setText(cust.getPhone());
        }
    }

    private void order() {
        if (view.getRbCustomerBaru().isSelected()) {
            if (view.getTxtNama().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Nama tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (view.getTxtTelepon().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Telepon tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (view.getTxtAlamat().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Alamat tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
        }
        if ((Integer) view.getSpnBerat().getValue() <= 0) {
            JOptionPane.showMessageDialog(view, "Berat harus lebih dari 0", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            LayananModel selectedLayanan = (LayananModel) view.getCmbLayanan().getSelectedItem();
            String idLayanan = selectedLayanan.getIdLayanan();
            String idPesanan = generateIdPesanan();

            String jam = convertTo24HourFormat(view.getTxtJam().getText());
            String formattedDate = null;
            if (view.getTxtTanggal().getText().equals("--/--/----")) {
                formattedDate = null;
            }else{
               formattedDate = convertDate(view.getTxtTanggal().getText());
            }
            if (view.getTxtJam().getText().equals("--:-- --")) {
                jam = null;
            }
            
            if (view.getRbCustomerBaru().isSelected()) {
                String idCust = generateIDCustomer();

                CustomerModel newCustomer = new CustomerModel(
                        idCust,
                        null,
                        view.getTxtNama().getText().trim(),
                        view.getTxtTelepon().getText().trim(),
                        view.getTxtAlamat().getText().trim());

                addCustomer(newCustomer);

                PesananModel newOrder = new PesananModel(
                        idPesanan,
                        idCust,
                        idLayanan,
                        (Integer) view.getSpnBerat().getValue(),
                        hitungHarga((Integer) view.getSpnBerat().getValue()),
                        formattedDate,
                        jam);

                insertPesananKasir(newOrder);

            } else {
                CustomerModel selectedCustomer = (CustomerModel) view.getCmbCustomer().getSelectedItem();
                String idCust = selectedCustomer.getIdCustomer();

                PesananModel newOrder = new PesananModel(
                        idPesanan,
                        idCust,
                        idLayanan,
                        (Integer) view.getSpnBerat().getValue(),
                        hitungHarga((Integer) view.getSpnBerat().getValue()),
                        formattedDate,
                        jam);

                insertPesananKasir(newOrder);
            }

            JOptionPane.showMessageDialog(view, "Order successfully placed");
            clear();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        showTabelPesanan();
    }

    private void ShowAddLayananPanel() {
        try {
            TambahLayananController addLayanan = new TambahLayananController(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void ShowEditLayananPanel() {
        try {
            if (layanan.getIdLayanan() != null) {
                EditLayananController edit = new EditLayananController(layanan,this);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Layanan Belum Di pilih");
        }
    }
    
    private void searchLayanan() {
        List<LayananModel> ListLayanan = searchLayananByKey(view.getTxtSearchLayanan().getText());
        DefaultTableModel tb1 =  createLayananTableModel(ListLayanan);
        view.getTbLayanan().setModel(tb1);
        setTableServiceSettings(view.getTbLayanan());
    }

    private void deleteLayanan() {
        try {
            if (pesanan != null) {
                int response = JOptionPane.showConfirmDialog(view, "Do you really want to delete ?", "Confirm",
                        JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    deleteLayananById(layanan.getIdLayanan());
                    JOptionPane.showMessageDialog(view, "The information was successfully updated");
                    showTabelLayanan();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Please choose an account");
        }
    }
    
    public void showTabelLayanan() {
        List<LayananModel> ListLayanan = getAllLayanan();
        DefaultTableModel tb1 = createLayananTableModel(ListLayanan);
        view.getTbLayanan().setModel(tb1);
        setTableServiceSettings(view.getTbLayanan());
    }

    private void searchPesanan() {
        String search = view.getTxtSearchPesanan().getText();
        ArrayList<PesananModel> pesananList = searchPesananByKey(search);
        DefaultTableModel tb1 = createPesananTableModel(pesananList);
        view.getTabelPesanan().setModel(tb1);
        setTableOrderSettings(view.getTabelPesanan());
    }

    private void ShowUpdatePesananPanel() {
        try {
            if (pesanan.getIdPesanan() != null) {
                UpdateOrderController updt = new UpdateOrderController(pesanan,this);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Pesanan Belum Di pilih");
        }
    }
    
    private void deletePesanan() {
        try {
            if (pesanan != null) {
                int response = JOptionPane.showConfirmDialog(view, "Do you really want to delete ?", "Confirm",
                        JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    deletePesananById(pesanan.getIdPesanan());
                    showTabelPesanan();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Please choose an account");
        }
    }
    
    public void showTabelPesanan() {
        ArrayList<PesananModel> pesananList = getAllPesanan();
        DefaultTableModel tb1 = createPesananTableModel(pesananList);
        view.getTabelPesanan().setModel(tb1);
        setTableOrderSettings(view.getTabelPesanan());
    }

    private int hitungHarga(int berat) {
        LayananModel selectedLayanan = (LayananModel) view.getCmbLayanan().getSelectedItem();
        int harga = getLayananById(selectedLayanan.getIdLayanan()).getHarga();
        int totalHarga =  harga * berat;
        if(view.getRbCustomerLama().isSelected()){
            totalHarga *= 0.9;
        }
        return totalHarga;
    }
    
    private void renderHarga() {
        int berat = Integer.parseInt(view.getSpnBerat().getValue().toString());
        int HargaTotal = hitungHarga(berat);
        view.getLabelHarga().setText(formatCurrency(HargaTotal));
    }
    
    private void logout() {
        int response = JOptionPane.showConfirmDialog(view, "Are you sure ?", "Confirm", JOptionPane.YES_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            LoginController logout = null;
            try {
                logout = new LoginController();
            } catch (Exception e) {
                e.printStackTrace();
            }
            view.dispose();
        } else {
            return;
        }
    }
    
    public void clear(){
        view.getTxtNama().setText("");
        view.getTxtJam().setText("");
        view.getTxtTanggal().setText("");
        view.getTxtTelepon().setText("");
        view.getTxtAlamat().setText("");
        view.getTxtAlamat2().setText("");
        view.getTxtTelepon2().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}