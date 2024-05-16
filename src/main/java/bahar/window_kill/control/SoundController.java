package bahar.window_kill.control;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public enum SoundController {
    SHOOT("shootSound.wav"), WHOOSH("whoosh.wav"), HIT("hit.wav"), DAMAGE("damage.wav"), COIN_COLLECT("coin_collect.mp3");
    private MediaPlayer mediaPlayer;
    static double volume = 100;
    SoundController(String name) {
        Media media = new Media(getClass().getResource("/sounds/" + name).toString());
        mediaPlayer = new MediaPlayer(media);
    }
    public void play() {
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.setVolume(volume / 100);
        mediaPlayer.play();
    }
    public static void setVolume(double volume) {
        if (volume < 0 || volume > 100) {
            System.err.println("Invalid volume");
            return;
        }
        SoundController.volume = volume;
    }
    public void changeVolume(double volume) { //todo checkThisPart
        mediaPlayer.setVolume(volume / 100);
    }
}
