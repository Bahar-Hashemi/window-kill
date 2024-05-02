package bahar.window_kill.control;

import bahar.window_kill.model.MainBoard;
import bahar.window_kill.model.entities.Epsilon;
import bahar.window_kill.view.MainStage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameController {
    static MainBoard mainBoard;
    static Scene scene;
    static Pane root;
    static int gameTicks = 0;

    static public void run() {
        mainBoard = new MainBoard(); mainBoard.show();
        scene = mainBoard.scene;
        root = (Pane) scene.getRoot();
        mainBoard.setDimensions(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 4, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        mainBoard.setEpsilon(new Epsilon(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 4));
        whoosh();
        launchGame();
    }
    private static void launchGame() {
        Timeline timeline = new Timeline(new KeyFrame(new Duration(1), actionEvent -> {
            mainBoard.moveEntities();
            gameTicks++;
            if (mainBoard.isShooting && gameTicks % 250 == 0) mainBoard.makeBullet();
        }));
        timeline.setCycleCount(-1);
        timeline.play();
    }
    public static void whoosh() {
        int[] count = new int[] {0};
        Timeline timeline = new Timeline(new KeyFrame(new Duration(30), actionEvent -> {
            count[0]++;
            mainBoard.setSize(mainBoard.getWidth() * 0.95, mainBoard.getHeight() * 0.95);
            mainBoard.setXAndMoveEntities((Constants.SCREEN_WIDTH - mainBoard.getWidth()) / 2);
            mainBoard.setYAndMoveEntities((Constants.SCREEN_HEIGHT - mainBoard.getHeight()) / 2);
        }));
        timeline.setCycleCount(20);
        timeline.play();
        SoundController.WHOOSH.play();
    }
    
}
