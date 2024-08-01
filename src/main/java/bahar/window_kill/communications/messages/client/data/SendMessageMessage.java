package bahar.window_kill.communications.messages.client.data;

import bahar.window_kill.communications.data.UserMessage;
import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.ClientMessageType;

public class SendMessageMessage extends ClientMessage {
    private UserMessage userMessage;
    public SendMessageMessage(UserMessage userMessage) {
        super(ClientMessageType.SEND_MESSAGE);
        this.userMessage = userMessage;
    }

    public UserMessage getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(UserMessage userMessage) {
        this.userMessage = userMessage;
    }
}
