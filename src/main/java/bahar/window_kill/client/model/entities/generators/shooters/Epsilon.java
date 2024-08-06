package bahar.window_kill.client.model.entities.generators.shooters;

import bahar.window_kill.client.control.states.offlline.processors.strategies.strategies.SpawnStrategy;
import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.attackers.AttackerEntity;
import bahar.window_kill.client.model.entities.attackers.Bullet;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Epsilon extends ShooterEntity implements AttackerEntity {
    private static final double radius = 10;
    int damage = 0;
    final int SPEED;
    public Epsilon(String id, int speed) {
        super(id, makeView(), 100, true, new SpawnStrategy(400));
        setBulletDamage(5);
        this.SPEED = speed;
        setSize(20, 20);
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
        return new Bullet(GameUtil.generateID(), getBulletDamage(), 3.5, Color.WHITE, getBulletDamage(), gunDirectionX, gunDirectionY, false);
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
            setX(getSceneX() - SPEED);
        if (user.hasRightRequest())
            setX(getSceneX() + SPEED);
        if (user.hasUpRequest())
            setY(getSceneY() - SPEED);
        if (user.hasDownRequest())
            setY(getSceneY() + SPEED);
    }

    @Override
    public void aggress() {
        strategy.act(this, game);
    }

    @Override
    public void shout() {
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
