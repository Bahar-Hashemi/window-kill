package bahar.window_kill.control.fazes.processors;

import bahar.window_kill.Main;
import bahar.window_kill.control.Deck;
import bahar.window_kill.control.GameUtil;
import bahar.window_kill.model.Watch;
import bahar.window_kill.model.boards.GameBoard;
import bahar.window_kill.model.entities.Barricados;
import bahar.window_kill.model.entities.BoardOwner;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.attackers.Bullet;
import bahar.window_kill.model.entities.attackers.Squarantine;
import bahar.window_kill.model.entities.attackers.Trigorath;
import bahar.window_kill.model.entities.generators.SpawnerArchmire;
import bahar.window_kill.model.entities.generators.shooters.Nechropic;
import bahar.window_kill.model.entities.generators.shooters.Omenoct;
import bahar.window_kill.model.entities.generators.shooters.Wyrm;
import bahar.window_kill.view.MainStage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

import java.lang.reflect.Constructor;
import java.util.Random;

import static bahar.window_kill.control.Deck.*;

public class Trojan extends GameProcessor implements EventHandler {
    boolean hasDone = false;
    Watch watch;
    public Trojan() {
        watch = new Watch(4000, this);
    }
    @Override
    public void run() { //we need to use reflection here
        watch.call();
    }
    Class[] entityTypes = {Wyrm.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class, Barricados.class, Trigorath.class, Squarantine.class};
    @Override
    public void handle(Event event) {
        Random random = new Random();
        Class<?> type = entityTypes[random.nextInt(entityTypes.length)];
        try {
            Constructor<?> constructor = type.getConstructor();
            Entity entity = (Entity) constructor.newInstance();

            if (entity instanceof BoardOwner boardOwner) {
                MainStage.add(boardOwner.getBoard());
                gameBoards.add(boardOwner.getBoard());
                boardOwner.getBoard().toBack();
                MainStage.add(entity.getView()); entities.add(entity);
                GameUtil.placeOutsideBoard(entity, mainBoard);
            } else {
                mainBoard.add(entity.getView());
                entities.add(entity);
                GameUtil.placeOutsideBoard(entity, mainBoard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
