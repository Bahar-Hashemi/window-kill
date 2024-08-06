package bahar.window_kill.client.model.bosswatch;

import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.client.model.entities.generators.shooters.SmileyHand;
import bahar.window_kill.client.model.boards.MainBoard;
import bahar.window_kill.client.model.entities.generators.shooters.SmileyFace;

public class ComingInWatch extends BossWatch {
    public ComingInWatch(Game game, SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(60, game, face, leftHand, rightHand);
        setCycleCount(300);
    }

    @Override
    protected void onCall() {
        super.onCall();
        face.setLocation(face.getX(), face.getY() + 1);
        leftHand.setLocation(leftHand.getX() + 1, leftHand.getY());
        rightHand.setLocation(rightHand.getX() - 1, rightHand.getY());
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
