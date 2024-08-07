package bahar.window_kill.communications.processors.util.abilities.shop;

import bahar.window_kill.communications.processors.util.abilities.AbilityType;
import bahar.window_kill.communications.processors.util.abilities.AbilityWatch;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.entities.Entity;

public class BanishWatch extends AbilityWatch {
    public BanishWatch(Game game, User user) {
        super(game, user, 30, AbilityType.BANISH, 100);
        setCycleCount(1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (Entity entity: game.entities)
            entity.impactFrom(user.getEpsilon().getX(), user.getEpsilon().getY(), 100);
    }

}
