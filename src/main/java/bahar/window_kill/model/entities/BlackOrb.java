package bahar.window_kill.model.entities;

import bahar.window_kill.control.fazes.processors.strategies.strategies.SpawnStrategy;
import bahar.window_kill.control.fazes.processors.strategies.strategies.Strategy;
import bahar.window_kill.control.loader.SoundLoader;
import bahar.window_kill.model.Watch;
import bahar.window_kill.model.boards.GameBoard;
import bahar.window_kill.model.entities.BoardOwner;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.attackers.Bullet;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class BlackOrb extends Entity implements BoardOwner, LootDropper{
    GameBoard gameBoard;
    public BlackOrb() {
        super(makeView(), 30, true, null);
        byBoard();
    }
    private static Node makeView() {
        Group sphereVisual = new Group();

        Circle outerCircle = new Circle(30); // Outer circle representing the sphere boundary
        outerCircle.setFill(Color.BLACK); // Set the outer circle color
        outerCircle.setStroke(Color.DARKBLUE); // Optional: set a stroke color to enhance the 3D effect
        outerCircle.setStrokeWidth(10);

        Circle innerCircle = new Circle(22); // Inner circle to suggest depth
        innerCircle.setFill(Color.BLACK); // Set the inner circle color to a lighter shade
        innerCircle.setStroke(Color.DARKBLUE);
        innerCircle.setStrokeWidth(18);
        innerCircle.setOpacity(0.8); // Making the inner circle slightly transparent
        innerCircle.setCenterY(new Random().nextDouble(-5, 5)); // Offset the inner circle to create a 3D illusion

        sphereVisual.getChildren().addAll(outerCircle, innerCircle);

        ScaleTransition transition = new ScaleTransition();
        transition.setDuration(Duration.millis(1000));
        transition.setNode(sphereVisual);
        transition.setFromX(1);
        transition.setFromY(1);
        transition.setToX(0.7);
        transition.setToY(0.7);
        transition.setCycleCount(-1);
        transition.setAutoReverse(true);
        transition.play();

        return sphereVisual;
    }

    @Override
    public void move(double targetX, double targetY) {

    }
    public void setSceneX(double x) {
        super.setSceneX(x);
        if (gameBoard != null)
            gameBoard.setLayoutX(x - 75);
    }
    public void setSceneY(double y) {
        super.setSceneY(y);
        if (gameBoard != null)
            gameBoard.setLayoutY(y - 75);
    }

    @Override
    public void aggress() {
    }

    @Override
    public void shout() {
        SoundLoader.HIT.play();
    }

    @Override
    public void byBoard() {
        gameBoard = new GameBoard(true);
        gameBoard.lockSize(150, 150);
    }

    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }

    @Override
    public int getLootCount() {
        return 5;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(30, Color.DARKBLUE);
    }
}
