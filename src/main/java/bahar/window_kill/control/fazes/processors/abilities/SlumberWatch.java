package bahar.window_kill.control.fazes.processors.abilities;

import bahar.window_kill.control.Deck;
import bahar.window_kill.control.fazes.processors.AggressionProcessor;
import bahar.window_kill.control.fazes.processors.MovementProcessor;
import bahar.window_kill.control.fazes.processors.SpawnProcessor;

public class SlumberWatch extends AbilityWatch {
    public SlumberWatch() {
        super(30, e -> {});
        setCycleCount(333);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AggressionProcessor.setLocked(true);
        MovementProcessor.setLocked(true);
        SpawnProcessor.setLocked(true);
        Deck.coolDown += 10 * 1000;
    }

    @Override
    protected void onEnd() {
        super.onEnd();
        AggressionProcessor.setLocked(false);
        MovementProcessor.setLocked(false);
        SpawnProcessor.setLocked(false);
    }

    @Override
    public String getName() {
        return "slumber";
    }
}
