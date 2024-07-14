package bahar.window_kill.model.bosswatch;

import bahar.window_kill.control.fazes.processors.strategies.strategies.SpawnStrategy;
import bahar.window_kill.control.fazes.processors.strategies.strategies.VomitStrategy;
import bahar.window_kill.model.boards.MainBoard;
import bahar.window_kill.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.model.entities.generators.shooters.SmileyFace;
import bahar.window_kill.model.entities.generators.shooters.SmileyHand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class VomitWatch extends BossWatch {
    public VomitWatch(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(6000, event -> {}, face, leftHand, rightHand);
        setCycleCount(2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        face.setAggressionStrategy(new VomitStrategy(250));
    }

    protected void onEnd() {
        face.setAggressionStrategy(null);
    }
    @Override
    public boolean isValid(Epsilon epsilon, MainBoard mainBoard) {
        return face.getAggressionStrategy() == null;
    }

    @Override
    public String getMessage() {
        return "Vomiting";
    }
}
