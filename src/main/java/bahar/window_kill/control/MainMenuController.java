package bahar.window_kill.control;

import bahar.window_kill.view.GameLauncher;
import bahar.window_kill.view.MainStage;
import bahar.window_kill.view.PaneBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MainMenuController {
    public Pane pane;
    String byteCoinString = "XP: 2000";
    String highScoreString = "High-Score: 1384";
    public Label byteCoin, highScore;
    Timeline timeline;
    @FXML
    public void initialize() {
        MainStage.requestCenterOnScreen(pane);
        /*write labels after initializing*/
        writeLabels();
    }
    private void writeLabels() {
        int[] tickPass = {0};
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.25), event -> {
            tickPass[0]++;
            if (tickPass[0] > 1) {
                byteCoin.setText(byteCoinString.substring(0, Math.min(byteCoinString.length(), byteCoin.getText().length() + 1)));
                highScore.setText(highScoreString.substring(0, Math.min(highScoreString.length(), highScore.getText().length() + 1)));
                SoundController.SHOOT.play();
            }
        }));
        timeline.setCycleCount(1 + Math.max(byteCoinString.length(), highScoreString.length()));

        timeline.play();
    }
    public void stopTimeline() {
        byteCoin.setText(byteCoinString);
        highScore.setText(highScoreString);
        timeline.stop();
    }
    @FXML
    public void onExit(ActionEvent e) {
        MainStage.getInstance().close();
    }
    @FXML
    public void onNewGame(ActionEvent e) {
        stopTimeline();
        MainStage.newScene();
        GameController.run();
    }
}
