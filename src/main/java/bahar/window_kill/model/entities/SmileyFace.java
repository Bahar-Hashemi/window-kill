package bahar.window_kill.model.entities;

import bahar.window_kill.control.fazes.processors.strategies.strategies.Strategy;
import bahar.window_kill.control.loader.SoundLoader;
import bahar.window_kill.model.boards.GameBoard;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;

public class SmileyFace extends Entity implements BoardOwner, LootDropper {
    GameBoard gameBoard;
    Circle leftEye, rightEye;
    CubicCurve mouth;
    public SmileyFace() {
        super(makeView(), 300, true, null);
        byBoard();
        makeEyes();
        makeMouth();
    }
    private void makeMouth() {
        // Creating a CubicCurve for the mouth
        mouth = new CubicCurve();
        mouth.setStartX(-32); // Starting point of the mouth on the left
        mouth.setStartY(15); // Adjust as needed
        mouth.setEndX(32); // Ending point of the mouth on the right
        mouth.setEndY(15); // Adjust as needed
        mouth.setControlX1(-10); // Control point 1 to curve the upper side of the mouth
        mouth.setControlY1(36); // Adjust to make the curve more natural
        mouth.setControlX2(10); // Control point 2 to curve the upper side of the mouth
        mouth.setControlY2(36); // Adjust to make the curve more natural
        mouth.setFill(Color.TRANSPARENT);
        mouth.setStroke(Color.RED);
        mouth.setStrokeWidth(5);

        ((Group) getView()).getChildren().add(mouth);
    }

    private void setHappiness(double y) { //between 0 - 100
        mouth.setControlY1(y / 100 * 36);
        mouth.setControlY2(y / 100 * 36);
    }

    private void makeEyes() {
        leftEye = new Circle(-10, -10, 6, Color.YELLOW); // Adjusted position
        rightEye = new Circle(10, -10, 6, Color.YELLOW); // Adjusted position
        ((Group) getView()).getChildren().addAll(leftEye, rightEye);
    }
    private static Node makeView() {
        Group smiley = new Group();
        // Face
        Circle face = new Circle(0, 0, 50, Color.TRANSPARENT);
        face.setStroke(Color.YELLOW);
        face.setStrokeWidth(5);

        smiley.getChildren().addAll(face);
        return smiley;
    }
    @Override
    public void byBoard() {
        gameBoard = new GameBoard(true);
        gameBoard.lockSize(140, 140);
    }

    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }

    @Override
    public void move(double targetX, double targetY) {
        moveEye(leftEye, -10, -10, targetX, targetY);
        moveEye(rightEye, 10, -10, targetX, targetY);
        setHappiness(getHP() / 300 * 100);
    }
    private void moveEye(Node node, double firstX, double firstY, double targetX, double targetY) {
        node.setLayoutX(firstX);
        node.setLayoutY(firstY);
        double dx = targetX - node.getLayoutX() - getView().getLayoutX();
        double dy = targetY - node.getLayoutY() - getView().getLayoutY();
        double chord = Math.sqrt(dx * dx + dy * dy);
        node.setLayoutX(node.getLayoutX() + dx / chord * 10);
        node.setLayoutY(node.getLayoutY() + dy / chord * 15);
    }
    @Override
    public void aggress() {
    }

    @Override
    public void shout() {
        SoundLoader.HIT.play();
    }

    @Override
    public int getLootCount() {
        return 25;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(10, Color.YELLOW);
    }
    public void setSceneX(double x) {
        super.setSceneX(x);
        gameBoard.setLayoutX(x - 70);
    }
    public void setSceneY(double y) {
        super.setSceneY(y);
        gameBoard.setLayoutY(y - 70);
    }
}
