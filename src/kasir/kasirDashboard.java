/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kasir;

import Auth.loginPage;
import com.formdev.flatlaf.FlatDarkLaf;
import com.toedter.calendar.JDateChooser;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import koneksiDatabase.Connect;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.*;
import java.text.ParseException;

/**
 *
 * @author dzikr
 */
public class kasirDashboard extends javax.swing.JFrame {

    /**
     * Creates new form kasirDashboard
     */
    String id_layanan,nama,harga,berat,hargaTotal,id_pesanan;
    public kasirDashboard() {
        initComponents();
        renderCbLayanan();
        renderCbCustomer();
        renderSpinerJam();
        setLocationRelativeTo(null);
    }
//getter komponen
    
    public void addActionListener(KasirController aThis){
        orderButton.addActionListener(aThis);
        btnAddLayanan.addActionListener(aThis);
        btnDeleteLayanan.addActionListener(aThis);
        btnDeletePesanan.addActionListener(aThis);
        btnEditLayanan.addActionListener(aThis);
        btnLayanan.addActionListener(aThis);
        btnLogout.addActionListener(aThis);
        btnOrderList.addActionListener(aThis);
        btnRefreshLayanan.addActionListener(aThis);
        btnRefreshPesanan.addActionListener(aThis);
        btnSearchLayanan.addActionListener(aThis);
        btnSearchPesanan.addActionListener(aThis);
        btnUpdatePesanan.addActionListener(aThis);
        bntRequestOrder.addActionListener(aThis);
        tabelPesanan.addMouseListener(aThis);
    }
    
    public JButton getorderButton() {
        return orderButton;
    }

    public JButton getBtnAddLayanan() {
        return btnAddLayanan;
    }

    public JButton getBtnDeleteLayanan() {
        return btnDeleteLayanan;
    }

    public JButton getBtnDeletePesanan() {
        return btnDeletePesanan;
    }

    public JButton getBtnEditLayanan() {
        return btnEditLayanan;
    }

    public JButton getBtnLayanan() {
        return btnLayanan;
    }

    public JButton getBtnLogout() {
        return btnLogout;
    }

    public JButton getBtnOrderList() {
        return btnOrderList;
    }

    public JButton getBtnRefreshLayanan() {
        return btnRefreshLayanan;
    }

    public JButton getBtnRefreshPesanan() {
        return btnRefreshPesanan;
    }

    public JButton getBtnSearchLayanan() {
        return btnSearchLayanan;
    }

    public JButton getBtnSearchPesanan() {
        return btnSearchPesanan;
    }

    public JButton getBtnUpdateLayanan() {
        return btnUpdatePesanan;
    }
    public JComboBox<String> getCmbCustomer() {
        return cmbCustomer;
    }

    public JComboBox<String> getCmbLayanan() {
        return cmbLayanan;
    }
    public JPanel getCustomerBaruPanel() {
        return customerBaruPanel;
    }

    public JPanel getCustomerLamaPanel() {
        return customerLamaPanel;
    }
    
    public JButton getBntOrderRequest(){
        return bntRequestOrder;
    }
    public JPanel getMainPanel(){
        return mainPanel;
    }
    public JPanel getOrderPanel(){
        return orderPanel;
    }
    public JLabel getJamLabel() {
    return jamLabel;
}

public JLabel getLabelHarga() {
    return labelHarga;
}

public JPanel getLayananPanel() {
    return layananPanel;
}

public JButton getOrderButton() {
    return orderButton;
}

public JPanel getOrderList() {
    return orderList;
}

public JPanel getPnlCustomer() {
    return pnlCustomer;
}

public ButtonGroup getRbCustomer() {
    return rbCustomer;
}

public JRadioButton getRbCustomerBaru() {
    return rbCustomerBaru;
}

public JRadioButton getRbCustomerLama() {
    return rbCustomerLama;
}

public JSpinner getSpnBerat() {
    return spnBerat;
}

public JSpinner getSpnJam() {
    return spnJam;
}

public JTable getTabelPesanan() {
    return tabelPesanan;
}

public JTable getTbLayanan() {
    return tbLayanan;
}

public JPanel getTittlePanel() {
    return tittlePanel;
}

public JTextArea getTxtAlamat() {
    return txtAlamat;
}

public JTextArea getTxtAlamat2() {
    return txtAlamat2;
}

public JTextField getTxtNama() {
    return txtNama;
}

public JTextField getTxtSearchLayanan() {
    return txtSearchLayanan;
}

public JTextField getTxtSearchPesanan() {
    return txtSearchPesanan;
}

public JTextField getTxtTelepon() {
    return txtTelepon;
}

public JTextField getTxtTelepon2() {
    return txtTelepon2;
}

public JDateChooser getcalTanggal(){
    return calTanggal;
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbCustomer = new javax.swing.ButtonGroup();
        tittlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        bntRequestOrder = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnLayanan = new javax.swing.JButton();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAlamat2 = new javax.swing.JTextArea();
        cmbCustomer = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        orderList = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPesanan = new javax.swing.JTable();
        btnUpdatePesanan = new javax.swing.JButton();
        txtSearchPesanan = new javax.swing.JTextField();
        btnSearchPesanan = new javax.swing.JButton();
        btnRefreshPesanan = new javax.swing.JButton();
        btnDeletePesanan = new javax.swing.JButton();
        layananPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbLayanan = new javax.swing.JTable();
        btnAddLayanan = new javax.swing.JButton();
        btnEditLayanan = new javax.swing.JButton();
        btnSearchLayanan = new javax.swing.JButton();
        txtSearchLayanan = new javax.swing.JTextField();
        btnRefreshLayanan = new javax.swing.JButton();
        btnDeleteLayanan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LAUNDRY HUB - Kasir Dashboard");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setIconImages(null);
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);

