package bahar.window_kill.server.control.game.processors.strategies.strategies;

import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.entities.Collectable;
import bahar.window_kill.client.model.entities.Entity;

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
