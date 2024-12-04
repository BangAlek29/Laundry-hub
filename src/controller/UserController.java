package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.CustomerDAO;
import dao.LayananDAO;
import dao.PesananDAO;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.CustomerModel;
import model.LayananModel;
import model.PesananModel;
import model.TableModelFactory;
import util.PanelUtils;
import util.PesananUtil;
import util.TableUtils;
import view.user.userDashboard;



public class UserController extends MouseAdapter implements ActionListener {
    private final userDashboard view;
    private CustomerModel cust;

    public UserController(CustomerModel cust) {
        this.cust = cust;
        this.view = new userDashboard();
        view.setVisible(true);
        addEvents();
        renderCbLayanan();
        refreshTable();
    }

    public void addEvents() {
        view.getBtnRequestOrder()
                .addActionListener(e -> PanelUtils.switchPanel(view.getMainPanel(), view.getOrderPanel()));
        view.getBtnOrderList()
                .addActionListener(e -> PanelUtils.switchPanel(view.getMainPanel(), view.getRiwayatPemesananPanel()));
        view.getRbCustomerBaru()
                .addActionListener(e -> PanelUtils.switchPanel(view.getPnlCustomer(), view.getCustomerBaruPanel()));
        view.getRbCustomerLama().addActionListener(e -> {
            PanelUtils.switchPanel(view.getPnlCustomer(), view.getCustomerLamaPanel());
            fillForm();
        });
        view.getOrderButton().addActionListener(e -> requestOrder());
        view.getBtnLogout().addActionListener(e -> Logout());
        view.getCmbLayanan().addActionListener(e -> renderHarga());
        view.getSpnBerat().addChangeListener(e -> renderHarga());
        view.getBtnRefresh().addChangeListener(e -> refreshTable());
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
    }

    public void fillForm() {
        view.getTxtNama1().setText(cust.getName());
        view.getTxtAlamat2().setText(cust.getAddress());
        view.getTxtTelepon2().setText(cust.getPhone());
    }

    private void searchPesanan() {
        String search = view.getTxtSearchPesanan().getText();
        ArrayList<PesananModel> pesananList = PesananDAO.searchPesananCustomer(search, cust.getIdCustomer());
        DefaultTableModel tb1 = TableModelFactory.createPesananTableModel(pesananList);
        view.getTabelPesanan().setModel(tb1);
        TableUtils.setColumnAlignment(view.getTabelPesanan(), SwingConstants.CENTER);
    }

    private int hitungHarga(int berat) {
        LayananModel selectedLayanan = (LayananModel) view.getCmbLayanan().getSelectedItem();
        String idLayanan = selectedLayanan.getIdLayanan();
        int harga = LayananDAO.getLayananById(idLayanan).getHarga();
        int hargaTotal = berat*harga;
        if (view.getRbCustomerLama().isSelected()) {
            hargaTotal *= 0.9;
        }
        return hargaTotal;
    }

    private void requestOrder() {
        try {
            LayananModel selectedLayanan = (LayananModel) view.getCmbLayanan().getSelectedItem();
            String idLayanan = selectedLayanan.getIdLayanan();
            String idPesanan = PesananDAO.generateIdPesanan();

            if (view.getRbCustomerBaru().isSelected()) {
                String idCust = CustomerDAO.generateIDCustomer();

                CustomerModel newCustomer = new CustomerModel(
                        idCust,
                        null,
                        view.getTxtNama().getText().trim(),
                        view.getTxtTelepon().getText().trim(),
                        view.getTxtAlamat().getText().trim());

                CustomerDAO.addCustomer(newCustomer);

                PesananModel newOrder = new PesananModel(
                        idPesanan,
                        idCust,
                        idLayanan,
                        (Integer) view.getSpnBerat().getValue(),
                        hitungHarga((Integer) view.getSpnBerat().getValue()));

                PesananDAO.insertPesanan(newOrder);

            } else {
                String idCust = cust.getIdCustomer();

                PesananModel newOrder = new PesananModel(
                        idPesanan,
                        idCust,
                        idLayanan,
                        (Integer) view.getSpnBerat().getValue(),
                        hitungHarga((Integer) view.getSpnBerat().getValue()));

                PesananDAO.insertPesanan(newOrder);
            }

            JOptionPane.showMessageDialog(view, "Order successfully placed");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        refreshTable();
    }

    private void Logout() {
        view.dispose();
        LoginController login = new LoginController();
    }

    public void refreshTable() {
        ArrayList<PesananModel> pesananList = PesananDAO.getPesananByCustomerId(cust.getIdCustomer());
        DefaultTableModel model = TableModelFactory.createPesananTableModel(pesananList);
        view.tabelPesanan.setModel(model);
        TableUtils.setColumnAlignment(view.tabelPesanan,SwingConstants.CENTER);
    }

    private void renderCbLayanan() {
        List<LayananModel> listLayanan = LayananDAO.getAllLayanan();
        DefaultComboBoxModel<LayananModel> model = new DefaultComboBoxModel<>();

        for (LayananModel layanan : listLayanan) {
            model.addElement(layanan);
        }

        view.getCmbLayanan().setModel(model);
    }

    private void renderHarga() {
        int berat = Integer.parseInt(view.getSpnBerat().getValue().toString());
        int HargaTotal = hitungHarga(berat);
        String displayHarga = "Rp. " + PesananUtil.formatCurrency(HargaTotal) + " -,";
        view.getLabelHarga().setText(displayHarga);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}