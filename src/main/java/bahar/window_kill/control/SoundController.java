package bahar.window_kill.control;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public enum SoundController {
    SHOOT("shootSound.wav"), WHOOSH("whoosh.wav");
    private MediaPlayer mediaPlayer;
    SoundController(String name) {
        Media media = new Media(getClass().getResource("/sounds/" + name).toString());
        mediaPlayer = new MediaPlayer(media);
    }
    public void play() {
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.play();
    }
}
