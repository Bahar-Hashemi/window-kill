package bahar.window_kill.client.view.controller.online;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;
import bahar.window_kill.client.control.util.FileUtil;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import bahar.window_kill.communications.data.Development;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class OnlineSkillsController extends OnlineController {
    private Label xpLabel;
    private VBox defenseBox, attackBox;
    private Development development;
    public OnlineSkillsController(Label xpLabel, VBox defenseBox, VBox attackBox) {
        this.xpLabel = xpLabel;
        this.defenseBox = defenseBox;
        this.attackBox = attackBox;
    }
    @Override
    public void initialize() {
        development = FileUtil.readDevelopment();
        makeData();
    }
    private void makeData() {
        xpLabel.setText("XP: " + development.getXp());
        defenseBox.getChildren().clear();
        attackBox.getChildren().clear();
        for (int i = 0; i < development.getDefenseStates().length; i++)
            defenseBox.getChildren().add(makeButton(development.getDefenseStates(), development.getDefenseWatch(), i));
        for (int i = 0; i < development.getAttackStates().length; i++)
            attackBox.getChildren().add(makeButton(development.getAttackStates(), development.getAttackWatch(), i));
        sendUpdatesToServer();
    }
    private Button makeButton(Development.State[] states, AbilityWatch[] abilityWatches, int index) {
        Button button = new Button("Writ of " + abilityWatches[index].getName());
        switch (states[index]) {
            case LOCKED:
                makeBorder("transparent", button);
                button.setText(button.getText() + "   " + abilityWatches[index].getPrice());
                button.setDisable(true);
                break;
            case UNLOCKED:
                makeBorder("transparent", button);
                button.setText(button.getText() + "   " + abilityWatches[index].getPrice());
                if (development.getXp() > abilityWatches[index].getPrice())
                    button.setOnAction(event -> {
                        development.bye(states, abilityWatches, index);
                        makeData();
                    });
                else
                    button.setDisable(true);
                break;
            case ACTIVE:
                makeBorder("yellow", button);
                button.setOnAction(event -> {
                    states[index] = Development.State.BOUGHT;
                    makeData();
                });
                break;
            case BOUGHT:
                makeBorder("white", button);
                button.setOnAction(event -> {
                    development.activate(states, abilityWatches, index);
                    makeData();
                });
                break;
        }
        button.setPrefWidth(225);
        return button;
    }
    private void makeBorder(String color, Button button) {
        button.setStyle("-fx-border-color: " + color);
    }
    public void run() {

    }
    private void sendUpdatesToServer() {

    }

}
