package bahar.window_kill.control;

import bahar.window_kill.view.GameLauncher;
import bahar.window_kill.view.MainStage;
import bahar.window_kill.view.PaneBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameOverController {
    public Label XPLabel;
    Timeline timeline;
    String XPString = "XP EARNED: " + GameController.mainBoard.xp;
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
                XPLabel.setText(XPString.substring(0, Math.min(XPString.length(), XPLabel.getText().length() + 1)));
                SoundController.SHOOT.play();
            }
        }));
        timeline.setCycleCount(1 + XPString.length());

        timeline.play();
    }

    public void onNewGame(ActionEvent actionEvent) {
        timeline.stop();
        MainStage.newScene();
        GameController.run();
    }

    public void onMainMenu(ActionEvent actionEvent) {
        timeline.stop();
        MainStage.newScene();
        Pane pane = PaneBuilder.MAIN_MENU_PANE.generatePane();
        MainStage.add(pane);
    }
}
