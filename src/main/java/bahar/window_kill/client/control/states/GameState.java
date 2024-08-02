package bahar.window_kill.client.control.states;

import bahar.window_kill.client.model.Game;
import javafx.animation.Timeline;

public abstract class GameState {
    protected final Timeline timeline;
    protected final Game game;

    protected GameState(Game game, Timeline timeline) {
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
