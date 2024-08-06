package bahar.window_kill.client.control.states.offlline.processors;

import bahar.window_kill.client.model.Game;

abstract public class GameProcessor { //implement Observer Pattern
    protected final Game game;
    public GameProcessor(Game game) {
        this.game = game;
    }
    abstract public void run();
}
