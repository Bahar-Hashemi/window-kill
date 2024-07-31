package bahar.window_kill.client.control.states.processors.abilities.skills;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;

public class AcesoWatch extends AbilityWatch {
    public AcesoWatch() {
        super(1000, () -> {
//            user.getEpsilon().setHP(user.getEpsilon().getHP() + 1);
        }, "Aceso", 500);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        user.coolDown += 2 * 60 * 1000;
    }
}
