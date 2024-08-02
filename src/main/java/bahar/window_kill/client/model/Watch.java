package bahar.window_kill.client.model;

public class Watch {
    long previousClick = System.currentTimeMillis();
    private long duration;
    private int cycleCount = -1;
    private boolean wasCalled = false;
    public Watch(int duration) {
        this.duration = duration;
    }
    public void setCycleCount(int cycleCount) {
        this.cycleCount = cycleCount;
    }
    public int getCycleCount() {
        return cycleCount;
    }
    public void call(long clock) {
        if (cycleCount == 0) {
            System.err.println("the watch was called in zero cycle count");
            return;
        }
        if (!wasCalled)
            onStart();
        wasCalled = true;
        if (previousClick / duration < clock / duration) {
            onCall();
            if (cycleCount != -1)
                cycleCount--;
            if (cycleCount == 0)
                onEnd();
        }
        previousClick = clock;
    }
    protected void onEnd() {
    }
    protected void onStart() {
    }
    protected void onCall() {
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }
    public long getDuration() {
        return duration;
    }
}
