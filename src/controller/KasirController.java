/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.CustomerDAO;
import dao.LayananDAO;
import dao.PesananDAO;
import model.CustomerModel;
import model.LayananModel;
import model.PesananModel;
import util.PanelUtils;
import util.PesananUtil;
import util.SpinerTimeModel;
import view.kasir.kasirDashboard;
import view.kasir.tambahLayanan;

/**
 *
 * @author David
 */
public class KasirController extends MouseAdapter implements ActionListener, ChangeListener {
    private final kasirDashboard view;
    private final PesananModel pesanan;
    private final LayananModel layanan;

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
        renderCbCustomer();
        renderCbLayanan();
        renderSpinerJam();
        AddEvents();
    }

    public void AddEvents() {
        view.getOrderButton().addActionListener(e -> handleOrder());
        view.getBtnAddLayanan().addActionListener(e -> handleAddLayanan());
        view.getBtnDeleteLayanan().addActionListener(e -> handleDeleteLayanan());
        view.getBtnDeletePesanan().addActionListener(e -> handleDeletePesanan());
        view.getBtnEditLayanan().addActionListener(e -> handleEditLayanan());
        view.getBtnLayanan()
                .addActionListener(e -> PanelUtils.switchPanel(view.getMainPanel(), view.getLayananPanel()));
        view.getBtnLogout().addActionListener(e -> handleLogout());
        view.getBtnOrderList().addActionListener(e -> PanelUtils.switchPanel(view.getMainPanel(), view.getOrderList()));
        view.getBtnRefreshLayanan().addActionListener(e -> showTabelLayanan());
        view.getBtnRefreshPesanan().addActionListener(e -> showTabelPesanan());
        view.getBtnSearchLayanan().addActionListener(e -> handleSearchLayanan());
        view.getBtnSearchPesanan().addActionListener(e -> handleSearchPesanan());
        view.getBtnUpdatePesanan().addActionListener(e -> handleUpdatePesanan());
        view.getBntOrderRequest()
                .addActionListener(e -> PanelUtils.switchPanel(view.getMainPanel(), view.getOrderPanel()));
        view.getCmbLayanan().addActionListener(e -> renderHarga());
        view.getRbCustomerBaru()
                .addActionListener(e -> PanelUtils.switchPanel(view.getPnlCustomer(), view.getCustomerBaruPanel()));
        view.getRbCustomerLama()
                .addActionListener(e -> PanelUtils.switchPanel(view.getPnlCustomer(), view.getCustomerLamaPanel()));
        view.getCmbCustomer().addActionListener(e -> handleFillCustomer());
        view.getSpnBerat().addChangeListener(e -> renderHarga());
        view.getTabelPesanan().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int row = view.getTabelPesanan().getSelectedRow();
                TableModel model = view.getTabelPesanan().getModel();
                pesanan.setIdPesanan(model.getValueAt(row, 1).toString());
                pesanan.setIdCustomer(model.getValueAt(row, 2).toString());
                pesanan.setBerat(Integer.parseInt(model.getValueAt(row, 3).toString()));
                pesanan.setHarga(Integer.parseInt(model.getValueAt(row, 4).toString()));
            }
        });

        view.getTbLayanan().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int row = view.getTbLayanan().getSelectedRow();
                TableModel model = view.getTbLayanan().getModel();
                layanan.setIdLayanan(model.getValueAt(row, 1).toString());
                layanan.setNama(model.getValueAt(row, 2).toString());
                layanan.setHarga(Integer.parseInt(model.getValueAt(row, 3).toString()));
            }
        });
    }

    private void handleFillCustomer() {
        try {
            CustomerModel selectedCustomer = (CustomerModel) view.getCmbCustomer().getSelectedItem();
            if (selectedCustomer != null) {
                String customerId = selectedCustomer.getIdCustomer();
                CustomerModel cust = CustomerDAO.getCustomerByIdCustomer(customerId);
                view.getTxtAlamat2().setText(cust.getAddress());
                view.getTxtTelepon2().setText(cust.getPhone());
            }
        } catch (Exception e) {
        }
    }

    private void handleOrder() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            LayananModel selectedLayanan = (LayananModel) view.getCmbLayanan().getSelectedItem();
            String idLayanan = selectedLayanan.getIdLayanan();
            String formattedDate = dateFormat.format(view.getcalTanggal().getDate());
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
                        hitungHarga((Integer) view.getSpnBerat().getValue()),
                        formattedDate,
                        PesananUtil.convertTo24HourFormat((String) view.getSpnJam().getValue()));

                PesananDAO.insertPesanan(newOrder);

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
                        PesananUtil.convertTo24HourFormat((String) view.getSpnJam().getValue()));

                PesananDAO.insertPesanan(newOrder);
            }

            JOptionPane.showMessageDialog(view, "Order successfully placed");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        showTabelPesanan();
    }

    private void handleAddLayanan() {
        try {
            tambahLayanan lyn = new tambahLayanan();
            lyn.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleDeleteLayanan() {
        try {
            if (!pesanan.equals(null)) {
                int response = JOptionPane.showConfirmDialog(view, "Do you really want to delete ?", "Confirm",
                        JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    LayananDAO.deleteLayananById(layanan.getIdLayanan());
                    JOptionPane.showMessageDialog(view, "The information was successfully updated");
                    showTabelLayanan();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Please choose an account");
        }
    }

    private void handleDeletePesanan() {
        try {
            if (!pesanan.getIdPesanan().equals(null)) {
                int response = JOptionPane.showConfirmDialog(view, "Do you really want to delete ?", "Confirm",
                        JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    PesananDAO.deletePesananById(pesanan.getIdPesanan());
                    showTabelPesanan();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Please choose an account");
        }
    }

    private void handleEditLayanan() {
        try {
            if (!layanan.getIdLayanan().equals(null)) {
                EditLayananController edit = new EditLayananController(layanan);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Layanan Belum Di pilih");
        }
    }

    private void handleLogout() {
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

    private void handleSearchLayanan() {
        int n = 0;
        String[] kolom = { "NO", "Id_layanan", "Nama", "Harga" };
        DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
        List<LayananModel> ListLayanan = LayananDAO.searchLayanan(view.getTxtSearchLayanan().getText());

        for (LayananModel layanan : ListLayanan) {
            Object[] row = {
                    ++n,
                    layanan.getIdLayanan(),
                    layanan.getNama(),
                    layanan.getHarga()
            };
            tb1.addRow(row);
        }

        view.getTbLayanan().setModel(tb1);
    }

    private void handleSearchPesanan() {
        int n = 0;
        String[] kolom = { "NO", "id_pesanan", "id_customer", "berat", "Harga", "Tanggal Ambil", "Jam Ambil" };
        DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
        String search = view.getTxtSearchPesanan().getText();

        ArrayList<PesananModel> pesananList = PesananDAO.searchPesanan(search);

        for (PesananModel pesanan : pesananList) {
            n++;
            String id_pesanan = pesanan.getIdPesanan();
            String id_customer = pesanan.getIdCustomer();
            int berat = pesanan.getBerat();
            int harga = pesanan.getHarga();
            String tanggalAmbil = pesanan.getTanggalSelesai();
            String jamAmbil = pesanan.getJamSelesai();
            tb1.addRow(new String[] { String.valueOf(n), id_pesanan, id_customer, String.valueOf(berat),
                    String.valueOf(harga), tanggalAmbil, jamAmbil });
        }
        view.getTabelPesanan().setModel(tb1);
    }

    private void handleUpdatePesanan() {
        try {
            if (!pesanan.getIdPesanan().equals(null)) {
                UpdateOrderController updt = new UpdateOrderController(pesanan);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Layanan Belum Di pilih");
        }
    }

    private void renderSpinerJam() {
        view.getSpnJam().setModel(new SpinerTimeModel());

        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) view.getSpnJam().getEditor();
        editor.getTextField().setHorizontalAlignment(JTextField.CENTER);
    }

    private int hitungHarga(int berat) {
        LayananModel selectedLayanan = (LayananModel) view.getCmbLayanan().getSelectedItem();
        String idLayanan = selectedLayanan.getIdLayanan();
        int harga = LayananDAO.getHargaById(idLayanan);
        return berat * harga;
    }

    public void showTabelLayanan() {
        int n = 0;
        String[] kolom = { "NO", "Id_layanan", "Nama", "Harga" };
        DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
        List<LayananModel> ListLayanan = LayananDAO.getAllLayanan();

        for (LayananModel layanan : ListLayanan) {
            Object[] row = {
                    ++n,
                    layanan.getIdLayanan(),
                    layanan.getNama(),
                    layanan.getHarga()
            };
            tb1.addRow(row);
        }

        view.getTbLayanan().setModel(tb1);
    }

    public void showTabelPesanan() {
        int n = 0;
        String[] kolom = { "NO", "id_pesanan", "id_customer", "berat", "Harga", "Tanggal Selesai", "Jam Selesai" };
        DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
        try {
            ArrayList<PesananModel> pesananList = PesananDAO.getAllPesanan();
            for (PesananModel pesanan : pesananList) {
                n++;
                String id_pesanan = pesanan.getIdPesanan();
                String id_customer = pesanan.getIdCustomer();
                int berat = pesanan.getBerat();
                int harga = pesanan.getHarga();
                String tanggalAmbil = pesanan.getTanggalSelesai();
                String jamAmbil = pesanan.getJamSelesai();
                tb1.addRow(new String[] { String.valueOf(n), id_pesanan, id_customer, String.valueOf(berat),
                        String.valueOf(harga), tanggalAmbil, jamAmbil });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.getTabelPesanan().setModel(tb1);
    }

    private void renderCbLayanan() {
        List<LayananModel> listLayanan = LayananDAO.getAllLayanan();
        DefaultComboBoxModel<LayananModel> model = new DefaultComboBoxModel<>();

        for (LayananModel layanan : listLayanan) {
            model.addElement(layanan);
        }

        view.getCmbLayanan().setModel(model);
    }

    private void renderCbCustomer() {
        try {
            DefaultComboBoxModel<CustomerModel> model = new DefaultComboBoxModel<>();
            List<CustomerModel> ListCustomer = CustomerDAO.getAllCustomers();
            for (CustomerModel customer : ListCustomer) {
                model.addElement(customer);
            }
            view.getCmbCustomer().setModel(model);
            view.getCmbCustomer().setSelectedIndex(0);
            view.getRbCustomerBaru().setSelected(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    @Override
    public void stateChanged(ChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}