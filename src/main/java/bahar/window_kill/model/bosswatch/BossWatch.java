package bahar.window_kill.model.bosswatch;

import bahar.window_kill.model.Watch;
import bahar.window_kill.model.entities.SmileyFace;
import bahar.window_kill.model.entities.SmileyHand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BossWatch extends Watch {
    protected final SmileyFace face;
    protected final SmileyHand leftHand, rightHand;
    public BossWatch(int duration, EventHandler<ActionEvent> eventHandler, SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(duration, eventHandler);
        this.face = face;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }
}
