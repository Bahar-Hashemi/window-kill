package bahar.window_kill.client.model;

import bahar.window_kill.client.model.bosswatch.*;
import bahar.window_kill.client.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.client.model.entities.generators.shooters.SmileyHand;
import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.model.boards.MainBoard;
import bahar.window_kill.client.model.entities.generators.shooters.SmileyFace;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;

public class SmileyBrain {
    private final SmileyFace face;
    private final SmileyHand leftHand, rightHand;
    ArrayList<BossWatch> watches = new ArrayList<>();
    Watch attackController; boolean canAttack = false;
    private static Class<?>[] attackWatches = new Class[] {SlapWatch.class, ProjectileWatch.class, RapidFireWatch.class, SlapWatch.class, SqueezeWatch.class, VomitWatch.class};
    public SmileyBrain(Game game, SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        this.face = face;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        face.setSceneLocation(Constants.SCREEN_WIDTH / 2, -100);
        leftHand.setSceneLocation(-100, Constants.SCREEN_HEIGHT / 2);
        rightHand.setSceneLocation(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 2);
        watches.add(new ComingInWatch(game, face, leftHand, rightHand));
        attackController = new Watch(10000) {
            @Override
            protected void onCall() {
                canAttack = true;
            }
        };
    }
    public void act(Game game) {
        if (face.getHP() <= 0) {
            leftHand.setHP(0);
            rightHand.setHP(0);
        }
        for (BossWatch watch: watches)
            watch.call(game.clock);
        for (int i = watches.size() - 1; i >= 0; i--)
            if (watches.get(i).getCycleCount() == 0)
                watches.remove(i);
        attackController.call(game.clock);
        if (canAttack) {
            for (User user: game.users)
                attack(user.getEpsilon(), user.mainBoard);
            canAttack = false;
        }
    }
    private void attack(Epsilon epsilon, MainBoard mainBoard) {
        boolean doneSomething = false;
        while (!doneSomething) {
            Class<?> type = attackWatches[new Random().nextInt(attackWatches.length)];
            try {
                Constructor<?> constructor = type.getConstructor(SmileyFace.class, SmileyHand.class, SmileyHand.class);
                BossWatch watch = (BossWatch) constructor.newInstance(face, leftHand, rightHand);
                if (watch.isValid(epsilon, mainBoard)) {
                    watches.add(watch);
                    doneSomething = true;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public double getHP() {
        return face.getHP() + leftHand.getHP() + rightHand.getHP();
    }
}
