package bahar.window_kill.client.control.states.processors.abilities.shop;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;

public class ThunderWatch extends AbilityWatch {
    public ThunderWatch() {
        super(30, () -> {}, "Thunder", 200);
        setCycleCount(30);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        user.getEpsilon().setBulletDamage(user.getEpsilon().getBulletDamage() * 10);
    }

    @Override
    protected void onEnd() {
        super.onEnd();
//        user.getEpsilon().setBulletDamage(user.getEpsilon().getBulletDamage() / 10);
    }
}
