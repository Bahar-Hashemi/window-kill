package bahar.window_kill.model.bosswatch;

import bahar.window_kill.control.Deck;
import bahar.window_kill.control.util.GameUtil;
import bahar.window_kill.model.boards.MainBoard;
import bahar.window_kill.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.model.entities.generators.shooters.SmileyFace;
import bahar.window_kill.model.entities.generators.shooters.SmileyHand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PowerPunchWatch extends BossWatch { //todo correct powerPunch later
    static int counter = 0;
    public PowerPunchWatch(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(30, makeRunnable(face, leftHand, rightHand), face, leftHand, rightHand);
        counter = 0;
        setCycleCount(60);
    }

    private static Runnable makeRunnable(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        return () -> {
            counter++;
            double change = (counter > 30)? -12: 12;
            leftHand.setSceneX(leftHand.getSceneX() + change);
            rightHand.setSceneX(rightHand.getSceneX() - change);
            //مقادیری تفمال
            boolean leftIntersect = GameUtil.intersects(Deck.mainBoard, leftHand.getBoard());
            boolean rightIntersect = GameUtil.intersects(Deck.mainBoard, rightHand.getBoard());
            if (leftIntersect && rightIntersect) {
                Deck.mainBoard.lockWidth(Deck.mainBoard.getWidth() - 10);
                Deck.mainBoard.IndependentMoveX(Deck.mainBoard.getLayoutX() + 5);
            }
            else if (leftIntersect)
                Deck.mainBoard.IndependentMoveX(Deck.mainBoard.getLayoutX() + 5);
            else if (rightIntersect)
                Deck.mainBoard.IndependentMoveX(Deck.mainBoard.getLayoutX() - 5);
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        leftHand.punch();
        rightHand.punch();
        leftHand.getBoard().setHovering(false);
        rightHand.getBoard().setHovering(false);
    }

    @Override
    protected void onEnd() {
        super.onEnd();
        leftHand.getBoard().setHovering(true);
        leftHand.free();
        rightHand.getBoard().setHovering(true);
        rightHand.free();
    }

    @Override
    public boolean isValid(Epsilon epsilon, MainBoard mainBoard) {
        return leftHand.getHP() > 0 && leftHand.getAggressionStrategy() == null &&
                rightHand.getHP() > 0 && rightHand.getAggressionStrategy() == null &&
                !GameUtil.intersects(leftHand.getBoard(), mainBoard) &&
                !GameUtil.intersects(rightHand.getBoard(), mainBoard);
    }

    @Override
    public String getMessage() {
        return "PowerPunch";
    }
}
