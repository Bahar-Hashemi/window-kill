package bahar.window_kill.server.control.game.processors.abilities.skills;

import bahar.window_kill.server.model.Game;
import bahar.window_kill.client.model.User;
import bahar.window_kill.server.control.game.processors.abilities.AbilityType;
import bahar.window_kill.server.control.game.processors.abilities.AbilityWatch;

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
