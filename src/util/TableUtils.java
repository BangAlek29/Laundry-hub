package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class TableUtils {
    public static void setTableAkunSettings(JTable table) {
        // Mengatur lebar kolom
        TableColumnModel kolom = table.getColumnModel();
        kolom.getColumn(0).setPreferredWidth(5);   // Kolom "NO"
        kolom.getColumn(1).setPreferredWidth(50); // Kolom "ID Akun"
        kolom.getColumn(2).setPreferredWidth(200); // Kolom "Username"
        kolom.getColumn(3).setPreferredWidth(110); // Kolom "Username"
        kolom.getColumn(4).setPreferredWidth(110); // Kolom "Password"
        kolom.getColumn(5).setPreferredWidth(110); // Kolom "Role"

        // Mengatur alignment per kolom
        for (int i = 0; i < table.getColumnCount(); i++) {
            final int columnIndex = i;
            kolom.getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    // Alternating row colors
                    if (!isSelected) {
                        if (row % 2 == 0) {
                            comp.setBackground(new Color(40, 40, 40)); // Warna abu-abu terang
                        } else {
                            comp.setBackground(new Color(45, 45, 45)); // Warna putih
                        }
                    } else {
                        comp.setBackground(table.getSelectionBackground()); // Warna saat dipilih
                    }

                    // Alignment sesuai kolom
                    if (columnIndex == 0) { // Kolom "NO"
                        setHorizontalAlignment(SwingConstants.CENTER);
                    } else if (columnIndex == 1) { // Kolom "ID Akun"
                        setHorizontalAlignment(SwingConstants.CENTER);
                    } else if (columnIndex == 2) { // Kolom "Username"
                        setHorizontalAlignment(SwingConstants.LEFT);
                    } else if (columnIndex == 3) { // Kolom "Password"
                        setHorizontalAlignment(SwingConstants.LEFT);
                    } else if (columnIndex == 4) { // Kolom "Role"
                        setHorizontalAlignment(SwingConstants.CENTER);
                    }

                    return comp;
                }
            });
        }
    }
    
    public static void setTableCustomerSettings(JTable table) {
        // Mengatur lebar kolom
        TableColumnModel kolom = table.getColumnModel();
        kolom.getColumn(0).setPreferredWidth(5);   // Kolom "NO"
        kolom.getColumn(1).setPreferredWidth(70);  // Kolom "ID Customer"
        kolom.getColumn(2).setPreferredWidth(35);  // Kolom "ID Akun"
        kolom.getColumn(3).setPreferredWidth(150); // Kolom "Nama"
        kolom.getColumn(4).setPreferredWidth(100); // Kolom "Telepon"
        kolom.getColumn(5).setPreferredWidth(250); // Kolom "Alamat"

        // Mengatur alignment per kolom
        for (int i = 0; i < table.getColumnCount(); i++) {
            final int columnIndex = i;
            kolom.getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    // Alternating row colors
                    if (!isSelected) {
                        if (row % 2 == 0) {
                            comp.setBackground(new Color(40, 40, 40)); // Warna abu-abu terang
                        } else {
                            comp.setBackground(new Color(45, 45, 45)); // Warna putih
                        }
                    } else {
                        comp.setBackground(table.getSelectionBackground()); // Warna saat dipilih
                    }

                    // Alignment sesuai kolom
                    if (columnIndex == 0 || columnIndex == 1 || columnIndex == 2) { // Kolom "NO", "ID Customer", "ID Akun"
                        setHorizontalAlignment(SwingConstants.CENTER);
                    } else if (columnIndex == 3) { // Kolom "Nama"
                        setHorizontalAlignment(SwingConstants.LEFT);
                    } else if (columnIndex == 4) { // Kolom "Telepon"
                        setHorizontalAlignment(SwingConstants.LEFT);
                    } else if (columnIndex == 5) { // Kolom "Alamat"
                        setHorizontalAlignment(SwingConstants.LEFT);
                    }
                    return comp;
                }
            });
        }
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
    }
    
    public static void setTableOrderSettings(JTable table) {
        // Mengatur lebar kolom
        TableColumnModel kolom = table.getColumnModel();
        kolom.getColumn(0).setPreferredWidth(20);   // Kolom "NO"
        kolom.getColumn(1).setPreferredWidth(80);  // Kolom "ID Pesanan"
        kolom.getColumn(2).setPreferredWidth(150); // Kolom "Nama Cus..."
        kolom.getColumn(3).setPreferredWidth(90); // Kolom "Layanan"
        kolom.getColumn(4).setPreferredWidth(60);  // Kolom "Berat (kg)"
        kolom.getColumn(5).setPreferredWidth(80); // Kolom "Harga (Rp)"
        kolom.getColumn(6).setPreferredWidth(120); // Kolom "Tanggal S..."
        kolom.getColumn(7).setPreferredWidth(100);  // Kolom "Jam Selesai"

        // Mengatur alignment per kolom
        for (int i = 0; i < table.getColumnCount(); i++) {
            final int columnIndex = i;
            kolom.getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    // Alternating row colors
                    if (!isSelected) {
                        if (row % 2 == 0) {
                            comp.setBackground(new Color(40, 40, 40)); // Warna abu-abu terang
                        } else {
                            comp.setBackground(new Color(45, 45, 45)); // Warna putih
                        }
                    } else {
                        comp.setBackground(table.getSelectionBackground()); // Warna saat dipilih
                    }
                    // Alignment sesuai kolom
                    if (columnIndex == 0 || columnIndex == 1 || columnIndex == 4 || columnIndex == 6 || columnIndex == 7) { // Kolom "NO", "ID Pesanan", "Berat (kg)", "Harga (Rp)", "Tanggal S...", "Jam Selesai"
                        setHorizontalAlignment(SwingConstants.CENTER);
                    } else if (columnIndex == 2 || columnIndex == 3 || columnIndex == 5) { // Kolom "Nama Cus...", "Layanan"
                        setHorizontalAlignment(SwingConstants.LEFT);
                    }
                    return comp;
                }
            });
        }
