package bahar.window_kill.communications.processors.util.strategies.attacks;

import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.entities.attackers.AttackerArchmire;
import bahar.window_kill.communications.model.entities.attackers.AttackerEntity;
import bahar.window_kill.communications.model.entities.attackers.Bullet;
import bahar.window_kill.communications.processors.BoardsProcessor;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.generators.shooters.MiniEpsilon;

public class DamageStrategy extends Strategy {
    @Override
    public void act(Entity aggressionSource, Game game) {
        if (!(aggressionSource instanceof AttackerEntity attacker)) {
            System.err.println("Illegal entity inserted");
            return;
        }
        attackEnemies(attacker, game);
        attackEpsilon(attacker, game);
        attackWalls(aggressionSource, game);

    }
    private void attackEnemies(AttackerEntity attacker, Game game) {
        if (!(attacker instanceof Bullet bullet)) return;
        if (bullet.isTraverser()) return;
        for (Entity entity: game.entities) {
            if (entity instanceof AttackerArchmire)
                continue;
            if (entity instanceof MiniEpsilon)
                continue;
            if (entity instanceof Bullet)
                continue;
            if (GameUtil.commonArea((Entity) attacker, entity) > 10) {
                entity.setHP(entity.getHP() - attacker.getDamage());
                entity.shout();
                onAct((Entity) attacker);
            }
        }
    }
    private void attackEpsilon(AttackerEntity attacker, Game game) {
        if (attacker instanceof Bullet && !((Bullet) attacker).isTraverser())
            return; //todo correct here فعلا نمیتونیم به اپسیلون های دیگه حمله کنیم
        for (User user: game.users)
            if (GameUtil.commonArea((Entity) attacker, user.getEpsilon()) > 10) {
                user.getEpsilon().setHP(user.getEpsilon().getHP() - attacker.getDamage());
                user.getEpsilon().shout();
                onAct((Entity) attacker);
            }
    }
    private void attackWalls(Entity attacker, Game game) { //walls are weaker than enemies
        if (!(attacker instanceof Bullet bullet)) return;
        if (bullet.isTraverser()) return;
        MainBoard mainBoard = GameUtil.findMyUser(bullet, game).mainBoard;
        if (attacker.getX() < mainBoard.getX()) {
            BoardsProcessor.requestMainBoardChangeInBounds(-5 * ((AttackerEntity) attacker).getDamage(), 0,
                    5 * ((AttackerEntity) attacker).getDamage(), 0, mainBoard, game);
            mainBoard.shout();
            onAct(attacker);
        }
        if (attacker.getY() < mainBoard.getY()) {
            BoardsProcessor.requestMainBoardChangeInBounds(0, -5 * ((AttackerEntity) attacker).getDamage(),
                    0, 5 * ((AttackerEntity) attacker).getDamage(), mainBoard, game);
            mainBoard.shout();
            onAct(attacker);
        }
        if (attacker.getX() > mainBoard.getX() + mainBoard.getWidth()) {
            BoardsProcessor.requestMainBoardChangeInBounds(0, 0,
                    5 * ((AttackerEntity) attacker).getDamage(), 0, mainBoard, game);
            mainBoard.shout();
            onAct(attacker);
        }
        if (attacker.getY() > mainBoard.getY() + mainBoard.getHeight()) {
            BoardsProcessor.requestMainBoardChangeInBounds(0, 0,
                    0, 5 * ((AttackerEntity) attacker).getDamage(), mainBoard, game);
            mainBoard.shout();
            onAct(attacker);
        }
    }
}
