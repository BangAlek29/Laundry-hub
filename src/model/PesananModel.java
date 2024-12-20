package model;

public class PesananModel {
    private String idPesanan;
    private String idCustomer;
    private String idLayanan;
    private int berat;
    private int harga;
    private String tanggalSelesai;
    private String jamSelesai;

    public PesananModel(String idPesanan, String idCustomer, String idLayanan, int berat, int harga,
            String tanggalSelesai, String jamSelesai) {
        this.idPesanan = idPesanan;
        this.idCustomer = idCustomer;
        this.idLayanan = idLayanan;
        this.berat = berat;
        this.harga = harga;
        this.tanggalSelesai = tanggalSelesai;
        this.jamSelesai = jamSelesai;
    }

    public PesananModel(String idPesanan, String idCustomer, String idLayanan, int berat, int harga) {
        this.idPesanan = idPesanan;
        this.idCustomer = idCustomer;
        this.idLayanan = idLayanan;
        this.berat = berat;
        this.harga = harga;
    }

    public PesananModel() {

    }

    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(String idPesanan) {
        this.idPesanan = idPesanan;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getIdLayanan() {
        return idLayanan;
    }

    public void setIdLayanan(String idLayanan) {
        this.idLayanan = idLayanan;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(String tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

}
