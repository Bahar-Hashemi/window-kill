package bahar.window_kill.server.control.game.processors.abilities.shop;

import bahar.window_kill.server.model.Game;
import bahar.window_kill.client.model.User;
import bahar.window_kill.server.control.game.processors.abilities.AbilityType;
import bahar.window_kill.server.control.game.processors.abilities.AbilityWatch;

public class EmpowerWatch extends AbilityWatch {
    long spawnDuration;
    public EmpowerWatch(Game game, User user) {
        super(game, user, 30, AbilityType.EMPOWER, 75);
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
