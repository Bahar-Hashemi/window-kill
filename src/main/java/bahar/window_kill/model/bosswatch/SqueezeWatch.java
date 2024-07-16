package bahar.window_kill.model.bosswatch;

import bahar.window_kill.control.util.GameUtil;
import bahar.window_kill.model.boards.MainBoard;
import bahar.window_kill.model.entities.generators.shooters.SmileyFace;
import bahar.window_kill.model.entities.generators.shooters.SmileyHand;
import bahar.window_kill.model.entities.generators.shooters.Epsilon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SqueezeWatch extends BossWatch {
    static int counter = 0;
    public SqueezeWatch(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(30, makeRunnable(face, leftHand, rightHand), face, leftHand, rightHand);
        setCycleCount(200);
        counter = 0;
    }
    private static Runnable makeRunnable(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        return () -> {
            counter++;
            double change = (counter > 100)? -1: 1;
            leftHand.setSceneX(leftHand.getSceneX() + change);
            rightHand.setSceneX(rightHand.getSceneX() - change);
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        leftHand.getBoard().setHovering(false);
        rightHand.getBoard().setHovering(false);
    }

    @Override
    protected void onEnd() {
        super.onEnd();
        leftHand.getBoard().setHovering(true);
        rightHand.getBoard().setHovering(true);
    }

    @Override
    public boolean isValid(Epsilon epsilon, MainBoard mainBoard) {
        return leftHand.getHP() > 0 && rightHand.getHP() > 0 &&
                !GameUtil.intersects(leftHand.getBoard(), mainBoard) &&
                !GameUtil.intersects(rightHand.getBoard(), mainBoard);
    }

    @Override
    public String getMessage() {
        return "Squeezing";
    }
}
