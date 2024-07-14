package bahar.window_kill.model.bosswatch;

import bahar.window_kill.control.fazes.processors.strategies.strategies.SpawnStrategy;
import bahar.window_kill.model.boards.MainBoard;
import bahar.window_kill.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.model.entities.generators.shooters.SmileyFace;
import bahar.window_kill.model.entities.generators.shooters.SmileyHand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ProjectileWatch extends BossWatch {
    public ProjectileWatch(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(6000, event -> {}, face, leftHand, rightHand);
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
