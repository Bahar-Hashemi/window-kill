package bahar.window_kill.control.fazes.processors.abilities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static bahar.window_kill.control.Deck.*;

public class AcesoWatch extends AbilityWatch {
    public AcesoWatch() {
        super(1000, event -> user.getEpsilon().setHP(user.getEpsilon().getHP() + 1));
    }

    @Override
    public String getName() {
        return "Writ of Aceso";
    }
}
