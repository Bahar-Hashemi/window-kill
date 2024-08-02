package bahar.window_kill.client.control.states.processors.abilities.shop;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;

public class HealWatch extends AbilityWatch {
    public HealWatch(Game game, User user) {
        super(game, user, 30, "Heal", 50);
        setCycleCount(1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user.getEpsilon().setHP(user.getEpsilon().getHP() + 10);
    }
}
