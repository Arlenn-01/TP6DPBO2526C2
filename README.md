# TP6DPBO2526C2

## Deskripsi

FlappyBird ini dibuat sebagai permainan sederhana menggunakan Java Swing. Pemain mengendalikan burung dengan menekan tombol `Space` agar dapat melewati celah pipa. Setiap pipa yang berhasil dilewati akan menambah satu poin. Permainan berakhir ketika burung menabrak pipa atau jatuh sampai batas bawah layar.

## Desain Program

Program dibagi menjadi beberapa class supaya bagian tampilan, aturan permainan, dan data objek lebih mudah dipahami serta dikembangkan.

### Struktur Class

| Class | Penjelasan |
| --- | --- |
| `App` | Membuat JFrame, menampilkan menu awal, dan mengatur tombol mulai serta keluar. |
| `Logic` | Mengatur state permainan, timer, pergerakan player dan pipa, gravity, collision, skor, Game Over, restart, dan input keyboard. |
| `View` | Menggambar player, pipa, skor, dan pesan Game Over pada JPanel. |
| `Player` | Menyimpan data player seperti posisi, ukuran, gambar, dan kecepatan vertikal. |
| `Pipe` | Menyimpan data setiap pipa serta status apakah pipa sudah dihitung untuk skor. |

### Komponen Utama

- `JFrame` menjadi jendela utama permainan dengan ukuran 360 x 640 pixel.
- `JPanel` pada `View` menjadi tempat untuk menggambar burung, pipa, skor, dan pesan Game Over.
- `Timer gameLoop` memperbarui permainan sekitar 60 kali setiap detik.
- `Timer pipesCooldown` membuat pasangan pipa baru setiap 1500 milidetik.
- `ArrayList<Pipe>` menyimpan pipa-pipa yang sedang muncul di layar.
- `Rectangle` membantu memeriksa apakah burung bersentuhan dengan pipa.
- Gambar permainan disimpan di `src/assets/`, yaitu gambar burung, pipa atas, pipa bawah, dan background.

## Alur Program

### 1. Aplikasi Dibuka

1. Method `main()` pada `App` membuat dan mengatur JFrame.
2. Object `Logic` dan `View` dibuat. `View` menerima referensi `Logic` agar bisa membaca kondisi permainan.
3. Menu awal ditampilkan dengan tombol `Mulai Permainan` dan `Keluar`.
4. Timer belum berjalan ketika menu awal ditampilkan.

### 2. Permainan Dimulai

1. Ketika tombol `Mulai Permainan` ditekan, content pane JFrame diganti menjadi `View`.
2. Panel permainan meminta fokus keyboard.
3. Method `Logic.start()` menjalankan `gameLoop` dan `pipesCooldown`.
4. Burung mulai jatuh karena pengaruh gravity dan permainan mulai berjalan.

### 3. Proses Game Loop

Pada setiap frame, `gameLoop` memanggil `actionPerformed()`, lalu method tersebut menjalankan `move()`. Di dalam `move()` terjadi beberapa hal berikut:

1. Velocity vertikal player ditambah nilai gravity.
2. Posisi vertikal player diperbarui berdasarkan velocity tersebut.
3. Semua pipa digerakkan ke kiri menggunakan `pipeVelocityX`.
4. Jika pipa sudah melewati posisi player, status `passed` diubah menjadi `true`.
5. Hanya pipa atas yang menambah skor, sehingga satu pasangan pipa hanya dihitung satu kali.
6. Area player dan pipa dibandingkan menggunakan `Rectangle`.
7. `View.repaint()` dipanggil agar posisi terbaru digambar ke layar.

### 4. Pipa Baru Muncul

Setiap 1500 milidetik, `pipesCooldown` memanggil `placePipes()`. Method ini kemudian:

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

Saat Game Over, kedua timer dihentikan dan pesan Game Over ditampilkan. Jika pemain menekan tombol `R`, method `restart()` akan:

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

## Dokumentasi Video

Video demonstrasi permainan tersedia di folder [`Dokumentasi`](Dokumentasi/):

- [Video demonstrasi FlappyBird](Dokumentasi/2026-11-02%2023-42-37.mp4)

## File yang Tidak Diunggah

File hasil kompilasi seperti `.class`, folder output seperti `out/`, konfigurasi lokal `.idea/`, serta file log dan `.jar` diabaikan melalui `.gitignore`.