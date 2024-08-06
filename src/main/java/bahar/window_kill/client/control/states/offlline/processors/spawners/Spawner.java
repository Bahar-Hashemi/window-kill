package bahar.window_kill.client.control.states.offlline.processors.spawners;

import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.model.Watch;
import bahar.window_kill.client.model.boards.MainBoard;
import bahar.window_kill.client.model.entities.BoardOwner;
import bahar.window_kill.client.model.entities.Entity;

import java.util.Random;

public class Spawner extends Watch {
    protected final Game game;
    public Spawner(int duration, Game game) {
        super(duration);
        this.game = game;
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
