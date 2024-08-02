package bahar.window_kill.client.control.states.processors.abilities.skills;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;

import java.util.Random;

public class MelampusWatch extends AbilityWatch {
    static double lastHP;
    public MelampusWatch(Game game, User user) {
        super(game, user,30, () -> {
            if (new Random().nextDouble(0, 1) > 0.80 && user.getEpsilon().getHP() < lastHP) {
                user.getEpsilon().setHP(lastHP);
            }
            lastHP = user.getEpsilon().getHP();
        }, "Melampus", 750);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        lastHP = user.getEpsilon().getHP();
    }

}
