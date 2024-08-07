package bahar.window_kill.communications.model.entities.generators;

import bahar.window_kill.communications.model.Bounds;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.processors.util.strategies.attacks.SpawnStrategy;
import bahar.window_kill.communications.processors.util.strategies.attacks.Strategy;
import javafx.scene.Node;

public abstract class GeneratorEntity extends Entity {
    protected GeneratorEntity(boolean isViewable, String id, Node view, Bounds bounds, String className, int HP, boolean canImpact, Strategy strategy) {
        super(isViewable, id, view, bounds, className, HP, canImpact, strategy);
    }
    abstract public Entity makeBullet();
    public void setSpawnDuration(long speed) {
        ((SpawnStrategy) strategy).setSpawnDuration(speed);
    }
    public long getSpawnDuration() {
        return ((SpawnStrategy) strategy).getSpawnDuration();
    }
}
