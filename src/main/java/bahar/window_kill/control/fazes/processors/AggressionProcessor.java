package bahar.window_kill.control.fazes.processors;

import bahar.window_kill.model.entities.Collectable;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.BlackOrb;
import bahar.window_kill.model.entities.attackers.BlackOrbLaser;
import bahar.window_kill.model.entities.attackers.Bullet;
import bahar.window_kill.model.entities.generators.shooters.MiniEpsilon;
import bahar.window_kill.model.entities.generators.shooters.ShooterEntity;

import java.util.ArrayList;

import static bahar.window_kill.control.Deck.*;

public class AggressionProcessor extends GameProcessor {
    private static Entity getEnemy() {
        for (Entity entity: entities)
            if (!(entity instanceof Bullet) && !(entity instanceof MiniEpsilon) && !(entity instanceof Collectable) && !(entity instanceof BlackOrbLaser) && entity.getHP() > 0)
                return entity;
        return null;
    }
    Entity target = null;
    @Override
    public void run() {
        user.aggress();
        for (int i = entities.size() - 1; i >= 0; i--) {
            Entity entity = entities.get(i);
            if (entity instanceof MiniEpsilon miniEpsilon) {
                if (target == null || target.getHP() <= 0)
                    target = getEnemy();
                if (target != null)
                    miniEpsilon.target(target);
                else
                    miniEpsilon.setGunDirection(0, -1);
                miniEpsilon.aggress();
            }
            if (isLocked && !(entity instanceof Bullet && !((Bullet) entity).isTraverser()))
                continue;
            if (entity instanceof ShooterEntity shooterEntity)
                shooterEntity.target(user.getEpsilon());
            entity.aggress();
        }
    }
}
