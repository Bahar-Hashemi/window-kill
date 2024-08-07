package bahar.window_kill.client.control.states;

import bahar.window_kill.communications.model.Game;
import javafx.animation.Timeline;

public abstract class GameState {
    protected final Timeline timeline;
    protected final Game game;
    protected final boolean isViewable;
    protected GameState(boolean isViewable, Game game, Timeline timeline) {
        this.isViewable = isViewable;
        this.game = game;
        this.timeline = timeline;
    }

    public void play() {
        timeline.play();
    }
    public void stop() {
        timeline.stop();
    }
}