        tittlePanel.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        tittlePanel.setForeground(java.awt.Color.white);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("KASIR DASHBOARD");

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
                .addGap(191, 191, 191)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tittlePanelLayout.setVerticalGroup(
            tittlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tittlePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tittlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel17))
                .addContainerGap())
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

        btnLogout.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setForeground(java.awt.Color.white);
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/icons8-logout-30.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.setAlignmentY(0.0F);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLogout.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnLayanan.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        btnLayanan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLayanan.setForeground(java.awt.Color.white);
        btnLayanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/shopping-list.png"))); // NOI18N
        btnLayanan.setText("Layanan");
        btnLayanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLayanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLayanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnOrderList.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Yellow"));
        btnOrderList.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnOrderList.setForeground(java.awt.Color.white);
        btnOrderList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/shopping-list.png"))); // NOI18N
        btnOrderList.setText("Order List");
        btnOrderList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOrderList.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOrderList.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntRequestOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLayanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(btnLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(286, 286, 286)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

        rbCustomer.add(rbCustomerBaru);
        rbCustomerBaru.setText("Baru");
        rbCustomerBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCustomerBaruActionPerformed(evt);
            }
        });

        rbCustomer.add(rbCustomerLama);
        rbCustomerLama.setText("Lama");
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
                .addGap(10, 10, 10)
                .addGroup(customerBaruPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(customerBaruPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 2, Short.MAX_VALUE))
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

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        txtAlamat2.setColumns(20);
        txtAlamat2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAlamat2.setRows(5);
        jScrollPane4.setViewportView(txtAlamat2);

        cmbCustomer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbCustomer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCustomerActionPerformed(evt);
            }
        });

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
                .addGroup(customerLamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbCustomer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(txtTelepon2))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        customerLamaPanelLayout.setVerticalGroup(
            customerLamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerLamaPanelLayout.createSequentialGroup()
                .addGroup(customerLamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cmbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(customerLamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtTelepon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(customerLamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlCustomer.add(customerLamaPanel, "card3");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/mesincuci__2_-removebg-preview.png"))); // NOI18N

        javax.swing.GroupLayout orderPanelLayout = new javax.swing.GroupLayout(orderPanel);
        orderPanel.setLayout(orderPanelLayout);
        orderPanelLayout.setHorizontalGroup(
            orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderPanelLayout.createSequentialGroup()
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE))
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
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
                                    .addComponent(spnBerat, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addGap(0, 173, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orderPanelLayout.createSequentialGroup()
                    .addContainerGap(557, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)))
        );
        orderPanelLayout.setVerticalGroup(
            orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rbCustomerBaru)
                    .addComponent(rbCustomerLama))
                .addGap(10, 10, 10)
                .addComponent(pnlCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(spnBerat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jamLabel)
                        .addGap(10, 10, 10)
                        .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelHarga)
                            .addComponent(jLabel11))
                        .addGap(10, 10, 10)
                        .addComponent(orderButton)
                        .addContainerGap(41, Short.MAX_VALUE))
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(10, 10, 10)
                        .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(calTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(10, 10, 10)
                        .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(spnJam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orderPanelLayout.createSequentialGroup()
                    .addContainerGap(376, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)))
        );

        mainPanel.add(orderPanel, "card2");

        orderList.setBackground(new java.awt.Color(40, 40, 40));

        jPanel4.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.background"));
        jPanel4.setForeground(java.awt.Color.white);
        jPanel4.setPreferredSize(new java.awt.Dimension(443, 45));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("ORDER LIST");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabelPesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Nama", "No Telepon", "Tipe Layanan", "Berat", "Harga", "Tanggal", "Jam"
            }
        ));
        tabelPesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPesananMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelPesanan);

        btnUpdatePesanan.setText("Update");

        btnSearchPesanan.setText("Search");
        btnSearchPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchPesananActionPerformed(evt);
            }
        });

        btnRefreshPesanan.setText("Refresh");

        btnDeletePesanan.setText("Delete");

        javax.swing.GroupLayout orderListLayout = new javax.swing.GroupLayout(orderList);
        orderList.setLayout(orderListLayout);
        orderListLayout.setHorizontalGroup(
            orderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(orderListLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(orderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(orderListLayout.createSequentialGroup()
                        .addComponent(txtSearchPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchPesanan))
                    .addGroup(orderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(orderListLayout.createSequentialGroup()
                            .addComponent(btnRefreshPesanan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnDeletePesanan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdatePesanan))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        orderListLayout.setVerticalGroup(
            orderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(orderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchPesanan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(orderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdatePesanan)
                    .addComponent(btnRefreshPesanan)
                    .addComponent(btnDeletePesanan))
                .addGap(27, 27, 27))
        );

        mainPanel.add(orderList, "card3");

        layananPanel.setBackground(new java.awt.Color(40, 40, 40));

        jPanel5.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.background"));
        jPanel5.setForeground(java.awt.Color.white);
        jPanel5.setPreferredSize(new java.awt.Dimension(443, 45));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(java.awt.Color.white);
        jLabel13.setText("LAYANAN");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(271, 271, 271)
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

        tbLayanan.setModel(new javax.swing.table.DefaultTableModel(
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
        tbLayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLayananMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbLayanan);

        btnAddLayanan.setText("Tambah Layanan");

        btnEditLayanan.setText("Edit Layanan");

        btnSearchLayanan.setText("Search");

        btnRefreshLayanan.setText("Refresh");

        btnDeleteLayanan.setText("Delete Layanan");

        javax.swing.GroupLayout layananPanelLayout = new javax.swing.GroupLayout(layananPanel);
        layananPanel.setLayout(layananPanelLayout);
        layananPanelLayout.setHorizontalGroup(
            layananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layananPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layananPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layananPanelLayout.createSequentialGroup()
                        .addComponent(btnRefreshLayanan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeleteLayanan)
                        .addGap(10, 10, 10)
                        .addComponent(btnAddLayanan)
                        .addGap(10, 10, 10)
                        .addComponent(btnEditLayanan))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layananPanelLayout.createSequentialGroup()
                        .addComponent(txtSearchLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearchLayanan)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layananPanelLayout.setVerticalGroup(
            layananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layananPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearchLayanan)
                    .addComponent(txtSearchLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddLayanan)
                    .addComponent(btnEditLayanan)
                    .addComponent(btnDeleteLayanan)
                    .addComponent(btnRefreshLayanan))
                .addGap(32, 32, 32))
        );

        mainPanel.add(layananPanel, "card4");

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
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
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
    
    private void cmbLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLayananActionPerformed
        // TODO add your handling code here:
        renderHarga();
    }//GEN-LAST:event_cmbLayananActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void tbLayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLayananMouseClicked
        // TODO add your handling code here:
        int row = tbLayanan.getSelectedRow();
        TableModel model = tbLayanan.getModel();
        this.id_layanan = model.getValueAt(row, 1).toString();
        this.nama = model.getValueAt(row, 2).toString();
        this.harga = model.getValueAt(row, 3).toString();
    }//GEN-LAST:event_tbLayananMouseClicked
    
    
    private void tabelPesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPesananMouseClicked
        // TODO add your handling code here:
        int row = tabelPesanan.getSelectedRow();
        TableModel model = tabelPesanan.getModel();
        this.id_pesanan = model.getValueAt(row, 1).toString();
        this.berat = model.getValueAt(row, 3).toString();
        this.hargaTotal = model.getValueAt(row,4).toString();
    }//GEN-LAST:event_tabelPesananMouseClicked

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
    }//GEN-LAST:event_rbCustomerLamaActionPerformed

    private void cmbCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCustomerActionPerformed
        // TODO add your handling code here:
        String alamat = "", noTelp = "";
        String SelectedName = (String) cmbCustomer.getSelectedItem();
        try {
            Statement stm = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM customer WHERE nama = '" + SelectedName + "';";
            ResultSet rs = stm.executeQuery(query);
            
            if(rs.next()){
                noTelp = rs.getString("telpon");
                alamat = rs.getString("alamat");
            }
            txtAlamat2.setText(alamat);
            txtTelepon2.setText(noTelp);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbCustomerActionPerformed

    private void spnBeratStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnBeratStateChanged
        // TODO add your handling code here:
        renderHarga();
    }//GEN-LAST:event_spnBeratStateChanged

    private void spnJamStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnJamStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_spnJamStateChanged

    private void btnSearchPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchPesananActionPerformed
        // TODO add your handling code here:
        int n = 0;
        String [] kolom = {"NO", "id_pesanan" ,"id_customer", "berat" ,"Harga", "Tanggal Ambil", "Jam Ambil"};
        DefaultTableModel tb1 = new DefaultTableModel(null, kolom);
        String search = txtSearchPesanan.getText();
        try {
            Statement stmt = (Statement) Connect.configDB().createStatement();
            String query = "SELECT * FROM pesanan WHERE id_pesanan = '" + search + "';";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                n++;
                String id_pesanan = rs.getString("id_pesanan");
                String id_customer = rs.getString("id_customer");
                String id_layanan = rs.getString("id_layanan");
                int berat = rs.getInt("berat");
                int harga = rs.getInt("harga");
                String tanggalAmbil = rs.getString("tanggalSelesai");
                String jamAmbil = rs.getString("jamSelesai");
                tb1.addRow(new String[] {String.valueOf(n), id_pesanan, id_customer , String.valueOf(berat) , String.valueOf(harga), tanggalAmbil, jamAmbil});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabelPesanan.setModel(tb1);
    }//GEN-LAST:event_btnSearchPesananActionPerformed

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
    
    private void renderCbCustomer(){
        try {
            Statement stmt = (Statement) Connect.configDB().createStatement();
            String query = "SELECT nama FROM customer;";
            ResultSet rs = stmt.executeQuery(query);
            
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            model.addElement("Pilih Customer");
            
            while (rs.next()) {
                model.addElement(rs.getString("nama"));
            }
            
            cmbCustomer.setModel(model);
            cmbCustomer.setSelectedIndex(0);
            rbCustomerBaru.setSelected(true);
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
                new kasirDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Menu;
    private javax.swing.JButton bntRequestOrder;
    private javax.swing.JButton btnAddLayanan;
    private javax.swing.JButton btnDeleteLayanan;
    private javax.swing.JButton btnDeletePesanan;
    private javax.swing.JButton btnEditLayanan;
    private javax.swing.JButton btnLayanan;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOrderList;
    private javax.swing.JButton btnRefreshLayanan;
    private javax.swing.JButton btnRefreshPesanan;
    private javax.swing.JButton btnSearchLayanan;
    private javax.swing.JButton btnSearchPesanan;
    private javax.swing.JButton btnUpdatePesanan;
    private com.toedter.calendar.JDateChooser calTanggal;
    private javax.swing.JComboBox<String> cmbCustomer;
    private javax.swing.JComboBox<String> cmbLayanan;
    private javax.swing.JPanel customerBaruPanel;
    private javax.swing.JPanel customerLamaPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel jamLabel;
    private javax.swing.JLabel labelHarga;
    private javax.swing.JPanel layananPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton orderButton;
    private javax.swing.JPanel orderList;
    private javax.swing.JPanel orderPanel;
    private javax.swing.JPanel pnlCustomer;
    private javax.swing.ButtonGroup rbCustomer;
    private javax.swing.JRadioButton rbCustomerBaru;
    private javax.swing.JRadioButton rbCustomerLama;
    private javax.swing.JSpinner spnBerat;
    private javax.swing.JSpinner spnJam;
    private javax.swing.JTable tabelPesanan;
    private javax.swing.JTable tbLayanan;
    private javax.swing.JPanel tittlePanel;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextArea txtAlamat2;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtSearchLayanan;
    private javax.swing.JTextField txtSearchPesanan;
    private javax.swing.JTextField txtTelepon;
    private javax.swing.JTextField txtTelepon2;
    // End of variables declaration//GEN-END:variables
}
