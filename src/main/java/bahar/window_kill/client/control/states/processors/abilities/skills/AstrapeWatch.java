package bahar.window_kill.client.control.states.processors.abilities.skills;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;

public class AstrapeWatch extends AbilityWatch {
    public AstrapeWatch() {
        super(30, () -> {}, "Astrape", 1000);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        user.getEpsilon().setDamage(user.getEpsilon().getDamage() + 10);
    }
}
