package bahar.window_kill.client.view.controller.online;

import bahar.window_kill.client.control.GameController;
import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.client.view.PaneBuilder;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.communications.messages.server.GeneralAnswer;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SignupMenuController {
    public TextField usernameTextField;
    public Label errorLabel;
    public PasswordField passwordField, repeatPasswordField;
    public Pane pane;
    public void initialize() {
        errorLabel.setOpacity(0);
        MainStage.requestCenterOnScreen(pane);
    }

    public void onSignUp(ActionEvent actionEvent) {
        GeneralAnswer registerMessage = new TCPClient().signupRequest(usernameTextField.getText(), passwordField.getText(), repeatPasswordField.getText());
        if (!registerMessage.isAccept()) {
            errorLabel.setOpacity(1);
            errorLabel.setText(registerMessage.getMessage());
        }
        else {
            GameController.user.setState("online");
            GameController.user.setUsername(usernameTextField.getText());
            GameController.user.setPassword(passwordField.getText());
            MainStage.newScene();
            MainStage.add(PaneBuilder.ONLINE_MENU_PANE.generatePane());
        }
    }

    public void onBack(ActionEvent actionEvent) {
        MainStage.newScene();
        MainStage.add(PaneBuilder.REGISTER_MENU_PANE.generatePane());
    }
}
