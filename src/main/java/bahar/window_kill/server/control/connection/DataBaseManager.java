package bahar.window_kill.server.control.connection;

import bahar.window_kill.communications.data.Development;
import bahar.window_kill.communications.data.TableSquad;
import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.communications.data.UserMessage;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseManager {
    private final String URL = "jdbc:mysql://127.0.0.1:3306/window_kill";
    private final String USER = "root";
    private final String PASSWORD = "Hashemi18401";
    private final Connection connection;
    private final Statement statement;
    private static DataBaseManager instance;
    private DataBaseManager() {
        Connection myConnection;
        Statement myStatement;
        try {
            myConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            myStatement = myConnection.createStatement();
        } catch (SQLException e) {
            myConnection = null;
            myStatement = null;
            e.printStackTrace();
        }
        connection = myConnection;
        statement = myStatement;
    }
    public static DataBaseManager getInstance() {
        if (instance == null)
            instance = new DataBaseManager();
        return instance;
    }
    public boolean tableExists(String tableName) {
        String query = "SHOW TABLES LIKE '" + tableName + "'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void createUserTable() {
        String query = "CREATE TABLE users (" +
                "username VARCHAR(255) NOT NULL, " +
                "password VARCHAR(255) NOT NULL, " +
                "development VARCHAR(255)," +
                "state VARCHAR(255)," +
                "squad VARCHAR(255) DEFAULT '', " +
                "messages VARCHAR(2047) DEFAULT 'null', " +
                "CHECK (state IN ('online', 'offline', 'busy'))" +
                ")";
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createSquadTable() {
        String query = "CREATE TABLE squads (" +
                "name VARCHAR(255) NOT NULL, " +
                "owner VARCHAR(255) NOT NULL, " +
                "enemy VARCHAR(255) DEFAULT ''," +
                "game_history VARCHAR(1023) DEFAULT '', " +
                "vault INT DEFAULT 0," +
                "PalioxisState INT DEFAULT -1, " +
                "AdonisState INT DEFAULT -1, " +
                "GefjonState INT DEFAULT -1" +
                ")";
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean hasUser(String username) {
        String query = "SELECT * FROM users WHERE username = '" + username + "'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public TableUser getUser(String username) {
        String query = "SELECT * FROM users WHERE username = '" + username + "'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                String squad = rs.getString("squad");
                if (squad.isEmpty()) squad = null;
                return new TableUser(rs.getString("username"), rs.getString("password"), rs.getString("state"), squad, UserMessage.fromJsonArray(rs.getString("messages")), Development.fromJson(rs.getString("development")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void addUser(TableUser tableUser) {
        String squad = tableUser.getSquad();
        if (squad == null)
            squad = "";
        String messages = new Gson().toJson(tableUser.getMessages());
        if (messages == null)
            messages = "";
        String query = "INSERT INTO users (username, password, state, squad, messages, development) VALUES ('" + tableUser.getUsername() + "', '" + tableUser.getPassword() + "', '" + tableUser.getState() + "', '" + squad + "', '" + messages + "', '" + tableUser.getDevelopment().toJson() + "')";
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUser(TableUser tableUser) {
        String squad = (tableUser.getSquad() == null)? "": tableUser.getSquad();
        String messages = new Gson().toJson(tableUser.getMessages());
        String query = "UPDATE users SET password = '" + tableUser.getPassword() +
                "', state = '" + tableUser.getState() +
                "', squad = '" + squad +
                "', messages = '" + messages +
                "', development = '" + tableUser.getDevelopment().toJson() +
                "' WHERE username = '" + tableUser.getUsername() + "'";
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasSquad(String squadName) {
        String query = "SELECT * FROM squads WHERE name = '" + squadName + "'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void newSquad(String squadName, String username) {
        String query = "INSERT INTO squads (name, owner) VALUES ('" + squadName + "', '" + username + "')";
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public TableSquad getSquad(String squadName) {
        String query = "SELECT * FROM squads WHERE name = '" + squadName + "'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return new TableSquad(
                        rs.getString("name"),
                        rs.getString("owner"),
                        rs.getString("enemy"),
                        rs.getString("game_history"),
                        rs.getInt("vault"),
                        rs.getInt("PalioxisState"),
                        rs.getInt("AdonisState"),
                        rs.getInt("GefjonState")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<String> getSquadNames() {
        ArrayList<String> squadNames = new ArrayList<>();
        String query = "SELECT name FROM squads";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                squadNames.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return squadNames;
    }
    public ArrayList<TableUser> getSquadMembers(String squadName) {
        ArrayList<TableUser> squadMembers = new ArrayList<>();
        String query = "SELECT * FROM users WHERE squad = '" + squadName + "'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                TableUser user = new TableUser(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("state"),
                        rs.getString("squad"),
                        UserMessage.fromJsonArray(rs.getString("messages")),
                        Development.fromJson(rs.getString("development"))
                );
                squadMembers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return squadMembers;
    }

    public void updateSquad(TableSquad tableSquad) {
        String query = "UPDATE squads SET owner = '" + tableSquad.getOwner() +
                "', enemy = '" + tableSquad.getEnemy() +
                "', game_history = '" + tableSquad.getHistory() +
                "', vault = " + tableSquad.getVault() +
                ", PalioxisState = " + tableSquad.getPalioxisState() +
                ", AdonisState = " + tableSquad.getAdonisState() +
                ", GefjonState = " + tableSquad.getGefjonState() +
                " WHERE name = '" + tableSquad.getName() + "'";
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeSquad(String squadName) {
        String query = "DELETE FROM squads WHERE name = '" + squadName + "'";
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
