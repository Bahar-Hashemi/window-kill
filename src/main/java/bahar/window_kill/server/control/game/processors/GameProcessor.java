package bahar.window_kill.server.control.game.processors;

import bahar.window_kill.server.model.Game;

abstract public class GameProcessor { //implement Observer Pattern
    protected final Game game;
    public GameProcessor(Game game) {
        this.game = game;
    }
    abstract public void run();
}
