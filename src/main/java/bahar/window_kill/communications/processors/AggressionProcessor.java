package bahar.window_kill.communications.processors;

import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.entities.Collectable;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.attackers.BlackOrbLaser;
import bahar.window_kill.communications.model.entities.attackers.Bullet;
import bahar.window_kill.communications.model.entities.generators.shooters.MiniEpsilon;
import bahar.window_kill.communications.model.entities.generators.shooters.ShooterEntity;

import java.util.Random;

public class AggressionProcessor extends GameProcessor {
    Entity target = null;

    public AggressionProcessor(Boolean isViewable, Game game) {
        super(isViewable, game);
    }
    private Entity getEnemy() {
        for (Entity entity: game.entities)
            if (!(entity instanceof Bullet) && !(entity instanceof MiniEpsilon) && !(entity instanceof Collectable) && !(entity instanceof BlackOrbLaser) && entity.getHP() > 0)
                return entity;
        return null;
    }
    @Override
    public void run() {
        for (User user: game.users)
            user.aggress();
        for (int i = game.entities.size() - 1; i >= 0; i--) {
            Entity entity = game.entities.get(i);
            if (entity instanceof MiniEpsilon miniEpsilon) {
                if (target == null || target.getHP() <= 0)
                    target = getEnemy();
                if (target != null)
                    miniEpsilon.target(target);
                else
                    miniEpsilon.setGunDirection(0, -1);
                miniEpsilon.aggress();
            }
            if (game.isLocked && !(entity instanceof Bullet && !((Bullet) entity).isTraverser()))
                continue;
            if (entity instanceof ShooterEntity shooterEntity) {
                User user = GameUtil.findMyUser(shooterEntity, game);
                if (user == null) user = game.users.get(new Random().nextInt(game.users.size()));
                shooterEntity.target(user.getEpsilon());
            }
            entity.aggress();
        }
    }
}
