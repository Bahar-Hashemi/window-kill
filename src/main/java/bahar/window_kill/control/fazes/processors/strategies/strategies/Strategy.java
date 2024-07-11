package bahar.window_kill.control.fazes.processors.strategies.strategies;

import bahar.window_kill.model.entities.Entity;

abstract public class Strategy {


    public void onAct(Entity aggressionSource) {}
    public abstract void act(Entity aggressionSource);
}
