package bahar.window_kill.model;

import bahar.window_kill.control.Constants;
import bahar.window_kill.view.SceneBuilder;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameBoard extends Stage {
    public Scene scene;
    public Pane root;
    public void setDimensions(double x, double y, double width, double height) {
        setX(x); setY(y);
        setWidth(width); setHeight(height);
    }
    public void setSize(double width, double height) {
        setWidth(width); setHeight(height);
    }
    public GameBoard() {
        this.initStyle(StageStyle.UNDECORATED);
        scene = SceneBuilder.GAME_SCENE.generateScene();
        root = (Pane) scene.getRoot();
        ((Pane) scene.getRoot()).setBackground(Background.fill(Constants.BACKGROUND_COLOR));
        this.setScene(scene);
        this.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
    }
    public void add(Node node) {
        root.getChildren().add(node);
    }
}
