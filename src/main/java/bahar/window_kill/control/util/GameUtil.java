package bahar.window_kill.control.util;

import bahar.window_kill.model.boards.GameBoard;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.view.MainStage;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.util.Random;

import static bahar.window_kill.control.Deck.gameBoards;

public class GameUtil {
    public static double commonArea(Entity first, Entity second) {
        double totalCommonArea = 0;
        for (Node firstNode : getNodesFromView(first.getView())) {
            Shape firstShape = (Shape) firstNode;
            for (Node secondNode : getNodesFromView(second.getView())) {
                Shape secondShape = (Shape) secondNode;
                Shape intersection = Shape.intersect(firstShape, secondShape);
                double area = intersection.getBoundsInLocal().getWidth() * intersection.getBoundsInLocal().getHeight();
                if (area > 1)
                    totalCommonArea += area;
            }
        }

        return totalCommonArea;
    }
    private static Node[] getNodesFromView(Node view) {
        if (view instanceof Group)
            return ((Group) view).getChildren().toArray(new Node[0]);
        else if (view instanceof Shape)
            return new Node[]{view};
        else
            return new Node[]{};
    }
    public static Bounds getSceneBounds(Entity entity) {
        return entity.getView().localToScene(entity.getView().getBoundsInLocal());
    }
    public static boolean nonHoveringBoardsInBounds(GameBoard board) {
        Bounds firstBounds = board.localToScene(board.getBoundsInLocal());
        for (GameBoard gameBoard: gameBoards)
            if (!gameBoard.getHovering()) {
                Bounds secondBounds = gameBoard.localToScene(gameBoard.getBoundsInLocal());
                if (firstBounds.intersects(secondBounds))
                    return true;
            }
       return false;
    }
    public static boolean intersects(GameBoard firstBoard, GameBoard secondBoard) {
        Bounds firstBounds = firstBoard.localToScene(firstBoard.getBoundsInLocal());
        Bounds secondBounds = secondBoard.localToScene(secondBoard.getBoundsInLocal());
        return firstBounds.intersects(secondBounds);
    }
    public static void setSceneX(Node node, double x) {
        node.setLayoutX(x - node.getParent().getLayoutX());
    }
    public static void setSceneY(Node node, double y) {
        node.setLayoutY(y - node.getParent().getLayoutY());
    }
    public static void setSceneLocation(Node node, double x, double y) {
        setSceneX(node, x);
        setSceneY(node, y);
    }
    public static boolean isInOneBoard(Entity entity) {
        Bounds boundsInScene = entity.getView().localToScene(entity.getView().getBoundsInLocal());
        for (Node node: ((Pane) MainStage.getInstance().getScene().getRoot()).getChildren())
            if (node instanceof Pane)
                if (node.localToScene(node.getBoundsInLocal()).contains(boundsInScene))
                    return true;
        return false;
    }
    public static void placeOutsideBoard(Entity entity, GameBoard gameBoard) {
        int placingStrategy = new Random().nextInt(0, 4);
        if (placingStrategy == 0) {
            entity.setSceneX(new Random().nextDouble(gameBoard.getLayoutX(), gameBoard.getLayoutX() + gameBoard.getWidth()));
            entity.setSceneY(gameBoard.getLayoutY() -100);
        } else if (placingStrategy == 1) {
            entity.setSceneX(gameBoard.getLayoutX() + gameBoard.getWidth() + 100);
            entity.setSceneY(new Random().nextDouble(gameBoard.getLayoutY(), gameBoard.getLayoutY() + gameBoard.getHeight()));
        } else if (placingStrategy == 2) {
            entity.setSceneX(new Random().nextDouble(gameBoard.getLayoutX(), gameBoard.getLayoutX() + gameBoard.getWidth()));
            entity.setSceneY(gameBoard.getLayoutY() + gameBoard.getHeight() + 100);
        } else {
            entity.setSceneX(gameBoard.getLayoutX() - 100);
            entity.setSceneY(new Random().nextDouble(gameBoard.getLayoutY(), gameBoard.getLayoutY() + gameBoard.getHeight()));
        }
    }
}
