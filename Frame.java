package tank;

import javax.swing.*;

public class Frame extends JFrame {

    public Frame (GameWorld game) {

        setTitle("Tank Game");
        setResizable(false);
        add(game);
        Thread thread = new Thread(game);
        thread.start();
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
