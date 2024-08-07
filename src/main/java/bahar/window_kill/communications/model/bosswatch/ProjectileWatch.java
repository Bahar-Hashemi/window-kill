package bahar.window_kill.communications.model.bosswatch;

import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.communications.model.entities.generators.shooters.SmileyHand;
import bahar.window_kill.communications.processors.util.strategies.attacks.SpawnStrategy;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.model.entities.generators.shooters.SmileyFace;

public class ProjectileWatch extends BossWatch {
    public ProjectileWatch(Game game, SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(6000, game, face, leftHand, rightHand);
        setCycleCount(1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (leftHand != null && leftHand.getHP() > 0)
            leftHand.setAggressionStrategy(new SpawnStrategy(500));
        if (rightHand != null && rightHand.getHP() > 0)
            rightHand.setAggressionStrategy(new SpawnStrategy(500));
    }

    @Override
    protected void onEnd() {
        if (leftHand != null && leftHand.getHP() > 0)
            leftHand.setAggressionStrategy(null);
        if (rightHand != null && rightHand.getHP() > 0)
            rightHand.setAggressionStrategy(null);
    }

    @Override
    public boolean isValid(Epsilon epsilon, MainBoard mainBoard) {
        return true;
    }

    @Override
    public String getMessage() {
        return "incoming projectiles";
    }
}
