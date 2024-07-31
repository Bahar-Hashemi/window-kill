package bahar.window_kill.client.control.states.processors.abilities.skills;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;

public class AthenaWatch extends AbilityWatch {
    public AthenaWatch() {
        super(30, () -> {}, "Athena", 1200);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Deck.shrink *= 0.8;
    }
}
