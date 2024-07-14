package bahar.window_kill.control.fazes.processors.strategies.spawners;

import bahar.window_kill.control.GameUtil;
import bahar.window_kill.model.Watch;
import bahar.window_kill.model.entities.BoardOwner;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.view.MainStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.lang.reflect.Constructor;
import java.util.Random;

import static bahar.window_kill.control.Deck.*;

public class SpawnWatch extends Watch {
    public SpawnWatch(int duration, int cycleCount, Class<?>[] enemyTypes) {
        super(duration, createEventHandler(enemyTypes));
        setCycleCount(cycleCount);
    }
    private static EventHandler<ActionEvent> createEventHandler(Class<?>[] enemyTypes) {
        return event -> {
            Random random = new Random();
            Class<?> type = enemyTypes[random.nextInt(enemyTypes.length)];
            try {
                Constructor<?> constructor = type.getConstructor();
                Entity entity = (Entity) constructor.newInstance();
                addEntity(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
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
