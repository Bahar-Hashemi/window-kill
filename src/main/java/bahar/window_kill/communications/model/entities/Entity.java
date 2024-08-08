package bahar.window_kill.communications.model.entities;

import bahar.window_kill.communications.model.Bounds;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.processors.util.strategies.attacks.Strategy;
import bahar.window_kill.communications.processors.util.strategies.movements.ImpactStrategy;
import bahar.window_kill.communications.model.GameElement;
import javafx.scene.Node;

public class Entity extends GameElement {
    protected double HP;
    transient protected final ImpactStrategy impactStrategy;
    transient protected Strategy strategy;
    transient protected Game game;
    protected Entity(boolean isViewable, String id, Node view, Bounds bounds, String className, int HP, boolean canImpact, Strategy strategy) {
        super(isViewable, id, view, bounds, className);
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
    public void move(){}
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
    public void aggress(){}
    public void morph(){
        if (isViewable)
            view.setOpacity(opacity);
    }
    public void shout(){}
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
