package bahar.window_kill.client.control.states.offlline.processors.abilities.shop;

import bahar.window_kill.client.control.states.offlline.processors.abilities.AbilityType;
import bahar.window_kill.client.control.states.offlline.processors.abilities.AbilityWatch;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;

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
