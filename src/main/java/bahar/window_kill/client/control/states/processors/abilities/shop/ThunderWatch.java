package bahar.window_kill.client.control.states.processors.abilities.shop;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;

public class ThunderWatch extends AbilityWatch {
    public ThunderWatch(Game game, User user) {
        super(game, user,30, "Thunder", 200);
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
