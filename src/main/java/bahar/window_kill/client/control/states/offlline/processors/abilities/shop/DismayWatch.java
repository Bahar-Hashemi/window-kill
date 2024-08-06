package bahar.window_kill.client.control.states.offlline.processors.abilities.shop;

import bahar.window_kill.client.control.states.offlline.processors.abilities.AbilityWatch;
import bahar.window_kill.client.control.states.offlline.processors.abilities.AbilityType;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.entities.Entity;

public class DismayWatch extends AbilityWatch {
    public DismayWatch(Game game, User user) {
        super(game, user, 30, AbilityType.DISMAY, 120);
        setCycleCount(333);
    }

    @Override
    protected void onCall() {
        super.onCall();
        for (Entity entity: game.entities)
            if (dist(user.getEpsilon(), entity) < 120)
                entity.impactFrom(user.getEpsilon().getSceneX(), user.getEpsilon().getSceneY(), 15);
    }

    private static double dist(Entity first, Entity second) {
        double dx = first.getSceneX() - second.getSceneX();
        double dy = first.getSceneY() - second.getSceneY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
