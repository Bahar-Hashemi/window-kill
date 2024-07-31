package bahar.window_kill.client.view;

import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.client.model.User;
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
        MainStage.add(PaneBuilder.MAIN_MENU_PANE.generatePane());
        MainStage.getInstance().show();
    }
    public void stop() {
        try {
            super.stop();
            if (User.getInstance().getState().equals("online") || User.getInstance().getState().equals("busy"))
                new TCPClient().logout(User.getInstance().getUsername(), User.getInstance().getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}