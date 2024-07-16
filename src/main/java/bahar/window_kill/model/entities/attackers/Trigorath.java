package bahar.window_kill.model.entities.attackers;

import bahar.window_kill.control.fazes.processors.strategies.strategies.DamageStrategy;
import bahar.window_kill.control.util.SoundUtil;
import bahar.window_kill.model.entities.Collectable;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.LootDropper;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class Trigorath extends Entity implements LootDropper, AttackerEntity {
    private double speed = 0.025;
    private int damage = 10;
    public Trigorath() {
        super(makeView(), 15, true, new DamageStrategy());
    }

    private static Node makeView() {
        Polygon polygon = new Polygon(15, 0, 0, 15 * Math.sqrt(3), 30, 15 * Math.sqrt(3));
        polygon.setFill(Color.TRANSPARENT);
        polygon.setStroke(Color.ORANGE);
        polygon.setStrokeWidth(3);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(10), polygon);
        rotateTransition.setCycleCount(-1);
        rotateTransition.setByAngle(360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();
        return polygon;
    }

    @Override
    public void move(double targetX, double targetY) {
        if (impactStrategy.isOnImpact()) {
            impactStrategy.act(this);
            return;
        }
        double x = targetX - getSceneX();
        double y = targetY - getSceneY();
        double deltaX = x * speed;
        double deltaY = y * speed;
        setSceneY(getSceneY() + deltaY);
        setSceneX(getSceneX() + deltaX);
    }

    @Override
    public void aggress() {
        strategy.act(this);
    }

    public void shout() {
        SoundUtil.HIT.play();
    }

    @Override
    public int getLootCount() {
        return 2;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(5, Color.ORANGE);
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