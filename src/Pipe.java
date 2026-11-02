import java.awt.*;

public class Pipe {
    // Data posisi, ukuran, dan gambar yang diperlukan untuk menggambar sebuah pipa.
    private int posX;
    private int posY;
    private int width;
    private int height;
    private Image image;
    private int velocityX = -0; // Disediakan untuk menyimpan kecepatan pipa jika diperlukan.
    boolean passed; // Mencegah satu pipa dihitung lebih dari sekali untuk skor.

    public Pipe(int posX, int posY, int width, int height, Image image) {
        // Setiap object Pipe mewakili satu pipa atas atau satu pipa bawah.
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;

        this.velocityX = 0;
        this.passed = false;
    }

    // Getter dibaca oleh Logic untuk collision/pergerakan dan oleh View untuk menggambar pipa.
    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Image getImage() { return image; }
    public int getVelocityX() { return velocityX; }

    // Setter memungkinkan Logic mengubah state pipa setiap frame.
    public void setPosx(int posX) { this.posX = posX; }
    public void setPosY(int posY) { this.posY = posY; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public void setImage(Image image) { this.image = image; }
    public void setVelocityX(int velocityX) { this.velocityX = velocityX; }
}
