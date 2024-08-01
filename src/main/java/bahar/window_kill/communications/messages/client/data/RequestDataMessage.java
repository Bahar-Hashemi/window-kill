package bahar.window_kill.communications.messages.client.data;

import bahar.window_kill.communications.messages.client.ClientMessage;
import bahar.window_kill.communications.messages.client.ClientMessageType;

public class RequestDataMessage extends ClientMessage {
    RequestedDataType requestedDataType;
    String username;
    public RequestDataMessage(RequestedDataType requestedDataType, String username) {
        super(ClientMessageType.REQUEST_DATA);
        this.requestedDataType = requestedDataType;
        this.username = username;
    }
    public RequestedDataType getRequestedDataType() {
        return requestedDataType;
    }

    public String getUsername() {
        return username;
    }
}
