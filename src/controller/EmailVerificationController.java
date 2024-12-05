package controller;

import dao.AkunDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import model.AkunModel;

import view.auth.EmailVerification;

public class EmailVerificationController extends MouseAdapter {
    private final EmailVerification view;
    private AkunModel akun;

    public EmailVerificationController(AkunModel akun) {
        view = new EmailVerification();
        this.akun = akun;
        view.setVisible(true);
        addEvents();
    }

    public void addEvents() {
        view.getBtnCheck().addActionListener(e -> CekKode());
        view.getSendEmail().addActionListener(e -> sendEmail() );
        view.getLbLogin().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openLoginPanel();
            }
        });
    }
    
    public static String generateRandomNumberCode(int length) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // Menghasilkan angka 0-9
            code.append(digit);
        }

        return code.toString();
    }
    
    private void sendEmail(){
        String code = generateRandomNumberCode(6);
        util.EmailVerification.sendEmail(akun.getEmail(),code);
        AkunDAO.updateVerificationCode(akun.getIdAkun(), code);
        JOptionPane.showMessageDialog(view, "Kode berhasil dikrim ke email" +akun.getEmail(), "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void CekKode() {
        String code = view.getTxtCode().getText();
        String idAkun = akun.getIdAkun();

        if (AkunDAO.isVerificationCodeValid(idAkun, code)) {
            // Jika kode valid, perbarui status verifikasi
            if (AkunDAO.updateVerificationStatus(idAkun)) {
                JOptionPane.showMessageDialog(view, "Verifikasi berhasil!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                view.dispose(); // Menutup jendela verifikasi
                openLoginPanel();
            } else {
                JOptionPane.showMessageDialog(view, "Gagal memperbarui status verifikasi.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Jika kode tidak valid
            JOptionPane.showMessageDialog(view, "Kode verifikasi salah atau tidak valid.", "Verifikasi Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openLoginPanel() {
        try {
            new LoginController();
            view.dispose();
        } catch (Exception ex) {
            Logger.getLogger(EmailVerificationController.class.getName()).log(
                    Level.SEVERE, "Error saat membuka halaman Login.", ex);

            JOptionPane.showMessageDialog(
                    view,
                    "Gagal membuka halaman Login. Silakan coba lagi.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
