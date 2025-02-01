# Laundry-Hub

Laundry-Hub adalah aplikasi manajemen laundry berbasis desktop yang dirancang menggunakan Java dengan IDE NetBeans. Aplikasi ini dilengkapi dengan antarmuka pengguna yang modern menggunakan library [FlatLaf](https://www.formdev.com/flatlaf/) dan memiliki tiga peran utama: **Admin**, **Kasir**, dan **Pelanggan**. Setiap peran memiliki akses dan fitur yang berbeda sesuai dengan kebutuhan operasional laundry.

## Fitur Utama

### 1. **Admin**
- Mengakses semua data pengguna (Admin, Kasir, Pelanggan).
- Mengelola data pengguna (CRUD: Create, Read, Update, Delete).
- Mencetak laporan laundry dalam format tertentu (PDF/Excel).

### 2. **Kasir**
- Menginputkan pesanan laundry.
  - Memilih pelanggan yang sudah menjadi member.
  - Menginputkan pelanggan baru yang belum bergabung sebagai member.
- Melihat riwayat semua pesanan pelanggan.
- Menampilkan tenggat waktu dan rincian pesanan.

### 3. **Pelanggan**
- Menginputkan pesanan laundry untuk dirinya sendiri.
- Melihat riwayat pesanan pribadi.
- Menginputkan pesanan untuk pelanggan lain (jika diperlukan).

---

## Teknologi yang Digunakan

- **Bahasa Pemrograman**: Java
- **IDE**: NetBeans
- **Library UI**: [FlatLaf](https://www.formdev.com/flatlaf/) (untuk desain antarmuka modern)
- **Database**: (Tuliskan database yang digunakan, misalnya MySQL, SQLite, dll.)
- **Tools Tambahan**:
  - JasperReports (untuk mencetak laporan PDF/Excel).

---

## Persyaratan Sistem

Sebelum menjalankan aplikasi, pastikan sistem Anda memenuhi persyaratan berikut:

- **Java Development Kit (JDK)**: Versi 8 atau lebih tinggi.
- **NetBeans IDE**: Versi terbaru (disarankan).
- **Database**: Pastikan database telah diinstal dan dikonfigurasi dengan benar.
- **Library FlatLaf**: Sudah disertakan dalam proyek.

---

## Cara Menjalankan Proyek

1. **Clone Repositori**
   ```bash
   git clone https://github.com/namarepositorianda/laundry-hub.git
   ```

2. **Buka Proyek di NetBeans**
   - Buka NetBeans IDE.
   - Pilih menu `File > Open Project` dan arahkan ke folder proyek yang telah di-clone.

3. **Konfigurasi Database**
   - Buka file konfigurasi database (misalnya `database.properties` atau file sejenis).
   - Sesuaikan parameter koneksi database seperti URL, username, dan password.

4. **Menambahkan Library**
   - Pastikan library FlatLaf telah ditambahkan ke proyek.
   - Jika belum, unduh dari [situs resmi FlatLaf](https://www.formdev.com/flatlaf/) dan tambahkan ke proyek melalui menu `Libraries`.

5. **Jalankan Aplikasi**
   - Klik kanan pada proyek di NetBeans dan pilih `Run`.
   - Aplikasi akan mulai berjalan dan menampilkan antarmuka utama.

---

## Struktur Proyek

Berikut adalah struktur direktori proyek:

```
laundry-hub/
├── src/
│   ├── controllers/          # Berisi logika kontroler untuk setiap role
│   ├── models/               # Model data dan entitas
│   ├── views/                # Antarmuka pengguna (UI)
│   ├── utils/                # Utilitas seperti koneksi database, helper, dll.
│   └── Main.java             # Entry point aplikasi
├── lib/                      # Library eksternal (FlatLaf, JasperReports, dll.)
├── reports/                  # Template laporan (JasperReports)
├── database.properties       # Konfigurasi database
└── README.md                 # Dokumentasi proyek
```

---

## Panduan Penggunaan

### 1. Login
- Aplikasi akan meminta login saat pertama kali dibuka.
- Masukkan username dan password sesuai dengan role Anda (Admin, Kasir, atau Pelanggan).

### 2. Admin
- Setelah login sebagai Admin, Anda dapat mengakses semua fitur yang tersedia.
- Gunakan menu navigasi untuk mengelola data pengguna dan mencetak laporan.

### 3. Kasir
- Setelah login sebagai Kasir, Anda dapat menginputkan pesanan dan melihat riwayat pesanan.
- Gunakan formulir input untuk memilih pelanggan atau menambahkan pelanggan baru.

### 4. Pelanggan
- Setelah login sebagai Pelanggan, Anda dapat menginputkan pesanan untuk diri sendiri atau pelanggan lain.
- Gunakan menu riwayat untuk melihat pesanan sebelumnya.

---

## Kontribusi

Kami sangat menghargai kontribusi Anda! Jika Anda ingin berkontribusi, silakan ikuti langkah-langkah berikut:

1. Fork repositori ini.
2. Buat branch baru untuk fitur atau perbaikan bug:
   ```bash
   git checkout -b feature/nama-fitur
   ```
3. Commit perubahan Anda:
   ```bash
   git commit -m "Tambahkan deskripsi perubahan"
   ```
4. Push ke branch:
   ```bash
   git push origin feature/nama-fitur
   ```
5. Buat Pull Request (PR) ke branch `main`.

---

## Lisensi

Proyek ini dilisensikan di bawah [MIT License](LICENSE). Silakan lihat file `LICENSE` untuk detail lebih lanjut.

---

## Kontak

Jika Anda memiliki pertanyaan atau saran, silakan hubungi kami melalui:

- Email: namakamu@gmail.com
- GitHub: [namarepositorianda](https://github.com/namarepositorianda)

---

Semoga dokumentasi ini membantu Anda memahami proyek **Laundry-Hub** dengan lebih baik. Terima kasih telah menggunakan aplikasi ini! 😊
