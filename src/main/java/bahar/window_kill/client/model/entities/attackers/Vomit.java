package bahar.window_kill.client.model.entities.attackers;

import bahar.window_kill.client.control.states.offlline.processors.strategies.strategies.DamageStrategy;
import bahar.window_kill.client.model.Watch;
import bahar.window_kill.client.model.entities.Entity;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Vomit extends Entity implements AttackerEntity {
    Watch opacityWatch;
    Watch damageWatch;
    private int damage = 20;
    public Vomit() {
        super(makeView(), 10, false, new DamageStrategy());
        opacityWatch = new Watch(30) {protected void onCall(){view.setOpacity(view.getOpacity() - 0.003);}};
        damageWatch = new Watch(500) {protected void onCall() {setHP(getHP() - 4); setDamage(getDamage() - 4); strategy.act(getMe(), game);}};
    }
    private Vomit getMe() {
        return this;
    }
    private static Node makeView() {
        Path path = new Path();

        // Reduce the length of each line to make the cross smaller
        // Line 1 - Vertical top
        MoveTo moveToTop = new MoveTo(0, -5); // Reduced from -10
        LineTo lineToTop = new LineTo(0, -15); // Reduced from -30
        path.getElements().addAll(moveToTop, lineToTop);

        // Line 2 - Vertical bottom
        MoveTo moveToBottom = new MoveTo(0, 5); // Reduced from 10
        LineTo lineToBottom = new LineTo(0, 15); // Reduced from 30
        path.getElements().addAll(moveToBottom, lineToBottom);

        // Line 3 - Horizontal left
        MoveTo moveToLeft = new MoveTo(-5, 0); // Reduced from -10
        LineTo lineToLeft = new LineTo(-15, 0); // Reduced from -30
        path.getElements().addAll(moveToLeft, lineToLeft);

        // Line 4 - Horizontal right
        MoveTo moveToRight = new MoveTo(5, 0); // Reduced from 10
        LineTo lineToRight = new LineTo(15, 0); // Reduced from 30
        path.getElements().addAll(moveToRight, lineToRight);

        path.setStroke(Color.RED);
        path.setStrokeWidth(5);
        path.setFill(Color.TRANSPARENT);

        return path;
    }
    @Override
    public void move() {
        opacityWatch.call(game.clock);
    }

    @Override
    public void aggress() {
        damageWatch.call(game.clock);
    }

    @Override
    public void shout() {
        //it doesn't shout
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
