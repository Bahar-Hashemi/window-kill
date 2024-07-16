package bahar.window_kill.model.bosswatch;

import bahar.window_kill.control.fazes.processors.strategies.strategies.DamageStrategy;
import bahar.window_kill.model.boards.MainBoard;
import bahar.window_kill.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.model.entities.generators.shooters.SmileyFace;
import bahar.window_kill.model.entities.generators.shooters.SmileyHand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Random;

public class SlapWatch extends BossWatch {
    private static SmileyHand hand;
    private static int counter;
    public SlapWatch(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(30, makeRunnable(face, leftHand, rightHand), face, leftHand, rightHand);
        setCycleCount(100);
        counter = 0;
    }
    private static Runnable makeRunnable(SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        return () -> {
            counter++;
            int change = (counter > 50)? -10: 10;
            if (hand == leftHand)
                hand.setSceneX(hand.getSceneX() + change);
            if (hand == rightHand)
                hand.setSceneX(hand.getSceneX() - change);
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        hand = new Random().nextBoolean()? leftHand: rightHand;
        hand.punch();
        hand.setAggressionStrategy(new DamageStrategy());
        hand.setBulletDamage(20);
    }

    @Override
    protected void onEnd() {
        super.onEnd();
        hand.free();
        hand.setAggressionStrategy(null);
    }

    @Override
    public boolean isValid(Epsilon epsilon, MainBoard mainBoard) {
        return hand.getHP() > 0 && hand.getAggressionStrategy() == null;
    }

    @Override
    public String getMessage() {
        return "Slap";
    }
}
