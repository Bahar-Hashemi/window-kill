package bahar.window_kill.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameLauncher extends Application {
    public static Stage stage;

    public void initialize() {
        launch();
    }
    public void start(Stage stage) throws Exception {
        GameLauncher.stage = new MainMenuStage();
        GameLauncher.stage.show();
    }
    public static void setStage(Stage stage) {
        GameLauncher.stage = stage;
    }
    public static void setScene(Scene scene) {
        stage.setScene(scene);
    }
}