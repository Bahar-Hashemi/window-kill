package bahar.window_kill.client.model.boards;

import bahar.window_kill.client.model.Bounds;
import bahar.window_kill.client.model.GameElement;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Scanner;

public class GameBoard extends GameElement {
    protected boolean hovering;
    public GameBoard(String id, boolean hovering) {
        super(id, makeView(hovering), new Bounds(0, 0, 0, 0));
        this.hovering = hovering;
        clip();
    }
    private static Pane makeView(boolean hovering) {
        Pane board = new Pane();
        board.setStyle("-fx-font-family: \"cooper black\"; -fx-font-weight: bold; -fx-font-size: 12px; -fx-background-color: rgba(0, 0, 0, 1);");
        makeBorder(board, hovering);
        return board;
    }
    private static void makeBorder(Pane board, boolean hovering) {
        BorderStroke borderStroke = new BorderStroke(
                (hovering? Color.rgb(255, 255, 255, 0.1): Color.RED), // Border color
                BorderStrokeStyle.SOLID, // Border style
                new CornerRadii(2),
                new BorderWidths((hovering? 2: 5)) // Border width
        );
        board.setBorder(new Border(borderStroke));
    }
    public void setHovering(boolean hovering) {
        this.hovering = hovering;
        makeBorder((Pane) getView(), hovering);
    }
    public void setDimensions(double x, double y, double width, double height) {
        setLayoutX(x); setLayoutY(y);
        lockBoardSize(width, height);
    }
    public void setLayoutX(double x) {
        setX(x);
        getView().setLayoutX(x);
    }
    public void setLayoutY(double y) {
        setY(y);
        getView().setLayoutY(y);
    }
    public void lockBoardSize(double width, double height) {
        lockBoardWidth(width);
        lockBoardHeight(height);
    }
    public void lockBoardWidth(double width) {
        bounds.setMaximumX(width);
        Pane board = (Pane) getView();
        board.setMinWidth(width);
        board.setMaxWidth(width);
        board.setPrefWidth(width);
        clip();
    }
    public void lockBoardHeight(double height) {
        bounds.setMaximumY(height);
        Pane board = (Pane) getView();
        board.setMinHeight(height); board.setMaxHeight(height);
        clip();
    }
    public void setSize(double width, double height) {
        bounds.setMaximumX(width);
        bounds.setMaximumY(height);
        lockBoardWidth(width); lockBoardHeight(height);
    }
    private void clip() {
        Rectangle clip = new Rectangle(bounds.getMaximumX(), bounds.getMaximumY());
        getView().setClip(clip);
    }
    public void add(Node node) {
        ((Pane) getView()).getChildren().add(node);
    }
    public void remove(Node node) {
        ((Pane) getView()).getChildren().remove(node);
    }
    public boolean getHovering() {
        return hovering;
    }
    public void clear() {
        Pane board = (Pane) getView();
        for (int i = board.getChildren().size() - 1; i >= 0; i--) {
            Node node = board.getChildren().get(i);
            if (!(node instanceof Pane))
                board.getChildren().remove(node);
        }
    }
    public void readFromString(Scanner sc) {
//        setHovering(sc.nextBoolean());
//        setLayoutX(sc.nextDouble());
//        setLayoutY(sc.nextDouble());
//        lockBoardSize(sc.nextDouble(), sc.nextDouble());
    }
}
