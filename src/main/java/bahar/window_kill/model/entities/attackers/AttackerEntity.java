package bahar.window_kill.model.entities.attackers;

import bahar.window_kill.control.fazes.processors.strategies.strategies.Strategy;
import bahar.window_kill.model.entities.Entity;
import javafx.scene.Node;

public abstract class AttackerEntity extends Entity {
    protected int damage;
    protected AttackerEntity(Node view, int HP, boolean canImpact, Strategy strategy, int damage) {
        super(view, HP, canImpact, strategy);
        this.damage = damage;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
