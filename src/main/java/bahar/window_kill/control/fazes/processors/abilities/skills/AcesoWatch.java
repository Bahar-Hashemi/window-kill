package bahar.window_kill.control.fazes.processors.abilities.skills;

import bahar.window_kill.control.fazes.processors.abilities.AbilityWatch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static bahar.window_kill.control.Deck.*;

public class AcesoWatch extends AbilityWatch {
    public AcesoWatch() {
        super(1000, () -> user.getEpsilon().setHP(user.getEpsilon().getHP() + 1), "Aceso", 500);
    }

    @Override
    protected void onStart() {
        super.onStart();
        coolDown += 2 * 60 * 1000;
    }
}
