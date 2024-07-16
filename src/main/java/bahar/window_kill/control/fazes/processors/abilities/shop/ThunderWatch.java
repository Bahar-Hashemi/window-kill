package bahar.window_kill.control.fazes.processors.abilities.shop;

import bahar.window_kill.control.fazes.processors.abilities.AbilityWatch;

import static bahar.window_kill.control.Deck.*;

public class ThunderWatch extends AbilityWatch {
    public ThunderWatch() {
        super(30, () -> {}, "Thunder", 200);
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
