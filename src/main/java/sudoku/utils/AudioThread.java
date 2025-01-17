package sudoku.utils;

public class AudioThread {

    public static void threadInit(String filePath) {
        Thread audioThread = new Thread(() -> AudioPlayer.playSound(filePath));
        audioThread.start();
    }
}
