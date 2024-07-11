package bahar.window_kill.model;

import bahar.window_kill.control.fazes.PlayingState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Watch {
    long previousClick = System.currentTimeMillis();
    private final long duration;
    private final EventHandler<ActionEvent> eventHandler;
    public Watch(int duration, EventHandler<ActionEvent> eventHandler) {
        this.duration = duration;
        this.eventHandler = eventHandler;
    }
    public void call() {
        if (previousClick / duration < PlayingState.getClock() / duration) {
            ActionEvent event = new ActionEvent(this, null);
            eventHandler.handle(event);
        }
        previousClick = PlayingState.getClock();
    }
}
