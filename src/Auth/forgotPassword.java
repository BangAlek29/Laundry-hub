package Auth;

import Main.loginPage;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax  .swing.JOptionPane;
import javax.swing.UIManager;
import koneksiDatabase.Connect;

/**
 * Forgot Password page for the Laundry Hub application.
 * Allows users to reset their password by verifying their username.
 */
public class forgotPassword extends javax.swing.JFrame {

    /**
     * Creates new form ForgotPassword.
     */
    public forgotPassword() {
        initComponents();
    }

    /**
     * Initialize the form components.
     * WARNING: Do NOT modify this code manually.
     * The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        javax.swing.JButton btnContinue = new javax.swing.JButton();
        javax.swing.JLabel lblBackToLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LAUNDRY HUB - Forgot Password");
        setResizable(false);

        // Panel Settings
        jPanel1.setBackground(new java.awt.Color(60, 60, 60));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 350));

        // Title Label
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setForeground(new java.awt.Color(187, 187, 187));
        jLabel1.setText("FORGOT PASSWORD");

        // Username Label
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel2.setForeground(new java.awt.Color(187, 187, 187));
        jLabel2.setText("Username");

        // Username Text Field
        txtUsername.setBackground(new java.awt.Color(60, 60, 60));
        txtUsername.setForeground(new java.awt.Color(187, 187, 187));
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // Continue Button
        btnContinue.setBackground(new java.awt.Color(99, 102, 241));
        btnContinue.setText("Continue");
        btnContinue.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnContinue.addActionListener(this::btnContinueActionPerformed);

        // Back to Login Label
        lblBackToLogin.setForeground(new java.awt.Color(99, 102, 241));
        lblBackToLogin.setText("Back to login page?");
        lblBackToLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBackToLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackToLoginMouseClicked(evt);
            }
        });

        // Layout for Panel
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBackToLogin)
                    .addComponent(btnContinue, javax.swing.GroupLayout.Alignment.CENTER))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblBackToLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnContinue)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        // Main Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                .addContainerGap(238, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                .addContainerGap(175, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Statement stmt = Connect.configDB().createStatement();
            String query = "SELECT * FROM akun WHERE username = '" + txtUsername.getText() + "';";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                resetPassword resetPasswordPage = new resetPassword();
                resetPasswordPage.labelnama.setText(txtUsername.getText().toUpperCase());
                resetPasswordPage.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username Not Found");
                txtUsername.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void lblBackToLoginMouseClicked(java.awt.event.MouseEvent evt) {
        try {
            loginPage login = new loginPage();
            login.setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(forgotPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            new forgotPassword().setVisible(true);
        });
    }

    // Variables declaration
    private javax.swing.JTextField txtUsername;
    // End of variables declaration
}