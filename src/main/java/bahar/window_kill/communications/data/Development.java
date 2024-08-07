package bahar.window_kill.communications.data;

import bahar.window_kill.communications.processors.util.abilities.AbilityType;
import com.google.gson.Gson;

public class Development {

    public enum State {
        LOCKED, UNLOCKED, ACTIVE, BOUGHT
    }
    private int highScore;
    private int xp;
    private State[] defenseStates, attackStates;
    private static AbilityType[] defenseType, attackType;
    public Development(int highScore, int xp, State[] defenseStates, State[] attackStates) {
        this.highScore = highScore;
        this.xp = xp;
        this.defenseStates = defenseStates;
        this.attackStates = attackStates;
        makeWatches();
    }
    private static void makeWatches() {
        if (defenseType == null)
            defenseType = new AbilityType[]{AbilityType.ACESO, AbilityType.MELAMPUS, AbilityType.CHIRON, AbilityType.ATHENA};
        if (attackType == null)
            attackType = new AbilityType[]{AbilityType.ARES, AbilityType.ASTRAPE, AbilityType.CERBERUS};
    }
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setDefenseStates(State[] defenseStates) {
        this.defenseStates = defenseStates;
    }

    public void setAttackStates(State[] attackStates) {
        this.attackStates = attackStates;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getXp() {
        return xp;
    }

    public State[] getDefenseStates() {
        return defenseStates;
    }

    public State[] getAttackStates() {
        return attackStates;
    }

    public AbilityType[] getDefenseWatch() {
        return defenseType;
    }

    public AbilityType[] getAttackWatch() {
        return attackType;
    }
    public void bye(State[] states, AbilityType[] abilityTypes, int index) {
        states[index] = State.BOUGHT;
        if (index < states.length - 1)
            states[index + 1] = State.UNLOCKED;
    }
    public void activate(State[] states, AbilityType[] abilityTypes, int index) {
        for (int i = 0; i < states.length; i++)
            if (states[i] == State.ACTIVE)
                states[i] = State.BOUGHT;
        states[index] = State.ACTIVE;
    }
    public AbilityType getActiveDefense() {
        for (int i = 0; i < defenseStates.length; i++)
            if (defenseStates[i] == State.ACTIVE)
                return defenseType[i];
        return null;
    }
    public AbilityType getActiveAttack() {
        for (int i = 0; i < attackStates.length; i++)
            if (attackStates[i] == State.ACTIVE)
                return attackType[i];
        return null;
    }
    public static Development getFirstState() {
        return new Development(0, 0, new State[]{State.UNLOCKED, State.LOCKED, State.LOCKED, State.LOCKED}, new State[]{State.UNLOCKED, State.LOCKED, State.LOCKED});
    }
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    // Method to create Development object from JSON string
    public static Development fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Development.class);
    }
}
