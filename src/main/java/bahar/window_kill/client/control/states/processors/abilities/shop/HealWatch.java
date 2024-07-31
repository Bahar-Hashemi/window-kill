package bahar.window_kill.client.control.states.processors.abilities.shop;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;

public class HealWatch extends AbilityWatch {
    public HealWatch() {
        super(30, () -> {
//            user.getEpsilon().setHP(user.getEpsilon().getHP() + 10)
        }, "Heal", 50);
        setCycleCount(1);
    }


}
