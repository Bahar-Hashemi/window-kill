package bahar.window_kill.client.view.controller;

import bahar.window_kill.client.control.connection.TCPClient;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.view.MainStage;
import bahar.window_kill.client.view.PaneBuilder;
import bahar.window_kill.communications.messages.server.GeneralAnswer;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class RegisterMenuController {
    public TextField usernameTextField;
    public Label errorLabel;
    public Pane pane;
    public PasswordField passwordField;

    public void initialize() {
        MainStage.requestCenterOnScreen(pane);
        errorLabel.setOpacity(0);
    }

    public void onLogin(ActionEvent actionEvent) {
        GeneralAnswer registerMessage = new TCPClient().loginRequest(usernameTextField.getText(), passwordField.getText());
        if (!registerMessage.isAccept()) {
            errorLabel.setOpacity(1);
            errorLabel.setText(registerMessage.getMessage());
        }
        else {
            User.getInstance().setState("online");
            User.getInstance().setUsername(usernameTextField.getText());
            User.getInstance().setPassword(passwordField.getText());
            MainStage.newScene();
            MainStage.add(PaneBuilder.ONLINE_MENU_PANE.generatePane());
        }
    }
    public void onBack(ActionEvent actionEvent) {
        MainStage.newScene();
        MainStage.add(PaneBuilder.MAIN_MENU_PANE.generatePane());
    }

    public void onSignUp(MouseEvent mouseEvent) {
        MainStage.newScene();
        MainStage.add(PaneBuilder.SIGNUP_MENU_PANE.generatePane());
    }
}
