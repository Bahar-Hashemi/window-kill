package bahar.window_kill.server;

import bahar.window_kill.server.control.connection.DataBaseManager;
import bahar.window_kill.server.control.connection.TCPWorkSpace;
import bahar.window_kill.server.control.game.GamesManager;

public class Main {
    public static void main(String[] args) {
        if (!DataBaseManager.getInstance().tableExists("users"))
            DataBaseManager.getInstance().createUserTable();
        if (!DataBaseManager.getInstance().tableExists("squads"))
            DataBaseManager.getInstance().createSquadTable();
        TCPWorkSpace.getInstance().start();
        new GamesManager().start();
        System.out.println("server has been started!");
    }
}
