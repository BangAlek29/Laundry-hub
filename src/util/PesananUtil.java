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
        String[] parts = time12Hour.split(" ");
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
                model.addElement(customer);
            }
            return model;
    }
}
