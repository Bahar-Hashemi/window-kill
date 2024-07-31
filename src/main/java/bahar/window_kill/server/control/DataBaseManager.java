package bahar.window_kill.server.control;

import bahar.window_kill.communications.data.Development;
import bahar.window_kill.communications.data.TableUser;

import java.sql.*;

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
                "squad VARCHAR(255)," +
                "messages VARCHAR(1023), " +
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
                "game_history VARCHAR(1023), " +
                "vault INT," +
                "isPalioxisOn BIT, " +
                "isAdonisOn BIT, " +
                "isGefjonOn BIT " +
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
                return new TableUser(rs.getString("username"), rs.getString("password"), rs.getString("state"), rs.getString("squad"), rs.getString("messages"), Development.fromJson(rs.getString("development")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void addUser(TableUser tableUser) {
        String query = "INSERT INTO users (username, password, state, squad, messages, development) VALUES ('" + tableUser.getUsername() + "', '" + tableUser.getPassword() + "', '" + tableUser.getState() + "', '" + tableUser.getSquad() + "', '" + tableUser.getMessages() + "', '" + tableUser.getDevelopment() + "')";
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUser(TableUser tableUser) {
        String query = "UPDATE users SET password = '" + tableUser.getPassword() +
                "', state = '" + tableUser.getState() +
                "', squad = '" + tableUser.getSquad() +
                "', messages = '" + tableUser.getMessages() +
                "', development = '" + tableUser.getDevelopment() +
                "' WHERE username = '" + tableUser.getUsername() + "'";
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
