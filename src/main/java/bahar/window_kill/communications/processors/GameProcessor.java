package bahar.window_kill.communications.processors;

import bahar.window_kill.communications.model.Game;

abstract public class GameProcessor { //implement Observer Pattern
    protected final Game game;
    protected final boolean isViewable;
    public GameProcessor(boolean isViewable, Game game) {
        this.isViewable = isViewable;
        this.game = game;
    }
    abstract public void run();
}
