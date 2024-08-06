package bahar.window_kill.server.control.game.processors.abilities.shop;

import bahar.window_kill.server.model.Game;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.server.control.game.processors.abilities.AbilityType;
import bahar.window_kill.server.control.game.processors.abilities.AbilityWatch;

public class BanishWatch extends AbilityWatch {
    public BanishWatch(Game game, User user) {
        super(game, user, 30, AbilityType.BANISH, 100);
        setCycleCount(1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (Entity entity: game.entities)
            entity.impactFrom(user.getEpsilon().getSceneX(), user.getEpsilon().getSceneY(), 100);
    }

}
