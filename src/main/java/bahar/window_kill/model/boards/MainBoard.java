package bahar.window_kill.model.boards;

import bahar.window_kill.control.loader.SoundLoader;
import bahar.window_kill.model.User;
import bahar.window_kill.model.boards.controller.ControlStrategy;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class MainBoard extends GameBoard {
    private final Label HPLabel, XPLabel, waveLabel;
    private ControlStrategy controlStrategy;
    public MainBoard(int HPValue, int XPValue, int waveNumber) {
        super(true);
        VBox labels = new VBox(); labels.setSpacing(2);
        HPLabel = new Label("HP: " + HPValue); HPLabel.setStyle("-fx-text-fill: #00FF00"); HPLabel.setOpacity(0.5);
        XPLabel = new Label("XP: " + XPValue); XPLabel.setStyle("-fx-text-fill: #8000FF"); XPLabel.setOpacity(0.5);
        waveLabel = new Label("Wave: " + waveNumber); waveLabel.setStyle("-fx-text-fill: #FFFF00"); waveLabel.setOpacity(0.5);
        labels.getChildren().addAll(HPLabel, XPLabel, waveLabel);
        add(labels);
    }
    public void setControlStrategy(ControlStrategy controlStrategy) {
        this.controlStrategy = controlStrategy;
    }
    public void requestUserControl(User user) {
        controlStrategy.requestUserControl(user);
    }
    public void setHP(int HP) {
        HPLabel.setText("HP: " + HP);
    }
    public void setXP(int XP) {
        XPLabel.setText("XP: " + XP);
    }
    public void setWave(int wave) {
        waveLabel.setText("wave: " + wave);
    }
    public void shout() {
        SoundLoader.WALLHIT.play();
    }
}
