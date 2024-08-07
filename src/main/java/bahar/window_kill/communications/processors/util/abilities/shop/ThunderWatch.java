package bahar.window_kill.communications.processors.util.abilities.shop;

import bahar.window_kill.communications.processors.util.abilities.AbilityType;
import bahar.window_kill.communications.processors.util.abilities.AbilityWatch;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;

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
