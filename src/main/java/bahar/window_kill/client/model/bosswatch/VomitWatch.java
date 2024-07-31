package bahar.window_kill.client.model.bosswatch;

import bahar.window_kill.client.model.boards.MainBoard;
import bahar.window_kill.client.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.client.model.entities.generators.shooters.SmileyHand;
import bahar.window_kill.client.control.states.processors.strategies.strategies.VomitStrategy;
import bahar.window_kill.client.model.entities.generators.shooters.SmileyFace;

public class VomitWatch extends BossWatch {
    public VomitWatch(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(6000,() -> {}, face, leftHand, rightHand);
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
