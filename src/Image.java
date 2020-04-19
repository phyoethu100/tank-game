package tank;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

public class Image {

    protected BufferedImage image;
    protected ImageObserver observer;
    protected int x;
    protected int y;

    public Image (String location) throws IOException {
        this (location, null);
    }

    public Image (String location, ImageObserver observer) throws IOException {
        x = 0;
        y = 0;
        image = ImageIO.read (new File(location));
        this.observer = observer;
    }

    public void setImageX (int x) {
        this.x = x;
    }

    public void setImageY (int y) {
        this.y = y;
    }

    public int getImageX() {
        return this.x;
    }

    public int getImageY() {
        return this.y;
    }

    public int getWidth() {
        return this.image.getWidth();
    }

    public int getHeight() {
        return this.image.getHeight();
    }

    public void draw (Graphics graphics) {
        graphics.drawImage (image, x, y, observer);
    }

}


