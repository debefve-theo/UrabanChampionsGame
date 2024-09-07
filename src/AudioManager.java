import javax.sound.sampled.*;
import java.io.File;

public class AudioManager {
    private Clip musicClip;

    public AudioManager(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            musicClip = (Clip) AudioSystem.getLine(info);
            musicClip.open(audioStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void startMusic() {
        if (musicClip != null) {
            musicClip.start();
        }
    }

    public void stopMusic() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
        }
    }
}
