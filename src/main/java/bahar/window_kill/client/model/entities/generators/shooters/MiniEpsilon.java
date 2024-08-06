package bahar.window_kill.client.model.entities.generators.shooters;

import bahar.window_kill.client.model.Bounds;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.attackers.Bullet;
import bahar.window_kill.client.control.states.offlline.processors.strategies.strategies.SpawnStrategy;
import bahar.window_kill.client.control.util.GameUtil;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class MiniEpsilon extends ShooterEntity {
    private static final double radius = 4;
    private final double difX, difY;
    public MiniEpsilon(String id) {
        super(id, makeView(), new Bounds(-4, -4, 4, 4),
                100, false, new SpawnStrategy(400));
        setBulletDamage(1);
        difX = new Random().nextDouble(-15, 15);
        difY = (new Random().nextBoolean()? 1: -1) * Math.sqrt(15 * 15 - difX * difX);
    }
    private static Node makeView() {
        Circle circle = new Circle();
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.rgb(255, 255, 255, 0.6)); // Set stroke color
        circle.setRadius(MiniEpsilon.radius);
        circle.setStrokeWidth(3);
        return circle;
    }
    public Entity makeBullet() {
        return new Bullet(GameUtil.generateID(), getBulletDamage(), 2, Color.rgb(255, 255, 255, 0.6), getBulletDamage(), gunDirectionX, gunDirectionY, false);
    }
    public double getDifX() {
        return difX;
    }
    public double getDifY() {
        return difY;
    }
    @Override
    public void move() {
        double speed = 6;
        User user = GameUtil.findMyUser(this, game);
        double dx = user.getEpsilon().getX() + difX - getX();
        double dy = user.getEpsilon().getY() + difY - getY();
        double chord = Math.sqrt(dx * dx + dy * dy);

        setX(getX() + dx / chord);
        setY(getY() + dy / chord);
    }
    @Override
    public void aggress() {
        strategy.act(this, game);
    }

    @Override
    public void shout() {

    }
}
