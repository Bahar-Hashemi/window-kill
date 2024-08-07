package bahar.window_kill.communications.model.boards;

import bahar.window_kill.communications.model.Bounds;
import bahar.window_kill.communications.model.GameElement;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Scanner;

public class GameBoard extends GameElement {
    protected boolean hovering;
    public GameBoard(boolean isViewable, String id, boolean hovering) {
        super(isViewable, id, makeView(isViewable, hovering), new Bounds(0, 0, 0, 0), GameBoard.class.getName());
        this.hovering = hovering;
        clip();
    }
    public GameBoard(boolean isViewable, String id, boolean hovering, String className) {
        super(isViewable, id, makeView(isViewable, hovering), new Bounds(0, 0, 0, 0), className);
        this.hovering = hovering;
        clip();
    }
    private static Pane makeView(boolean isViewable, boolean hovering) {
        if (!isViewable)
            return null;
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
        if (!isViewable)
            return;
        makeBorder((Pane) getView(), hovering);
    }
    public void setDimensions(double x, double y, double width, double height) {
        setLayoutX(x); setLayoutY(y);
        lockBoardSize(width, height);
    }
    public void setLayoutX(double x) {
        setX(x);
        if (!isViewable)
            return;
        getView().setLayoutX(x);
    }
    public void setLayoutY(double y) {
        setY(y);
        if(!isViewable)
            return;
        getView().setLayoutY(y);
    }
    public void lockBoardSize(double width, double height) {
        lockBoardWidth(width);
        lockBoardHeight(height);
    }
    public void lockBoardWidth(double width) {
        bounds.setMaximumX(width);
        if (!isViewable)
            return;
        Pane board = (Pane) getView();
        board.setMinWidth(width);
        board.setMaxWidth(width);
        board.setPrefWidth(width);
        clip();
    }
    public void lockBoardHeight(double height) {
        bounds.setMaximumY(height);
        if (!isViewable)
            return;
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
        if (!isViewable)
            return;
        Rectangle clip = new Rectangle(bounds.getMaximumX(), bounds.getMaximumY());
        getView().setClip(clip);
    }
    public void add(Node node) {
        if (node == null) return;
        ((Pane) getView()).getChildren().add(node);
    }
    public void remove(Node node) {
        if (node == null) return;
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
}
