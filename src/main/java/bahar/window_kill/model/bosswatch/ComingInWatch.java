package bahar.window_kill.model.bosswatch;

import bahar.window_kill.model.boards.MainBoard;
import bahar.window_kill.model.entities.generators.shooters.SmileyFace;
import bahar.window_kill.model.entities.generators.shooters.SmileyHand;
import bahar.window_kill.model.entities.generators.shooters.Epsilon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ComingInWatch extends BossWatch {
    public ComingInWatch(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(60, makeEventHandler(face, leftHand, rightHand), face, leftHand, rightHand);
        setCycleCount(300);
    }
    private static EventHandler<ActionEvent> makeEventHandler(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        return event -> {
            face.setSceneLocation(face.getSceneX(), face.getSceneY() + 1);
            leftHand.setSceneLocation(leftHand.getSceneX() + 1, leftHand.getSceneY());
            rightHand.setSceneLocation(rightHand.getSceneX() - 1, rightHand.getSceneY());
        };
    }

    @Override
    public boolean isValid(Epsilon epsilon, MainBoard mainBoard) {
        return true;
    }

    @Override
    public String getMessage() {
        return "Boss is coming for you!";
    }
}
