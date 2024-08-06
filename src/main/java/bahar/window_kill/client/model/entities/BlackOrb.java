package bahar.window_kill.client.model.entities;

import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.model.boards.GameBoard;
import bahar.window_kill.client.control.util.SoundUtil;
import javafx.animation.ScaleTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Random;

public class BlackOrb extends Entity implements BoardOwner, LootDropper{
    GameBoard gameBoard;
    public BlackOrb(String id) {
        super(id, makeView(), 30, true, null);
        byBoard();
        setWidth(60);
        setHeight(60);
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
    public void move() {
        //it does not move as you know :)
    }
    public void setX(double x) {
        super.setX(x);
        if (gameBoard != null)
            gameBoard.setLayoutX(x - 75);
    }
    public void setY(double y) {
        super.setY(y);
        if (gameBoard != null)
            gameBoard.setLayoutY(y - 75);
    }

    @Override
    public void aggress() {
    }

    @Override
    public void shout() {
        SoundUtil.HIT.play();
    }

    @Override
    public void byBoard() {
        gameBoard = new GameBoard(GameUtil.generateID(), true);
        gameBoard.lockBoardSize(150, 150);
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
        return new Collectable(GameUtil.generateID(), 30, Color.DARKBLUE);
    }
}
