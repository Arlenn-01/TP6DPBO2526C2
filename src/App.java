import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        // JFrame adalah jendela utama yang menjadi wadah menu dan area permainan.
        JFrame frame = new JFrame("FlappyBird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Logic menyimpan aturan permainan, sedangkan View bertugas menggambar layar.
        Logic logika = new Logic();
        View tampilan = new View(logika);
        logika.setView(tampilan);

        // Menu awal ditampilkan sebelum timer permainan dijalankan.
        JPanel menu = new JPanel(new GridBagLayout());
        JPanel menuButtons = new JPanel();
        JButton startButton = new JButton("Mulai Permainan");
        JButton exitButton = new JButton("Keluar");
        menuButtons.add(startButton);
        menuButtons.add(exitButton);
        menu.add(menuButtons);

        // Mengganti isi JFrame ke View dan memulai timer saat permainan dimulai.
        startButton.addActionListener(e -> {
            frame.setContentPane(tampilan);
            frame.pack();
            frame.setLocationRelativeTo(null);
            tampilan.requestFocusInWindow();
            logika.start();
        });

        // Menutup seluruh aplikasi ketika tombol keluar ditekan.
        exitButton.addActionListener(e -> System.exit(0));

        // Menu dipasang dan ditampilkan sebagai layar pertama aplikasi.
        frame.add(menu);
        frame.pack();
        frame.setVisible(true);
    }
}
