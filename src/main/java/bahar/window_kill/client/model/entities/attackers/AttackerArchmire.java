package bahar.window_kill.client.model.entities.attackers;

import bahar.window_kill.client.control.states.processors.strategies.strategies.DamageStrategy;
import bahar.window_kill.client.model.Watch;
import bahar.window_kill.client.model.entities.Collectable;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.LootDropper;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class AttackerArchmire extends Entity implements LootDropper, AttackerEntity {
    Watch opacityWatch;
    Watch damageWatch;
    private int damage = 10;
    public AttackerArchmire() {
        super(makeView(), 10, false, new DamageStrategy());
        opacityWatch = new Watch(30, () -> {view.setOpacity(view.getOpacity() - 0.003);});
        damageWatch = new Watch(800, () -> {setHP(getHP() - 2); setDamage(getDamage() - 2); strategy.act(this, deck);});
    }
    private static Node makeView() {
        Path path = new Path();

        // Move to the start point of the upper curve
        MoveTo moveTo = new MoveTo(-25, 0);

        // Upper curve
        QuadCurveTo upperCurve = new QuadCurveTo();
        upperCurve.setControlX(0);
        upperCurve.setControlY(-50);
        upperCurve.setX(25);
        upperCurve.setY(0);

        // Lower curve, starting from the end of the upper curve
        QuadCurveTo lowerCurve = new QuadCurveTo();
        lowerCurve.setControlX(0);
        lowerCurve.setControlY(25);
        lowerCurve.setX(-25);
        lowerCurve.setY(0);

        // Add all parts to the path
        path.getElements().add(moveTo);
        path.getElements().addAll(upperCurve, lowerCurve);

        // Close the path to make a continuous shape
        path.getElements().add(new ClosePath());

        path.setStroke(Color.DARKMAGENTA);
        path.setFill(Color.DARKMAGENTA);
        path.setOpacity(0.5);

        return path;
    }
    @Override
    public void move() {
        opacityWatch.call(deck.clock);
        //it doesn't move
    }

    @Override
    public void aggress() {
        damageWatch.call(deck.clock);
    }

    @Override
    public void shout() {
        //it doesn't shout
    }

    @Override
    public int getLootCount() {
        return 1;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(3, Color.DARKMAGENTA);
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
