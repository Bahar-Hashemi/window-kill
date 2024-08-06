package bahar.window_kill.client.model.entities;

import bahar.window_kill.client.model.Bounds;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.control.states.offlline.processors.strategies.strategies.Strategy;
import bahar.window_kill.client.control.states.offlline.processors.strategies.movements.ImpactStrategy;
import bahar.window_kill.client.model.GameElement;
import javafx.scene.Node;

abstract public class Entity extends GameElement {
    protected double HP;
    protected final ImpactStrategy impactStrategy;
    protected Strategy strategy;
    protected Game game;
    protected String id;
    protected Entity(String id, Node view, Bounds bounds, int HP, boolean canImpact, Strategy strategy) {
        super(id, view, bounds);
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
    public double getCenterOnSceneX() {
        return view.getBoundsInParent().getCenterX() + view.getParent().getLayoutX();
    }
    public double getCenterOnSceneY() {
        return view.getBoundsInParent().getCenterY() + view.getParent().getLayoutY();
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
        impactStrategy.impact(sourceX, sourceY, this);
    }
    public void impactFrom(double sourceX, double sourceY, int power) {
        impactStrategy.impact(sourceX, sourceY, this, power);
    }
}
