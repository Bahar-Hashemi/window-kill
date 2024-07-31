package bahar.window_kill.client.control.states.processors.abilities.skills;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;

public class MelampusWatch extends AbilityWatch {
    static double lastHP;
    public MelampusWatch() {
        super(30, () -> {
//            if (new Random().nextDouble(0, 1) > 0.80 && user.getEpsilon().getHP() < lastHP) {
//                user.getEpsilon().setHP(lastHP);
//            }
//            lastHP = user.getEpsilon().getHP();
        }, "Melampus", 750);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        lastHP = user.getEpsilon().getHP();
    }

}
