package bahar.window_kill.client.control.util;

import bahar.window_kill.client.model.Bounds;
import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.GameElement;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.boards.GameBoard;
import bahar.window_kill.client.model.boards.MainBoard;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.view.MainStage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.util.Random;
import java.util.UUID;

public class GameUtil {

    public static double commonArea(GameElement first, GameElement second) {
        Bounds firstBounds = first.getBounds();
        Bounds secondBounds = second.getBounds();

        double xOverlap = Math.max(0, Math.min(firstBounds.getMaximumX(), secondBounds.getMaximumX()) - Math.max(firstBounds.getMinimumX(), secondBounds.getMinimumX()));
        double yOverlap = Math.max(0, Math.min(firstBounds.getMaximumY(), secondBounds.getMaximumY()) - Math.max(firstBounds.getMinimumY(), secondBounds.getMinimumY()));

        return xOverlap * yOverlap;
    }
    public static boolean nonHoveringBoardsInBounds(GameBoard board, Game game) { //todo correct here
        for (GameBoard gameBoard: game.gameBoards)
            if (!gameBoard.getHovering()) {
                if (commonArea(board, gameBoard) > 8)
                    return true;
            }
       return false;
    }
    public static User findMyUser(Entity entity, Game game) {
        for (User user : game.users) {
            if (user.mainBoard.getView() == entity.getView().getParent())
                return user;
        }
        return null;
    }
    public static double distance(Entity first, Entity second) {
        double dx = first.getX() - second.getX();
        double dy = first.getY() - second.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    public static boolean isInOneBoard(Entity entity) {
        javafx.geometry.Bounds boundsInScene = entity.getView().localToScene(entity.getView().getBoundsInLocal());
        for (Node node: ((Pane) MainStage.getInstance().getScene().getRoot()).getChildren())
            if (node instanceof Pane)
                if (node.localToScene(node.getBoundsInLocal()).contains(boundsInScene))
                    return true;
        return false;
    }
    public static void placeOutsideBoard(Entity entity, GameBoard gameBoard) {
        int placingStrategy = new Random().nextInt(0, 4);
        if (placingStrategy == 0) {
            entity.setX(new Random().nextDouble(gameBoard.getBounds().getMinimumX(), gameBoard.getBounds().getMaximumX()));
            entity.setY(gameBoard.getBounds().getMinimumX() - 100);
        } else if (placingStrategy == 1) {
            entity.setX(gameBoard.getBounds().getMaximumX() + 100);
            entity.setY(new Random().nextDouble(gameBoard.getBounds().getMinimumY(), gameBoard.getBounds().getMaximumY()));
        } else if (placingStrategy == 2) {
            entity.setX(new Random().nextDouble(gameBoard.getBounds().getMinimumX(), gameBoard.getBounds().getMaximumX()));
            entity.setY(gameBoard.getBounds().getMaximumY() + 100);
        } else {
            entity.setX(gameBoard.getBounds().getMinimumX() - 100);
            entity.setY(new Random().nextDouble(gameBoard.getBounds().getMinimumY(), gameBoard.getBounds().getMaximumY()));
        }
    }
    public static String generateID() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

    public static boolean intersects(GameElement first, GameElement second) {
        return commonArea(first, second) > 10;
    }
    public static void setSceneLocation(Node node, double x, double y) {
        if (node.getParent() != null) {
            node.setLayoutX(x - node.getParent().getLayoutX());
            node.setLayoutY(y - node.getParent().getLayoutY());
        }
        else {
            node.setLayoutX(x);
            node.setLayoutY(y);
        }
    }
}
