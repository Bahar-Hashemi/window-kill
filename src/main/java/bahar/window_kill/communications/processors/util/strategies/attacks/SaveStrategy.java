package bahar.window_kill.communications.processors.util.strategies.attacks;

import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.GitPush;

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
