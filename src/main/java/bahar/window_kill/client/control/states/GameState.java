package bahar.window_kill.client.control.states;

import bahar.window_kill.client.model.Deck;
import javafx.animation.Timeline;

public abstract class GameState {
    protected final Timeline timeline;
    protected final Deck deck;

    protected GameState(Deck deck, Timeline timeline) {
        this.deck = deck;
        this.timeline = timeline;
    }

    public void play() {
        timeline.play();
    }
    public void stop() {
        timeline.stop();
    }
}
