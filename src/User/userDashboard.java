/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package User;

import Main.loginPage;
import com.formdev.flatlaf.FlatDarkLaf;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.UIManager;
import koneksiDatabase.Connect;
import javax.swing.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author dzikr
 */
public class userDashboard extends javax.swing.JFrame {

    /**
     * Creates new form kasirDashboard
     */
     String id_user;
    public userDashboard(String id_user) {
        this.id_user = id_user;
        initComponents();
        setLocationRelativeTo(null);
        renderSpinerJam();
        renderCbLayanan();
        showTabelRiwayat();
    }
    
    private void showTabelRiwayat(){
        int n = 0;
        String [] kolom = {"NO", "id_pesanan" , "berat" ,"Harga", "Tanggal Ambil", "Jam Ambil"};
        DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
        try {
            Statement stmt = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM pesanan  WHERE id_user = '"+this.id_user+"'";
            ResultSet rs = stmt.executeQuery(query);

            
            while(rs.next()){
                n++;
                String id_pesanan = rs.getString("id_pesanan");
                int berat = rs.getInt("berat");
                int harga = rs.getInt("harga");
                String tanggalAmbil = rs.getString("tanggalSelesai");
                String jamAmbil = rs.getString("jamSelesai");
                tb1.addRow(new String[] {String.valueOf(n), id_pesanan, String.valueOf(berat) , String.valueOf(harga), tanggalAmbil, jamAmbil});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jTable2.setModel(tb1);
    }
    
    public class SpinnerTimeModel extends AbstractSpinnerModel {
        private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        private Calendar calendar;
        private Calendar startTime;
        private Calendar endTime;
    
        public SpinnerTimeModel() {
            calendar = Calendar.getInstance();

            startTime = Calendar.getInstance();
            startTime.set(Calendar.HOUR_OF_DAY, 8);
            startTime.set(Calendar.MINUTE, 0);

            endTime = Calendar.getInstance();
            endTime.set(Calendar.HOUR_OF_DAY, 21);
            endTime.set(Calendar.MINUTE, 0);

            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 0);
        }

        @Override
        public Object getValue() {
            return timeFormat.format(calendar.getTime());
        }

        @Override
        public void setValue(Object value) {
            if (value instanceof String) {
                try {
                    calendar.setTime(timeFormat.parse((String) value));
                    fireStateChanged();
                } catch (ParseException e) {
                    e.printStackTrace();
                    
                }
            }
        }

        @Override
        public Object getNextValue() {
            Calendar next = (Calendar) calendar.clone();
            next.add(Calendar.MINUTE, 30);

            if (next.after(endTime)) {
                return null;
            }
            return timeFormat.format(next.getTime());
        }

        @Override
        public Object getPreviousValue() {
            Calendar prev = (Calendar) calendar.clone();
            prev.add(Calendar.MINUTE, -30);

            if (prev.before(startTime)) {
                return null;
            }
            return timeFormat.format(prev.getTime());
        }
    }
    
    private void renderSpinerJam() {
        spnJam.setModel(new SpinnerTimeModel());

        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spnJam.getEditor();
        editor.getTextField().setHorizontalAlignment(JTextField.CENTER);

    }
    
    private void renderCbLayanan(){
        try {
            Statement stmt = (Statement) Connect.configDB().createStatement();
            String query = "SELECT nama FROM layanan;";
            ResultSet rs = stmt.executeQuery(query);
            
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            
            while (rs.next()) {
                model.addElement(rs.getString("nama"));
            }
            
            cmbLayanan.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editLoginForm1 = new admin.editLoginForm();
        buttonGroup1 = new javax.swing.ButtonGroup();
        tittlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        bntRequestOrder = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnOrderList = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        orderPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbLayanan = new javax.swing.JComboBox<>();
        jamLabel = new javax.swing.JLabel();
        orderButton = new javax.swing.JButton();
        calTanggal = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        labelHarga = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rbCustomerBaru = new javax.swing.JRadioButton();
        rbCustomerLama = new javax.swing.JRadioButton();
        spnBerat = new javax.swing.JSpinner();
        spnJam = new javax.swing.JSpinner();
        pnlCustomer = new javax.swing.JPanel();
        customerBaruPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelepon = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        customerLamaPanel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTelepon2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtNama1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAlamat2 = new javax.swing.JTextArea();
        RiwayatPemesananPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        searchLayananButton = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LAUNDRY HUB - Kasir Dashboard");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setIconImages(null);
        setLocationByPlatform(true);
        setResizable(false);

        tittlePanel.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        tittlePanel.setForeground(java.awt.Color.white);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("USER DASHBOARD");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/laundry.png"))); // NOI18N
        jLabel17.setText("Laundry - Hub");

        javax.swing.GroupLayout tittlePanelLayout = new javax.swing.GroupLayout(tittlePanel);
        tittlePanel.setLayout(tittlePanelLayout);
        tittlePanelLayout.setHorizontalGroup(
            tittlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tittlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(205, 205, 205)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tittlePanelLayout.setVerticalGroup(
            tittlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tittlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tittlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Menu.setBackground(new java.awt.Color(40, 40, 40));

        bntRequestOrder.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.background"));
        bntRequestOrder.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bntRequestOrder.setForeground(java.awt.Color.white);
        bntRequestOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/booking.png"))); // NOI18N
        bntRequestOrder.setText("Request Order");
        bntRequestOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntRequestOrder.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bntRequestOrder.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        bntRequestOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntRequestOrderActionPerformed(evt);
            }
        });

        btnLogout.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setForeground(java.awt.Color.white);
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/icons8-logout-30.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.setAlignmentY(0.0F);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLogout.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnOrderList.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Yellow"));
        btnOrderList.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnOrderList.setForeground(java.awt.Color.white);
        btnOrderList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/shopping-list.png"))); // NOI18N
        btnOrderList.setText("Riwayat");
        btnOrderList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOrderList.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOrderList.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnOrderList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntRequestOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOrderList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bntRequestOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnOrderList, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.setBackground(new java.awt.Color(40, 40, 40));
        mainPanel.setLayout(new java.awt.CardLayout());

        orderPanel.setBackground(new java.awt.Color(40, 40, 40));

        jPanel3.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.background"));
        jPanel3.setForeground(java.awt.Color.white);
        jPanel3.setPreferredSize(new java.awt.Dimension(443, 45));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("ORDER");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(428, 428, 428)
                .addComponent(jLabel7)
                .addContainerGap(392, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("BERAT CUCIAN(KG) :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("LAYANAN :");

        cmbLayanan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLayananActionPerformed(evt);
            }
        });

        jamLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jamLabel.setForeground(new java.awt.Color(255, 255, 255));
        jamLabel.setText("08.00 - 21.00");

        orderButton.setText("Order");
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText(" TANGGAL SELESAI:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("HARGA :");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText(" JAM SELESAI:");

        labelHarga.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelHarga.setForeground(new java.awt.Color(255, 255, 255));
        labelHarga.setText("Rp. 0 -,");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CUSTOMER :");

        buttonGroup1.add(rbCustomerBaru);
        rbCustomerBaru.setText("Baru");
        rbCustomerBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCustomerBaruActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbCustomerLama);
        rbCustomerLama.setText("Isi Otomatis");
        rbCustomerLama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCustomerLamaActionPerformed(evt);
            }
        });

        spnBerat.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnBeratStateChanged(evt);
            }
        });

        spnJam.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnJamStateChanged(evt);
            }
        });

        pnlCustomer.setBackground(new java.awt.Color(40, 40, 40));
        pnlCustomer.setLayout(new java.awt.CardLayout());

        customerBaruPanel.setBackground(new java.awt.Color(40, 40, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NAMA :");

        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NO TELEPON :");

        txtTelepon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ALAMAT :");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        txtAlamat.setColumns(20);
        txtAlamat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        javax.swing.GroupLayout customerBaruPanelLayout = new javax.swing.GroupLayout(customerBaruPanel);
        customerBaruPanel.setLayout(customerBaruPanelLayout);
        customerBaruPanelLayout.setHorizontalGroup(
            customerBaruPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerBaruPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customerBaruPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(customerBaruPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        customerBaruPanelLayout.setVerticalGroup(
            customerBaruPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerBaruPanelLayout.createSequentialGroup()
                .addGroup(customerBaruPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customerBaruPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(customerBaruPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customerBaruPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 71, Short.MAX_VALUE))
                    .addGroup(customerBaruPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pnlCustomer.add(customerBaruPanel, "card2");

        customerLamaPanel.setBackground(new java.awt.Color(40, 40, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("NAMA :");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("NO TELEPON :");

        txtTelepon2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("ALAMAT :");

        txtNama1.setEditable(false);
        txtNama1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNama1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNama1ActionPerformed(evt);
            }
        });

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        txtAlamat2.setColumns(20);
        txtAlamat2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAlamat2.setRows(5);
        jScrollPane4.setViewportView(txtAlamat2);

        javax.swing.GroupLayout customerLamaPanelLayout = new javax.swing.GroupLayout(customerLamaPanel);
        customerLamaPanel.setLayout(customerLamaPanelLayout);
        customerLamaPanelLayout.setHorizontalGroup(
            customerLamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerLamaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customerLamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(customerLamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customerLamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                        .addComponent(txtTelepon2))
                    .addComponent(txtNama1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        customerLamaPanelLayout.setVerticalGroup(
            customerLamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerLamaPanelLayout.createSequentialGroup()
                .addGroup(customerLamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtNama1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customerLamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtTelepon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(customerLamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pnlCustomer.add(customerLamaPanel, "card3");

        javax.swing.GroupLayout orderPanelLayout = new javax.swing.GroupLayout(orderPanel);
        orderPanel.setLayout(orderPanelLayout);
        orderPanelLayout.setHorizontalGroup(
            orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(orderPanelLayout.createSequentialGroup()
                    .addGap(222, 222, 222)
                    .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(orderPanelLayout.createSequentialGroup()
                            .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10)
                                .addComponent(jLabel12)
                                .addComponent(jLabel9)
                                .addComponent(jLabel5))
                            .addGap(18, 18, 18)
                            .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orderPanelLayout.createSequentialGroup()
                                    .addComponent(spnJam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jamLabel))
                                .addComponent(cmbLayanan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(calTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelHarga)
                                .addComponent(spnBerat)))
                        .addGroup(orderPanelLayout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(orderButton)
                                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(orderPanelLayout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbCustomerBaru)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbCustomerLama))
                                    .addComponent(pnlCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap(222, Short.MAX_VALUE)))
        );
        orderPanelLayout.setVerticalGroup(
            orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(536, Short.MAX_VALUE))
            .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(orderPanelLayout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(rbCustomerBaru)
                        .addComponent(rbCustomerLama))
                    .addGap(11, 11, 11)
                    .addComponent(pnlCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(spnBerat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orderPanelLayout.createSequentialGroup()
                            .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9))
                            .addGap(18, 18, 18)
                            .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(calTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addGap(18, 18, 18)
                            .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(spnJam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jamLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGap(18, 18, 18)
                    .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelHarga)
                        .addComponent(jLabel11))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(orderButton)
                    .addContainerGap(62, Short.MAX_VALUE)))
        );

        mainPanel.add(orderPanel, "card2");

        RiwayatPemesananPanel.setBackground(new java.awt.Color(40, 40, 40));

        jPanel5.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.background"));
        jPanel5.setForeground(java.awt.Color.white);
        jPanel5.setPreferredSize(new java.awt.Dimension(443, 45));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(java.awt.Color.white);
        jLabel13.setText("RIWAYAT PEMESANAN");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(358, 358, 358)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "id_layanan", "Nama Layanan", "Harga", "Deskripsi"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        searchLayananButton.setText("Search");

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RiwayatPemesananPanelLayout = new javax.swing.GroupLayout(RiwayatPemesananPanel);
        RiwayatPemesananPanel.setLayout(RiwayatPemesananPanelLayout);
        RiwayatPemesananPanelLayout.setHorizontalGroup(
            RiwayatPemesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RiwayatPemesananPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RiwayatPemesananPanelLayout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addGroup(RiwayatPemesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(RiwayatPemesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(RiwayatPemesananPanelLayout.createSequentialGroup()
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(searchLayananButton))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(91, 91, 91))
        );
        RiwayatPemesananPanelLayout.setVerticalGroup(
            RiwayatPemesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RiwayatPemesananPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addGroup(RiwayatPemesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchLayananButton)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        mainPanel.add(RiwayatPemesananPanel, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tittlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tittlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntRequestOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntRequestOrderActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(orderPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_bntRequestOrderActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int response =JOptionPane.showConfirmDialog(this, "Are you sure ?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            loginPage logout = null;
            try {
                logout = new loginPage();
            } catch (SQLException ex) {
                //Logger.getLogger(memberDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            logout.setVisible(true);
            this.dispose();
        }else{
            return;
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnOrderListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderListActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(RiwayatPemesananPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_btnOrderListActionPerformed

    private void cmbLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLayananActionPerformed
        // TODO add your handling code here:
        renderHarga();
    }//GEN-LAST:event_cmbLayananActionPerformed
    
    private void renderHarga(){
        int berat = Integer.parseInt(spnBerat.getValue().toString());
        
        int HargaTotal = hitungHarga(berat);
        
        String displayHarga = "Rp. " + formatHarga(HargaTotal) +" -,";
        
        labelHarga.setText(displayHarga);
    }
    private String formatHarga(int harga) {
        java.text.NumberFormat format = java.text.NumberFormat.getInstance();
        format.setGroupingUsed(true);
        
        return format.format(harga);
    }
    
    
    private String generateJam() {
        String jam = (String) spnJam.getValue();

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
    
    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderButtonActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (txtNama.getText().equals("") && txtTelepon.getText().equals("") && txtAlamat.getText().equals("") && ((Integer) spnBerat.getValue()) == 0 && calTanggal.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please fill out the form");
            return;
        }
        try {
            String formattedDate = dateFormat.format(calTanggal.getDate());
            String getNumber = generateUserNumber();
            String number = getNumber.replace("CST", "");

            Statement stmt = (Statement) Connect.configDB().createStatement();
            if(rbCustomerBaru.isSelected()){
                String query1 = "INSERT INTO pesanan VALUE ('"+generateIdPesanan()+"','"+generateIdUser()+"','"+getIdLayanan()+"','"+spnBerat.getValue()+"','"+hitungHarga(Integer.parseInt(spnBerat.getValue().toString()))+"','"+formattedDate+"','"+generateJam()+"')";
                String query2 = "INSERT INTO customer (number,id_user,nama,telpon,alamat) VALUE ('"+number+"','"+generateIdUser()+"','"+txtNama.getText()+"','"+txtTelepon.getText()+"','"+txtAlamat.getText()+"')";
                stmt.executeUpdate(query2);
                stmt.executeUpdate(query1);

            } else {
                String query = "INSERT INTO pesanan VALUE ('"+generateIdPesanan()+"','"+id_user+"','"+getIdLayanan()+"','"+spnBerat.getValue()+"','"+hitungHarga(Integer.parseInt(spnBerat.getValue().toString()))+"','"+formattedDate+"','"+generateJam()+"')";
                stmt.executeUpdate(query);
            }
            JOptionPane.showMessageDialog(this, "Order successfully placed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_orderButtonActionPerformed
    
    private String generateIdUser(){
        String tempID = "";
        try {
            Statement stm = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM customer ORDER BY id_user ASC";
            ResultSet rs = stm.executeQuery(query);
            
            while(rs.next()){
                tempID = rs.getString("id_user");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String ID = tempID.replace("CST", "");
        int IDint = Integer.parseInt(ID);
        
        return "CST" + (IDint+1);
    }
    
    private String generateIdPesanan(){
        String tempID = "";
        try {
            Statement stm = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM pesanan ORDER BY id_pesanan DESC LIMIT 1";
            ResultSet rs = stm.executeQuery(query);

            if(rs.next()){
                tempID = rs.getString("id_pesanan");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (tempID.isEmpty()) {
            return "LDR1";
        }

        String ID = tempID.replace("LDR", "");
        int IDint = Integer.parseInt(ID);

        return "LDR" + (IDint + 1);
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
    
    private String getIdLayanan(){
        String idLayanan = null;
        String namaLayanan = cmbLayanan.getSelectedItem().toString();
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
    
    private String generateUserNumber () throws SQLException { 
        int tempNumber = 0;
        try {
            Statement stmt = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM customer ORDER BY number ASC";

            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                tempNumber = rs.getInt("number");
            }

        } catch (Exception e) {
            System.err.println(e);
        }
            return "CST" + (tempNumber+1);
    }
    
    private void rbCustomerBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCustomerBaruActionPerformed
        // TODO add your handling code here:
        pnlCustomer.removeAll();
        pnlCustomer.repaint();
        pnlCustomer.revalidate();

        pnlCustomer.add(customerBaruPanel);
        pnlCustomer.repaint();
        pnlCustomer.revalidate();

    }//GEN-LAST:event_rbCustomerBaruActionPerformed

    private void rbCustomerLamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCustomerLamaActionPerformed
        // TODO add your handling code here:
        pnlCustomer.removeAll();
        pnlCustomer.repaint();
        pnlCustomer.revalidate();

        pnlCustomer.add(customerLamaPanel);
        pnlCustomer.repaint();
        pnlCustomer.revalidate();
        String alamat = "", noTelp = "",nama = "";
        try {
            Statement stm = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM customer WHERE id_user = '" + this.id_user + "';";
            ResultSet rs = stm.executeQuery(query);
            
            if(rs.next()){
                nama = rs.getString("nama");
                noTelp = rs.getString("telpon");
                alamat = rs.getString("alamat");
            }
            txtNama1.setText(nama);
            txtAlamat2.setText(alamat);
            txtTelepon2.setText(noTelp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_rbCustomerLamaActionPerformed

    private void spnBeratStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnBeratStateChanged
        // TODO add your handling code here:
        renderHarga();
    }//GEN-LAST:event_spnBeratStateChanged

    private void spnJamStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnJamStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_spnJamStateChanged

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void txtNama1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNama1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNama1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        showTabelRiwayat();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    
    private void hitungHarga(){
        try {
            Statement stmt = (Statement) Connect.configDB().createStatement();
            String query = "SELECT nama,harga FROM layanan;";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()){
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userDashboard("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel RiwayatPemesananPanel;
    private javax.swing.JButton bntRequestOrder;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOrderList;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser calTanggal;
    private javax.swing.JComboBox<String> cmbLayanan;
    private javax.swing.JPanel customerBaruPanel;
    private javax.swing.JPanel customerLamaPanel;
    private admin.editLoginForm editLoginForm1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel jamLabel;
    private javax.swing.JLabel labelHarga;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton orderButton;
    private javax.swing.JPanel orderPanel;
    private javax.swing.JPanel pnlCustomer;
    private javax.swing.JRadioButton rbCustomerBaru;
    private javax.swing.JRadioButton rbCustomerLama;
    private javax.swing.JButton searchLayananButton;
    private javax.swing.JSpinner spnBerat;
    private javax.swing.JSpinner spnJam;
    private javax.swing.JPanel tittlePanel;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextArea txtAlamat2;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNama1;
    private javax.swing.JTextField txtTelepon;
    private javax.swing.JTextField txtTelepon2;
    // End of variables declaration//GEN-END:variables
}
