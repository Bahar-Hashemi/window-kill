package bahar.window_kill.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainStage extends Application {
    public static Stage stage;
    public static Scene scene;

    public void initialize(String[] args) {
        launch(args);
    }
    public void start(Stage stage) throws Exception {
        MainStage.stage = stage;
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
        scene = SceneBuilder.MAIN_MENU_SCENE.generateScene();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    public static Scene getScene() {
        return scene;
    }
    public static void setScene(Scene scene) {
        MainStage.scene = scene;
        stage.setScene(scene);
    }
}