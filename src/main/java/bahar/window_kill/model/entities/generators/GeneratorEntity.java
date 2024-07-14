package bahar.window_kill.model.entities.generators;

import bahar.window_kill.control.fazes.processors.strategies.strategies.SpawnStrategy;
import bahar.window_kill.control.fazes.processors.strategies.strategies.Strategy;
import bahar.window_kill.model.entities.Entity;
import javafx.scene.Node;

public abstract class GeneratorEntity extends Entity {
    protected GeneratorEntity(Node view, int HP, boolean canImpact, Strategy strategy) {
        super(view, HP, canImpact, strategy);
    }
    abstract public Entity makeBullet();
    public void setSpawnDuration(long speed) {
        ((SpawnStrategy) strategy).setSpawnDuration(speed);
    }
    public long getSpawnDuration() {
        return ((SpawnStrategy) strategy).getSpawnDuration();
    }
}
