package bahar.window_kill.client.control.states.processors.strategies.strategies;

import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.entities.Collectable;
import bahar.window_kill.client.model.entities.Entity;

public class PositiveStrategy extends Strategy {
    @Override
    public void act(Entity source, Deck deck) {
        if (!(source instanceof Collectable collectable)) {
            System.err.println("Illegal entity inserted");
            return;
        }
        for (User user: deck.users)
            if (GameUtil.distance(collectable, user.getEpsilon()) < 10) {
                user.setXp(collectable.getXp() + user.getXp());
                onAct(source);
            }
    }
}
