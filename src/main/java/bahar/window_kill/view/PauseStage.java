package bahar.window_kill.view;

import bahar.window_kill.control.Constants;
import bahar.window_kill.control.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PauseStage extends Stage {

    public static Scene scene;
    public PauseStage() {
        this.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
        scene = SceneBuilder.PAUSE_SCENE.generateScene();
        this.setOpacity(0.80);
        this.setX(50);
        this.setY(50);
        this.setScene(scene);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.E) {
                this.close();
                GameController.reStart();
            }
        });
    }
}
