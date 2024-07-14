package bahar.window_kill.model.entities.attackers;
import bahar.window_kill.control.fazes.processors.strategies.strategies.DamageStrategy;
import bahar.window_kill.control.loader.SoundLoader;
import bahar.window_kill.model.entities.Collectable;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.LootDropper;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.util.Random;

public class Squarantine extends Entity implements LootDropper, AttackerEntity  {
    private double speed = 2;
    private double dashSpeed = 1;
    private int damage = 6;
    public Squarantine() {
        super(makeView(), 15, true, new DamageStrategy());
    }

    private static Node makeView() {
        Polygon polygon = new Polygon(0, 0, 0, 25, 25, 25, 25, 0);
        polygon.setFill(Color.TRANSPARENT);
        polygon.setStroke(Color.LIMEGREEN);
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
        double dx = targetX - getSceneX();
        double dy = targetY - getSceneY();
        double chord = Math.sqrt(dx * dx + dy * dy);
        Random random = new Random();
        if (dashSpeed > 5) dashSpeed -= 5;
        else if (random.nextDouble(0, 1) > 0.99)
            dashSpeed = 16;
        setSceneX(getSceneX() + dx / chord * speed * dashSpeed);
        setSceneY(getSceneY() + dy / chord * speed * dashSpeed);
    }

    @Override
    public void aggress() {
        strategy.act(this);
    }

    public void shout() {
        SoundLoader.HIT.play();
    }

    @Override
    public int getLootCount() {
        return 1;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(5, Color.LIMEGREEN);
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