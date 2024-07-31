package bahar.window_kill.client.control.states.processors;

import bahar.window_kill.client.model.Deck;

abstract public class GameProcessor { //implement Observer Pattern
    protected final Deck deck;
    public GameProcessor(Deck deck) {
        this.deck = deck;
    }
    abstract public void run();
}
