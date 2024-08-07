package bahar.window_kill.communications.processors.util.spawners;

import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.communications.model.Watch;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.model.entities.BoardOwner;
import bahar.window_kill.communications.model.entities.Entity;

import java.util.Random;

public class Spawner extends Watch {
    protected final Game game;
    protected final boolean isViewable;
    public Spawner(boolean isViewable, int duration, Game game) {
        super(duration);
        this.game = game;
        this.isViewable = isViewable;
    }

    protected static void addEntity(Entity entity, Game game) {
        if (entity instanceof BoardOwner) {
            BoardOwner boardOwner = (BoardOwner) entity;
            MainStage.add(boardOwner.getBoard().getView()); game.gameBoards.add(boardOwner.getBoard());
            MainStage.add(entity.getView()); game.addEntity(entity);
            boardOwner.getBoard().getView().toBack();
            GameUtil.placeOutsideBoard(entity, game.users.get(0).mainBoard);
        } else {
            MainBoard mainBoard = game.users.get(new Random().nextInt(game.users.size())).mainBoard;
            mainBoard.add(entity.getView());
            game.addEntity(entity);
            GameUtil.placeOutsideBoard(entity, mainBoard);
        }
    }
}
