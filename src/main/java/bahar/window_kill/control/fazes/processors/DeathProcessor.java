package bahar.window_kill.control.fazes.processors;

import bahar.window_kill.control.Constants;
import bahar.window_kill.control.Deck;
import bahar.window_kill.control.GameController;
import bahar.window_kill.control.fazes.RestartingState;
import bahar.window_kill.control.util.GameUtil;
import bahar.window_kill.control.fazes.PlayingState;
import bahar.window_kill.control.util.reader.SaveUtil;
import bahar.window_kill.model.entities.BoardOwner;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.LootDropper;
import bahar.window_kill.model.entities.attackers.Bullet;
import bahar.window_kill.view.MainStage;
import bahar.window_kill.view.controller.SaveController;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;

import java.util.Scanner;

import static bahar.window_kill.control.Deck.*;

public class DeathProcessor extends GameProcessor {
    @Override
    public void run() {
        killBulletsOutsideMonitor();
        checkEpsilonHealth();
        checkEntitiesHealth();
    }
    private void killBulletsOutsideMonitor() {
        for (Entity entity: entities)
            if (entity instanceof Bullet && ((Bullet) entity).isTraverser() &&
                    (entity.getSceneX() < 0 || entity.getSceneX() > Constants.SCREEN_WIDTH ||
                            entity.getSceneY() < 0 || entity.getSceneY() > Constants.SCREEN_HEIGHT)
            )
                entity.setHP(0);
    }
    private void checkEpsilonHealth() {
        if (user.getEpsilon().getHP() <= 0) {
            if (save != null) {
                (new SaveUtil()).read(new Scanner(save));
                GameController.setGameState(new RestartingState());
                save = null;
            }
            else
                PlayingState.endGame();
        }
    }
    private void checkEntitiesHealth() {
        for (int i = entities.size() - 1; i >= 0; i--) {
            Entity entity = entities.get(i);
            if (entity.getHP() <= 0) {
                kill(entity);
            }
        }
    }
    private void kill(Entity entity) {
        Pane parent = (Pane) entity.getView().getParent();
        Bounds bounds = GameUtil.getSceneBounds(entity);
        if (entity instanceof LootDropper lootDropper) {
            for (int i = 0; i < lootDropper.getLootCount(); i++) {
                Entity collectable = lootDropper.makeLoot();
                MainStage.add(collectable.getView());
                entities.add(collectable);
                collectable.setSceneLocation(bounds.getMinX() + Math.random() * bounds.getWidth(), bounds.getMinY() + Math.random() * bounds.getHeight());
            }
        }
        if (entity instanceof BoardOwner boardOwner) {
            MainStage.remove(boardOwner.getBoard());
            Deck.gameBoards.remove(boardOwner.getBoard());
        }
        parent.getChildren().remove(entity.getView());
        Deck.entities.remove(entity);
        entity.shout();
    }
}
