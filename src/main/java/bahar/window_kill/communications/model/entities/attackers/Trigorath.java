package bahar.window_kill.communications.model.entities.attackers;

import bahar.window_kill.communications.processors.util.strategies.attacks.DamageStrategy;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.communications.model.Bounds;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.entities.Collectable;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.LootDropper;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class Trigorath extends Entity implements LootDropper, AttackerEntity {
    private double speed = 0.025;
    private int damage = 10;
    public Trigorath(boolean isViewable, String id) {
        super(isViewable, id, (isViewable? makeView(): null), new Bounds(0, 0, 30, 30), Trigorath.class.getName(),
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

    @Override
    public void morph() {

    }

    public void shout() {
        if (isViewable)
            SoundUtil.HIT.play();
    }

    @Override
    public int getLootCount() {
        return 2;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(isViewable, GameUtil.generateID(), 5, Color.ORANGE);
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