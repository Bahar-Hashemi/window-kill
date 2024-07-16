package bahar.window_kill.control.fazes.processors.abilities.shop;

import bahar.window_kill.control.fazes.processors.abilities.AbilityWatch;

import static bahar.window_kill.control.Deck.user;

public class EmpowerWatch extends AbilityWatch {
    long spawnDuration;
    public EmpowerWatch() {
        super(30, () -> {}, "Empower", 75);
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
}
