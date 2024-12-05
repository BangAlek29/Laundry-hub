/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author dzikr
 */
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailVerification {
        
    public static void sendEmail(String recipientEmail, String code) {
        // Konfigurasi properti untuk SMTP Gmail
        String host = "smtp.gmail.com";
        final String senderEmail = "senggolbacokgym@gmail.com"; // Email LaundryHub
        final String senderPassword = "ybhjnnmphngemwkp"; // Ganti dengan App Password

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        // Membuat sesi email
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Membuat pesan email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Email Verification");
            message.setText("Dear User,\n\nYour verification code is: " + code);

            // Mengirim email
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    }
}
