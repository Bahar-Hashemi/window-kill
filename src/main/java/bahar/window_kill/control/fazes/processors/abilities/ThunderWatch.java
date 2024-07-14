package bahar.window_kill.control.fazes.processors.abilities;

import static bahar.window_kill.control.Deck.*;

public class ThunderWatch extends AbilityWatch {
    public ThunderWatch() {
        super(30, event -> {});
        setCycleCount(30);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user.getEpsilon().setBulletDamage(user.getEpsilon().getBulletDamage() * 10);
        coolDown += 120 * 1000;
    }

    @Override
    protected void onEnd() {
        super.onEnd();
        user.getEpsilon().setBulletDamage(user.getEpsilon().getBulletDamage() / 10);
    }

    @Override
    public String getName() {
        return "thunder";
    }
}
