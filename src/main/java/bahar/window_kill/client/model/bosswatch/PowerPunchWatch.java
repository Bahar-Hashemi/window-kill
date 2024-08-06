package bahar.window_kill.client.model.bosswatch;

import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.client.model.entities.generators.shooters.SmileyHand;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.model.boards.MainBoard;
import bahar.window_kill.client.model.entities.generators.shooters.SmileyFace;

public class PowerPunchWatch extends BossWatch { //todo correct powerPunch later
    static int counter = 0;
    public PowerPunchWatch(Game game, SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(30, game, face, leftHand, rightHand);
        counter = 0;
        setCycleCount(60);
    }

    @Override
    protected void onCall() {
        super.onCall();
            counter++;
            double change = (counter > 30)? -12: 12;
            leftHand.setX(leftHand.getX() + change);
            rightHand.setX(rightHand.getX() - change);
            //مقادیری تفمال
            for (User user: game.users) {
                MainBoard mainBoard = user.mainBoard;
                boolean leftIntersect = GameUtil.intersects(mainBoard, leftHand.getBoard());
                boolean rightIntersect = GameUtil.intersects(mainBoard, rightHand.getBoard());
                if (leftIntersect && rightIntersect) {
                    mainBoard.lockBoardWidth(mainBoard.getWidth() - 10);
                    mainBoard.setLayoutX(mainBoard.getX() + 5);
                }
                else if (leftIntersect)
                    mainBoard.setLayoutX(mainBoard.getX() + 5);
                else if (rightIntersect)
                    mainBoard.setLayoutX(mainBoard.getX() - 5);
            }
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
