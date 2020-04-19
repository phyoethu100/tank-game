package tank;

import javax.swing.JFrame;

public class Start extends JFrame {

    public static void main(String[] args0) {

        System.out.println("Welcome to Tank World!");

        Title background = new Title();
        background.createTitle();

        Sound music = new Sound();
        music.playForever("src/resource/unity.wav");

        new Frame (new Control());
    }

}