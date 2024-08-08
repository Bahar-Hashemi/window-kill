package bahar.window_kill.communications.model;

import bahar.window_kill.client.control.states.GameState;
import bahar.window_kill.communications.model.boards.GameBoard;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.processors.GameProcessor;

import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    public final ArrayList<User> users;
    public final ArrayList<Entity> entities;
    public final ArrayList<GameBoard> gameBoards;
    public int wave;
    public boolean isLocked;
    public long clock;
    transient public Game save = null;
    transient public GameState gameState;
    transient public ArrayList<GameProcessor> gameProcessors;
    transient public boolean needsView;
    public Game(boolean needsView) {
        this.needsView = needsView;
        entities = new ArrayList<>();
        gameBoards = new ArrayList<>();
        users = new ArrayList<>();
        gameProcessors = new ArrayList<>();
        wave = 6;
        isLocked = false;
        clock = 0;
    }
    public void setGameState(GameState gameState) {
        synchronized (this) {
            if (this.gameState != null)
                this.gameState.stop();
            this.gameState = gameState;
            this.gameState.play();
        }
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
        synchronized (entities) {
            entities.add(entity);
            entity.setDeck(this);
        }
    }
    public void addUser(User user) {
        synchronized (users) {
            users.add(user);
            user.epsilon.setDeck(this);
        }
    }
    public void removeUser(User user) {
        synchronized (users) {
            users.remove(user);
        }
    }
    public void removeEntity(Entity entity) {
        synchronized (entities) {
            entities.remove(entity);
        }
    }
    public Entity getEntity(String id) {
        synchronized (entities) {
            for (Entity entity: entities)
                if (entity.getId().equals(id))
                    return entity;
        }
        return null;
    }

}
