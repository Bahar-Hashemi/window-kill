package bahar.window_kill.view;

import bahar.window_kill.control.Constants;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameLauncher extends Application {
    public void initialize() {
        launch();
    }
    public void start(Stage stage) {
        MainStage.newScene();
        Pane pane = PaneBuilder.MAIN_MENU_PANE.generatePane();
        MainStage.add(pane);
        MainStage.getInstance().show();
    }
}