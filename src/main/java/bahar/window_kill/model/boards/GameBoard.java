package bahar.window_kill.model.boards;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameBoard extends Pane {
    protected boolean hovering;
    public GameBoard(boolean hovering) {
        this.hovering = hovering;
        makeBorder(hovering);
        setStyle("-fx-font-family: \"cooper black\"; -fx-font-weight: bold; -fx-font-size: 12px; -fx-background-color: rgba(0, 0, 0, 1);");
        clip();
    }
    private void makeBorder(boolean hovering) {
        BorderStroke borderStroke = new BorderStroke(
                (hovering? Color.rgb(255, 255, 255, 0.1): Color.RED), // Border color
                BorderStrokeStyle.SOLID, // Border style
                new CornerRadii(2),
                new BorderWidths((hovering? 2: 5)) // Border width
        );
        setBorder(new Border(borderStroke));
    }
    public void setHovering(boolean hovering) {
        this.hovering = hovering;
        makeBorder(hovering);
    }
    public void setDimensions(double x, double y, double width, double height) {
        setLayoutX(x); setLayoutY(y);
        lockSize(width, height);
    }
    public void setIndependentDimensions(double x, double y, double width, double height) {
        IndependentMoveX(x); IndependentMoveY(y);
        lockSize(width, height);
    }
    public void lockSize(double width, double height) {
        lockWidth(width);
        lockHeight(height);
    }
    public void lockWidth(double width) {
        setMinWidth(width);
        setMaxWidth(width);
        setPrefWidth(width);
        clip();
    }
    public void lockHeight(double height) {
        setMinHeight(height); setMaxHeight(height);
        clip();
    }
    public void setSize(double width, double height) {
        lockWidth(width); lockHeight(height);
    }
    public void IndependentMoveX(double x) {
        double deltaX = getLayoutX() - x;
        for (Node node: getChildren())
            if (!(node instanceof Pane))
                node.setLayoutX(node.getLayoutX() + deltaX);
        setLayoutX(x);
    }
    public void IndependentMoveY(double y) {
        double deltaY = getLayoutY() - y;
        for (Node node: getChildren())
            if (!(node instanceof Pane))
                node.setLayoutY(node.getLayoutY() + deltaY);
        setLayoutY(y);
    }
    private void clip() {
        Rectangle clip = new Rectangle(getMinWidth(), getMinHeight());
        setClip(clip);
    }
    public void add(Node node) {
        getChildren().add(node);
    }
    public boolean getHovering() {
        return hovering;
    }
}
