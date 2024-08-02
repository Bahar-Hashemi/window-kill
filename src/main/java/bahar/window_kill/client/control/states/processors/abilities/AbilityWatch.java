package bahar.window_kill.client.control.states.processors.abilities;

import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.Watch;


public class AbilityWatch extends Watch {
    final String name;
    final int price;
    protected final Game game;
    protected final User user;
    public AbilityWatch(Game game, User user, int duration, String name, int price) {
        super(duration);
        this.name = name;
        this.price = price;
        this.game = game;
        this.user = user;
    }
    protected void onEnd() {
        user.abilities.remove(this);
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
}
