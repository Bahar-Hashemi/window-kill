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
                entity.impactFrom(user.getEpsilon().getX(), user.getEpsilon().getY(), 15);
    }

    private static double dist(Entity first, Entity second) {
        double dx = first.getX() - second.getX();
        double dy = first.getY() - second.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
