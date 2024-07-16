package bahar.window_kill.control.fazes.processors;

import bahar.window_kill.control.Deck;
import bahar.window_kill.control.fazes.PlayingState;
import bahar.window_kill.control.fazes.processors.spawners.BlackOrbSpawner;
import bahar.window_kill.control.fazes.processors.spawners.SaveSpawner;
import bahar.window_kill.control.fazes.processors.spawners.EntitySpawner;
import bahar.window_kill.model.SmileyBrain;
import bahar.window_kill.model.Watch;
import bahar.window_kill.model.entities.Barricados;
import bahar.window_kill.model.entities.attackers.Squarantine;
import bahar.window_kill.model.entities.attackers.Trigorath;
import bahar.window_kill.model.entities.generators.SpawnerArchmire;
import bahar.window_kill.model.entities.generators.shooters.*;

import java.util.ArrayList;

import static bahar.window_kill.control.Deck.settings;

public class SpawnProcessor extends GameProcessor {
    EntitySpawner[] entitySpawners = new EntitySpawner[10];
    ArrayList<Watch> currentWatches = new ArrayList<>();
    SmileyBrain smileyBrain;
    public SpawnProcessor() {
        makeWatches();
    }
    private void makeWatches() {
        int difficulty = 100 / settings.getDifficulty();
        makeWatch(0, 5000 * difficulty, 4, new Class<?>[]{Trigorath.class, Squarantine.class});
        makeWatch(1, 4000 * difficulty, 50, new Class<?>[]{Trigorath.class, Squarantine.class});
        makeWatch(2, 8000 * difficulty, 50, new Class<?>[]{Trigorath.class, Squarantine.class, Nechropic.class, Omenoct.class});
        makeWatch(3, 6000 * difficulty, 60, new Class<?>[]{Trigorath.class, Squarantine.class, Nechropic.class, Omenoct.class});

        makeWatch(4, 8000 * difficulty, 50, new Class<?>[]{
                Trigorath.class, Squarantine.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(5, 9500 * difficulty, 50, new Class<?>[]{
                Wyrm.class, Squarantine.class, Trigorath.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(6, 9500 * difficulty, 60, new Class<?>[]{
                Wyrm.class, Barricados.class, Squarantine.class, Trigorath.class});
        makeWatch(7, 9500 * difficulty, 50, new Class<?>[]{
                Wyrm.class, Barricados.class, Squarantine.class, Trigorath.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(8, 9500 * difficulty, 40, new Class<?>[]{
                Wyrm.class, Barricados.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(9, 9000 * difficulty, 40, new Class<?>[]{
                Wyrm.class, Barricados.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        currentWatches.add(entitySpawners[Deck.wave]); //todo think more about here!
    }
    private void makeWatch(int index, int duration, int cycleCount, Class<?>[] enemyTypes) {
        entitySpawners[index] = new EntitySpawner(duration, cycleCount, enemyTypes) {
            @Override
            protected void onEnd() {
                currentWatches.remove(entitySpawners[Deck.wave]);
                currentWatches.add(makeSaveWatch(index));
            }

            @Override
            protected void onStart() {
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
    private SaveSpawner makeSaveWatch(int index) {
        SaveSpawner saveSpawner = new SaveSpawner() {
            @Override
            protected void onEnd() {
                super.onEnd();
                currentWatches.remove(this);
                Deck.wave = index + 1;
                if (index == 9) {
                    SmileyFace face = new SmileyFace(); addEntity(face);
                    SmileyHand leftHand = new SmileyHand(true); addEntity(leftHand);
                    SmileyHand rightHand = new SmileyHand(false); addEntity(rightHand);
                    smileyBrain = new SmileyBrain(face, leftHand, rightHand);
                    return;
                }
                currentWatches.add(entitySpawners[Deck.wave]);
            }
        };
        return saveSpawner;
    }
    @Override
    public void run() {
        if (Deck.wave == 10 && smileyBrain.getHP() <= 0)
            PlayingState.endGame();
        if (Deck.isLocked)
            return;
        if (Deck.wave == 10) {
            smileyBrain.act(Deck.user.getEpsilon(), Deck.mainBoard);
            return;
        }
        for (int i = currentWatches.size() - 1; i >= 0; i--)
            currentWatches.get(i).call();
    }
}
