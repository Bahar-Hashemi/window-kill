package bahar.window_kill.server.control.game;

import bahar.window_kill.communications.data.TableSquad;
import bahar.window_kill.server.control.connection.DataBaseManager;

import java.util.ArrayList;
import java.util.Collections;

public class BattleController {
    public static void initiateSquadBattle() {
        ArrayList<String> squads = DataBaseManager.getInstance().getSquadNames();
        Collections.shuffle(squads);
        for (int i = 0; i + 1 < squads.size(); i += 2) {
            TableSquad squad = DataBaseManager.getInstance().getSquad(squads.get(i));
            TableSquad newSquad = DataBaseManager.getInstance().getSquad(squads.get(i + 1));
            squad.setEnemy(newSquad.getName()); DataBaseManager.getInstance().updateSquad(squad);
            newSquad.setEnemy(squad.getName()); DataBaseManager.getInstance().updateSquad(newSquad);
        }
    }
    public static void endSquadBattle() {

    }
}
