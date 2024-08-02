package bahar.window_kill.client.control.states.processors.abilities.shop;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;

public class EmpowerWatch extends AbilityWatch {
    long spawnDuration;
    public EmpowerWatch(Game game, User user) {
        super(game, user, 30, "Empower", 75);
        setCycleCount(333);
    }

    @Override
    protected void onStart() {
        super.onStart();
        spawnDuration = user.getEpsilon().getSpawnDuration();
        user.getEpsilon().setSpawnDuration(spawnDuration / 3);
    }

    @Override
    protected void onEnd() {
        super.onEnd();
        user.getEpsilon().setSpawnDuration(spawnDuration);
    }
}
