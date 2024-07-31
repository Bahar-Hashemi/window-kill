package bahar.window_kill.server.control.handlers;

import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.LoginMessage;
import bahar.window_kill.communications.messages.server.RegisterAnswer;
import bahar.window_kill.server.control.DataBaseManager;
import bahar.window_kill.communications.data.TableUser;

import java.io.DataOutputStream;

public class LoginHandler extends MessageHandler {

    public LoginHandler() {
        super();
    }

    @Override
    public boolean handle(DataOutputStream sendBuffer, ClientMessage clientMessage) {
        if (!(clientMessage instanceof LoginMessage loginMessage))
            return false;
        if (!DataBaseManager.getInstance().hasUser(loginMessage.getUsername())) {
            sendMessage(sendBuffer, new RegisterAnswer(false, "user not found!"));
            return true;
        }
        TableUser tableUser = DataBaseManager.getInstance().getUser(loginMessage.getUsername());
        if (!tableUser.getPassword().equals(loginMessage.getPassword())){
            sendMessage(sendBuffer, new RegisterAnswer(false, "incorrect password!"));
            return true;
        }
        if (tableUser.getState().equals("online")) {
            sendMessage(sendBuffer, new RegisterAnswer(false, "a user with this account has already logged in!"));
            return true;
        }
        tableUser.setState("online");
        DataBaseManager.getInstance().updateUser(tableUser);
        sendMessage(sendBuffer, new RegisterAnswer(true, "login successful!"));
        return true;
    }
}
