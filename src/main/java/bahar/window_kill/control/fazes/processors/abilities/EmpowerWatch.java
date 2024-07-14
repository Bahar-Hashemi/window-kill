package bahar.window_kill.control.fazes.processors.abilities;

import static bahar.window_kill.control.Deck.user;

public class EmpowerWatch extends AbilityWatch {
    long spawnDuration;
    public EmpowerWatch() {
        super(30, event -> {});
        setCycleCount(333);
    }

    @Override
    protected void onStart() {
        super.onStart();
        spawnDuration = user.getEpsilon().getSpawnDuration();
        user.getEpsilon().setSpawnDuration(spawnDuration / 3);
    }

    @Override
    protected void onEnd() {
        super.onEnd();
        user.getEpsilon().setSpawnDuration(spawnDuration);
    }

    @Override
    public String getName() {
        return "empower";
    }
}
