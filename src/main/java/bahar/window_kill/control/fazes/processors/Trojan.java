package bahar.window_kill.control.fazes.processors;

import bahar.window_kill.control.Constants;
import bahar.window_kill.control.GameUtil;
import bahar.window_kill.model.SmileyBrain;
import bahar.window_kill.model.Watch;
import bahar.window_kill.model.entities.*;
import bahar.window_kill.model.entities.attackers.BlackOrbLaser;
import bahar.window_kill.model.entities.attackers.Squarantine;
import bahar.window_kill.model.entities.attackers.Trigorath;
import bahar.window_kill.model.entities.generators.SpawnerArchmire;
import bahar.window_kill.model.entities.generators.shooters.Nechropic;
import bahar.window_kill.model.entities.generators.shooters.Omenoct;
import bahar.window_kill.model.entities.generators.shooters.Wyrm;
import bahar.window_kill.view.MainStage;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.lang.reflect.Constructor;
import java.util.Random;

import static bahar.window_kill.control.Deck.*;

public class Trojan extends GameProcessor {
    boolean hasDone = false;
    SmileyBrain smileyBrain;
    public Trojan() {

    }
    @Override
    public void run() { //we need to use reflection here
        if (!hasDone) {
            SmileyFace smileyFace = new SmileyFace(); addEntity(smileyFace);
            SmileyHand leftHand = new SmileyHand(true); addEntity(leftHand);
            SmileyHand rightHand = new SmileyHand(false); addEntity(rightHand);
            smileyBrain = new SmileyBrain(smileyFace, leftHand, rightHand);
            hasDone = true;
        }
        //smileyBrain.act();
    }
    private void implementBlackOrb() {
        double centerX = Constants.SCREEN_WIDTH / 4;
        double centerY = Constants.SCREEN_HEIGHT / 4;
        double radius = 200;
        final int n = 5; // Number of vertices for a pentagon
        double[] xPoints = new double[n];
        double[] yPoints = new double[n];
        double theta = 2 * Math.PI / n; // Angle between vertices
        BlackOrb[]  blackOrbs = new BlackOrb[n];
        for (int i = 0; i < n; i++) {
            xPoints[i] = centerX + radius * Math.cos(i * theta);
            yPoints[i] = centerY + radius * Math.sin(i * theta);
            blackOrbs[i] = new BlackOrb();
            addEntity(blackOrbs[i]);
            blackOrbs[i].setSceneLocation(xPoints[i], yPoints[i]);
        }
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                BlackOrbLaser blackOrbLaser = new BlackOrbLaser(blackOrbs[i], blackOrbs[j]);
                addEntity(blackOrbLaser);
                blackOrbLaser.setSceneLocation(blackOrbs[i].getSceneX(), blackOrbs[i].getSceneY());
            }
    }
    private void addEntity(Entity entity) {
        if (entity instanceof BoardOwner) {
            BoardOwner boardOwner = (BoardOwner) entity;
            MainStage.add(boardOwner.getBoard()); gameBoards.add(boardOwner.getBoard());
            MainStage.add(entity.getView()); entities.add(entity);
            boardOwner.getBoard().toBack();
        } else {
            mainBoard.add(entity.getView());
            entities.add(entity);
        }
    }
}
