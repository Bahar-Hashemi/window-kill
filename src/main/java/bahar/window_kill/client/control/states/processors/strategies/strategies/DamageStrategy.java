package bahar.window_kill.client.control.states.processors.strategies.strategies;

import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.model.entities.attackers.AttackerArchmire;
import bahar.window_kill.client.model.entities.attackers.AttackerEntity;
import bahar.window_kill.client.model.entities.attackers.Bullet;
import bahar.window_kill.client.control.states.processors.BoardsProcessor;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.boards.MainBoard;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.generators.shooters.MiniEpsilon;

public class DamageStrategy extends Strategy {
    @Override
    public void act(Entity aggressionSource, Deck deck) {
        if (!(aggressionSource instanceof AttackerEntity attacker)) {
            System.err.println("Illegal entity inserted");
            return;
        }
        attackEnemies(attacker, deck);
        attackEpsilon(attacker, deck);
        attackWalls(aggressionSource, deck);

    }
    private void attackEnemies(AttackerEntity attacker, Deck deck) {
        if (!(attacker instanceof Bullet bullet)) return;
        if (bullet.isTraverser()) return;
        for (Entity entity: deck.entities) {
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
    private void attackEpsilon(AttackerEntity attacker, Deck deck) {
        if (attacker instanceof Bullet && !((Bullet) attacker).isTraverser())
            return; //todo correct here فعلا نمیتونیم به اپسیلون های دیگه حمله کنیم
        for (User user: deck.users)
            if (GameUtil.commonArea((Entity) attacker, user.getEpsilon()) > 10) {
                user.getEpsilon().setHP(user.getEpsilon().getHP() - attacker.getDamage());
                user.getEpsilon().shout();
                onAct((Entity) attacker);
            }
    }
    private void attackWalls(Entity attacker, Deck deck) { //walls are weaker than enemies
        if (!(attacker instanceof Bullet bullet)) return;
        if (bullet.isTraverser()) return;
        MainBoard mainBoard = GameUtil.findMyUser(bullet, deck).mainBoard;
        if (attacker.getSceneX() < mainBoard.getLayoutX()) {
            BoardsProcessor.requestMainBoardChangeInBounds(-5 * ((AttackerEntity) attacker).getDamage(), 0,
                    5 * ((AttackerEntity) attacker).getDamage(), 0, mainBoard);
            mainBoard.shout();
            onAct(attacker);
        }
        if (attacker.getSceneY() < mainBoard.getLayoutY()) {
            BoardsProcessor.requestMainBoardChangeInBounds(0, -5 * ((AttackerEntity) attacker).getDamage(),
                    0, 5 * ((AttackerEntity) attacker).getDamage(), mainBoard);
            mainBoard.shout();
            onAct(attacker);
        }
        if (attacker.getSceneX() > mainBoard.getLayoutX() + mainBoard.getWidth()) {
            BoardsProcessor.requestMainBoardChangeInBounds(0, 0,
                    5 * ((AttackerEntity) attacker).getDamage(), 0, mainBoard);
            mainBoard.shout();
            onAct(attacker);
        }
        if (attacker.getSceneY() > mainBoard.getLayoutY() + mainBoard.getHeight()) {
            BoardsProcessor.requestMainBoardChangeInBounds(0, 0,
                    0, 5 * ((AttackerEntity) attacker).getDamage(), mainBoard);
            mainBoard.shout();
            onAct(attacker);
        }
    }
}
