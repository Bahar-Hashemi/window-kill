package bahar.window_kill.client.control.states.processors.abilities.shop;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;
import bahar.window_kill.client.model.entities.Entity;

public class DismayWatch extends AbilityWatch {
    public DismayWatch() {
        super(30, makeRunnable(), "Dismay", 120);
        setCycleCount(333);
    }
    private static Runnable makeRunnable() {
        return () -> {
//            for (Entity entity: entities)
//                if (dist(user.getEpsilon(), entity) < 120)
//                    entity.impactFrom(user.getEpsilon().getSceneX(), user.getEpsilon().getSceneY(), 15);
        };
    }
    private static double dist(Entity first, Entity second) {
        double dx = first.getSceneX() - second.getSceneX();
        double dy = first.getSceneY() - second.getSceneY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
