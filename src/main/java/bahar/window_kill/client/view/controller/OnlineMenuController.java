package bahar.window_kill.client.view.controller;

import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class OnlineMenuController {
    public AnchorPane pane;
    public Label xpLabel;
    public VBox attackBox, defenseBox;

    public void initialize() {
        MainStage.requestCenterOnScreen(pane);
    }
}
