package bahar.window_kill.communications.model.bosswatch;

import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.communications.model.entities.generators.shooters.SmileyHand;
import bahar.window_kill.communications.processors.util.strategies.attacks.SpawnStrategy;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.model.entities.generators.shooters.SmileyFace;

public class RapidFireWatch extends BossWatch {
    public RapidFireWatch(Game game, SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(6000, game, face, leftHand, rightHand);
        setCycleCount(2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        face.setAggressionStrategy(new SpawnStrategy(100));
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
        return "Rapid Fire";
    }
}
