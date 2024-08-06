package bahar.window_kill.server.model;

import bahar.window_kill.client.control.states.GameState;
import bahar.window_kill.server.model.User;
import bahar.window_kill.client.model.boards.GameBoard;
import bahar.window_kill.client.model.entities.Entity;
//todo this imports need to be from server!!!
import java.util.ArrayList;
import java.util.Properties;

public class Game {
    public ArrayList<User> users;
    public ArrayList<Entity> entities;
    public ArrayList<GameBoard> gameBoards;
    public int wave;
    public boolean isLocked;
    public long clock;
    public String save = null;
    public GameState gameState;
    private static final ArrayList<Game> onlineGames = new ArrayList<>();
    public Game() {
        entities = new ArrayList<>();
        gameBoards = new ArrayList<>();
        users = new ArrayList<>();
        wave = 0;
        isLocked = false;
        clock = 0;
    }
    public static Game getMyGame(String username) {
        for (Game game: onlineGames)
            for (User user: game.users)
                if (user.getUsername().equals(username))
                    return game;
        return null;
    }
    public static Game notNullGame(String username) {
        synchronized (onlineGames) {
            while (true) {
                Game game = getMyGame(username);
                if (game != null)
                    return game;
                try {
                    onlineGames.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void addGame(Game game) {
        synchronized (onlineGames) {
            onlineGames.add(game);
            onlineGames.notifyAll();
        }
    }

    public void setGameState(GameState gameState) {
        if (this.gameState != null)
            this.gameState.stop();
        this.gameState = gameState;
        this.gameState.play();
    }
    public int getPR() {
        double result = 0;
        for (User user: users)
            result += (1 / 0.3 * getHPPrice(user.getEpsilon().getHP()) + 2 / 0.3 * getWavePrice(wave)) * user.getXp();
        return (int) result;
    }
    private double getHPPrice(double hp) {
        hp = Math.min(100, hp);
        return hp * hp / 100 / 100;
    }
    private double getWavePrice(int wave) {
        return wave * wave / 100.0;
    }
    public void addEntity(Entity entity) {
        entities.add(entity);
//        entity.setDeck(this);
    }
    public void addUser(User user) {
        users.add(user);
//        user.epsilon.setDeck(this);
    }
    public String writeToString(StringBuilder sb) {
        sb.append(wave).append("\n");
        sb.append(users.size()).append("\n");
        for (User user: users)
            user.writeToString(sb);
        return sb.toString();
    }
}

