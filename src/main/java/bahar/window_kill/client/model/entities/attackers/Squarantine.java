package bahar.window_kill.client.model.entities.attackers;
import bahar.window_kill.client.control.states.offlline.processors.strategies.strategies.DamageStrategy;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.entities.Collectable;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.LootDropper;
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
    public Squarantine(String id) {
        super(id, makeView(), 15, true, new DamageStrategy());
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
    public void move() {
        if (impactStrategy.isOnImpact()) {
            impactStrategy.act(this);
            return;
        }
        User user = GameUtil.findMyUser(this, game);
        double dx = user.getEpsilon().getSceneX() - getSceneX();
        double dy = user.getEpsilon().getSceneY() - getSceneY();
        double chord = Math.sqrt(dx * dx + dy * dy);
        Random random = new Random();
        if (dashSpeed > 5) dashSpeed -= 5;
        else if (random.nextDouble(0, 1) > 0.99)
            dashSpeed = 16;
        setX(getSceneX() + dx / chord * speed * dashSpeed);
        setY(getSceneY() + dy / chord * speed * dashSpeed);
    }

    @Override
    public void aggress() {
        strategy.act(this, game);
    }

    public void shout() {
        SoundUtil.HIT.play();
    }

    @Override
    public int getLootCount() {
        return 1;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(GameUtil.generateID(), 5, Color.LIMEGREEN);
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