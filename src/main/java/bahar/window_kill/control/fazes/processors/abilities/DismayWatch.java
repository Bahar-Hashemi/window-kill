package bahar.window_kill.control.fazes.processors.abilities;

import bahar.window_kill.model.entities.Entity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static bahar.window_kill.control.Deck.entities;
import static bahar.window_kill.control.Deck.user;

public class DismayWatch extends AbilityWatch {
    public DismayWatch() {
        super(30, makeEventHandler());
        setCycleCount(333);
    }
    private static EventHandler<ActionEvent> makeEventHandler() {
        return event -> {
            for (Entity entity: entities)
                if (dist(user.getEpsilon(), entity) < 120)
                    entity.impactFrom(user.getEpsilon().getSceneX(), user.getEpsilon().getSceneY(), 15);
        };
    }
    private static double dist(Entity first, Entity second) {
        double dx = first.getSceneX() - second.getSceneX();
        double dy = first.getSceneY() - second.getSceneY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    @Override
    public String getName() {
        return "dismay";
    }
}
