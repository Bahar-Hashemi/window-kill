package bahar.window_kill.model.data;

import bahar.window_kill.control.fazes.processors.abilities.AbilityWatch;
import bahar.window_kill.control.fazes.processors.abilities.skills.*;

import java.lang.reflect.Constructor;

public class Development {

    public enum State {
        LOCKED, UNLOCKED, ACTIVE, BOUGHT
    }
    private int highScore;
    private int xp;
    private State[] defenseStates, attackStates;
    private AbilityWatch[] defenseWatch, attackWatch;
    public Development(int highScore, int xp, State[] defenseStates, State[] attackStates) {
        this.highScore = highScore;
        this.xp = xp;
        this.defenseStates = defenseStates;
        this.attackStates = attackStates;
        defenseWatch = new AbilityWatch[]{new AcesoWatch(), new MelampusWatch(), new ChironWatch(), new AthenaWatch()};
        attackWatch = new AbilityWatch[]{new AresWatch(), new AstrapeWatch(), new CerberusWatch()};
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

    public AbilityWatch[] getDefenseWatch() {
        return defenseWatch;
    }

    public AbilityWatch[] getAttackWatch() {
        return attackWatch;
    }
    public void bye(State[] states, AbilityWatch[] abilityWatches, int index) {
        xp -= abilityWatches[index].getPrice();
        states[index] = State.BOUGHT;
        if (index < states.length - 1)
            states[index + 1] = State.UNLOCKED;
    }
    public void activate(State[] states, AbilityWatch[] abilityWatches, int index) {
        for (int i = 0; i < states.length; i++)
            if (states[i] == State.ACTIVE)
                states[i] = State.BOUGHT;
        states[index] = State.ACTIVE;
    }
    public AbilityWatch getActiveDefense() {
        for (int i = 0; i < defenseStates.length; i++)
            if (defenseStates[i] == State.ACTIVE)
                return makeWatch(defenseWatch[i]);
        return null;
    }
    public AbilityWatch getActiveAttack() {
        for (int i = 0; i < attackStates.length; i++)
            if (attackStates[i] == State.ACTIVE)
                return makeWatch(attackWatch[i]);
        return null;
    }
    public AbilityWatch makeWatch(AbilityWatch abilityWatch) {
        try {
            Class<?> clazz = abilityWatch.getClass();
            Constructor<?> constructor = clazz.getConstructor();
            return (AbilityWatch) constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
