package bahar.window_kill.client.model.bosswatch;

import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.entities.generators.shooters.Epsilon;
import bahar.window_kill.client.model.entities.generators.shooters.SmileyHand;
import bahar.window_kill.client.control.states.offlline.processors.strategies.strategies.DamageStrategy;
import bahar.window_kill.client.model.boards.MainBoard;
import bahar.window_kill.client.model.entities.generators.shooters.SmileyFace;

import java.util.Random;

public class SlapWatch extends BossWatch {
    private static SmileyHand hand;
    private static int counter;
    public SlapWatch(Game game, SmileyFace face, SmileyHand leftHand, SmileyHand rightHand) {
        super(30, game, face, leftHand, rightHand);
        setCycleCount(100);
        counter = 0;
    }

    @Override
    protected void onCall() {
        super.onCall();
        counter++;
        int change = (counter > 50)? -10: 10;
        if (hand == leftHand)
            hand.setX(hand.getX() + change);
        if (hand == rightHand)
            hand.setX(hand.getX() - change);
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
