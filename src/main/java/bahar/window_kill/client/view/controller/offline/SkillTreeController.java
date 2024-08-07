package bahar.window_kill.client.view.controller.offline;

import bahar.window_kill.communications.processors.util.abilities.AbilityType;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import bahar.window_kill.communications.processors.util.abilities.AbilityWatch;
import bahar.window_kill.client.control.util.FileUtil;
import bahar.window_kill.communications.data.Development;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class SkillTreeController {
    public VBox root;
    public VBox defenseBox, attackBox;
    private Development development;
    public Label xpLabel;

    public void initialize() {
        development = FileUtil.readDevelopment();
        makeData();
        MainStage.requestCenterOnScreen(root);
    }
    private void makeData() {
        xpLabel.setText("XP: " + development.getXp());
        defenseBox.getChildren().clear();
        attackBox.getChildren().clear();
        for (int i = 0; i < development.getDefenseStates().length; i++)
            defenseBox.getChildren().add(makeButton(development.getDefenseStates(), development.getDefenseWatch(), i));
        for (int i = 0; i < development.getAttackStates().length; i++)
            attackBox.getChildren().add(makeButton(development.getAttackStates(), development.getAttackWatch(), i));
    }
    private Button makeButton(Development.State[] states, AbilityType[] abilityTypes, int index) {
        AbilityWatch watch = AbilityWatch.getAbilityByType(null, null, abilityTypes[index]);
        Button button = new Button("Writ of " + abilityTypes[index]);
        switch (states[index]) {
            case LOCKED:
                makeBorder("transparent", button);
                button.setText(button.getText() + "   " + watch.getPrice());
                button.setDisable(true);
                break;
            case UNLOCKED:
                makeBorder("transparent", button);
                button.setText(button.getText() + "   " + watch.getPrice());
                if (development.getXp() > watch.getPrice())
                    button.setOnAction(event -> {
                        development.bye(states, abilityTypes, index);
                        development.setXp(development.getXp() - watch.getPrice());
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
                    development.activate(states, abilityTypes, index);
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

    public void onBack(ActionEvent actionEvent) {
        FileUtil.saveDevelopment(development);
        MainStage.newScene();
        MainStage.add(PaneBuilder.MAIN_MENU_PANE.generatePane());
    }
}
