package bahar.window_kill.client.control.states.processors.strategies.strategies;

import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.control.states.SavingState;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.GitPush;

public class SaveStrategy extends Strategy {
    @Override
    public void act(Entity source, Deck deck) {
        if (!(source instanceof GitPush gitPush)) {
            System.err.println("Illegal entity inserted");
            return;
        }
        User user = GameUtil.findMyUser(source, deck);
        if (GameUtil.commonArea(gitPush, user.getEpsilon()) > 250) {
            deck.setGameState(new SavingState(deck));
            onAct(source);
        }
    }
}
