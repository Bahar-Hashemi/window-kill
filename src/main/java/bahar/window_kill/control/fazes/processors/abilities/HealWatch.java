package bahar.window_kill.control.fazes.processors.abilities;

import bahar.window_kill.control.Deck;

import static bahar.window_kill.control.Deck.*;

public class HealWatch extends AbilityWatch {
    public HealWatch() {
        super(30, event -> user.getEpsilon().setHP(user.getEpsilon().getHP() + 10));
        setCycleCount(1);
    }

    @Override
    public void call() {
        super.call();
    }

    @Override
    public String getName() {
        return "heal";
    }
}
