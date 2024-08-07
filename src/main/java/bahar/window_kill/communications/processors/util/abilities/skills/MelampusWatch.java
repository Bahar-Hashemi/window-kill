package bahar.window_kill.communications.processors.util.abilities.skills;

import bahar.window_kill.communications.processors.util.abilities.AbilityType;
import bahar.window_kill.communications.processors.util.abilities.AbilityWatch;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;

import java.util.Random;

public class MelampusWatch extends AbilityWatch {
    private double lastHP;
    public MelampusWatch(Game game, User user) {
        super(game, user,30, AbilityType.MELAMPUS, 750);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lastHP = user.getEpsilon().getHP();
    }

    @Override
    protected void onCall() {
        super.onCall();
        if (new Random().nextDouble(0, 1) > 0.80 && user.getEpsilon().getHP() < lastHP) {
            user.getEpsilon().setHP(lastHP);
        }
        lastHP = user.getEpsilon().getHP();
    }
}
