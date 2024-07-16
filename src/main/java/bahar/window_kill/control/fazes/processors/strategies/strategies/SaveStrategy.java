package bahar.window_kill.control.fazes.processors.strategies.strategies;

import bahar.window_kill.control.GameController;
import bahar.window_kill.control.fazes.PauseState;
import bahar.window_kill.control.fazes.SavingState;
import bahar.window_kill.control.util.GameUtil;
import bahar.window_kill.model.entities.Collectable;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.GitPush;

import static bahar.window_kill.control.Deck.user;

public class SaveStrategy extends Strategy {
    @Override
    public void act(Entity source) {
        if (!(source instanceof GitPush gitPush)) {
            System.err.println("Illegal entity inserted");
            return;
        }
        if (GameUtil.commonArea(gitPush, user.getEpsilon()) > 250) {
            GameController.setGameState(new SavingState());
            onAct(source);
        }
    }
}
