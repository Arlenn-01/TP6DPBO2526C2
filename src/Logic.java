import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    // Ukuran area permainan dan posisi awal player.
    int frameWidth = 360;
    int frameHeight = 640;

    int playerStartPosX = frameWidth / 3;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    // Posisi, ukuran, dan ukuran gambar setiap pipa.
    int pipeStartPosX = frameWidth;
    int pipeStartPOsY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    // gameLoop memperbarui permainan 60 kali per detik.
    // pipesCooldown membuat pasangan pipa baru secara berkala.
    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1; // Percepatan jatuh player pada setiap frame.

    int pipeVelocityX = -2; // Kecepatan gerak pipa ke arah kiri.

    // Referensi View diperlukan agar layar dapat digambar ulang setelah state berubah.
    View view;
    Image birdImage;
    Player player;
    boolean gameOver; // Menandai apakah permainan sedang berhenti karena kalah.
    int score; // Jumlah pasangan pipa yang berhasil dilewati.

    // Gambar pipa dan daftar semua pipa yang sedang berada di layar.
    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    public Logic() {
        // Resource gambar diambil dari folder assets yang berada di dalam source project.
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage= new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<Pipe>();

        // Timer ini hanya menambahkan pipa; timer baru dijalankan setelah tombol mulai ditekan.
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                placePipes();
            }
        });

        // Timer menggunakan Logic sebagai listener agar actionPerformed menjalankan satu frame game.
        gameLoop = new Timer(1000/60, this);
        gameOver = false;
        score = 0;
    }
    public void setView(View view) {
        this.view = view;
    }
    public Player getPlayer() {
        return player;
    }

    public ArrayList<Pipe> getPipes() { return pipes; }
    public boolean isGameOver() { return gameOver; }
    public int getScore() { return score; }

    // Memulai kedua timer setelah user memilih menu mulai.
    public void start() {
        gameOver = false;
        pipesCooldown.start();
        gameLoop.start();
    }

    public void restart() {
        // Timer dihentikan sementara agar reset state tidak bercampur dengan frame berjalan.
        pipesCooldown.stop();
        gameLoop.stop();
        pipes.clear();
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocity(0);
        score = 0;
        gameOver = false;
        start();
    }

    // Menghentikan permainan ketika terjadi collision atau player menyentuh batas bawah.
    private void endGame() {
        gameOver = true;
        pipesCooldown.stop();
        gameLoop.stop();
    }

    public void placePipes() {
        // Posisi vertikal acak membuat celah pipa berbeda pada setiap kemunculan.
        int randomPosY = (int) (pipeStartPOsY - (pipeHeight/4) - Math.random() * (pipeHeight/2));
        int openingSpace = frameHeight/4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPosY + openingSpace + pipeHeight, pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void move() {
        // Update posisi player berdasarkan velocity dan gravity.
        player.setVelocity(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (int i=0; i<pipes.size(); ++i) {
            Pipe pipe = pipes.get(i);
            pipe.setPosx(pipe.getPosX() + pipeVelocityX);

            // Pipa atas dan bawah memiliki flag sendiri; hanya pipa atas yang menambah skor.
            if (!pipe.passed && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                pipe.passed = true;
                if (pipe.getPosY() < 0) {
                    score++;
                }
            }

            // Rectangle dipakai untuk memeriksa apakah area player bersentuhan dengan pipa.
            Rectangle playerBounds = new Rectangle(player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight());
            Rectangle pipeBounds = new Rectangle(pipe.getPosX(), pipe.getPosY(),
                    pipe.getWidth(), pipe.getHeight());
            if (playerBounds.intersects(pipeBounds)) {
                endGame();
                return;
            }
        }

        if (player.getPosY() + player.getHeight() >= frameHeight) {
            endGame();

        }
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        // @Override diperlukan karena Logic mengimplementasikan ActionListener milik Timer Swing.
        move();
        if (view != null) {
            view.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {} // Tidak digunakan karena input diproses di keyPressed.

    @Override
    public void keyPressed(KeyEvent e) {
        // R hanya bekerja setelah game over, sedangkan Space membuat player mengepak.
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restart();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            player.setVelocity(-10);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {} // Tidak ada aksi khusus ketika tombol dilepas.
}

