package bahar.window_kill.communications.processors.util.abilities.skills;

import bahar.window_kill.communications.processors.util.abilities.AbilityType;
import bahar.window_kill.communications.processors.util.abilities.AbilityWatch;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;

public class AresWatch extends AbilityWatch {
    public AresWatch(Game game, User user) {
        super(game, user, 30, AbilityType.ARES, 750);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user.getEpsilon().setBulletDamage(user.getEpsilon().getBulletDamage() + 1);
        user.coolDown += 2 * 60 * 1000;
    }

}
