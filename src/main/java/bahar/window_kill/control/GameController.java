package bahar.window_kill.control;

import bahar.window_kill.Main;
import bahar.window_kill.model.MainBoard;
import bahar.window_kill.model.User;
import bahar.window_kill.model.entities.Enemy;
import bahar.window_kill.model.entities.Epsilon;
import bahar.window_kill.model.entities.Squarantine;
import bahar.window_kill.model.entities.Trigorath;
import bahar.window_kill.view.MainStage;
import bahar.window_kill.view.PaneBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Random;

public class GameController {
    public static MainBoard mainBoard;
    static int gameTicks = 0;
    static Timeline gameTimeLine, whooshTimeLine;
    private static Pane pausePane;
    public static double shootCount = 1;

    static public void run() {
        gameTicks = 0;
        mainBoard = new MainBoard();
        mainBoard.setDimensions(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 4, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        mainBoard.setUser(new User(new Epsilon(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 4)));
        MainStage.add(mainBoard);
        whoosh();
    }
    public static void launchGame() {
        generateEnemies();
        mainBoard.requestUserControls(mainBoard.getUser());
        gameTimeLine = new Timeline(new KeyFrame(new Duration(Constants.RESPOND_DURATION), actionEvent -> {
            if (mainBoard.getUser().hasPauseRequest)
                pauseGame();
            gameTicks++;
            generateEnemies();
            mainBoard.moveEntities();
            for (int i = 0; i < shootCount; i++)
                if (mainBoard.getUser().isShooting && gameTicks % ((int) (Constants.RESPOND_DURATION / 3) + i) == 0) {
                    mainBoard.makeBullet();
                }
            mainBoard.moveWalls();
            mainBoard.updateXPLabel();
            mainBoard.updateHPLabel();
        }));
        gameTimeLine.setCycleCount(-1);
        gameTimeLine.play();
    }
    private static void generateEnemies() {
        if (gameTicks % 300 == 0) {
            int tmp = gameTicks;
            while (tmp > 0) {
                mainBoard.addEnemy(generateEnemy());
                tmp /= 10;
            }
        }
    }
    private static Enemy generateEnemy() {
        Random random = new Random();
        if (random.nextDouble(0, 1) > 0.55)
            return Squarantine.randomGenerate(mainBoard);
        return Trigorath.randomGenerate(mainBoard);
    }
    public static void pauseGame() {
        pausePane = PaneBuilder.PAUSE_PANE.generatePane();
        pausePane.requestFocus();
        MainStage.add(pausePane);
        if (gameTimeLine == null) {
            whooshTimeLine.pause();
        }
        if (gameTimeLine != null)
            gameTimeLine.pause();
    }
    public static void reStart() {
        MainStage.remove(pausePane);
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
        mainBoard.getChildren().add(label);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            tick[0]++;
            label.setTextFill(colors[tick[0]]);
            label.setText("" + (3 - tick[0]));
            SoundController.SHOOT.play();
        }));
        timeline.setDelay(Duration.seconds(1));
        timeline.setCycleCount(3);
        timeline.setOnFinished(e -> {
            mainBoard.getChildren().remove(label);
            mainBoard.getUser().hasPauseRequest = false;
            if (gameTimeLine == null) {
                whooshTimeLine.play();
            }
            if (gameTimeLine != null)
                gameTimeLine.play();
            mainBoard.requestFocus();
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
        gameTimeLine.stop();
        Pane pane = PaneBuilder.GAME_OVER_PANE.generatePane();
        MainStage.requestCenterOnScreen(pane);
        MainStage.add(pane);
    }
}
