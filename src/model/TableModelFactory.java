/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.CustomerDAO;
import dao.LayananDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import util.PesananUtil;

/**
 *
 * @author dzikr
 */
public class TableModelFactory {
    /**
     * Membuat TableModel khusus untuk tabel akun.
     *
     * @param akunList Daftar objek AkunModel.
     * @return DefaultTableModel untuk tabel akun.
     */
    public static DefaultTableModel createAkunTableModel(List<AkunModel> akunList) {
        String[] kolom = { "NO", "ID Akun", "Username", "Password", "Role" };
        DefaultTableModel model = new DefaultTableModel(null, kolom);

        int n = 0;
        for (AkunModel akun : akunList) {
            n++;
            model.addRow(new String[] {
                String.valueOf(n),
                akun.getIdAkun(),
                akun.getUsername(),
                akun.getPassword(),
                akun.getRole()
            });
        }
        return model;
    }
    
        /**
     * Membuat TableModel khusus untuk tabel akun.
     *
     * @param custList Daftar objek CustomerModel.
     * @return DefaultTableModel untuk tabel customer.
     */
    public static DefaultTableModel createCustomerTableModel(List<CustomerModel> custList) {
        String[] kolom = { "NO", "ID Customer", "ID Akun", "Nama", "Telepon", "Alamat" };
        DefaultTableModel model = new DefaultTableModel(null, kolom);

        int n = 0;
        for (CustomerModel customer : custList) {
            n++;
            String[] rowData = {
                    String.valueOf(n),
                    customer.getIdCustomer(),
                    customer.getIdAkun(),
                    customer.getName(),
                    customer.getPhone(),
                    customer.getAddress()
            };
            model.addRow(rowData);
        }
        return model;
    }
    
    public static DefaultTableModel createPesananTableModel(List<PesananModel> pesananList) {
        String[] kolom = {
            "NO", "ID Pesanan", "Nama Customer", "Layanan", 
            "Berat", "Harga", "Tanggal Selesai", "Jam Selesai"
        };
        DefaultTableModel model = new DefaultTableModel(null, kolom);

        int n = 0;
        for (PesananModel pesanan : pesananList) {
            n++;
            try {
                String namaCustomer = CustomerDAO.getCustomerByIdCustomer(pesanan.getIdCustomer()).getName();
                String namaLayanan = LayananDAO.getLayananById(pesanan.getIdLayanan()).getNama();
                String harga = PesananUtil.formatCurrency(pesanan.getHarga());
                String berat = String.valueOf(pesanan.getBerat()) + " Kg";
                String[] rowData = {
                    String.valueOf(n),
                    pesanan.getIdPesanan(),
                    namaCustomer,
                    namaLayanan,
                    berat,
                    harga,
                    pesanan.getTanggalSelesai(),
                    pesanan.getJamSelesai()
                };
                model.addRow(rowData);
            } catch (Exception e) {
                e.printStackTrace(); // Untuk debugging jika ada kesalahan
            }
        }
        return model;
    }
    
        public static DefaultTableModel createPesananTableModelForCustomer(List<PesananModel> pesananList) {
        String[] kolom = {
            "NO", "ID Pesanan", "Layanan", 
            "Berat", "Harga", "Tanggal Selesai", "Jam Selesai"
        };
        DefaultTableModel model = new DefaultTableModel(null, kolom);

        int n = 0;
        for (PesananModel pesanan : pesananList) {
            n++;
            try {
                String namaLayanan = LayananDAO.getLayananById(pesanan.getIdLayanan()).getNama();
                String harga = PesananUtil.formatCurrency(pesanan.getHarga());
                String berat = String.valueOf(pesanan.getBerat()) + " Kg";
                String[] rowData = {
                    String.valueOf(n),
                    pesanan.getIdPesanan(),
                    namaLayanan,
                    berat,
                    harga,
                    pesanan.getTanggalSelesai(),
                    pesanan.getJamSelesai()
                };
                model.addRow(rowData);
            } catch (Exception e) {
                e.printStackTrace(); // Untuk debugging jika ada kesalahan
            }
        }
        return model;
    }

    public static DefaultTableModel createLayananTableModel(List<LayananModel> layananList) {
        String[] kolom = {"NO", "ID Layanan", "Nama Layanan", "Harga", "Deskripsi"};
        DefaultTableModel model = new DefaultTableModel(null, kolom);
        
        int n = 0; 
        for (LayananModel layanan : layananList) {
            n++;
            try {
                String[] rowData = {
                    String.valueOf(n),
                    layanan.getIdLayanan(),
                    layanan.getNama(),
                    PesananUtil.formatCurrency(layanan.getHarga()),
                    layanan.getDeskripsi()
                };
                model.addRow(rowData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return model;
    }
    
    

}
