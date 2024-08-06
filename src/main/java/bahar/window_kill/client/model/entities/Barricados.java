package bahar.window_kill.client.model.entities;

import bahar.window_kill.client.control.util.GameUtil;
import bahar.window_kill.client.model.Watch;
import bahar.window_kill.client.model.boards.GameBoard;
import bahar.window_kill.client.control.util.ImageUtil;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;

import java.util.Random;

public class Barricados extends Entity implements BoardOwner {
    GameBoard gameBoard;
    Watch watch;
    public Barricados(String id) {
        super(id, makeView(), 1000 * 1000 * 1000, true, null);
        watch = new Watch(60 * 1000) {
            @Override
            protected void onEnd() {
                super.onEnd();
                setHP(0);
            }
        };
        setSize(80, 80);
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
    public void move() {
        //it does not move as you know :)
    }

    @Override
    public void aggress() {
        watch.call(game.clock);
    }

    @Override
    public void shout() {
    }

    @Override
    public void byBoard() {
        gameBoard = new GameBoard(GameUtil.generateID(), new Random().nextBoolean());
        gameBoard.lockBoardSize(120, 120);
    }

    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }

    public void setX(double x) {
        super.setX(x);
        if (gameBoard != null)
            gameBoard.setLayoutX(x - 20);
    }

    public void setY(double y) {
        super.setY(y);
        if (gameBoard != null)
            gameBoard.setLayoutY(y - 20);
    }
}
