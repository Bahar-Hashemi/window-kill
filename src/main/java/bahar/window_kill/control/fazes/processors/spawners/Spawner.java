package bahar.window_kill.control.fazes.processors.spawners;

import bahar.window_kill.control.util.GameUtil;
import bahar.window_kill.model.Watch;
import bahar.window_kill.model.entities.BoardOwner;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.view.MainStage;

import static bahar.window_kill.control.Deck.*;

public class Spawner extends Watch {
    public Spawner(int duration, Runnable runnable) {
        super(duration, runnable);
    }
    protected static void addEntity(Entity entity) {
        if (entity instanceof BoardOwner) {
            BoardOwner boardOwner = (BoardOwner) entity;
            MainStage.add(boardOwner.getBoard()); gameBoards.add(boardOwner.getBoard());
            MainStage.add(entity.getView()); entities.add(entity);
            boardOwner.getBoard().toBack();
        } else {
            mainBoard.add(entity.getView());
            entities.add(entity);
        }
        GameUtil.placeOutsideBoard(entity, mainBoard);
    }
}
