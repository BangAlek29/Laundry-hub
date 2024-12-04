/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dao.CustomerDAO;
import dao.LayananDAO;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import model.CustomerModel;
import model.LayananModel;

/**
 *
 * @author dzikr
 */
public class PesananUtil {
    public static String convertTo24HourFormat(String time12Hour) {
        try {
            String[] parts = time12Hour.split(" ");
            String time = parts[0];   // Bagian "hh:mm"
            String period = parts[1].toUpperCase(); // AM/PM

            String[] timeParts = time.split(":");
            int hour = Integer.parseInt(timeParts[0]);
            String minute = timeParts[1];

            if (period.equals("PM") && hour != 12) {
                hour += 12; // Tambahkan 12 jika PM dan bukan jam 12
            } else if (period.equals("AM") && hour == 12) {
                hour = 0; // Ubah ke 0 jika AM dan jam 12
            }

            return String.format("%02d:%02d:00", hour, Integer.parseInt(minute));
        } catch (Exception e) {
            // Tangani kesalahan jika format input tidak valid
            return "Invalid time format";
        }
    }
    
    public static String convertTo12HourFormat(String time24Hour) {
        try {
            // Format waktu asli (HH:mm:ss)
            SimpleDateFormat timeFormat24Hour = new SimpleDateFormat("HH:mm:ss");
            // Format waktu baru (hh:mm a)
            SimpleDateFormat timeFormat12Hour = new SimpleDateFormat("hh:mm a");

            // Parse dari format asli ke objek Date
            Date time = timeFormat24Hour.parse(time24Hour);
            // Format ulang ke format baru
            return timeFormat12Hour.format(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid time format"; // Kembalikan pesan kesalahan jika parsing gagal
        }
    }

    public static String convertDate(String dateInOriginalFormat) {
        try {
            // Format tanggal asli (dd/MM/yyyy)
            SimpleDateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
            // Format tanggal baru (yyyy-MM-dd)
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            // Parse dari format asli ke objek Date
            Date date = originalFormat.parse(dateInOriginalFormat);
            // Format ulang ke format baru
            return newFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid date format"; // Kembalikan pesan kesalahan jika parsing gagal
        }
    }
    
        public static String convertDateReverse(String dateInNewFormat) {
        try {
            // Format tanggal asli (yyyy-MM-dd)
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Format tanggal baru (dd/MM/yyyy)
            SimpleDateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Parse dari format baru ke objek Date
            Date date = newFormat.parse(dateInNewFormat);
            // Format ulang ke format asli
            return originalFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid date format"; // Kembalikan pesan kesalahan jika parsing gagal
        }
    }

    public static String formatCurrency(int amount) {
        NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return rupiahFormat.format(amount);
    }
    
    public static DefaultComboBoxModel renderCbLayanan() {
        List<LayananModel> listLayanan = LayananDAO.getAllLayanan();
        DefaultComboBoxModel<LayananModel> model = new DefaultComboBoxModel<>();

        for (LayananModel layanan : listLayanan) {
            model.addElement(layanan);
        }

        return model;
    }
    
    public static DefaultComboBoxModel renderCbCustomer() {
            DefaultComboBoxModel<CustomerModel> model = new DefaultComboBoxModel<>();
            List<CustomerModel> ListCustomer = CustomerDAO.getAllCustomers();
            for (CustomerModel customer : ListCustomer) {
                if(customer.getIdAkun() != null){
                model.addElement(customer);
                }
            }
            return model;
    }
}
