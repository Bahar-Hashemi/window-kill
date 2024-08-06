package bahar.window_kill.client.control.states.offlline.processors.abilities.skills;

import bahar.window_kill.client.control.states.offlline.processors.abilities.AbilityType;
import bahar.window_kill.client.control.states.offlline.processors.abilities.AbilityWatch;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;

public class AcesoWatch extends AbilityWatch {
    public AcesoWatch(Game game, User user) {
        super(game, user, 1000, AbilityType.ACESO, 500);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user.coolDown += 2 * 60 * 1000;
    }

    @Override
    protected void onCall() {
        super.onCall();
        user.getEpsilon().setHP(user.getEpsilon().getHP() + 1);
    }
}
