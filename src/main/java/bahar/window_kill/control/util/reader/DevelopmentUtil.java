package bahar.window_kill.control.util.reader;

import bahar.window_kill.model.data.Development;
import bahar.window_kill.model.data.Development.*;

import java.util.Scanner;

public class DevelopmentUtil extends ModelUtil {
    @Override
    public Object read(Scanner sc) {
        int highScore = sc.nextInt();
        int xp = sc.nextInt();
        State[] defenseStates = new State[sc.nextInt()];
        for (int i = 0; i < defenseStates.length; i++) {
            defenseStates[i] = State.valueOf(sc.next());
        }
        State[] attackStates = new State[sc.nextInt()];
        for (int i = 0; i < attackStates.length; i++) {
            attackStates[i] = State.valueOf(sc.next());
        }
        return new Development(highScore, xp, defenseStates, attackStates);
    }

    @Override
    public String write(Object object) {
        Development development = (Development) object;
        StringBuilder sb = new StringBuilder();
        sb.append(development.getHighScore()).append(" ");
        sb.append(development.getXp()).append("\n");
        sb.append(development.getDefenseStates().length).append("\n");
        for (State state : development.getDefenseStates()) {
            sb.append(state).append(" ");
        }
        sb.append("\n");
        sb.append(development.getAttackStates().length).append("\n");
        for (State state : development.getAttackStates()) {
            sb.append(state).append(" ");
        }
        return sb.toString();
    }
}
