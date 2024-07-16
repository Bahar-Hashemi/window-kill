package bahar.window_kill.model.bosswatch;

import bahar.window_kill.model.Watch;
import bahar.window_kill.model.boards.MainBoard;
import bahar.window_kill.model.entities.generators.shooters.SmileyFace;
import bahar.window_kill.model.entities.generators.shooters.SmileyHand;
import bahar.window_kill.model.entities.generators.shooters.Epsilon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

abstract public class BossWatch extends Watch {
    protected final SmileyFace face;
    protected final SmileyHand leftHand, rightHand;
    public BossWatch(int duration, Runnable runnable, SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(duration, runnable);
        this.face = face;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }
    abstract public boolean isValid(Epsilon epsilon, MainBoard mainBoard);
    abstract public String getMessage();
}
