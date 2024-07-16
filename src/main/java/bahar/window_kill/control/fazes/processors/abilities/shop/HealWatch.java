package bahar.window_kill.control.fazes.processors.abilities.shop;

import bahar.window_kill.control.Deck;
import bahar.window_kill.control.fazes.processors.abilities.AbilityWatch;

import static bahar.window_kill.control.Deck.*;

public class HealWatch extends AbilityWatch {
    public HealWatch() {
        super(30, () -> user.getEpsilon().setHP(user.getEpsilon().getHP() + 10), "Heal", 50);
        setCycleCount(1);
    }

    @Override
    public void call() {
        super.call();
    }

}
