package bahar.window_kill.client.control.states.offlline.processors.spawners;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.entities.BlackOrb;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.attackers.BlackOrbLaser;

import java.util.ArrayList;
import java.util.Random;

public class BlackOrbSpawner extends Spawner {
    static double centerX, centerY;
    static ArrayList<BlackOrb> blackOrbs = new ArrayList<>();
    static double radius;
    public BlackOrbSpawner(Game game) {
        super(500, game);
        setCycleCount(findCycle(game));
    }
    private static void makeBlackOrbs(Game game) {
        blackOrbs = new ArrayList<>();
        for (Entity entity: game.entities)
            if (entity instanceof BlackOrb)
                blackOrbs.add((BlackOrb) entity);
    }
    private static int findIndex(Game game) {
        makeBlackOrbs(game);
        return blackOrbs.size();
    }
    private static int findCycle(Game game) {
        return 5 - findIndex(game);
    }
    private static void makeConstants() {
        Random random = new Random();
        centerX = random.nextDouble(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_WIDTH * 3 / 4);
        centerY = random.nextDouble(Constants.SCREEN_HEIGHT / 4, Constants.SCREEN_HEIGHT * 3 / 4);
        radius = random.nextDouble(150, 250);
    }
    protected void onCall() {
        int index = findIndex(game);
        if (index == 0)
            makeConstants();
        double theta = 2 * Math.PI / 5;
        double x = centerX + radius * Math.cos(index * theta);
        double y = centerY + radius * Math.sin(index * theta);
        BlackOrb blackOrb = new BlackOrb(GameUtil.generateID());
        blackOrbs.add(blackOrb);
        addEntity(blackOrb, game);
        blackOrb.setLocation(x, y);
        makeLaser(blackOrb, game);
    }
    private static void makeLaser(BlackOrb blackOrb, Game game) {
        makeBlackOrbs(game);
        for (BlackOrb orb: blackOrbs)
            if (orb != blackOrb) {
                BlackOrbLaser blackOrbLaser = new BlackOrbLaser(GameUtil.generateID(), blackOrb, orb);
                addEntity(blackOrbLaser, game);
                blackOrbLaser.setLocation(blackOrb.getX(), blackOrb.getY());
            }
    }
}
