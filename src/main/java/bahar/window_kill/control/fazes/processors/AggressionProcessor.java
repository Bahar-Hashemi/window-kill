package bahar.window_kill.control.fazes.processors;

import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.generators.shooters.BlackOrb;
import bahar.window_kill.model.entities.generators.shooters.ShooterEntity;

import static bahar.window_kill.control.Deck.*;

public class AggressionProcessor extends GameProcessor {
    @Override
    public void run() {
        user.aggress();

        for (int i = entities.size() - 1; i >= 0; i--) {
            Entity entity = entities.get(i);
            if (entity instanceof BlackOrb blackOrb)
                System.out.println("hello");
            else if (entity instanceof ShooterEntity shooterEntity)
                shooterEntity.target(user.getEpsilon());
            entity.aggress();
        }
    }
}
