package bahar.window_kill.model;

import bahar.window_kill.control.fazes.PlayingState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Watch {
    long previousClick = System.currentTimeMillis();
    private long duration;
    private final EventHandler<ActionEvent> eventHandler;
    private int cycleCount = -1;
    private boolean wasCalled = false;
    public Watch(int duration, EventHandler<ActionEvent> eventHandler) {
        this.duration = duration;
        this.eventHandler = eventHandler;
    }
    public void setCycleCount(int cycleCount) {
        this.cycleCount = cycleCount;
    }
    public int getCycleCount() {
        return cycleCount;
    }
    public void call() {
        if (cycleCount == 0) {
            System.err.println("the watch was called in zero cycle count");
            return;
        }
        if (!wasCalled)
            onStart();
        wasCalled = true;
        if (previousClick / duration < PlayingState.getClock() / duration) {
            ActionEvent event = new ActionEvent(this, null);
            eventHandler.handle(event);
            if (cycleCount != -1)
                cycleCount--;
            if (cycleCount == 0)
                onEnd();
        }
        previousClick = PlayingState.getClock();
    }
    protected void onEnd() {
    }
    protected void onStart() {
    }
}
