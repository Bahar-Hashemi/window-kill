package bahar.window_kill.control;

import bahar.window_kill.model.User;
import bahar.window_kill.control.fazes.processors.abilities.AbilityWatch;
import bahar.window_kill.model.boards.GameBoard;
import bahar.window_kill.model.boards.MainBoard;
import bahar.window_kill.model.entities.Entity;

import java.util.ArrayList;

public class Deck {
    public static MainBoard mainBoard;
    public static User user;
    public static ArrayList<Entity> entities = new ArrayList<>();
    public static ArrayList<GameBoard> gameBoards = new ArrayList<>();
    public static ArrayList<AbilityWatch> abilities = new ArrayList<>();
    public static int wave = 0;
    public static long coolDown = 0;
    public static void newDeck() {
        entities = new ArrayList<>();
        gameBoards = new ArrayList<>();
        user = null;
        mainBoard = null;
        wave = 0;
        coolDown = 0;
    }
}
