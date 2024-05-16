package bahar.window_kill.control;

import bahar.window_kill.view.MainStage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class MainMenuController {
    String byteCoinString = "XP: 2000";
    String highScoreString = "High-Score: 1384";
    public Label byteCoin, highScore;
    Timeline timeline;
    @FXML
    public void initialize() {
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
        MainStage.stage.close();
    }
    @FXML
    public void onNewGame(ActionEvent e) {
        stopTimeline();
        MainStage.stage.close();
        GameController.run();
    }
}
