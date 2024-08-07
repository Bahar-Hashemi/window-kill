package bahar.window_kill.communications.processors;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.client.control.states.offlline.RestartingState;
import bahar.window_kill.client.control.util.reader.SaveUtil;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.entities.BoardOwner;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.LootDropper;
import bahar.window_kill.communications.model.entities.attackers.Bullet;
import javafx.scene.layout.Pane;
import bahar.window_kill.client.view.PaneBuilder;

import java.util.Random;
import java.util.Scanner;

public class DeathProcessor extends GameProcessor {
    public DeathProcessor(Boolean isViewable, Game game) {
        super(isViewable, game);
    }

    @Override
    public void run() {
        killBulletsOutsideMonitor();
        checkEpsilonHealth();
        checkEntitiesHealth();
    }
    private void killBulletsOutsideMonitor() {
        for (Entity entity: game.entities)
            if (entity instanceof Bullet && ((Bullet) entity).isTraverser() &&
                    (entity.getX() < 0 || entity.getX() > Constants.SCREEN_WIDTH ||
                            entity.getY() < 0 || entity.getY() > Constants.SCREEN_HEIGHT)
            )
                entity.setHP(0);
    }
    private void checkEpsilonHealth() { //todo correct here
        for (User user: game.users)
            if (user.getEpsilon().getHP() <= 0) {
                if (game.save != null) { //todo complete save!
//                    (new SaveUtil()).read(new Scanner(game.save));
                    game.setGameState(new RestartingState(isViewable, game));
                    game.save = null;
                }
                else {
                    game.gameState.stop();
                    if (isViewable) {
                        Pane pane = PaneBuilder.GAME_OVER_PANE.generatePane();
                        MainStage.add(pane);
                    }
                }
            }
    }
    private void checkEntitiesHealth() {
        for (int i = game.entities.size() - 1; i >= 0; i--) {
            Entity entity = game.entities.get(i);
            if (entity.getHP() <= 0) {
                kill(entity);
            }
        }
    }
    private void kill(Entity entity) {
        if (entity.isViewable) {
            Pane parent = (Pane) entity.getView().getParent();
            if (entity instanceof LootDropper lootDropper) {
                for (int i = 0; i < lootDropper.getLootCount(); i++) {
                    Entity collectable = lootDropper.makeLoot();
                    ((Pane) entity.getView().getParent()).getChildren().add(collectable.getView());
                    game.addEntity(collectable);

                    collectable.setLocation(new Random().nextDouble(entity.getBounds().getMinimumX(), entity.getBounds().getMaximumX()),
                            new Random().nextDouble(entity.getBounds().getMinimumY(), entity.getBounds().getMaximumY()));
                }
            }
            if (entity instanceof BoardOwner boardOwner) {
                MainStage.remove(boardOwner.getBoard().getView());
                game.gameBoards.remove(boardOwner.getBoard());
            }
            parent.getChildren().remove(entity.getView());
        }
        game.entities.remove(entity);
        entity.shout();
    }
}
