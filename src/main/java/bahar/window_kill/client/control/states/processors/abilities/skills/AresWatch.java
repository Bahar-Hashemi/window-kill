package bahar.window_kill.client.control.states.processors.abilities.skills;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;

public class AresWatch extends AbilityWatch {
    public AresWatch(Game game, User user) {
        super(game, user, 30, "Ares", 750);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user.getEpsilon().setBulletDamage(user.getEpsilon().getBulletDamage() + 1);
        user.coolDown += 2 * 60 * 1000;
    }

}
