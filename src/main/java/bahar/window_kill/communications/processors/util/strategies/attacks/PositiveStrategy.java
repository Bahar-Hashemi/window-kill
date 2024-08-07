package bahar.window_kill.communications.processors.util.strategies.attacks;

import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.entities.Collectable;
import bahar.window_kill.communications.model.entities.Entity;

public class PositiveStrategy extends Strategy {
    @Override
    public void act(Entity source, Game game) {
        if (!(source instanceof Collectable collectable)) {
            System.err.println("Illegal entity inserted");
            return;
        }
        for (User user: game.users)
            if (GameUtil.distance(collectable, user.getEpsilon()) < 10) {
                user.setXp(collectable.getXp() + user.getXp());
                onAct(source);
            }
    }
}
