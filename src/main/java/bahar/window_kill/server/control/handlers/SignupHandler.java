package bahar.window_kill.server.control.handlers;

import bahar.window_kill.communications.data.Development;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.SignupMessage;
import bahar.window_kill.communications.messages.server.RegisterAnswer;
import bahar.window_kill.server.control.DataBaseManager;
import bahar.window_kill.communications.data.TableUser;

import java.io.DataOutputStream;

public class SignupHandler extends MessageHandler {
    public SignupHandler() {
        super();
    }

    @Override
    public boolean handle(DataOutputStream sendBuffer, ClientMessage clientMessage) {
        if (!(clientMessage instanceof SignupMessage signupMessage))
            return false;
        if (!signupMessage.getPassword().equals(signupMessage.getRepeatedPassword())) {
            sendMessage(sendBuffer, new RegisterAnswer(false, "passwords do not match!"));
            return true;
        }
        if (DataBaseManager.getInstance().hasUser(signupMessage.getUsername())) {
            sendMessage(sendBuffer, new RegisterAnswer(false, "username already exists"));
            return true;
        }
        TableUser tableUser = new TableUser(signupMessage.getUsername(), signupMessage.getPassword(), "online", null, null, Development.getFirstState());
        DataBaseManager.getInstance().addUser(tableUser);
        sendMessage(sendBuffer, new RegisterAnswer(true, "signup successful!"));
        return true;
    }
}