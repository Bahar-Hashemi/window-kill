package bahar.window_kill.control.fazes;

import javafx.animation.Timeline;

public abstract class GameState {
    protected final Timeline timeline;

    protected GameState(Timeline timeline) {
        this.timeline = timeline;
    }

    public void play() {
        timeline.play();
    }
    public void stop() {
        timeline.stop();
    }
}
