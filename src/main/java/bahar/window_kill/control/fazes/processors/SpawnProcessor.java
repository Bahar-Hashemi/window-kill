package bahar.window_kill.control.fazes.processors;

import bahar.window_kill.control.Deck;
import bahar.window_kill.control.fazes.PlayingState;
import bahar.window_kill.control.fazes.processors.strategies.spawners.BlackOrbSpawner;
import bahar.window_kill.control.fazes.processors.strategies.spawners.SpawnWatch;
import bahar.window_kill.model.SmileyBrain;
import bahar.window_kill.model.Watch;
import bahar.window_kill.model.entities.Barricados;
import bahar.window_kill.model.entities.attackers.Squarantine;
import bahar.window_kill.model.entities.attackers.Trigorath;
import bahar.window_kill.model.entities.generators.SpawnerArchmire;
import bahar.window_kill.model.entities.generators.shooters.*;

import javax.crypto.spec.DESKeySpec;
import java.util.ArrayList;

public class SpawnProcessor extends GameProcessor {
    SpawnWatch[] spawnWatches = new SpawnWatch[10];
    ArrayList<Watch> currentWatches = new ArrayList<>();
    SmileyBrain smileyBrain;
    static boolean isLocked = false;
    public SpawnProcessor() {
        makeWatches();
    }
    private void makeWatches() {
        makeWatch(0, 2500, 30, new Class<?>[]{Trigorath.class, Squarantine.class});
        makeWatch(1, 1800, 50, new Class<?>[]{Trigorath.class, Squarantine.class});
        makeWatch(2, 4000, 50, new Class<?>[]{Trigorath.class, Squarantine.class, Nechropic.class, Omenoct.class});
        makeWatch(3, 3000, 60, new Class<?>[]{Trigorath.class, Squarantine.class, Nechropic.class, Omenoct.class});

        makeWatch(4, 4000, 50, new Class<?>[]{
                Trigorath.class, Squarantine.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(5, 4500, 50, new Class<?>[]{
                Wyrm.class, Squarantine.class, Trigorath.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(6, 4500, 60, new Class<?>[]{
                Wyrm.class, Barricados.class, Squarantine.class, Trigorath.class});
        makeWatch(7, 4500, 50, new Class<?>[]{
                Wyrm.class, Barricados.class, Squarantine.class, Trigorath.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(8, 4500, 40, new Class<?>[]{
                Wyrm.class, Barricados.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(9, 4000, 1, new Class<?>[]{
                Wyrm.class, Barricados.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        currentWatches.add(spawnWatches[Deck.wave]); //todo think more about here!
    }
    private void makeWatch(int index, int duration, int cycleCount, Class<?>[] enemyTypes) {
        spawnWatches[index] = new SpawnWatch(duration, cycleCount, enemyTypes) {
            @Override
            protected void onEnd() {
                currentWatches.remove(spawnWatches[Deck.wave]);
                Deck.wave = index + 1;
                if (index == 9) {
                    SmileyFace face = new SmileyFace(); addEntity(face);
                    SmileyHand leftHand = new SmileyHand(true); addEntity(leftHand);
                    SmileyHand rightHand = new SmileyHand(false); addEntity(rightHand);
                    smileyBrain = new SmileyBrain(face, leftHand, rightHand);
                    return;
                }
                currentWatches.add(spawnWatches[Deck.wave]);
                if (index >= 7)
                    currentWatches.add(new BlackOrbSpawner() {
                        @Override
                        protected void onEnd() {
                            currentWatches.remove(this);
                        }
                    });
            }
        };
    }
    @Override
    public void run() {
        if (isLocked)
            return;
        if (Deck.wave == 10) {
            smileyBrain.act(Deck.user.getEpsilon(), Deck.mainBoard);
            return;
        }
        for (int i = currentWatches.size() - 1; i >= 0; i--)
            currentWatches.get(i).call();
    }
    public static void setLocked(boolean isLocked) {
        SpawnProcessor.isLocked = isLocked;
    }
    public static boolean getLocked() {
        return isLocked;
    }
}
