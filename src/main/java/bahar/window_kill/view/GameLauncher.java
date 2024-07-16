package bahar.window_kill.view;

import bahar.window_kill.control.util.SoundUtil;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameLauncher extends Application {
    public void initialize() {
        launch();
    }
    public void start(Stage stage) {
        SoundUtil.BACKGROUND.play();
        MainStage.newScene();
        Pane pane = PaneBuilder.MAIN_MENU_PANE.generatePane();
        MainStage.add(pane);
        MainStage.getInstance().show();
    }
}