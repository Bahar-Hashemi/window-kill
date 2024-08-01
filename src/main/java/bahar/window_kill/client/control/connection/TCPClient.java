package bahar.window_kill.client.control.connection;

import bahar.window_kill.communications.data.Development;
import bahar.window_kill.communications.data.TableSquad;
import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.data.UserMessage;
import bahar.window_kill.communications.messages.client.data.*;
import bahar.window_kill.communications.messages.client.register.LoginMessage;
import bahar.window_kill.communications.messages.client.register.SignupMessage;
import bahar.window_kill.communications.messages.client.squads.NewSquadMessage;
import bahar.window_kill.communications.messages.server.GeneralAnswer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

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


    public GeneralAnswer signupRequest(String username, String password, String repeatedPassword) {
        SignupMessage signupMessage = new SignupMessage(username, password, repeatedPassword);
        try {
            establishConnection();
            sendMessage(gsonAgent.toJson(signupMessage));
            GeneralAnswer result = gsonAgent.fromJson(
                    receiveResponse(), GeneralAnswer.class);
            endConnection();
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public GeneralAnswer loginRequest(String username, String password) {
        LoginMessage loginMessage = new LoginMessage(username, password);
        try {
            establishConnection();
            sendMessage(gsonAgent.toJson(loginMessage));
            GeneralAnswer result = gsonAgent.fromJson(
                    receiveResponse(), GeneralAnswer.class);
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
    public TableUser getMe(String username) {
        RequestDataMessage requestDataMessage = new RequestDataMessage(RequestedDataType.ME, username);
        try {
            establishConnection();
            sendMessage(gsonAgent.toJson(requestDataMessage));
            TableUser user = new Gson().fromJson(receiveResponse(), TableUser.class);
            endConnection();
            return user;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getGlobe(String username) {
        RequestDataMessage requestDataMessage = new RequestDataMessage(RequestedDataType.GLOBAL_SQUADS, username);
        try {
            establishConnection();
            sendMessage(gsonAgent.toJson(requestDataMessage));
            ArrayList<String> result = gsonAgent.fromJson(
                    receiveResponse(), ArrayList.class);
            endConnection();
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public GeneralAnswer newSquad(String username, String newSquadName) {
        NewSquadMessage newSquadMessage = new NewSquadMessage(username, newSquadName);
        try {
            establishConnection();
            sendMessage(gsonAgent.toJson(newSquadMessage));
            GeneralAnswer result = gsonAgent.fromJson(
                    receiveResponse(), GeneralAnswer.class);
            endConnection();
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void sendDevelopment(String username, Development development) {
        SendDevelopmentMessage sendDevelopmentMessage = new SendDevelopmentMessage(username, development);
        try {
            establishConnection();
            sendMessage(gsonAgent.toJson(sendDevelopmentMessage));
            endConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendSquad(String username, TableSquad tableSquad) {
        SendSquadMessage sendSquadMessage = new SendSquadMessage(username, tableSquad);
        try {
            establishConnection();
            sendMessage(gsonAgent.toJson(sendSquadMessage));
            endConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TableSquad mySquad(String username) {
        RequestDataMessage requestDataMessage = new RequestDataMessage(RequestedDataType.MY_SQUAD, username);
        try {
            establishConnection();
            sendMessage(gsonAgent.toJson(requestDataMessage));
            TableSquad squad = gsonAgent.fromJson(
                    receiveResponse(), TableSquad.class);
            endConnection();
            return squad;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<TableUser> mySquadMembers(String username) {
        RequestDataMessage requestDataMessage = new RequestDataMessage(RequestedDataType.MY_SQUAD_MEMBERS, username);
        try {
            establishConnection();
            sendMessage(gsonAgent.toJson(requestDataMessage));
            ArrayList<TableUser> teamMates = TableUser.fromJsonArray(receiveResponse());
            endConnection();
            return teamMates;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void sendUserMessage(UserMessage userMessage) {
        SendMessageMessage sendMessageMessage = new SendMessageMessage(userMessage);
        try {
            establishConnection();
            sendMessage(gsonAgent.toJson(sendMessageMessage));
            endConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
