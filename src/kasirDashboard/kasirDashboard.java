/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kasirDashboard;

import Main.loginPage;
import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import javax.swing.UIManager;
/**
 *
 * @author dzikr
 */
public class kasirDashboard extends javax.swing.JFrame {

    /**
     * Creates new form kasirDashboard
     */
    public kasirDashboard() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tittlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        bntRequestOrder = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnOrderList = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        orderPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        orderList = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LAUNDRY HUB - Kasir Dashboard");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setIconImages(null);
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(900, 600));
        setResizable(false);

        tittlePanel.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        tittlePanel.setForeground(java.awt.Color.white);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("KASIR DASHBOARD");

        javax.swing.GroupLayout tittlePanelLayout = new javax.swing.GroupLayout(tittlePanel);
        tittlePanel.setLayout(tittlePanelLayout);
        tittlePanelLayout.setHorizontalGroup(
            tittlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tittlePanelLayout.createSequentialGroup()
                .addGap(322, 322, 322)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(289, 289, 289))
        );
        tittlePanelLayout.setVerticalGroup(
            tittlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tittlePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        Menu.setBackground(new java.awt.Color(40, 40, 40));

        bntRequestOrder.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.background"));
        bntRequestOrder.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bntRequestOrder.setForeground(java.awt.Color.white);
        bntRequestOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/booking.png"))); // NOI18N
        bntRequestOrder.setText("Request Order");
        bntRequestOrder.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
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
        btnLogout.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
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
        btnOrderList.setText("Order List");
        btnOrderList.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOrderList, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
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
                .addGap(299, 299, 299)
                .addComponent(jLabel7)
                .addContainerGap(323, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout orderPanelLayout = new javax.swing.GroupLayout(orderPanel);
        orderPanel.setLayout(orderPanelLayout);
        orderPanelLayout.setHorizontalGroup(
            orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                .addContainerGap())
        );
        orderPanelLayout.setVerticalGroup(
            orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(411, Short.MAX_VALUE))
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
                .addGap(258, 258, 258)
                .addComponent(jLabel8)
                .addContainerGap(310, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout orderListLayout = new javax.swing.GroupLayout(orderList);
        orderList.setLayout(orderListLayout);
        orderListLayout.setHorizontalGroup(
            orderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                .addContainerGap())
        );
        orderListLayout.setVerticalGroup(
            orderListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(411, Short.MAX_VALUE))
        );

        mainPanel.add(orderList, "card3");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        mainPanel.add(orderList);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_btnOrderListActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            UIManager.put( "TextComponent.arc", 10 );
            UIManager.put( "Button.arc", 10 );
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
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOrderList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel orderList;
    private javax.swing.JPanel orderPanel;
    private javax.swing.JPanel tittlePanel;
    // End of variables declaration//GEN-END:variables
}
