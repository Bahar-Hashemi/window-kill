package bahar.window_kill.client.model.boards;

import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.boards.controller.ControlStrategy;
import bahar.window_kill.client.control.util.SoundUtil;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class MainBoard extends GameBoard {
    private final Label HPLabel, XPLabel, waveLabel, abilitiesLabel;
    private final VBox labels;
    private ControlStrategy controlStrategy;
    public Label countDown;
    public double shrink = 0.3;
    public MainBoard(int HPValue, int XPValue, int waveNumber) {
        super(true);
        labels = new VBox(); labels.setSpacing(2);
        //HP
        HPLabel = new Label("HP: " + HPValue);
        HPLabel.setStyle("-fx-text-fill: #00FF00");
        // XP
        XPLabel = new Label("XP: " + XPValue);
        XPLabel.setStyle("-fx-text-fill: #8000FF");
        XPLabel.setOpacity(0.5);
        // wave
        waveLabel = new Label("Wave: " + waveNumber);
        waveLabel.setStyle("-fx-text-fill: #FFFF00");
        waveLabel.setOpacity(0.5);
        // abilities
        abilitiesLabel = new Label();
        abilitiesLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-family: \"cooper black\";-fx-font-size: 8px;");
        abilitiesLabel.setOpacity(0.3);
        abilitiesLabel.setWrapText(true);
        //add all labels
        labels.getChildren().addAll(HPLabel, XPLabel, waveLabel, abilitiesLabel);
        labels.setLayoutX(3);
        labels.setLayoutY(3);
        add(labels);
    }
    public void labelsToFront() {
        labels.toFront();
    }
    public void setControlStrategy(ControlStrategy controlStrategy) {
        this.controlStrategy = controlStrategy;
    }
    public void requestUserControl(User user) {
        controlStrategy.requestUserControl(this, user);
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
    public void setAbilities(String abilities) {
        abilitiesLabel.setText(abilities);
    }
    public void shout() {
        SoundUtil.WALLHIT.play();
    }
    public void clear() {
        for (int i = getChildren().size() - 1; i >= 0; i--) {
            Node node = getChildren().get(i);
            if (!(node instanceof Pane))
                getChildren().remove(node);
        }
    }
    public void setCountDown(Label countDown) {
        this.countDown = countDown;
        getChildren().add(countDown);
    }
    public void removeCountDown() {
        getChildren().remove(countDown);
    }

}
