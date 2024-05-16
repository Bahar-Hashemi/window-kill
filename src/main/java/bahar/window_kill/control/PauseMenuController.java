package bahar.window_kill.control;

import bahar.window_kill.Main;
import bahar.window_kill.model.GameBoard;
import bahar.window_kill.model.MainBoard;
import bahar.window_kill.model.entities.Entity;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class PauseMenuController {
    @FXML
    public Label HPLabel, XPLabel;
    @FXML
    public Button healButton, banishButton, empowerButton;
    @FXML
    public void initialize() {
        updateLabels();
        processButtonsDisable();
    }
    private void updateLabels() {
        HPLabel.setText("HP: " + MainBoard.epsilon.getHP());
        XPLabel.setText("XP: " + MainBoard.xp);
    }
    @FXML
    public void onHeal(ActionEvent actionEvent) {
        MainBoard.epsilon.setHP(MainBoard.epsilon.getHP() + 10);
        MainBoard.xp -= 50;
        updateLabels();
        processButtonsDisable();
    }

    public void onEmpower(ActionEvent actionEvent) {
        MainBoard.xp -= 75;
        GameController.ShootTicks /= 5;
        Timeline timeline = new Timeline();
        timeline.setDelay(Duration.seconds(10));
        timeline.setOnFinished(e -> {GameController.ShootTicks *= 5; });
        timeline.play();
        updateLabels();
        processButtonsDisable();
    }
    private void processButtonsDisable() {
        if (MainBoard.xp < 75) {
            empowerButton.setDisable(true);
        }
        if (MainBoard.xp < 50) {
            healButton.setDisable(true);
        }
        if (MainBoard.xp < 100) {
            banishButton.setDisable(true);
        }
    }
    public void onBanish(ActionEvent actionEvent) {
        MainBoard.xp -= 100;
        MainBoard mainBoard = GameController.mainBoard;
        Entity[] entities = new Entity[mainBoard.enemies.size()];
        for (int i = 0; i < mainBoard.enemies.size(); i++) entities[i] = mainBoard.enemies.get(i);
        mainBoard.impact(MainBoard.epsilon.getLayoutX(), MainBoard.epsilon.getLayoutY(), entities, 3);
        updateLabels();
        processButtonsDisable();
    }
}
