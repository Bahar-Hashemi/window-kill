package bahar.window_kill.control.fazes.processors;

import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.BlackOrb;
import bahar.window_kill.model.entities.generators.shooters.ShooterEntity;

import java.util.ArrayList;

import static bahar.window_kill.control.Deck.*;

public class AggressionProcessor extends GameProcessor {
    ArrayList<BlackOrb> blackOrbs = new ArrayList<>();
    @Override
    public void run() {
        findBlackOrbs();
        user.aggress();
        for (int i = entities.size() - 1; i >= 0; i--) {
            Entity entity = entities.get(i);
            if (entity instanceof ShooterEntity shooterEntity)
                shooterEntity.target(user.getEpsilon());
            entity.aggress();
        }
    }
    private void findBlackOrbs() {
        for (Entity entity: entities)
            if (entity instanceof BlackOrb blackOrb)
                blackOrbs.add(blackOrb);
    }
}
