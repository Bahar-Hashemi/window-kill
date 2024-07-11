package bahar.window_kill.control;

import bahar.window_kill.model.User;
import bahar.window_kill.model.boards.GameBoard;
import bahar.window_kill.model.boards.MainBoard;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.view.MainStage;

import java.util.ArrayList;

public class Deck {
    public static MainBoard mainBoard;
    public static User user;
    public static ArrayList<Entity> entities = new ArrayList<>();
    public static ArrayList<GameBoard> gameBoards = new ArrayList<>();
    public static void newDeck() {
        entities = new ArrayList<>();
        gameBoards = new ArrayList<>();
        user = null;
        mainBoard = null;
    }
}
