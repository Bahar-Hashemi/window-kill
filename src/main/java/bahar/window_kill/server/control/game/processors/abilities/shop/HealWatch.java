package bahar.window_kill.server.control.game.processors.abilities.shop;

import bahar.window_kill.server.model.Game;
import bahar.window_kill.client.model.User;
import bahar.window_kill.server.control.game.processors.abilities.AbilityType;
import bahar.window_kill.server.control.game.processors.abilities.AbilityWatch;

public class HealWatch extends AbilityWatch {
    public HealWatch(Game game, User user) {
        super(game, user, 30, AbilityType.HEAL, 50);
        setCycleCount(1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user.getEpsilon().setHP(user.getEpsilon().getHP() + 10);
    }
}
