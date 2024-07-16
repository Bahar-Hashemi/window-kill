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
    public static MainBoard mainBoard;
    public static User user;
    public static ArrayList<Entity> entities;
    public static ArrayList<GameBoard> gameBoards;
    public static ArrayList<AbilityWatch> abilities;
    public static int wave;
    public static long coolDown;
    public static boolean isLocked;
    public static double shrink;
    public static Development development;
    public static Settings settings;
    public static long clock;
    public static String save = null;
    public static void newDeck() {
        development = FileUtil.readDevelopment();
        settings = FileUtil.readSettings();
        entities = new ArrayList<>();
        gameBoards = new ArrayList<>();
        abilities = new ArrayList<>();
        user = new User(new Epsilon(settings.getSpeed()));
        mainBoard = new MainBoard((int) user.getEpsilon().getHP(), 0, wave);
        wave = 0;
        coolDown = 0;
        isLocked = false;
        shrink = 0.3;
        clock = 0;
    } //pr = clock / 100 * xp / hp
    public static int getPR() {
        return (int) (clock / 500 * user.getEpsilon().getXp() / user.getEpsilon().getHP());
    }
}
