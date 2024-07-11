package bahar.window_kill.view;

import bahar.window_kill.control.Constants;
import bahar.window_kill.control.loader.ImageLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainStage extends Stage {
    private static MainStage instance;
    private static Scene scene;
    MainStage() {
        // make scene
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
        pane.setMaxHeight(Constants.SCREEN_HEIGHT); pane.setMinHeight(Constants.SCREEN_HEIGHT);
        pane.setMaxWidth(Constants.SCREEN_WIDTH); pane.setMinWidth(Constants.SCREEN_WIDTH);
        scene = new Scene(pane, Color.TRANSPARENT);
        setScene(scene);
        //
        this.getIcons().add(ImageLoader.GAME_ICON.getImage());
        this.setMaxHeight(Constants.SCREEN_HEIGHT); this.setMinHeight(Constants.SCREEN_HEIGHT);
        this.setMaxWidth(Constants.SCREEN_WIDTH); this.setMinWidth(Constants.SCREEN_WIDTH);
        this.initStyle(StageStyle.TRANSPARENT);
        this.setResizable(false);
    }
    public static MainStage getInstance() {
        if (instance == null)
            instance = new MainStage();
        return instance;
    }
    public static void newScene() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
        pane.setMaxHeight(Constants.SCREEN_HEIGHT); pane.setMinHeight(Constants.SCREEN_HEIGHT);
        pane.setMaxWidth(Constants.SCREEN_WIDTH); pane.setMinWidth(Constants.SCREEN_WIDTH);
        getInstance().setScene(new Scene(pane, Color.TRANSPARENT));
    }
    public static void add(Node node) {
        ((Pane) getInstance().getScene().getRoot()).getChildren().add(node);
    }
    public static void remove(Node node) {
        ((Pane) getInstance().getScene().getRoot()).getChildren().remove(node);
    }
    public static void requestCenterOnScreen(Pane node) {
        Timeline timeline = new Timeline(new KeyFrame(new Duration(10), actionEvent -> {
            node.setLayoutX((getInstance().getWidth() - (node).getWidth()) / 2);
            node.setLayoutY((getInstance().getHeight() - (node).getHeight()) / 2);
        }));
        timeline.play();
    } //todo if you can clean here
}
