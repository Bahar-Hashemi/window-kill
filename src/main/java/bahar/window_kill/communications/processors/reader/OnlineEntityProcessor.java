package bahar.window_kill.communications.processors.reader;

import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.model.entities.BoardOwner;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.processors.GameProcessor;
import javafx.scene.layout.Pane;

import java.lang.reflect.Constructor;
import java.util.Random;

public class OnlineEntityProcessor extends GameProcessor {
    public OnlineEntityProcessor(boolean isViewable, Game game) {
        super(isViewable, game);
    }

    @Override
    public void run() {
        if (game.save == null)
            return;
        int myPointer = 0;
        int savePointer = 0;
        while (myPointer < game.entities.size() && savePointer < game.save.entities.size()) {
            if (game.entities.get(myPointer).getId().equals(game.save.entities.get(savePointer).getId())) {
                myPointer++;
                savePointer++;
            }
            else
                removeEntity(game.entities.get(myPointer));
        }
        while (myPointer < game.entities.size())
            removeEntity(game.entities.get(myPointer));
        while (savePointer < game.save.entities.size())
            makeEntity(game.save.entities.get(savePointer++));
    }
    private void removeEntity(Entity entity) {
        if (entity.isViewable) {
            Pane parent = (Pane) entity.getView().getParent();
            if (entity instanceof BoardOwner boardOwner) {
                MainStage.remove(boardOwner.getBoard().getView());
                game.gameBoards.remove(boardOwner.getBoard());
            }
            parent.getChildren().remove(entity.getView());
        }
        game.entities.remove(entity);
        entity.shout();
    }
    private void makeEntity(Entity entity) {
        try {
            Class<?> type = Class.forName(entity.getClassName());
            Constructor<?> constructor = type.getConstructor(boolean.class, String.class);
            Entity result = (Entity) constructor.newInstance(true, entity.getId());
            addEntity(result, game);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected static void addEntity(Entity entity, Game game) {
        if (entity instanceof BoardOwner) {
            BoardOwner boardOwner = (BoardOwner) entity;
            MainStage.add(boardOwner.getBoard().getView()); game.gameBoards.add(boardOwner.getBoard());
            MainStage.add(entity.getView()); game.addEntity(entity);
            boardOwner.getBoard().getView().toBack();
        } else { //todo correct here!!!!!!
            MainBoard mainBoard = game.users.get(new Random().nextInt(game.users.size())).mainBoard;
            mainBoard.add(entity.getView());
            game.addEntity(entity);
        }
    }

}
