package bahar.window_kill.client.control.states.processors.strategies.strategies;

import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.model.entities.Entity;

abstract public class Strategy {


    public void onAct(Entity aggressionSource) {}
    public abstract void act(Entity aggressionSource, Deck deck);
}
