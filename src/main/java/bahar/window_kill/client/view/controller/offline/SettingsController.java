package bahar.window_kill.client.view.controller.offline;

import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import bahar.window_kill.client.control.util.FileUtil;
import bahar.window_kill.client.control.util.SoundUtil;
import bahar.window_kill.communications.model.boards.controller.KIJLStrategy;
import bahar.window_kill.communications.model.boards.controller.WASDStrategy;
import bahar.window_kill.communications.data.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class SettingsController {
    public Pane root;
    public Label speedLabel, difficultyLabel, volumeLabel, settingsLabel;
    public Slider speedSlider, difficultySlider, volumeSlider;
    public Button WASDButton, KIJLButton;
    private Settings settings;
    @FXML
    public void initialize() {
        MainStage.requestCenterOnScreen(root);
        makeLabels();
        makeSliders();
        makeButtons();
        makeSliderControls();
    }
    private void makeLabels() {
        settings = new Settings(10, 50, 100, new WASDStrategy());
        speedLabel.setText("Speed: " + settings.getSpeed());
        difficultyLabel.setText("Difficulty: " + settings.getDifficulty());
        volumeLabel.setText("Volume: " + settings.getVolume());
    }
    private void makeSliders() {
        speedSlider.setValue(settings.getSpeed());
        difficultySlider.setValue(settings.getDifficulty());
        volumeSlider.setValue(settings.getVolume());
    }
    private void makeButtons() {
        if (settings.getControlStrategy() instanceof WASDStrategy)
            makeBorder("white", WASDButton);
        else
            makeBorder("white", KIJLButton);
    }
    private void makeSliderControls() {
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            settings.setSpeed(newValue.intValue());
            speedLabel.setText("Speed: " + newValue.intValue());
        });
        difficultySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            settings.setDifficulty(newValue.intValue());
            difficultyLabel.setText("Difficulty: " + newValue.intValue());
        });
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            settings.setVolume(newValue.intValue());
            SoundUtil.setVolume(newValue.intValue());
            volumeLabel.setText("Volume: " + newValue.intValue());
        });
    }
    public void onWASDControls(ActionEvent actionEvent) {
        makeBorder("transparent", KIJLButton);
        makeBorder("white", WASDButton);
        settings.setControlStrategy(new WASDStrategy());
    }

    public void onKIJLControls(ActionEvent actionEvent) {
        makeBorder("transparent", WASDButton);
        makeBorder("white", KIJLButton);
        settings.setControlStrategy(new KIJLStrategy());
    }
    private void makeBorder(String color, Button button) {
        button.setStyle("-fx-border-color: " + color);
    }
    public void onBack(ActionEvent actionEvent) {
        FileUtil.saveSettings(settings);
        MainStage.newScene();
        MainStage.add(PaneBuilder.MAIN_MENU_PANE.generatePane());
    }
}
