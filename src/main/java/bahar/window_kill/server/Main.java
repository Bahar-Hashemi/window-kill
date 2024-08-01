package bahar.window_kill.server;

import bahar.window_kill.communications.data.TableUser;
import bahar.window_kill.server.control.DataBaseManager;
import bahar.window_kill.server.control.TCPWorkSpace;

public class Main {
    public static void main(String[] args) {
        if (!DataBaseManager.getInstance().tableExists("users"))
            DataBaseManager.getInstance().createUserTable();
        if (!DataBaseManager.getInstance().tableExists("squads"))
            DataBaseManager.getInstance().createSquadTable();
        TCPWorkSpace.getInstance().start();
        System.out.println("server has been started!");
    }
}
