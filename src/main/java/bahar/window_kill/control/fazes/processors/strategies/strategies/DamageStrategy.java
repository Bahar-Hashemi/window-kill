package bahar.window_kill.control.fazes.processors.strategies.strategies;

import bahar.window_kill.control.fazes.processors.BoardsProcessor;
import bahar.window_kill.model.entities.BlackOrb;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.control.GameUtil;
import bahar.window_kill.model.entities.attackers.AttackerArchmire;
import bahar.window_kill.model.entities.attackers.AttackerEntity;
import bahar.window_kill.model.entities.attackers.BlackOrbLaser;
import bahar.window_kill.model.entities.attackers.Bullet;
import bahar.window_kill.model.entities.generators.SpawnerArchmire;

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
        attackWalls(attacker);

    }
    private void attackEnemies(AttackerEntity attacker) {
        for (Entity entity: entities) {
            if (entity instanceof AttackerArchmire)
                continue;
            if (entity == attacker || ((attacker instanceof Bullet) && ((Bullet) attacker).getSource() == entity))
                continue;
            if (attacker instanceof AttackerArchmire && entity instanceof SpawnerArchmire)
                continue;
            if (attacker instanceof BlackOrbLaser && (entity instanceof BlackOrb || entity instanceof BlackOrbLaser))
                continue;
            if (GameUtil.commonArea(attacker, entity) > 10) {
                entity.setHP(entity.getHP() - attacker.getDamage());
                entity.shout();
                onAct(attacker);
            }
        }
    }
    private void attackEpsilon(AttackerEntity attacker) {
        if (GameUtil.commonArea(attacker, user.getEpsilon()) > 10 &&
                (!(attacker instanceof Bullet) || ((Bullet) attacker).getSource() != user.getEpsilon())) {
            user.getEpsilon().setHP(user.getEpsilon().getHP() - attacker.getDamage());
            user.getEpsilon().shout();
            onAct(attacker);
        }
    }
    private void attackWalls(AttackerEntity attacker) { //walls are weaker than enemies
        if (attacker instanceof Bullet && !((Bullet) attacker).isTraverser()) {
            if (attacker.getSceneX() < mainBoard.getLayoutX()) {
                BoardsProcessor.requestMainBoardChangeInBounds(-3 * attacker.getDamage(), 0,
                        3 * attacker.getDamage(), 0);
                mainBoard.shout();
                onAct(attacker);
            }
            if (attacker.getSceneY() < mainBoard.getLayoutY()) {
                BoardsProcessor.requestMainBoardChangeInBounds(0, -3 * attacker.getDamage(),
                        0, 3 * attacker.getDamage());
                mainBoard.shout();
                onAct(attacker);
            }
            if (attacker.getSceneX() > mainBoard.getLayoutX() + mainBoard.getWidth()) {
                BoardsProcessor.requestMainBoardChangeInBounds(0, 0,
                        3 * attacker.getDamage(), 0);
                mainBoard.shout();
                onAct(attacker);
            }
            if (attacker.getSceneY() > mainBoard.getLayoutY() + mainBoard.getHeight()) {
                BoardsProcessor.requestMainBoardChangeInBounds(0, 0,
                        0, 3 * attacker.getDamage());
                mainBoard.shout();
                onAct(attacker);
            }
        }
    }
}
