package bahar.window_kill.control.fazes.processors.abilities.skills;

import bahar.window_kill.control.Deck;
import bahar.window_kill.control.fazes.processors.abilities.AbilityWatch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AthenaWatch extends AbilityWatch {
    public AthenaWatch() {
        super(30, () -> {}, "Athena", 1200);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Deck.shrink *= 0.8;
    }
}
