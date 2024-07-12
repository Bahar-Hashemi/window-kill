package bahar.window_kill.model.bosswatch;

import bahar.window_kill.model.Watch;
import bahar.window_kill.model.entities.SmileyFace;
import bahar.window_kill.model.entities.SmileyHand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ComingInWatch extends BossWatch {
    public ComingInWatch(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(60, makeEventHandler(face, leftHand, rightHand), face, leftHand, rightHand);
        setCycleCount(500);
    }
    private static EventHandler<ActionEvent> makeEventHandler(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        return event -> {
            face.setSceneLocation(face.getSceneX(), face.getSceneY() + 1);
            leftHand.setSceneLocation(leftHand.getSceneX() + 1, leftHand.getSceneY());
            rightHand.setSceneLocation(rightHand.getSceneX() - 1, rightHand.getSceneY());
        };
    }
}
