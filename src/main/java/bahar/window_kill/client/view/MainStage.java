package bahar.window_kill.client.view;

import bahar.window_kill.client.control.Constants;
import bahar.window_kill.client.control.util.ImageUtil;
import bahar.window_kill.client.model.User;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        this.getIcons().add(ImageUtil.GAME_ICON.getImage());
        this.setMaxHeight(Constants.SCREEN_HEIGHT); this.setMinHeight(Constants.SCREEN_HEIGHT);
        this.setMaxWidth(Constants.SCREEN_WIDTH); this.setMinWidth(Constants.SCREEN_WIDTH);
        this.initStyle(StageStyle.TRANSPARENT);
        this.setResizable(false);
        //
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
        node.layoutXProperty().bind(((Pane) getInstance().getScene().getRoot()).widthProperty().subtract(node.widthProperty()).divide(2));
        node.layoutYProperty().bind(((Pane) getInstance().getScene().getRoot()).heightProperty().subtract(node.heightProperty()).divide(2));
    } //todo if you can clean here
}
