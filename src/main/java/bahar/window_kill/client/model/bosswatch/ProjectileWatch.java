package bahar.window_kill.client.model.bosswatch;

import bahar.window_kill.client.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.client.model.entities.generators.shooters.SmileyHand;
import bahar.window_kill.client.control.states.processors.strategies.strategies.SpawnStrategy;
import bahar.window_kill.client.model.boards.MainBoard;
import bahar.window_kill.client.model.entities.generators.shooters.SmileyFace;

public class ProjectileWatch extends BossWatch {
    public ProjectileWatch(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(6000,() -> {}, face, leftHand, rightHand);
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
