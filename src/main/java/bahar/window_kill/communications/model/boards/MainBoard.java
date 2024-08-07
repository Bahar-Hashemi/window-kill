package bahar.window_kill.communications.model.boards;

import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.boards.controller.ControlStrategy;
import bahar.window_kill.client.control.util.SoundUtil;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class MainBoard extends GameBoard {
    transient private Label HPLabel, XPLabel, waveLabel, abilitiesLabel;
    transient private VBox labels;
    transient private ControlStrategy controlStrategy;
    transient public Label countDown;
    public double shrink = 0.3;
    public MainBoard(boolean isViewable, String id) {
        super(isViewable, id, true, MainBoard.class.getName());
        if (!isViewable)
            return;
        labels = new VBox(); labels.setSpacing(2);
        //HP
        HPLabel = new Label("HP: ");
        HPLabel.setStyle("-fx-text-fill: #00FF00");
        // XP
        XPLabel = new Label("XP: ");
        XPLabel.setStyle("-fx-text-fill: #8000FF");
        XPLabel.setOpacity(0.5);
        // wave
        waveLabel = new Label("Wave: ");
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
        if (labels != null)
            labels.toFront();
    }
    public void setControlStrategy(ControlStrategy controlStrategy) {
        this.controlStrategy = controlStrategy;
    }
    public void requestUserControl(User user) {
        controlStrategy.requestUserControl(this, user);
    }
    public void setHP(int HP) {
        if (isViewable)
            HPLabel.setText("HP: " + HP);
    }
    public void setXP(int XP) {
        if (isViewable)
            XPLabel.setText("XP: " + XP);
    }
    public void setWave(int wave) {
        if (isViewable)
            waveLabel.setText("wave: " + wave);
    }
    public void setAbilities(String abilities) {
        if (isViewable)
            abilitiesLabel.setText(abilities);
    }
    public void shout() {
        if (isViewable)
            SoundUtil.WALLHIT.play();
    }
    public void setCountDown(Label countDown) {
        this.countDown = countDown;
        if (isViewable)
            add(countDown);
    }
    public void removeCountDown() {
        remove(countDown);
    }

}
