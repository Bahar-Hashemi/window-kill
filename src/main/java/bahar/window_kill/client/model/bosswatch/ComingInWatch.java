package bahar.window_kill.client.model.bosswatch;

import bahar.window_kill.client.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.client.model.entities.generators.shooters.SmileyHand;
import bahar.window_kill.client.model.boards.MainBoard;
import bahar.window_kill.client.model.entities.generators.shooters.SmileyFace;

public class ComingInWatch extends BossWatch {
    public ComingInWatch(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(60, makeRunnable(face, leftHand, rightHand), face, leftHand, rightHand);
        setCycleCount(300);
    }
    private static Runnable makeRunnable(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        return () -> {
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
