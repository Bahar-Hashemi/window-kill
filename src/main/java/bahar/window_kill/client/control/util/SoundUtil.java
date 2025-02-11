package bahar.window_kill.client.control.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public enum SoundUtil {
    WHOOSH("whoosh.wav", 1), HIT("hit.wav", 1), DAMAGE("damage.wav", 1),
    COIN_COLLECT("coin_collect.mp3", 1), ENEMY_SHOOT("enemy_shoot.wav", 1),
    DRILL("drill.wav", 1), BACKGROUND("background.wav", -1),
    WALLHIT("wall_hit.wav", 1);
    private MediaPlayer mediaPlayer;
    private int loopCount;
    private static double volume = FileUtil.readSettings().getVolume();
    SoundUtil(String name, int loopCount) {
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
        for (SoundUtil soundUtil : SoundUtil.values())
            if (soundUtil.loopCount == -1)
                soundUtil.mediaPlayer.setVolume(volume / 100);
        SoundUtil.volume = volume;
    }
    public static double getVolume() {
        return volume;
    }
}
