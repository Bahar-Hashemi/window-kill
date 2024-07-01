package bahar.window_kill.model;

import bahar.window_kill.control.Constants;
import bahar.window_kill.view.PaneBuilder;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameBoard extends Pane {
    public void setDimensions(double x, double y, double width, double height) {
        setLayoutX(x); setLayoutY(y);
        setSize(width, height);
    }
    public void setSize(double width, double height) {
        setMinWidth(width); setMinHeight(height);
        setMaxWidth(width); setMaxHeight(height);
        clip();
    }
    private void clip() {
        Rectangle clip = new Rectangle(getMinWidth(), getMinHeight());
        setClip(clip);
    }
    public void add(Node node) {
        getChildren().add(node);
    }
    public GameBoard() {
        setOpacity(1);
        setBackground(Background.fill(Constants.BACKGROUND_COLOR));
        setStyle("-fx-font-family: \"cooper black\"; -fx-font-weight: bold; -fx-font-size: 12px;");
        clip();
    }
}
