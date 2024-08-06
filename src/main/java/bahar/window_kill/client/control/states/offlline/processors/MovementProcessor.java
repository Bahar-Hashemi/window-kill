package bahar.window_kill.client.control.states.offlline.processors;


import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.boards.MainBoard;
import bahar.window_kill.client.model.entities.Collectable;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.attackers.Bullet;
import bahar.window_kill.client.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.client.model.entities.generators.shooters.SmileyHand;

import java.util.ArrayList;

public class MovementProcessor extends GameProcessor {
    public MovementProcessor(Game game) {
        super(game);
    }

    public void run() {
        processEpsilonMovement();
        processImpacts();
        simpleMovement();
        checkOpacities();
    }
    private void checkOpacities() {
        for (Entity entity: game.entities)
            if ((entity instanceof Bullet && ((Bullet) entity).isTraverser()) || (entity instanceof Collectable)) {
                if (GameUtil.isInOneBoard(entity))
                    entity.getView().setOpacity(1);
                else
                    entity.getView().setOpacity(0);
            }
    }
    private void simpleMovement() {
        for (Entity entity: game.entities) {
            if (game.isLocked && !(entity instanceof Bullet && !((Bullet) entity).isTraverser()))
                continue;
            entity.move();
        }
    }
    private void processImpacts() {
        ArrayList<Entity> newEntities = new ArrayList<>(game.entities);
        for (User user: game.users)
            newEntities.add(user.epsilon);
        for (int i = newEntities.size() - 1; i >= 0; i--)
            for (int j = i - 1; j >= 0; j--) {
                if (newEntities.get(i).canImpact() && newEntities.get(j).canImpact()) {
                    if (GameUtil.commonArea(newEntities.get(i), newEntities.get(j)) > 15) {
                        int power = 6;
                        if (newEntities.get(i) instanceof SmileyHand || newEntities.get(j) instanceof SmileyHand)
                            power = 35;
                        newEntities.get(i).impactFrom(newEntities.get(j).getCenterOnSceneX(), newEntities.get(j).getCenterOnSceneY(), power);
                        newEntities.get(j).impactFrom(newEntities.get(i).getCenterOnSceneX(), newEntities.get(i).getCenterOnSceneY(), power);
                    }
                }
            }
    }
    private void processEpsilonMovement() {
        for (User user: game.users) {
            user.epsilon.move();
            makeEpsilonInBoard(user.epsilon, user.mainBoard);
        }
    }
    private void makeEpsilonInBoard(Epsilon epsilon, MainBoard mainBoard) {
        if (epsilon.getBounds().getMinimumX() < mainBoard.getBounds().getMinimumX())
            epsilon.impactFrom(epsilon.getBounds().getMinimumX(), epsilon.getY());
        if (epsilon.getBounds().getMaximumX() > mainBoard.getBounds().getMaximumX())
            epsilon.impactFrom(epsilon.getBounds().getMaximumX(), epsilon.getY());
        if (epsilon.getBounds().getMinimumY() < mainBoard.getBounds().getMinimumY())
            epsilon.impactFrom(epsilon.getX(), epsilon.getBounds().getMinimumY());
        if (epsilon.getBounds().getMaximumY() > mainBoard.getBounds().getMaximumY())
            epsilon.impactFrom(epsilon.getX(), epsilon.getBounds().getMaximumY());
    }
}
