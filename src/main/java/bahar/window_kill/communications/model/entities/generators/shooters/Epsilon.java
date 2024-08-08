package bahar.window_kill.communications.model.entities.generators.shooters;

import bahar.window_kill.communications.processors.util.strategies.attacks.SpawnStrategy;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.communications.model.Bounds;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.attackers.AttackerEntity;
import bahar.window_kill.communications.model.entities.attackers.Bullet;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Epsilon extends ShooterEntity implements AttackerEntity {
    private static final double radius = 10;
    int damage = 0;
    final int SPEED;
    public Epsilon(boolean isViewable, String id, int speed) {
        super(isViewable, id, (isViewable? makeView(): null), new Bounds(-20, -20, 20, 20), Epsilon.class.getName(),
                100, true, new SpawnStrategy(400));
        setBulletDamage(5);
        this.SPEED = speed;
    }
    private static Node makeView() {
        Circle circle = new Circle();
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.WHITE); // Set stroke color
        circle.setRadius(Epsilon.radius);
        circle.setStrokeWidth(3);
        return circle;
    }
    public Entity makeBullet() {
        if (isViewable)
            new Bullet(isViewable, GameUtil.generateID(), getBulletDamage(), 3.5, (Color) ((Circle) view).getStroke(), getBulletDamage(), gunDirectionX, gunDirectionY, false);
        return new Bullet(isViewable, GameUtil.generateID(), getBulletDamage(), 3.5, Color.WHITE, getBulletDamage(), gunDirectionX, gunDirectionY, false);
    }
    public void setColor(Color color) {
        ((Circle) view).setStroke(color);
    }

    @Override
    public void move() {
        if (impactStrategy.isOnImpact()) {
            impactStrategy.act(this);
            return;
        }
        User user = GameUtil.findMyUser(this, game);
        if (user.hasLeftRequest())
            setX(getX() - SPEED);
        if (user.hasRightRequest())
            setX(getX() + SPEED);
        if (user.hasUpRequest())
            setY(getY() - SPEED);
        if (user.hasDownRequest())
            setY(getY() + SPEED);
    }

    @Override
    public void aggress() {
        strategy.act(this, game);
    }

    @Override
    public void morph() {
        super.morph();
    }

    @Override
    public void shout() {
        if (isViewable)
            SoundUtil.DAMAGE.play();
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
