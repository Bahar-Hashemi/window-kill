package bahar.window_kill.model.entities;

import bahar.window_kill.control.fazes.processors.strategies.strategies.Strategy;
import bahar.window_kill.control.fazes.processors.strategies.movements.ImpactStrategy;
import javafx.scene.Node;

abstract public class Entity {
    protected double HP;
    protected final ImpactStrategy impactStrategy;
    protected final Node view;
    protected final Strategy strategy;
    protected Entity(Node view, int HP, boolean canImpact, Strategy strategy) {
        this.view = view;
        this.HP = HP;
        impactStrategy = new ImpactStrategy(canImpact);
        this.strategy = strategy;
    }
    public void setLayoutX(double x) {
        view.setLayoutX(x);
    }
    public void setLayoutY(double y) {
        view.setLayoutY(y);
    }
    public void setLocation(double x, double y) {
        setLayoutX(x);
        setLayoutY(y);
    }
    public double getCenterOnSceneX() {
        return view.getBoundsInParent().getCenterX() + view.getParent().getLayoutX();
    }
    public double getCenterOnSceneY() {
        return view.getBoundsInParent().getCenterY() + view.getParent().getLayoutY();
    }
    public abstract void move(double targetX, double targetY);
    public Node getView() {
        return view;
    }
    public void setSceneLocation(double x, double y) {setSceneX(x);setSceneY(y);}
    public void setSceneX(double x) {
        view.setLayoutX(x - view.getParent().getLayoutX());
    }
    public void setSceneY(double y) {
        view.setLayoutY(y - view.getParent().getLayoutY());
    }
    public double getSceneX() {
        return view.getParent().getLayoutX() + view.getLayoutX();
    }
    public double getSceneY() {
        return view.getParent().getLayoutY() + view.getLayoutY();
    }
    public double getHP() {
        return HP;
    }
    public void setHP(double HP) {
        this.HP = HP;
    }
    public Strategy getAggressionStrategy() {
        return strategy;
    }
    public abstract void aggress();
    public abstract void shout();
    public boolean canImpact() {
        return impactStrategy.canImpact();
    }
    public void impactFrom(double sourceX, double sourceY) {
        impactStrategy.impact(sourceX, sourceY, this);
    }
}
