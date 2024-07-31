package bahar.window_kill.client.control.connection;

import bahar.window_kill.communications.messages.client.ChangeStateMessage;
import bahar.window_kill.communications.messages.client.LoginMessage;
import bahar.window_kill.communications.messages.client.SignupMessage;
import bahar.window_kill.communications.messages.server.RegisterAnswer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient {
    private final String serverIP = "127.0.0.1";
    private final int serverPort = 8080;
    private Socket socket;
    private DataInputStream receiveBuffer;
    private DataOutputStream sendBuffer;

    private Gson gsonAgent;

    private String username, password;

    public TCPClient() {
        GsonBuilder builder = new GsonBuilder();
        this.gsonAgent = builder.create();
    }

    private boolean establishConnection() {
        try {
            socket = new Socket(serverIP, serverPort);
            sendBuffer = new DataOutputStream(
                    socket.getOutputStream()
            );
            receiveBuffer = new DataInputStream(
                    socket.getInputStream()
            );
            return true;
        } catch (Exception e) {
            System.err.println("Unable to initialize socket!");
            e.printStackTrace();
            return false;
        }
    }

    private boolean endConnection() {
        if(socket == null) return true;
        try {
            socket.close();
            receiveBuffer.close();
            sendBuffer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean sendMessage(String message) {
        try {
            sendBuffer.writeUTF(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String receiveResponse() {
        try {
            return receiveBuffer.readUTF();
        } catch (IOException e) {
            return null;
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public RegisterAnswer signupRequest(String username, String password, String repeatedPassword) {
        SignupMessage signupMessage = new SignupMessage(username, password, repeatedPassword);
        try {
            establishConnection();
            sendMessage(gsonAgent.toJson(signupMessage));
            RegisterAnswer result = gsonAgent.fromJson(
                    receiveResponse(), RegisterAnswer.class);
            endConnection();
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public RegisterAnswer loginRequest(String username, String password) {
        LoginMessage loginMessage = new LoginMessage(username, password);
        try {
            establishConnection();
            sendMessage(gsonAgent.toJson(loginMessage));
            RegisterAnswer result = gsonAgent.fromJson(
                    receiveResponse(), RegisterAnswer.class);
            endConnection();
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void logout(String username, String password) {
        ChangeStateMessage changeStateMessage = new ChangeStateMessage(username, password, "offline");
        try {
            establishConnection();
            sendMessage(gsonAgent.toJson(changeStateMessage));
            endConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
