package bahar.window_kill.client.view.controller;

import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import bahar.window_kill.client.view.controller.online.OnlineController;
import bahar.window_kill.client.view.controller.online.OnlineSkillsController;
import bahar.window_kill.client.view.controller.online.UpdateDataController;
import bahar.window_kill.communications.data.Development;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;

public class OnlineMenuController {
    public AnchorPane pane;
    public Label xpLabel;
    public VBox attackBox, defenseBox;
    private ArrayList<OnlineController> controllers;
    public void initialize() {
        MainStage.requestCenterOnScreen(pane);
        makeControllers();
        for (OnlineController controller : controllers)
            controller.initialize();
        setUpTimeline();
    }
    private void makeControllers() {
        controllers = new ArrayList<>();
        controllers.add(new UpdateDataController());
        controllers.add(new OnlineSkillsController(xpLabel, defenseBox, attackBox));
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

}
