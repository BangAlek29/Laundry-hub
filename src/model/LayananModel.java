package model;

public class LayananModel {
    private String idLayanan;
    private String nama;
    private int harga;
    private String deskripsi;

    public LayananModel(String idLayanan, String nama, int harga, String deskripsi) {
        this.idLayanan = idLayanan;
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }
    
    public LayananModel(){
        
    }

    public String getIdLayanan() {
        return idLayanan;
    }

    public void setIdLayanan(String idLayanan) {
        this.idLayanan = idLayanan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    
    @Override
    public String toString() {
        return nama;
    }
}
