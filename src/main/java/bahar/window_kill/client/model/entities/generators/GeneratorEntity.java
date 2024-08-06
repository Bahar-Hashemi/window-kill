package bahar.window_kill.client.model.entities.generators;

import bahar.window_kill.client.model.Bounds;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.control.states.offlline.processors.strategies.strategies.SpawnStrategy;
import bahar.window_kill.client.control.states.offlline.processors.strategies.strategies.Strategy;
import javafx.scene.Node;

public abstract class GeneratorEntity extends Entity {
    protected GeneratorEntity(String id, Node view, Bounds bounds,  int HP, boolean canImpact, Strategy strategy) {
        super(id, view, bounds, HP, canImpact, strategy);
    }
    abstract public Entity makeBullet();
    public void setSpawnDuration(long speed) {
        ((SpawnStrategy) strategy).setSpawnDuration(speed);
    }
    public long getSpawnDuration() {
        return ((SpawnStrategy) strategy).getSpawnDuration();
    }
}
