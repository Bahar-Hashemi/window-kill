package bahar.window_kill.communications.model;

import bahar.window_kill.communications.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.communications.model.entities.generators.shooters.SmileyHand;
import bahar.window_kill.communications.util.Constants;
import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.model.entities.generators.shooters.SmileyFace;
import bahar.window_kill.communications.model.bosswatch.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;

public class SmileyBrain {
    private final SmileyFace face;
    private final SmileyHand leftHand, rightHand;
    private Game game;
    ArrayList<BossWatch> watches = new ArrayList<>();
    Watch attackController; boolean canAttack = false;
    private static Class<?>[] attackWatches = new Class[] {SlapWatch.class, ProjectileWatch.class, RapidFireWatch.class, SlapWatch.class, SqueezeWatch.class, VomitWatch.class};
    public SmileyBrain(Game game, SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        this.face = face;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.game = game;
        face.setLocation(1536.0 / 2, -100);
        leftHand.setLocation(-100, 950.0 / 2);
        rightHand.setLocation(1536, 950.0 / 2);
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
                Constructor<?> constructor = type.getConstructor(Game.class, SmileyFace.class, SmileyHand.class, SmileyHand.class);
                BossWatch watch = (BossWatch) constructor.newInstance(game, face, leftHand, rightHand);
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