//        JTableHeader header = table.getTableHeader();
//        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
    }
    
    public static void setTableOrderSettingsForCustomer(JTable table) {
        // Mengatur lebar kolom
        TableColumnModel kolom = table.getColumnModel();
        kolom.getColumn(0).setPreferredWidth(10);   // Kolom "NO"
        kolom.getColumn(1).setPreferredWidth(80);  // Kolom "ID Pesanan"
        kolom.getColumn(2).setPreferredWidth(90); // Kolom "Layanan"
        kolom.getColumn(3).setPreferredWidth(30);  // Kolom "Berat (kg)"
        kolom.getColumn(4).setPreferredWidth(80); // Kolom "Harga (Rp)"
        kolom.getColumn(5).setPreferredWidth(120); // Kolom "Tanggal S..."
        kolom.getColumn(6).setPreferredWidth(100);  // Kolom "Jam Selesai"

        // Mengatur alignment per kolom
        for (int i = 0; i < table.getColumnCount(); i++) {
            final int columnIndex = i;
            kolom.getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    // Alternating row colors
                    if (!isSelected) {
                        if (row % 2 == 0) {
                            comp.setBackground(new Color(40, 40, 40)); // Warna abu-abu terang
                        } else {
                            comp.setBackground(new Color(45, 45, 45)); // Warna putih
                        }
                    } else {
                        comp.setBackground(table.getSelectionBackground()); // Warna saat dipilih
                    }
                    // Alignment sesuai kolom
                    if (columnIndex == 0 || columnIndex == 1 || columnIndex == 3 || columnIndex == 5 || columnIndex == 6) { // Kolom "NO", "ID Pesanan", "Berat (kg)", "Harga (Rp)", "Tanggal S...", "Jam Selesai"
                        setHorizontalAlignment(SwingConstants.CENTER);
                    } else if (columnIndex == 2) { // Kolom "Nama Cus...", "Layanan"
                        setHorizontalAlignment(SwingConstants.LEFT);
                    }
                    return comp;
                }
            });
        }
//        JTableHeader header = table.getTableHeader();
//        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
    }
    
    public static void setTableServiceSettings(JTable table) {
        // Mengatur lebar kolom
        TableColumnModel kolom = table.getColumnModel();
        kolom.getColumn(0).setPreferredWidth(5);   // Kolom "NO"
        kolom.getColumn(1).setPreferredWidth(60);  // Kolom "ID Layanan"
        kolom.getColumn(2).setPreferredWidth(120); // Kolom "Nama Layanan"
        kolom.getColumn(3).setPreferredWidth(80); // Kolom "Harga (Rp)"
        kolom.getColumn(4).setPreferredWidth(300); // Kolom "Deskripsi"

        // Mengatur alignment per kolom
        for (int i = 0; i < table.getColumnCount(); i++) {
            final int columnIndex = i;
            kolom.getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    // Alternating row colors
                    if (!isSelected) {
                        if (row % 2 == 0) {
                            comp.setBackground(new Color(40, 40, 40)); // Warna abu-abu terang
                        } else {
                            comp.setBackground(new Color(45, 45, 45)); // Warna putih
                        }
                    } else {
                        comp.setBackground(table.getSelectionBackground()); // Warna saat dipilih
                    }
                    // Alignment sesuai kolom
                    if (columnIndex == 0 || columnIndex == 1) { // Kolom "NO", "ID Layanan", "Harga (Rp)"
                        setHorizontalAlignment(SwingConstants.CENTER);
                    } else if (columnIndex == 2 || columnIndex == 4 || columnIndex == 3) { // Kolom "Nama Layanan", "Deskripsi"
                        setHorizontalAlignment(SwingConstants.LEFT);
                    }
                    return comp;
                }
            });
        }
//        JTableHeader header = table.getTableHeader();
//        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
    }
    

}
