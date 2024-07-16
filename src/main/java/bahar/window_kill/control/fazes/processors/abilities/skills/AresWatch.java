package bahar.window_kill.control.fazes.processors.abilities.skills;

import bahar.window_kill.control.fazes.processors.abilities.AbilityWatch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static bahar.window_kill.control.Deck.coolDown;
import static bahar.window_kill.control.Deck.user;

public class AresWatch extends AbilityWatch {
    public AresWatch() {
        super(30, () -> {}, "Ares", 750);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user.getEpsilon().setBulletDamage(user.getEpsilon().getBulletDamage() + 1);
        coolDown += 2 * 60 * 1000;
    }

}
