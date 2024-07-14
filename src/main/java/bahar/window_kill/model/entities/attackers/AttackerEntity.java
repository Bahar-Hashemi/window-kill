package bahar.window_kill.model.entities.attackers;

import bahar.window_kill.control.fazes.processors.strategies.strategies.Strategy;
import bahar.window_kill.model.entities.Entity;
import javafx.scene.Node;

public interface AttackerEntity {
    public int getDamage();
    public void setDamage(int damage);
}
