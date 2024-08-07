package bahar.window_kill.communications.model.entities.generators.shooters;

import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.communications.model.Bounds;
import bahar.window_kill.communications.model.boards.GameBoard;
import bahar.window_kill.communications.model.entities.BoardOwner;
import bahar.window_kill.communications.model.entities.Collectable;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.LootDropper;
import bahar.window_kill.communications.model.entities.attackers.Bullet;
import bahar.window_kill.client.control.util.SoundUtil;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;

import java.util.Random;

public class SmileyFace extends ShooterEntity implements BoardOwner, LootDropper {
    GameBoard gameBoard;
    Circle leftEye, rightEye;
    CubicCurve mouth;
    private double x;

    public SmileyFace(boolean isViewable, String id) {
        super(isViewable, id, (isViewable? makeView(): null), new Bounds(-50, -50, 50, 50), SmileyFace.class.getName(),
                1000, true, null);
        byBoard();
        makeEyes();
        makeMouth();
        setBulletDamage(20);
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
        gameBoard = new GameBoard(isViewable, GameUtil.generateID(), true);
        gameBoard.lockBoardSize(140, 140);
    }

    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }

    @Override
    public void move() {
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
        if (strategy != null)
            strategy.act(this, game);
    }

    @Override
    public void morph() {
        moveEye(leftEye, -10, -10, gunDirectionX, gunDirectionY);
        moveEye(rightEye, 10, -10, gunDirectionX, gunDirectionY);
        setHappiness(getHP() / 1000 * 100);
    }

    @Override
    public void shout() {
        SoundUtil.HIT.play();
    }

    @Override
    public int getLootCount() {
        return 25;
    }

    @Override
    public Entity makeLoot() {
        return new Collectable(isViewable, GameUtil.generateID(), 40, Color.YELLOW);
    }
    public void setX(double x) {
        super.setX(x);
        gameBoard.setLayoutX(x - 70);
    }
    public void setY(double y) {
        super.setY(y);
        gameBoard.setLayoutY(y - 70);
    }

    @Override
    public Entity makeBullet() {
        double x = new Random().nextDouble(-1, 1);
        double y = Math.sqrt(1 - x * x);
        return new Bullet(isViewable, GameUtil.generateID(), getBulletDamage(), 5, Color.YELLOW, getBulletDamage(), x, y, true);    }
}
