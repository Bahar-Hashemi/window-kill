package bahar.window_kill.server.model.entities;

import bahar.window_kill.client.control.states.offlline.processors.strategies.movements.ImpactStrategy;
import bahar.window_kill.client.control.states.offlline.processors.strategies.strategies.Strategy;
import bahar.window_kill.client.model.Game;

abstract public class Entity {
    protected double HP;
    protected final ImpactStrategy impactStrategy;
    protected final double radius;
    double layoutX, layoutY;
    protected Strategy strategy;
    protected Game game;
    protected String id;
    protected Entity(double radius, int HP, boolean canImpact, Strategy strategy) {
        this.radius = radius;
        this.HP = HP;
        impactStrategy = new ImpactStrategy(canImpact);
        this.strategy = strategy;
    }
    public void setDeck(Game game) {
        this.game = game;
    }
    public void getDeck(Game game) {
        this.game = game;
    }
    public void setLayoutX(double x) {
        this.layoutX = x;
    }
    public void setLayoutY(double y) {
        this.layoutY = y;
    }
    public void setLocation(double x, double y) {
        setLayoutX(x);
        setLayoutY(y);
    }
    public abstract void move();

    public double getHP() {
        return HP;
    }
    public void setHP(double HP) {
        this.HP = HP;
    }
    public Strategy getAggressionStrategy() {
        return strategy;
    }
    public void setAggressionStrategy(Strategy strategy) { this.strategy = strategy; }
    public abstract void aggress();
    public abstract void shout();
    public boolean canImpact() {
        return impactStrategy.canImpact();
    }
    public void impactFrom(double sourceX, double sourceY) {
//        impactStrategy.impact(sourceX, sourceY, this);
    }
    public void impactFrom(double sourceX, double sourceY, int power) {
//        impactStrategy.impact(sourceX, sourceY, this, power);
    }

    public double getLayoutX() {
        return layoutX;
    }
    public double getLayoutY() {
        return layoutY;
    }
}
