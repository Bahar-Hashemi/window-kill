package bahar.window_kill.control;

import bahar.window_kill.model.MainBoard;
import bahar.window_kill.model.entities.Epsilon;
import bahar.window_kill.model.entities.Squarantine;
import bahar.window_kill.model.entities.Trigorath;
import bahar.window_kill.view.GameLauncher;
import bahar.window_kill.view.MainMenuStage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Random;

public class GameController {
    public static MainBoard mainBoard;
    static Scene scene;
    static Pane root;
    static int gameTicks = 0;
    static Timeline gameTimeLine, whooshTimeLine;
    public static double shootCount = 1;

    static public void run() {
        mainBoard = new MainBoard(); mainBoard.show();
        scene = mainBoard.scene;
        root = (Pane) scene.getRoot();
        mainBoard.setDimensions(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 4, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        mainBoard.setEpsilon(new Epsilon(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 4));
        whoosh();
    }
    public static void launchGame() {
        generateEnemies(0);
        gameTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            generateEnemies(99.7);
            mainBoard.moveEntities();
            gameTicks++;
            for (int i = 0; i < shootCount; i++)
                if (mainBoard.isShooting && gameTicks % ((int) (Constants.RESPOND_DURATION / 3) + i) == 0) {
                    mainBoard.makeBullet();
                }
            mainBoard.moveWalls();
            mainBoard.updateXPLabel();
            mainBoard.updateHPLabel();
        }));
        gameTimeLine.setCycleCount(-1);
        gameTimeLine.play();
    }
    private static void generateEnemies(double prob) {
        Random random = new Random();
        if (random.nextDouble(0, 1) > prob / 100) {
            mainBoard.addEnemy(Trigorath.randomGenerate(mainBoard));
            mainBoard.addEnemy(Squarantine.randomGenerate(mainBoard));
            mainBoard.addEnemy(Squarantine.randomGenerate(mainBoard));
        }
    }
    public static void pauseGame() {
        if (gameTimeLine == null) {
            whooshTimeLine.pause();
        }
        if (gameTimeLine != null)
            gameTimeLine.pause();
    }
    public static void reStart() {
        int[] tick = new int[]{0};
        Color[] colors = new Color[] {Color.GREENYELLOW, Color.ORANGE, Color.RED, Color.RED};
        Label label = new Label("" + (3 - tick[0]));
        label.setTextFill(colors[0]);
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("cooper black", 68));
        label.setPrefHeight(mainBoard.getHeight());
        label.setPrefWidth(mainBoard.getWidth());
        label.setLayoutX(0);
        label.setLayoutY(0);
        mainBoard.root.getChildren().add(label);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            tick[0]++;
            label.setTextFill(colors[tick[0]]);
            label.setText("" + (3 - tick[0]));
            SoundController.SHOOT.play();
        }));
        timeline.setDelay(Duration.seconds(1));
        timeline.setCycleCount(3);
        timeline.setOnFinished(e -> {
            mainBoard.root.getChildren().remove(label);
            mainBoard.onPause = false;
            if (gameTimeLine == null) {
                whooshTimeLine.play();
            }
            if (gameTimeLine != null)
                gameTimeLine.play();
        });
        timeline.play();
    }
    public static void whoosh() {
        int[] count = new int[] {0};
        whooshTimeLine = new Timeline(new KeyFrame(new Duration(30), actionEvent -> {
            count[0]++;
            mainBoard.setSize(mainBoard.getWidth() * 0.95, mainBoard.getHeight() * 0.95);
            mainBoard.setXAndMoveEntities((Constants.SCREEN_WIDTH - mainBoard.getWidth()) / 2);
            mainBoard.setYAndMoveEntities((Constants.SCREEN_HEIGHT - mainBoard.getHeight()) / 2);
        }));
        whooshTimeLine.setCycleCount(15);
        whooshTimeLine.play();
        whooshTimeLine.setOnFinished(actionEvent -> launchGame());
        SoundController.WHOOSH.play();
    }
    public static void endGame() {
        pauseGame();
        mainBoard.close();
    }
    
}
