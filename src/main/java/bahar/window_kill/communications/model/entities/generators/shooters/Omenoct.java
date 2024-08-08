package bahar.window_kill.communications.model.entities.generators.shooters;

import bahar.window_kill.communications.model.boards.MainBoard;
import bahar.window_kill.communications.processors.util.strategies.attacks.SpawnStrategy;
import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.communications.model.Bounds;
import bahar.window_kill.communications.model.entities.Collectable;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.LootDropper;
import bahar.window_kill.communications.model.entities.attackers.Bullet;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.util.Random;

public class Omenoct extends ShooterEntity implements LootDropper {
    private int lockStrategy; // 0: up, 1: right, 2: down, 3: left
    public Omenoct(boolean isViewable, String id) {
        super(isViewable, id, (isViewable? makeView(): null), new Bounds(-40, -40, 40, 40), Omenoct.class.getName(),
                20, true, new SpawnStrategy(500));
        lockStrategy = new Random().nextInt(4);
        setBulletDamage(4);
    }
    private static Node makeView() {
        Group shapes = new Group();
        double centerX = 0, centerY = 0; // Assuming the octagon is centered at (0,0)
        Color[] colors = {Color.WHITE, Color.RED};
        int colorIndex = 0;

        // Coordinates for an octagon with a radius of 40 units
        double[] points = {
                35.0, 0.0,    // Right from center
                24.75, 24.75, // Bottom right
                0.0, 35.0,    // Bottom from center
                -24.75, 24.75,// Bottom left
                -35.0, 0.0,   // Left from center
                -24.75, -24.75,// Top left
                0.0, -35.0,   // Top from center
                24.75, -24.75 // Top right
        };
        // Create triangles
        for (int i = 0; i < points.length; i += 2) {
            Polygon triangle = new Polygon();
            triangle.getPoints().addAll(
                    centerX, centerY,
                    points[i], points[i + 1],
                    points[(i + 2) % points.length], points[(i + 3) % points.length]
            );
            triangle.setFill(colors[colorIndex % 2]);
            triangle.setStroke(Color.BLACK);
            triangle.setStrokeWidth(1.5);
            shapes.getChildren().add(triangle);
            colorIndex++;
        }
        //give it transition
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(10), shapes);
        rotateTransition.setCycleCount(-1);
        rotateTransition.setByAngle(360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();
        return shapes;
    }
    @Override
    public void move() { //todo correct here!!
        int speed = 5;
        MainBoard mainBoard = GameUtil.findMyUser(this, game).mainBoard;
        Bounds bounds = mainBoard.getBounds();
        if (lockStrategy == 0 && Math.abs(getY() - bounds.getMinimumY()) + 2 > speed)
            setY(getY() + (getY() - bounds.getMinimumY() > 0? -1: 1) * speed);
        else if (lockStrategy == 1 && Math.abs(getX() - bounds.getMaximumX()) + 2 > speed)
            setX(getX() + (getX() - bounds.getMaximumX() > 0? -1: 1) * speed);
        else if (lockStrategy == 2 && Math.abs(getY() - bounds.getMaximumY()) + 2 > speed)
            setY(getY() + (getY() - bounds.getMaximumY() > 0? -1: 1) * speed);
        else if (lockStrategy == 3 && Math.abs(getX() - bounds.getMinimumX()) + 2 > speed)
            setX(getX() + (getX() - bounds.getMinimumX() > 0? -1: 1) * speed);
        else
            lockStrategy = new Random().nextInt(4);
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
            SoundUtil.HIT.play();
    }

    @Override
    public Entity makeBullet() {
        return new Bullet(isViewable, GameUtil.generateID(), getBulletDamage(), 3, Color.RED, getBulletDamage(), gunDirectionX, gunDirectionY, true);
    }

    @Override
    public int getLootCount() {
        return 8;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(isViewable, GameUtil.generateID(), 4, Color.RED);
    }
}
