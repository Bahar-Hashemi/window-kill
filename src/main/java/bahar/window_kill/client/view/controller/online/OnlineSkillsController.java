package bahar.window_kill.client.view.controller.online;

import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.client.control.states.offlline.processors.abilities.AbilityType;
import bahar.window_kill.client.control.states.offlline.processors.abilities.AbilityWatch;
import bahar.window_kill.client.model.User;
import bahar.window_kill.communications.data.Development;
import bahar.window_kill.communications.data.TableSquad;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class OnlineSkillsController extends OnlineController {
    private final Label xpLabel;
    private final VBox defenseBox, attackBox, squadBox;
    private Development development;
    public OnlineSkillsController(Label xpLabel, VBox defenseBox, VBox attackBox, VBox squadBox) {
        this.xpLabel = xpLabel;
        this.defenseBox = defenseBox;
        this.attackBox = attackBox;
        this.squadBox = squadBox;
    }
    @Override
    public void initialize() {
        development = User.getInstance().development;
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
        makeSquadBox();
    }
    private void makeSquadBox() { //todo correct prices
        if (User.getInstance().tableSquad == null)
            return;
        squadBox.getChildren().clear();
        squadBox.getChildren().add(makeSquadButton("Call OF PALIOXIS", User.getInstance().tableSquad.getPalioxisState(), 100,  User.getInstance().tableSquad, event -> {
            if (User.getInstance().tableSquad.getPalioxisState() == -1)
                User.getInstance().tableSquad.setVault(User.getInstance().tableSquad.getVault() - 100);
            User.getInstance().tableSquad.setPalioxisState(Math.max(User.getInstance().tableSquad.getPalioxisState() + 1, 3));
            new TCPClient().sendSquad(User.getInstance().getUsername(), User.getInstance().tableSquad);
        }));
        squadBox.getChildren().add(makeSquadButton("Call OF ADONIS", User.getInstance().tableSquad.getAdonisState(), 100,  User.getInstance().tableSquad, event -> {
            if (User.getInstance().tableSquad.getAdonisState() == -1)
                User.getInstance().tableSquad.setVault(User.getInstance().tableSquad.getVault() - 100);
            User.getInstance().tableSquad.setAdonisState(Math.max(User.getInstance().tableSquad.getPalioxisState() + 1, 3));
            new TCPClient().sendSquad(User.getInstance().getUsername(), User.getInstance().tableSquad);
        }));
        squadBox.getChildren().add(makeSquadButton("Call OF GEFJON", User.getInstance().tableSquad.getGefjonState(), 100,  User.getInstance().tableSquad, event -> {
            if (User.getInstance().tableSquad.getPalioxisState() == -1)
                User.getInstance().tableSquad.setVault(User.getInstance().tableSquad.getVault() - 100);
            User.getInstance().tableSquad.setGefjonState(Math.max(User.getInstance().tableSquad.getPalioxisState() + 1, 3));
            new TCPClient().sendSquad(User.getInstance().getUsername(), User.getInstance().tableSquad);
        }));
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
        button.setStyle("-fx-border-color: " + color + "; -fx-border-radius: 15; -fx-background-radius: 15; -fx-border-width: 3; -fx-pref-width: 225px; -fx-pref-height: 30px;");
    }
    public void run() {
        makeSquadBox();
    }
    private void sendUpdatesToServer() {
        new TCPClient().sendDevelopment(User.getInstance().getUsername(), development);
    }
    private Button makeSquadButton(String name, int state, int price, TableSquad tableSquad, EventHandler<ActionEvent> eventEventHandler) {
        Button button = new Button(name);
        if (state == -1) makeBorder("transparent", button);
        if (state == 0) makeBorder("white", button);
        if (state == 1) makeBorder("yellow", button);
        if (!tableSquad.getOwner().equals(name))
            button.setDisable(true);
        else if (tableSquad.getVault() < price && state == -1)
            button.setDisable(true);
        else
            button.setOnAction(eventEventHandler);
        return button;
    }
}
