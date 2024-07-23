package bahar.window_kill.control.fazes.processors.strategies.strategies;

import bahar.window_kill.control.util.GameUtil;
import bahar.window_kill.model.entities.Collectable;
import bahar.window_kill.model.entities.Entity;

import static bahar.window_kill.control.Deck.*;

public class PositiveStrategy extends Strategy {
    @Override
    public void act(Entity source) {
        if (!(source instanceof Collectable collectable)) {
            System.err.println("Illegal entity inserted");
            return;
        }
        if (GameUtil.distance(collectable, user.getEpsilon()) < 10) {
            user.getEpsilon().setXp(collectable.getXp() + user.getEpsilon().getXp());
            onAct(source);
        }
    }
}
