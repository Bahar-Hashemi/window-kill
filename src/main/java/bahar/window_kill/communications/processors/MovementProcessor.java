package bahar.window_kill.communications.processors;


import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.model.entities.Collectable;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.attackers.Bullet;
import bahar.window_kill.communications.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.communications.model.entities.generators.shooters.SmileyHand;

import java.util.ArrayList;

public class MovementProcessor extends GameProcessor {
    public MovementProcessor(Boolean isViewable, Game game) {
        super(isViewable, game);
    }

    public void run() {
        processEpsilonMovement();
        processImpacts();
        simpleMovement();
        checkOpacities();
        checkEveryThingInLocation();
    }
    private void checkEveryThingInLocation() {
        for (User user: game.users)
            user.getEpsilon().setLocation(user.getEpsilon().getX(), user.getEpsilon().getY());
        for (Entity entity: game.entities)
            entity.setLocation(entity.getX(), entity.getY());
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
                        newEntities.get(i).impactFrom(newEntities.get(j).getBounds().getCenterX(), newEntities.get(j).getBounds().getCenterY(), power);
                        newEntities.get(j).impactFrom(newEntities.get(i).getBounds().getCenterX(), newEntities.get(i).getBounds().getCenterY(), power);
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
