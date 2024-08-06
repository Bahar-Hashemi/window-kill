package bahar.window_kill.client.model.entities.attackers;

import bahar.window_kill.client.control.states.offlline.processors.strategies.strategies.DamageStrategy;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.client.model.Bounds;
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

public class Trigorath extends Entity implements LootDropper, AttackerEntity {
    private double speed = 0.025;
    private int damage = 10;
    public Trigorath(String id) {
        super(id, makeView(), new Bounds(0, 0, 30, 30),
                15, true, new DamageStrategy());
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
    public void move() {
        if (impactStrategy.isOnImpact()) {
            impactStrategy.act(this);
            return;
        }
        User user = GameUtil.findMyUser(this, game);
        double dx = user.getEpsilon().getX() - getX();
        double dy = user.getEpsilon().getY() - getY();
        setY(getY() + dy * speed);
        setX(getX() + dx * speed);
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
        return 2;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(GameUtil.generateID(), 5, Color.ORANGE);
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