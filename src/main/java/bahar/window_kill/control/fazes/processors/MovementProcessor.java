package bahar.window_kill.control.fazes.processors;

import bahar.window_kill.control.Deck;
import bahar.window_kill.model.entities.Collectable;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.control.GameUtil;
import bahar.window_kill.model.entities.attackers.Bullet;
import bahar.window_kill.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.model.entities.generators.shooters.Omenoct;
import bahar.window_kill.model.entities.generators.shooters.SmileyHand;

import java.util.ArrayList;

import static bahar.window_kill.control.Deck.mainBoard;
import static bahar.window_kill.control.Deck.user;

public class MovementProcessor extends GameProcessor {
    static boolean isLocked = false;
    public void run() {
        processEpsilonMovement();
        processImpacts();
        simpleMovement();
        checkOpacities();
    }
    private void checkOpacities() {
        for (Entity entity: Deck.entities)
            if ((entity instanceof Bullet && ((Bullet) entity).isTraverser()) || (entity instanceof Collectable)) {
                if (GameUtil.isInOneBoard(entity))
                    entity.getView().setOpacity(1);
                else
                    entity.getView().setOpacity(0);
            }
    }
    private void simpleMovement() {
        double epsilonX = Deck.user.getEpsilon().getSceneX();
        double epsilonY = (int) Deck.user.getEpsilon().getSceneY();
        for (Entity entity: Deck.entities) {
            if (isLocked && !(entity instanceof Bullet && ((Bullet) entity).getSource() == user.getEpsilon()))
                continue;
            if (entity instanceof Omenoct)
                entity.move(mainBoard.getWidth(), mainBoard.getHeight());
            else
                entity.move(epsilonX, epsilonY);
        }
    }
    private void processImpacts() {
        ArrayList<Entity> newEntities = new ArrayList<>(Deck.entities);
        newEntities.add(Deck.user.getEpsilon());
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
        int moveX = 0, moveY = 0;
        if (Deck.user.hasUpRequest()) moveY -= 1;
        if (Deck.user.hasDownRequest()) moveY += 1;
        if (Deck.user.hasLeftRequest()) moveX -= 1;
        if (Deck.user.hasRightRequest()) moveX += 1;
        Epsilon epsilon = user.getEpsilon();
        epsilon.move(moveX, moveY);
        makeEpsilonInBoard();
    }
    private void makeEpsilonInBoard() {
        Epsilon epsilon = user.getEpsilon();
        if (epsilon.getSceneX() < mainBoard.getLayoutX())
            epsilon.impactFrom(epsilon.getSceneX() - 10, epsilon.getSceneY());
        if (epsilon.getSceneX() + 6 > mainBoard.getLayoutX() + mainBoard.getWidth())
            epsilon.impactFrom(epsilon.getSceneX() + 10, epsilon.getSceneY());
        if (epsilon.getSceneY() < mainBoard.getLayoutY())
            epsilon.impactFrom(epsilon.getSceneX(), epsilon.getSceneY() - 10);
        if (epsilon.getSceneY() + 6 > mainBoard.getLayoutY() + mainBoard.getHeight())
            epsilon.impactFrom(epsilon.getSceneX(), epsilon.getSceneY() + 10);
    }
    public static void setLocked(boolean isLocked) {
        MovementProcessor.isLocked = isLocked;
    }
    public static boolean getLocked() {
        return isLocked;
    }
}
