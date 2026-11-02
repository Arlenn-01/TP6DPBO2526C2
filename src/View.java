import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel{
    // Ukuran panel harus sama dengan area permainan agar posisi objek konsisten.
    int width = 360;
    int height = 640;

    private Logic logic;
    private JLabel scoreLabel; // Label Swing untuk menampilkan skor terbaru.

    public View(Logic logic) {
        this.logic = logic;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.cyan);

        // Panel dibuat fokusable supaya dapat menerima input keyboard dari player.
        setFocusable(true);
        addKeyListener(logic);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel);
    }

    @Override
    public void paintComponent(Graphics g){
        // @Override mengganti mekanisme menggambar JPanel dengan gambar game sendiri.
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Nilai label disinkronkan dengan score setiap kali panel digambar ulang.
        scoreLabel.setText("Score: " + logic.getScore());
        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight(), null);
        }

        ArrayList<Pipe> pipes = logic.getPipes();
        if (player != null) {
            // Semua pipa aktif digambar berdasarkan posisi terkini dari Logic.
            for (int i=0; i<pipes.size(); ++i) {
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        if (logic.isGameOver()) {
            // Pesan ini hanya muncul setelah timer berhenti karena game over.
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("Game Over", 95, 280);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Tekan R untuk restart", 82, 315);
        }
    }
}
