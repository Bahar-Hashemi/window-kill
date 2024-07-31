package bahar.window_kill.client.view.controller;

import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import bahar.window_kill.client.control.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameOverController {
    public Label XPLabel;
    public VBox root;
    public Label messageLabel;
    @FXML
    public void initialize() {
        MainStage.requestCenterOnScreen(root);
    }

    public void onNewGame(ActionEvent actionEvent) {
        MainStage.newScene();
        new GameController().newGame();
    }

    public void onMainMenu(ActionEvent actionEvent) {
        MainStage.newScene();
        Pane pane = PaneBuilder.MAIN_MENU_PANE.generatePane();
        MainStage.add(pane);
    }
}
