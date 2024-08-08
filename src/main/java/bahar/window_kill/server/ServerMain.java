package bahar.window_kill.server;

import bahar.window_kill.server.control.connection.DataBaseManager;
import bahar.window_kill.server.control.connection.TCPWorkSpace;
import bahar.window_kill.server.control.game.BattleController;
import bahar.window_kill.server.control.game.GamesManager;

import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args) {
        if (!DataBaseManager.getInstance().tableExists("users"))
            DataBaseManager.getInstance().createUserTable();
        if (!DataBaseManager.getInstance().tableExists("squads"))
            DataBaseManager.getInstance().createSquadTable();
        TCPWorkSpace.getInstance().start();
        new GamesManager().start();
        System.out.println("server has been started!");
        boolean isRunningSquadBattle = false;
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (isRunningSquadBattle) {
                System.out.println("Do you want to end squad battle? (yes/no)\n");
                String answer = sc.nextLine();
                if (answer.equals("yes")) {
                    BattleController.endSquadBattle();
                    isRunningSquadBattle = false;
                }
            }
            else {
                System.out.println("Do you want to start squad battle? (yes/no)\n");
                String answer = sc.nextLine();
                if (answer.equals("yes")) {
                    BattleController.initiateSquadBattle();
                    isRunningSquadBattle = true;
                }
            }
        }
    }
}
