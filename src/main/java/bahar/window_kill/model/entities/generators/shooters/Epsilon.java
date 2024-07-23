package bahar.window_kill.model.entities.generators.shooters;

import bahar.window_kill.control.fazes.processors.strategies.strategies.SpawnStrategy;
import bahar.window_kill.control.util.FileUtil;
import bahar.window_kill.control.util.SoundUtil;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.attackers.AttackerEntity;
import bahar.window_kill.model.entities.attackers.Bullet;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Epsilon extends ShooterEntity implements AttackerEntity {
    private static final double radius = 10;
    int xp = 0;
    int damage = 0;
    final int SPEED;
    public Epsilon(int speed) {
        super(makeView(), 100, true, new SpawnStrategy(400));
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
    public void setXp(int xp) {
        this.xp = xp;
    }
    public int getXp() {
        return xp;
    }
    public Entity makeBullet() {
        return new Bullet(getBulletDamage(), 3.5, Color.WHITE, getBulletDamage(), gunDirectionX, gunDirectionY, false);
    }
    public void setColor(Color color) {
        ((Circle) view).setStroke(color);
    }

    @Override
    public void move(double targetX, double targetY) {
        if (impactStrategy.isOnImpact()) {
            impactStrategy.act(this);
            return;
        }
        if (targetX < 0)
            setSceneX(getSceneX() - SPEED);
        if (targetX > 0)
            setSceneX(getSceneX() + SPEED);
        if (targetY < 0)
            setSceneY(getSceneY() - SPEED);
        if (targetY > 0)
            setSceneY(getSceneY() + SPEED);
    }

    @Override
    public void aggress() {
        strategy.act(this);
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
