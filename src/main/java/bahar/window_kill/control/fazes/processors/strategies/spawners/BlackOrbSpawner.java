package bahar.window_kill.control.fazes.processors.strategies.spawners;

import bahar.window_kill.control.Constants;
import bahar.window_kill.control.GameUtil;
import bahar.window_kill.model.Watch;
import bahar.window_kill.model.entities.BlackOrb;
import bahar.window_kill.model.entities.BoardOwner;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.attackers.BlackOrbLaser;
import bahar.window_kill.view.MainStage;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

import static bahar.window_kill.control.Deck.*;

public class BlackOrbSpawner extends Watch {
    static double centerX, centerY;
    static ArrayList<BlackOrb> blackOrbs = new ArrayList<>();
    static double radius;
    public BlackOrbSpawner() {
        super(500, createEventHandler());
        setCycleCount(findCycle());
    }
    private static void makeBlackOrbs() {
        blackOrbs = new ArrayList<>();
        for (Entity entity: entities)
            if (entity instanceof BlackOrb)
                blackOrbs.add((BlackOrb) entity);

    }
    private static int findIndex() {
        makeBlackOrbs();
        return blackOrbs.size();
    }
    private static int findCycle() {
        return 5 - findIndex();
    }
    private static void makeConstants() {
        Random random = new Random();
        centerX = random.nextDouble(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_WIDTH * 3 / 4);
        centerY = random.nextDouble(Constants.SCREEN_HEIGHT / 4, Constants.SCREEN_HEIGHT * 3 / 4);
        radius = random.nextDouble(150, 250);
    }
    private static EventHandler<ActionEvent> createEventHandler() {
        return event -> {
            int index = findIndex();
            if (index == 0)
                makeConstants();
            double theta = 2 * Math.PI / 5;
            double x = centerX + radius * Math.cos(index * theta);
            double y = centerY + radius * Math.sin(index * theta);
            BlackOrb blackOrb = new BlackOrb();
            blackOrbs.add(blackOrb);
            addEntity(blackOrb);
            blackOrb.setSceneLocation(x, y);
            makeLaser(blackOrb);
        };
    }
    private static void makeLaser(BlackOrb blackOrb) {
        makeBlackOrbs();
        for (BlackOrb orb: blackOrbs)
            if (orb != blackOrb) {
                BlackOrbLaser blackOrbLaser = new BlackOrbLaser(blackOrb, orb);
                addEntity(blackOrbLaser);
                blackOrbLaser.setSceneLocation(blackOrb.getSceneX(), blackOrb.getSceneY());
            }
    }
    private static void addEntity(Entity entity) {
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
