package bahar.window_kill.server.control.connection.handlers.register;

import bahar.window_kill.communications.data.Development;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.register.SignupMessage;
import bahar.window_kill.communications.messages.server.GeneralAnswer;
import bahar.window_kill.server.control.connection.DataBaseManager;
import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.server.control.connection.handlers.MessageHandler;

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
            sendMessage(sendBuffer, new GeneralAnswer(false, "passwords do not match!"));
            return true;
        }
        if (DataBaseManager.getInstance().hasUser(signupMessage.getUsername())) {
            sendMessage(sendBuffer, new GeneralAnswer(false, "username already exists"));
            return true;
        }
        TableUser tableUser = new TableUser(signupMessage.getUsername(), signupMessage.getPassword(), "online", null, null, Development.getFirstState());
        DataBaseManager.getInstance().addUser(tableUser);
        sendMessage(sendBuffer, new GeneralAnswer(true, "signup successful!"));
        return true;
    }
}