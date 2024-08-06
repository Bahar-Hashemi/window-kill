package bahar.window_kill.client.model;

import bahar.window_kill.client.control.states.GameState;
import bahar.window_kill.client.model.boards.GameBoard;
import bahar.window_kill.client.model.entities.Entity;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Game {
    public ArrayList<User> users;
    public ArrayList<Entity> entities;
    public ArrayList<GameBoard> gameBoards;
    public int wave;
    public boolean isLocked;
    public long clock;
    public String save = null;
    public GameState gameState;
    public static Game onlineInstance;
    private Game() {
        entities = new ArrayList<>();
        gameBoards = new ArrayList<>();
        users = new ArrayList<>();
        wave = 0;
        isLocked = false;
        clock = 0;
        onlineInstance = this;
    }
    public static void newInstance() {
        onlineInstance = new Game();
    }
    public static Game getInstance() {
        return onlineInstance;
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
        entity.setDeck(this);
    }
    public void addUser(User user) {
        users.add(user);
        user.epsilon.setDeck(this);
    }
    public void readFromString(Scanner sc) {
        wave = sc.nextInt();
        System.out.println("Wave: " + wave);
    }
}
