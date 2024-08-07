package bahar.window_kill.communications.model.bosswatch;

import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.communications.model.entities.generators.shooters.SmileyHand;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.communications.model.entities.generators.shooters.SmileyFace;

public class SqueezeWatch extends BossWatch {
    static int counter = 0;
    public SqueezeWatch(Game game, SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(30, game, face, leftHand, rightHand);
        setCycleCount(200);
        counter = 0;
    }

    @Override
    protected void onCall() {
        super.onCall();
        counter++;
        double change = (counter > 100)? -1: 1;
        leftHand.setX(leftHand.getX() + change);
        rightHand.setX(rightHand.getX() - change);
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
