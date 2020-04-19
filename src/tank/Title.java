package tank;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Title extends JFrame {

    public void createTitle() {

        try {
            JFrame frame = new JFrame();

            File imageFile = new File("src/resource/bg.bmp");
            Image i = ImageIO.read(imageFile);
            ImageIcon image = new ImageIcon(i);
            JLabel label = new JLabel(image);
            frame.add(label);
            frame.setLayout(null);
            label.setLocation(0, 0);
            label.setSize(600, 380);
            label.setVisible(true);

            frame.setVisible(true);
            frame.setSize(580, 400);
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}