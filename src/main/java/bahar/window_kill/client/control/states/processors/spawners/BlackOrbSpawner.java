package bahar.window_kill.client.control.states.processors.spawners;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.model.entities.BlackOrb;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.attackers.BlackOrbLaser;

import java.util.ArrayList;
import java.util.Random;

public class BlackOrbSpawner extends Spawner {
    static double centerX, centerY;
    static ArrayList<BlackOrb> blackOrbs = new ArrayList<>();
    static double radius;
    public BlackOrbSpawner(Deck deck) {
        super(500, createRunnable(deck), deck);
        setCycleCount(findCycle(deck));
    }
    private static void makeBlackOrbs(Deck deck) {
        blackOrbs = new ArrayList<>();
        for (Entity entity: deck.entities)
            if (entity instanceof BlackOrb)
                blackOrbs.add((BlackOrb) entity);
    }
    private static int findIndex(Deck deck) {
        makeBlackOrbs(deck);
        return blackOrbs.size();
    }
    private static int findCycle(Deck deck) {
        return 5 - findIndex(deck);
    }
    private static void makeConstants() {
        Random random = new Random();
        centerX = random.nextDouble(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_WIDTH * 3 / 4);
        centerY = random.nextDouble(Constants.SCREEN_HEIGHT / 4, Constants.SCREEN_HEIGHT * 3 / 4);
        radius = random.nextDouble(150, 250);
    }
    private static Runnable createRunnable(Deck deck) {
        return () -> {
            int index = findIndex(deck);
            if (index == 0)
                makeConstants();
            double theta = 2 * Math.PI / 5;
            double x = centerX + radius * Math.cos(index * theta);
            double y = centerY + radius * Math.sin(index * theta);
            BlackOrb blackOrb = new BlackOrb();
            blackOrbs.add(blackOrb);
            addEntity(blackOrb, deck);
            blackOrb.setSceneLocation(x, y);
            makeLaser(blackOrb, deck);
        };
    }
    private static void makeLaser(BlackOrb blackOrb, Deck deck) {
        makeBlackOrbs(deck);
        for (BlackOrb orb: blackOrbs)
            if (orb != blackOrb) {
                BlackOrbLaser blackOrbLaser = new BlackOrbLaser(blackOrb, orb);
                addEntity(blackOrbLaser, deck);
                blackOrbLaser.setSceneLocation(blackOrb.getSceneX(), blackOrb.getSceneY());
            }
    }
}
