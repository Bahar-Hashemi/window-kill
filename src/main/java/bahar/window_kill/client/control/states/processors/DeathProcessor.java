package bahar.window_kill.client.control.states.processors;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.control.states.RestartingState;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.control.util.reader.SaveUtil;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.entities.BoardOwner;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.LootDropper;
import bahar.window_kill.client.model.entities.attackers.Bullet;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import bahar.window_kill.client.view.PaneBuilder;

import java.util.Scanner;

public class DeathProcessor extends GameProcessor {
    public DeathProcessor(Deck deck) {
        super(deck);
    }

    @Override
    public void run() {
        killBulletsOutsideMonitor();
        checkEpsilonHealth();
        checkEntitiesHealth();
    }
    private void killBulletsOutsideMonitor() {
        for (Entity entity: deck.entities)
            if (entity instanceof Bullet && ((Bullet) entity).isTraverser() &&
                    (entity.getSceneX() < 0 || entity.getSceneX() > Constants.SCREEN_WIDTH ||
                            entity.getSceneY() < 0 || entity.getSceneY() > Constants.SCREEN_HEIGHT)
            )
                entity.setHP(0);
    }
    private void checkEpsilonHealth() { //todo correct here
        for (User user: deck.users)
            if (user.getEpsilon().getHP() <= 0) {
                if (deck.save != null) {
                    (new SaveUtil()).read(new Scanner(deck.save));
                    deck.setGameState(new RestartingState(deck));
                    deck.save = null;
                }
                else {
                    deck.gameState.stop();
                    Pane pane = PaneBuilder.GAME_OVER_PANE.generatePane();
                    MainStage.add(pane);
                    MainStage.requestCenterOnScreen(pane);
                }
            }
    }
    private void checkEntitiesHealth() {
        for (int i = deck.entities.size() - 1; i >= 0; i--) {
            Entity entity = deck.entities.get(i);
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
                ((Pane) entity.getView().getParent()).getChildren().add(collectable.getView());
                deck.addEntity(collectable);
                collectable.setSceneLocation(bounds.getMinX() + Math.random() * bounds.getWidth(), bounds.getMinY() + Math.random() * bounds.getHeight());
            }
        }
        if (entity instanceof BoardOwner boardOwner) {
            MainStage.remove(boardOwner.getBoard());
            deck.gameBoards.remove(boardOwner.getBoard());
        }
        parent.getChildren().remove(entity.getView());
        deck.entities.remove(entity);
        entity.shout();
    }
}
