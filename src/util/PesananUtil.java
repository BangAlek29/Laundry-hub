/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dao.CustomerDAO;
import dao.LayananDAO;
import java.util.List;
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


    public static String formatCurrency(int amount) {
        java.text.NumberFormat format = java.text.NumberFormat.getInstance();
        format.setGroupingUsed(true);
        return format.format(amount);
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
