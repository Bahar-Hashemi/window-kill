package bahar.window_kill.server.control.game.processors.abilities.shop;

import bahar.window_kill.server.model.Game;
import bahar.window_kill.client.model.User;
import bahar.window_kill.server.control.game.processors.abilities.AbilityType;
import bahar.window_kill.server.control.game.processors.abilities.AbilityWatch;

public class ThunderWatch extends AbilityWatch {
    public ThunderWatch(Game game, User user) {
        super(game, user,30, AbilityType.THUNDER, 200);
        setCycleCount(30);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user.getEpsilon().setBulletDamage(user.getEpsilon().getBulletDamage() * 10);
    }

    @Override
    protected void onEnd() {
        super.onEnd();
        user.getEpsilon().setBulletDamage(user.getEpsilon().getBulletDamage() / 10);
    }
}
