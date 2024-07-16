package bahar.window_kill.control.fazes.processors.abilities.skills;

import bahar.window_kill.control.fazes.processors.BoardsProcessor;
import bahar.window_kill.control.fazes.processors.abilities.AbilityWatch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Random;

import static bahar.window_kill.control.Deck.*;

public class MelampusWatch extends AbilityWatch {
    static double lastHP;
    public MelampusWatch() {
        super(30, () -> {
            if (new Random().nextDouble(0, 1) > 0.80 && user.getEpsilon().getHP() < lastHP) {
                user.getEpsilon().setHP(lastHP);
                BoardsProcessor.updateMainBoardLabel();
            }
            lastHP = user.getEpsilon().getHP();
        }, "Melampus", 750);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lastHP = user.getEpsilon().getHP();
    }

}
