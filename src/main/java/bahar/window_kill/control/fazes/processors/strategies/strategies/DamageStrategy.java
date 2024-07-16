package bahar.window_kill.control.fazes.processors.strategies.strategies;

import bahar.window_kill.control.fazes.processors.BoardsProcessor;
import bahar.window_kill.model.entities.BlackOrb;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.control.util.GameUtil;
import bahar.window_kill.model.entities.attackers.AttackerArchmire;
import bahar.window_kill.model.entities.attackers.AttackerEntity;
import bahar.window_kill.model.entities.attackers.BlackOrbLaser;
import bahar.window_kill.model.entities.attackers.Bullet;
import bahar.window_kill.model.entities.generators.SpawnerArchmire;
import bahar.window_kill.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.model.entities.generators.shooters.MiniEpsilon;

import static bahar.window_kill.control.Deck.*;

public class DamageStrategy extends Strategy {
    @Override
    public void act(Entity aggressionSource) {
        if (!(aggressionSource instanceof AttackerEntity attacker)) {
            System.err.println("Illegal entity inserted");
            return;
        }
        attackEnemies(attacker);
        attackEpsilon(attacker);
        attackWalls(aggressionSource);

    }
    private void attackEnemies(AttackerEntity attacker) {
        if (attacker instanceof Bullet && !(((Bullet) attacker).getSource() instanceof Epsilon))
            return;
        for (Entity entity: entities) {
            if (entity instanceof AttackerArchmire)
                continue;
            if (entity == attacker || ((attacker instanceof Bullet) && ((Bullet) attacker).getSource() == entity))
                continue;
            if (attacker instanceof AttackerArchmire && entity instanceof SpawnerArchmire)
                continue;
            if (attacker instanceof BlackOrbLaser && (entity instanceof BlackOrb || entity instanceof BlackOrbLaser))
                continue;
            if (entity instanceof MiniEpsilon)
                continue;
            if (GameUtil.commonArea((Entity) attacker, entity) > 10) {
                entity.setHP(entity.getHP() - attacker.getDamage());
                entity.shout();
                onAct((Entity) attacker);
            }
        }
    }
    private void attackEpsilon(AttackerEntity attacker) {
        if (attacker instanceof Bullet bullet) {
            if (bullet.getSource() == user.getEpsilon())
                return;
            if (bullet.getSource() instanceof MiniEpsilon)
                return;
        }
        if (GameUtil.commonArea((Entity) attacker, user.getEpsilon()) > 10){
            user.getEpsilon().setHP(user.getEpsilon().getHP() - attacker.getDamage());
            user.getEpsilon().shout();
            onAct((Entity) attacker);
        }
    }
    private void attackWalls(Entity attacker) { //walls are weaker than enemies
        if (attacker instanceof Bullet && !((Bullet) attacker).isTraverser()) {
            if (attacker.getSceneX() < mainBoard.getLayoutX()) {
                BoardsProcessor.requestMainBoardChangeInBounds(-5 * ((AttackerEntity) attacker).getDamage(), 0,
                        5 * ((AttackerEntity) attacker).getDamage(), 0);
                mainBoard.shout();
                onAct(attacker);
            }
            if (attacker.getSceneY() < mainBoard.getLayoutY()) {
                BoardsProcessor.requestMainBoardChangeInBounds(0, -5 * ((AttackerEntity) attacker).getDamage(),
                        0, 5 * ((AttackerEntity) attacker).getDamage());
                mainBoard.shout();
                onAct(attacker);
            }
            if (attacker.getSceneX() > mainBoard.getLayoutX() + mainBoard.getWidth()) {
                BoardsProcessor.requestMainBoardChangeInBounds(0, 0,
                        5 * ((AttackerEntity) attacker).getDamage(), 0);
                mainBoard.shout();
                onAct(attacker);
            }
            if (attacker.getSceneY() > mainBoard.getLayoutY() + mainBoard.getHeight()) {
                BoardsProcessor.requestMainBoardChangeInBounds(0, 0,
                        0, 5 * ((AttackerEntity) attacker).getDamage());
                mainBoard.shout();
                onAct(attacker);
            }
        }
    }
}
