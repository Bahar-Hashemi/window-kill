package bahar.window_kill.model.entities.collectables;

import bahar.window_kill.model.entities.Entity;
import javafx.scene.Node;

public abstract class Collectable extends Entity {
    int xp;
    abstract void setXp();
    public int getXp() {
        return xp;
    }
}
