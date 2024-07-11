package bahar.window_kill.model.entities.generators.shooters;

import bahar.window_kill.control.fazes.processors.strategies.strategies.SpawnStrategy;
import bahar.window_kill.model.Watch;
import bahar.window_kill.model.boards.GameBoard;
import bahar.window_kill.model.entities.Collectable;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.LootDropper;
import bahar.window_kill.model.entities.attackers.Bullet;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.util.Random;

public class Omenoct extends ShooterEntity implements LootDropper {
    private int lockStrategy; // 0: up, 1: right, 2: down, 3: left
    Watch watch;
    public Omenoct() {
        super(makeView(), 20, true, new SpawnStrategy());
        lockStrategy = new Random().nextInt(4);
        watch = new Watch(500, actionEvent -> getAggressionStrategy().act(this));
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
    public void move(double width, double height) {
        int speed = 1;
        if (lockStrategy == 0 && Math.abs(view.getLayoutY()) > speed)
            view.setLayoutY(view.getLayoutY() + (view.getLayoutY() > 0? -1: 1) * speed);

        else if (lockStrategy == 1 && Math.abs(view.getLayoutX() - width) > speed)
            view.setLayoutX(view.getLayoutX() + (view.getLayoutX() > width? -1: 1) * speed);

        else if (lockStrategy == 2 && (view.getLayoutY() - height) > speed)
            view.setLayoutY(view.getLayoutY() + (view.getLayoutY() > height? -1: 1) * speed);

        else if (lockStrategy == 3 && Math.abs(view.getLayoutX()) > speed)
            view.setLayoutX(view.getLayoutX() + (view.getLayoutX() > 0? -1: 1) * speed);
    }

    @Override
    public void aggress() {
        watch.call();
    }

    @Override
    public void shout() {

    }

    @Override
    public Entity makeBullet() {
        return new Bullet(4, 3, Color.RED, 4, gunDirectionX, gunDirectionY, this, true);
    }

    @Override
    public int getLootCount() {
        return 8;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(4, Color.RED);
    }
}
