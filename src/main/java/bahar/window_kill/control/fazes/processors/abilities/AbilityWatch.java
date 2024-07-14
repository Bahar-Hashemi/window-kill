package bahar.window_kill.control.fazes.processors.abilities;

import bahar.window_kill.model.Watch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static bahar.window_kill.control.Deck.abilities;

abstract public class AbilityWatch extends Watch {
    public AbilityWatch(int duration, EventHandler<ActionEvent> eventHandler) {
        super(duration, eventHandler);
    }
    abstract public String getName();
    protected void onEnd() {
        abilities.remove(this);
    }
}
