package bahar.window_kill.client.control.states.processors.abilities.shop;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;

public class SlumberWatch extends AbilityWatch {
    public SlumberWatch() {
        super(30, () -> {}, "Slumber", 150);
        setCycleCount(333);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Deck.isLocked = true;
    }

    @Override
    protected void onEnd() {
        super.onEnd();
//        Deck.isLocked = false;
    }

}
