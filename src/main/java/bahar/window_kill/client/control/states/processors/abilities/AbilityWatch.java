package bahar.window_kill.client.control.states.processors.abilities;

import bahar.window_kill.client.model.Watch;


public class AbilityWatch extends Watch {
    final String name;
    final int price;
    public AbilityWatch(int duration, Runnable runnable, String name, int price) {
        super(duration, runnable);
        this.name = name;
        this.price = price;
    }
    protected void onEnd() {
//        user.abilities.remove(this);
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
}
