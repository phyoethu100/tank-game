package tank;

import java.io.File;
import javax.sound.sampled.*;

public class Sound {
    
    Sound() {}

    // Play an audio continuously
    public void playForever (String audioFile) {
       
        File sound = new File(audioFile);

        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    // Play an audio once
    public void playOnce (String audioFile) {
       
        File sound = new File(audioFile);

        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

