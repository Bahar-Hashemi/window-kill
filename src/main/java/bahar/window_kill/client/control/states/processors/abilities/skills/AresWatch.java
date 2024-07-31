package bahar.window_kill.client.control.states.processors.abilities.skills;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;

public class AresWatch extends AbilityWatch {
    public AresWatch() {
        super(30, () -> {}, "Ares", 750);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        user.getEpsilon().setBulletDamage(user.getEpsilon().getBulletDamage() + 1);
//        coolDown += 2 * 60 * 1000;
    }

}
