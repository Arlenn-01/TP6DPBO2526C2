# TP6DPBO2526C2

## Deskripsi

FlappyBird adalah permainan sederhana berbasis Java Swing. Player mengendalikan burung agar melewati celah di antara pipa. Permainan berakhir ketika burung menyentuh pipa atau batas bawah area permainan.

## Desain Program

Program menggunakan pendekatan pemisahan tanggung jawab antara tampilan, logika permainan, dan model objek permainan.

### Struktur Class

| Class | Tanggung jawab |
| --- | --- |
| `App` | Membuat JFrame, menampilkan menu awal, dan mengatur tombol mulai serta keluar. |
| `Logic` | Mengatur state permainan, timer, pergerakan player dan pipa, gravity, collision, skor, Game Over, restart, dan input keyboard. |
| `View` | Menggambar player, pipa, skor, dan pesan Game Over pada JPanel. |
| `Player` | Menyimpan data player seperti posisi, ukuran, gambar, dan kecepatan vertikal. |
| `Pipe` | Menyimpan data setiap pipa serta status apakah pipa sudah dihitung untuk skor. |

### Komponen Utama

- `JFrame` digunakan sebagai jendela utama berukuran 360 x 640 pixel.
- `JPanel` pada `View` digunakan sebagai area untuk menggambar permainan.
- `Timer gameLoop` berjalan sekitar 60 kali per detik untuk memperbarui posisi dan memeriksa kondisi permainan.
- `Timer pipesCooldown` membuat pasangan pipa baru setiap 1500 milidetik.
- `ArrayList<Pipe>` menyimpan seluruh pipa yang sedang aktif.
- `Rectangle` digunakan untuk memeriksa persentuhan antara player dan pipa.
- Asset permainan berada di `src/assets/`, yaitu gambar burung, pipa atas, pipa bawah, dan background.

## Alur Program

### 1. Menjalankan Aplikasi

1. Method `main()` pada `App` membuat dan mengatur JFrame.
2. Object `Logic` dan `View` dibuat. `View` menerima referensi `Logic` agar dapat membaca state permainan.
3. Menu awal ditampilkan dengan tombol `Mulai Permainan` dan `Keluar`.
4. Timer belum berjalan ketika menu awal ditampilkan.

### 2. Memulai Permainan

1. Ketika tombol `Mulai Permainan` ditekan, content pane JFrame diganti menjadi `View`.
2. Panel permainan meminta fokus keyboard.
3. Method `Logic.start()` menjalankan `gameLoop` dan `pipesCooldown`.
4. Player mulai jatuh karena pengaruh gravity.

### 3. Proses Game Loop

Pada setiap frame, `actionPerformed()` dipanggil oleh `gameLoop`, kemudian menjalankan `move()`:

1. Velocity vertikal player ditambah nilai gravity.
2. Posisi vertikal player diperbarui berdasarkan velocity tersebut.
3. Semua pipa digerakkan ke kiri menggunakan `pipeVelocityX`.
4. Jika pipa sudah melewati posisi player, status `passed` diubah menjadi `true`.
5. Hanya pipa atas yang menambah skor, sehingga satu pasangan pipa hanya dihitung satu kali.
6. Area player dan pipa dibandingkan menggunakan `Rectangle`.
7. `View.repaint()` dipanggil agar posisi terbaru digambar ke layar.

### 4. Pembuatan Pipa

`pipesCooldown` memanggil `placePipes()` setiap 1500 milidetik. Method tersebut:

1. Menentukan posisi vertikal acak untuk pipa atas.
2. Membuat pipa bawah dengan jarak celah yang sama.
3. Menambahkan kedua pipa ke dalam `ArrayList<Pipe>`.

### 5. Input Keyboard

- `Space`: memberikan velocity negatif pada player sehingga burung bergerak ke atas.
- `R`: melakukan restart hanya ketika status permainan sudah `gameOver`.
- Tombol `Keluar`: menutup aplikasi menggunakan `System.exit(0)`.

### 6. Game Over dan Restart

Game Over terjadi jika:

- `Rectangle` player beririsan dengan `Rectangle` pipa; atau
- bagian bawah player mencapai batas bawah JFrame.

Ketika Game Over, kedua timer dihentikan dan pesan Game Over ditampilkan. Saat tombol `R` ditekan, method `restart()`:

1. Menghentikan timer.
2. Menghapus semua pipa.
3. Mengembalikan player ke posisi awal.
4. Mengatur velocity player dan skor menjadi nol.
5. Mengubah status Game Over menjadi `false`.
6. Menjalankan kembali kedua timer.

## Cara Menjalankan

### IntelliJ IDEA

1. Buka folder project atau file `FlappyBird.iml`.
2. Pastikan folder `src` ditandai sebagai source root.
3. Jalankan class `App.java`.

### VS Code atau Terminal

Pastikan Java Development Kit (JDK) sudah terpasang, lalu jalankan dari folder project:

```bash
mkdir -p out
javac -d out src/*.java
java -cp out:src App
```

Asset gambar dibaca melalui classpath dari folder `src`, sehingga folder `src` perlu tetap disertakan ketika program dijalankan dari terminal. Pada konfigurasi IntelliJ, `src` digunakan sebagai source root dan asset berada di dalam package resources relatif terhadap class.

## Kontrol Permainan

| Tombol | Fungsi |
| --- | --- |
| `Space` | Menggerakkan burung ke atas |
| `R` | Restart setelah Game Over |
| `Mulai Permainan` | Memulai permainan dari menu |
| `Keluar` | Menutup aplikasi |

## File yang Tidak Diunggah

File hasil kompilasi seperti `.class`, folder output seperti `out/`, konfigurasi lokal `.idea/`, serta file log dan `.jar` diabaikan melalui `.gitignore`.