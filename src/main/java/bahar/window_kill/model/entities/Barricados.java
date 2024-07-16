package bahar.window_kill.model.entities;

import bahar.window_kill.control.util.ImageUtil;
import bahar.window_kill.model.Watch;
import bahar.window_kill.model.boards.GameBoard;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;

import java.util.Random;

public class Barricados extends Entity implements BoardOwner {
    GameBoard gameBoard;
    Watch watch;
    public Barricados() {
        super(makeView(), 1000 * 1000 * 1000, true, null);
        watch = new Watch(60 * 1000, () -> setHP(0));
        byBoard();
    }
    private static Node makeView() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(80);
        rectangle.setHeight(80);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(2);
        rectangle.setArcWidth(2);
        rectangle.setArcHeight(2);
        ImagePattern imagePattern = new ImagePattern(ImageUtil.BARRICADOS.getImage());
        rectangle.setFill(imagePattern);
        return rectangle;
    }
    @Override
    public void move(double targetX, double targetY) {
    }

    @Override
    public void aggress() {
        watch.call();
    }

    @Override
    public void shout() {
    }

    @Override
    public void byBoard() {
        gameBoard = new GameBoard(new Random().nextBoolean());
        gameBoard.lockSize(120, 120);
    }

    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }

    @Override
    public void setSceneX(double x) {
        super.setSceneX(x);
        if (gameBoard != null)
            gameBoard.setLayoutX(x - 20);
    }

    @Override
    public void setSceneY(double y) {
        super.setSceneY(y);
        if (gameBoard != null)
            gameBoard.setLayoutY(y - 20);
    }
    public void setLayoutX(double x) {
        super.setLayoutX(x);
        if (gameBoard != null)
            gameBoard.setLayoutX(x - 20);
    }
    public void setLayoutY(double y) {
        super.setLayoutY(y);
        if (gameBoard != null)
            gameBoard.setLayoutY(y - 20);
    }
}
