package bahar.window_kill.control;

import bahar.window_kill.control.util.FileUtil;
import bahar.window_kill.model.User;
import bahar.window_kill.control.fazes.processors.abilities.AbilityWatch;
import bahar.window_kill.model.boards.GameBoard;
import bahar.window_kill.model.boards.MainBoard;
import bahar.window_kill.model.data.Development;
import bahar.window_kill.model.data.Settings;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.generators.shooters.Epsilon;

import java.util.ArrayList;

public class Deck {
    public ArrayList<User> users;
    public ArrayList<Entity> entities;
    public ArrayList<GameBoard> gameBoards;
    public int wave;
    public boolean isLocked;
    public long clock;
    public String save = null;
    public Deck() {
        entities = new ArrayList<>();
        gameBoards = new ArrayList<>();
        users = new ArrayList<>();
        wave = 0;
        isLocked = false;
        clock = 0;
    } //pr = clock / 100 * xp / hp
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
}
