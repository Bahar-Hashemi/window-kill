package bahar.window_kill.control.fazes.processors.abilities.skills;

import bahar.window_kill.control.fazes.processors.abilities.AbilityWatch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static bahar.window_kill.control.Deck.user;

public class AstrapeWatch extends AbilityWatch {
    public AstrapeWatch() {
        super(30, () -> {}, "Astrape", 1000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user.getEpsilon().setDamage(user.getEpsilon().getDamage() + 10);
    }
}
