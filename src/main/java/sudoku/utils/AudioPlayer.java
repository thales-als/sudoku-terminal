package sudoku.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    public static void playSound(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();
            clip.drain();
            clip.stop();
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Erro ao tentar tocar o áudio: " + ex.getMessage());
            
        }
    }
}
