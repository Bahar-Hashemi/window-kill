package bahar.window_kill.control.loader;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.util.ArrayList;

public enum SoundLoader {
    WHOOSH("whoosh.wav", 1), HIT("hit.wav", 1), DAMAGE("damage.wav", 1),
    COIN_COLLECT("coin_collect.mp3", 1), ENEMY_SHOOT("enemy_shoot.wav", 1),
    DRILL("drill.wav", 1), BACKGROUND("background.wav", -1),
    WALLHIT("wall_hit.wav", 1);
    private MediaPlayer mediaPlayer;
    private int loopCount;
    private static double volume = 100;
    SoundLoader(String name, int loopCount) {
        Media media = new Media(getClass().getResource("/sounds/" + name).toString());
        this.loopCount = loopCount;
        mediaPlayer = new MediaPlayer(media);
    }
    public void play() {
        mediaPlayer.setCycleCount(loopCount);
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.setVolume(volume / 100);
        mediaPlayer.play();
    }
    public static void setVolume(double volume) {
        if (volume < 0 || volume > 100) {
            System.err.println("Invalid volume");
            return;
        }
        for (SoundLoader soundLoader: SoundLoader.values())
            if (soundLoader.loopCount == -1)
                soundLoader.mediaPlayer.setVolume(volume / 100);
        SoundLoader.volume = volume;
    }
    public static double getVolume() {
        return volume;
    }
}
