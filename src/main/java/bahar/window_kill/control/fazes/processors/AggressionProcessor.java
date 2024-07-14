package bahar.window_kill.control.fazes.processors;

import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.BlackOrb;
import bahar.window_kill.model.entities.attackers.Bullet;
import bahar.window_kill.model.entities.generators.shooters.ShooterEntity;

import java.util.ArrayList;

import static bahar.window_kill.control.Deck.*;

public class AggressionProcessor extends GameProcessor {
    static boolean isLocked = false;
    @Override
    public void run() {
        user.aggress();
        for (int i = entities.size() - 1; i >= 0; i--) {
            Entity entity = entities.get(i);
            if (isLocked && !(entity instanceof Bullet && ((Bullet) entity).getSource() == user.getEpsilon()))
                continue;
            if (entity instanceof ShooterEntity shooterEntity)
                shooterEntity.target(user.getEpsilon());
            entity.aggress();
        }
    }
    public static void setLocked(boolean isLocked) {
        AggressionProcessor.isLocked = isLocked;
    }
    public static boolean getLocked() {
        return isLocked;
    }
}
