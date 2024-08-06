package bahar.window_kill.client.view.controller;

import bahar.window_kill.client.control.GameController;
import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.controller.online.*;
import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.data.UserMessage;
import bahar.window_kill.communications.messages.server.GeneralAnswer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;

public class OnlineMenuController {
    public AnchorPane pane;
    public Label xpLabel, messageLabel;
    public VBox attackBox, defenseBox, squadBox;
    public TextField newSquadName;
    public ListView<String> globalSquads;
    public Label squadName;
    public Label squadVault;
    public ListView<UserMessage> messagesBox;
    public ListView<TableUser> teamMembersBox;
    private ArrayList<OnlineController> controllers;
    public void initialize() {
        MainStage.requestCenterOnScreen(pane);
        makeControllers();
        for (OnlineController controller : controllers)
            controller.initialize();
        setUpTimeline();
        //todo you may clean here!
        messageLabel.setOpacity(0);
    }
    private void makeControllers() {
        controllers = new ArrayList<>();
        controllers.add(new UpdateDataController());
        controllers.add(new GlobeMenuController(globalSquads));
        controllers.add(new OnlineSkillsController(xpLabel, defenseBox, attackBox, squadBox));
        controllers.add(new SquadMenuController(squadName, squadVault, messagesBox, teamMembersBox));
    }
    private void setUpTimeline() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> runControllers()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    private void runControllers() {
        for (OnlineController controller : controllers)
            controller.run();
    }

    public void onNewSquad(ActionEvent actionEvent) {
        GeneralAnswer generalAnswer = new TCPClient().newSquad(User.getInstance().getUsername(), newSquadName.getText());
        if (!generalAnswer.isAccept()) {
            messageLabel.setOpacity(1);
            messageLabel.setStyle("-fx-text-fill: red");
            messageLabel.setText(generalAnswer.getMessage());
        }
        else {
            messageLabel.setOpacity(1);
            messageLabel.setStyle("-fx-text-fill: green");
            messageLabel.setText(generalAnswer.getMessage());
        }
    }

    public void onNewGame(ActionEvent actionEvent) {
        new TCPClient().sendGameRequest(true, User.getInstance().getUsername(), "");
        new GameController().newOnlineGame();
        MainStage.newScene();
    }
}
