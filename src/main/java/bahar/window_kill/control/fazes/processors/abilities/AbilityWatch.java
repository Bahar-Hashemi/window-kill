package bahar.window_kill.control.fazes.processors.abilities;

import bahar.window_kill.model.Watch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static bahar.window_kill.control.Deck.abilities;

public class AbilityWatch extends Watch {
    final String name;
    final int price;
    public AbilityWatch(int duration, Runnable runnable, String name, int price) {
        super(duration, runnable);
        this.name = name;
        this.price = price;
    }
    protected void onEnd() {
        abilities.remove(this);
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
}
