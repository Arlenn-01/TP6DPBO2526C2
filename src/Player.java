import java.awt.*;

public class Player {
    // Data berikut membentuk state visual dan gerak player.
    private int posX;
    private int posY;
    private int width;
    private int height;
    private Image image;
    private int velocityY = -0; // Kecepatan vertikal; nilai negatif berarti bergerak ke atas.

    public Player(int posX, int posY, int width, int height, Image image) {
        // Constructor menerima semua data awal agar object player langsung siap digunakan.
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;

        this.velocityY = -0;
    }

    // Getter digunakan Logic dan View untuk membaca state player tanpa mengakses field langsung.
    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Image getImage() { return image; }
    public int getVelocityY() { return velocityY; }

    // Setter digunakan untuk memperbarui posisi dan atribut player selama permainan berjalan.
    public void setPosx(int posX) { this.posX = posX; }
    public void setPosX(int posX) { this.posX = posX; }
    public void setPosY(int posY) { this.posY = posY; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public void setImage(Image image) { this.image = image; }
    public void setVelocity(int velocityY) { this.velocityY = velocityY; }
}
