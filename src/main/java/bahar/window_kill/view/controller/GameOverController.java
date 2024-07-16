package bahar.window_kill.view.controller;

import bahar.window_kill.control.Deck;
import bahar.window_kill.control.GameController;
import bahar.window_kill.control.util.FileUtil;
import bahar.window_kill.view.MainStage;
import bahar.window_kill.view.PaneBuilder;
import bahar.window_kill.control.util.SoundUtil;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class GameOverController {
    public Label XPLabel;
    public VBox root;
    public Label messageLabel;
    Timeline timeline;
    String XPString; //todo correct here
    @FXML
    public void initialize() {
        MainStage.requestCenterOnScreen(root);
        /*write labels after initializing*/
        Deck.development.setXp(Deck.development.getXp() + Deck.user.getEpsilon().getXp());
        if (Deck.user.getEpsilon().getHP() > 0) {
            Deck.development.setHighScore(Math.max(Deck.development.getHighScore(), Deck.user.getEpsilon().getXp()));
            messageLabel.setText("GAME\nFINISHED :)");
        }
        else
            messageLabel.setText("GAME\nOVER :(");
        FileUtil.saveDevelopment(Deck.development);
        writeLabels();
    }
    private void writeLabels() {
        setXP(Deck.user.getEpsilon().getXp());
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.25), event -> {
            XPLabel.setText(XPString.substring(0, Math.min(XPString.length(), XPLabel.getText().length() + 1)));
            SoundUtil.HIT.play();
        }));
        timeline.setCycleCount(XPString.length());
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
    public void setXP(int xp) {
        XPString = "XP EARNED: " + xp;
    }
}
