package bahar.window_kill.model;

import bahar.window_kill.control.Deck;
import bahar.window_kill.control.fazes.PlayingState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Watch {
    long previousClick = System.currentTimeMillis();
    private long duration;
    private final Runnable runnable;
    private int cycleCount = -1;
    private boolean wasCalled = false;
    public Watch(int duration, Runnable runnable) {
        this.duration = duration;
        this.runnable = runnable;
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
        if (previousClick / duration < Deck.clock / duration) {
            runnable.run();
            if (cycleCount != -1)
                cycleCount--;
            if (cycleCount == 0)
                onEnd();
        }
        previousClick = Deck.clock;
    }
    protected void onEnd() {
    }
    protected void onStart() {
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }
    public long getDuration() {
        return duration;
    }
}
