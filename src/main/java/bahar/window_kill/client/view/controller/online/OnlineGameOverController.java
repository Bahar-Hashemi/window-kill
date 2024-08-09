package bahar.window_kill.client.view.controller.online;

import bahar.window_kill.client.control.GameController;
import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class OnlineGameOverController {
    public Label donateLabel;
    public VBox root;
    public Label messageLabel;
    public Slider donationSlider;
    public Button donateButton;

    public void initialize() {
        donationSlider.setMax(Math.min(GameController.user.getXp(), 500));
        donationSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            donateLabel.setText("DONATION: " + newValue.intValue());
        });
        MainStage.requestCenterOnScreen(root);
        if (GameController.user.tableUser.getSquad() == null)
            donateButton.setDisable(true);
    }

    public void onDonate(ActionEvent actionEvent) {
        new TCPClient().donate(GameController.user.getUsername(), (int) donationSlider.getValue());
        donateButton.setDisable(true);
    }

    public void onOnlineMenu(ActionEvent actionEvent) {
        MainStage.newScene();
        Pane pane = PaneBuilder.ONLINE_MENU_PANE.generatePane();
        MainStage.add(pane);
    }
}
