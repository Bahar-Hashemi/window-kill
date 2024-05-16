package bahar.window_kill.view;

import bahar.window_kill.control.Constants;
import bahar.window_kill.control.GameController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameOverStage extends Stage {
    Scene scene;
    public GameOverStage() {
        this.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
        scene = SceneBuilder.GAME_OVER_SCENE.generateScene();
        this.setScene(scene);
        centerOnScreen();
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
    }
}
