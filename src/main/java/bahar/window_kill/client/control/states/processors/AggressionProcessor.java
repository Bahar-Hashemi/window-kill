package bahar.window_kill.client.control.states.processors;

import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.entities.Collectable;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.attackers.BlackOrbLaser;
import bahar.window_kill.client.model.entities.attackers.Bullet;
import bahar.window_kill.client.model.entities.generators.shooters.MiniEpsilon;
import bahar.window_kill.client.model.entities.generators.shooters.ShooterEntity;

import java.util.Random;

public class AggressionProcessor extends GameProcessor {
    Entity target = null;

    public AggressionProcessor(Deck deck) {
        super(deck);
    }
    private Entity getEnemy() {
        for (Entity entity: deck.entities)
            if (!(entity instanceof Bullet) && !(entity instanceof MiniEpsilon) && !(entity instanceof Collectable) && !(entity instanceof BlackOrbLaser) && entity.getHP() > 0)
                return entity;
        return null;
    }
    @Override
    public void run() {
        for (User user: deck.users)
            user.aggress();
        for (int i = deck.entities.size() - 1; i >= 0; i--) {
            Entity entity = deck.entities.get(i);
            if (entity instanceof MiniEpsilon miniEpsilon) {
                if (target == null || target.getHP() <= 0)
                    target = getEnemy();
                if (target != null)
                    miniEpsilon.target(target);
                else
                    miniEpsilon.setGunDirection(0, -1);
                miniEpsilon.aggress();
            }
            if (deck.isLocked && !(entity instanceof Bullet && !((Bullet) entity).isTraverser()))
                continue;
            if (entity instanceof ShooterEntity shooterEntity) {
                User user = GameUtil.findMyUser(shooterEntity, deck);
                if (user == null) user = deck.users.get(new Random().nextInt(deck.users.size()));
                shooterEntity.target(user.getEpsilon());
            }
            entity.aggress();
        }
    }
}
