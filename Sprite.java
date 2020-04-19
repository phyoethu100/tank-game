package tank;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite {

    private BufferedImage[] image;
    private String sprite;
    private int size;

    public Sprite (String sprite, int size) throws IOException {
        this.sprite = sprite;
        this.size = size;
        this.drawImages();
    }

    public BufferedImage getImage(int frame) {
        return this.image[frame];
    }

    public int count() {
        return this.image.length;
    }

    private void drawImages() throws IOException {
        BufferedImage graphic = ImageIO.read(new File(sprite));
        this.image = new BufferedImage[graphic.getWidth()/size];

        int frame = 0;

        while (frame < this.image.length) {
            this.image[frame] = graphic.getSubimage(frame * this.size, 0, this.size, this.size);
            frame++;
        }
    }

}