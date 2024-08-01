package bahar.window_kill.client.view.controller;

import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import bahar.window_kill.client.control.GameController;
import bahar.window_kill.client.control.util.FileUtil;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.communications.data.Development;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MainMenuController {
    public Pane pane;
    String xpString;
    String highScoreString;
    public Label xpLabel, highScoreLabel;
    Timeline timeline;
    @FXML
    public void initialize() {
        MainStage.requestCenterOnScreen(pane);
        /*write labels after initializing*/
        writeLabels();
    }
    private void writeLabels() {
        Development development = FileUtil.readDevelopment();
        xpString = "xp: " + development.getXp();
        highScoreString = "High-Score: " + development.getHighScore();
        int[] tickPass = {0};
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.25), event -> {
            tickPass[0]++;
            if (tickPass[0] > 1) {
                xpLabel.setText(xpString.substring(0, Math.min(xpString.length(), xpLabel.getText().length() + 1)));
                highScoreLabel.setText(highScoreString.substring(0, Math.min(highScoreString.length(), highScoreLabel.getText().length() + 1)));
                SoundUtil.HIT.play();
            }
        }));
        timeline.setCycleCount(1 + Math.max(xpString.length(), highScoreString.length()));

        timeline.play();
    }
    public void stopTimeline() {
        xpLabel.setText(xpString);
        highScoreLabel.setText(highScoreString);
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
        new GameController().newGame();
    }

    public void onSkillTree(ActionEvent actionEvent) {
        stopTimeline();
        MainStage.newScene();
        Pane pane = PaneBuilder.SKILL_TREE_PANE.generatePane();
        MainStage.add(pane);
    }

    public void onSettings(ActionEvent actionEvent) {
        stopTimeline();
        MainStage.newScene();
        Pane pane = PaneBuilder.SETTINGS_PANE.generatePane();
        MainStage.add(pane);
    }

    public void onOnline(ActionEvent actionEvent) {
        stopTimeline();
        MainStage.newScene();
        MainStage.add(PaneBuilder.REGISTER_MENU_PANE.generatePane());
    }
}
