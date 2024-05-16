package bahar.window_kill.model.entities;

import bahar.window_kill.model.MainBoard;
import javafx.geometry.Bounds;
import javafx.scene.Node;

abstract public class Enemy extends Entity {
    final int attackDamage;

    protected Enemy(int attackDamage) {
        this.attackDamage = attackDamage;
    }
    abstract public void processMovement(MainBoard mainBoard);
    public int getAttackDamage()
    {
        return attackDamage;
    }
    abstract public void addCollectible(MainBoard mainBoard);
}
