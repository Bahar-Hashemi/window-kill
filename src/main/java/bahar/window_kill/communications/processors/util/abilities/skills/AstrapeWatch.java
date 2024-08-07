package bahar.window_kill.communications.processors.util.abilities.skills;

import bahar.window_kill.communications.processors.util.abilities.AbilityType;
import bahar.window_kill.communications.processors.util.abilities.AbilityWatch;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;

public class AstrapeWatch extends AbilityWatch {
    public AstrapeWatch(Game game, User user) {
        super(game, user, 30, AbilityType.ASTRAPE, 1000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user.getEpsilon().setDamage(user.getEpsilon().getDamage() + 10);
    }
}
