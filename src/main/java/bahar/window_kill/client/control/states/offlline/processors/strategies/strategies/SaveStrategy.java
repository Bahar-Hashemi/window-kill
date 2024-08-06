package bahar.window_kill.client.control.states.offlline.processors.strategies.strategies;

import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.GitPush;

public class SaveStrategy extends Strategy {
    @Override
    public void act(Entity source, Game game) {
        if (!(source instanceof GitPush gitPush)) {
            System.err.println("Illegal entity inserted");
            return;
        }
        User user = GameUtil.findMyUser(source, game);
        if (GameUtil.commonArea(gitPush, user.getEpsilon()) > 250) {
//            game.setGameState(new SavingState(game));
            onAct(source);
        }
    }
}
