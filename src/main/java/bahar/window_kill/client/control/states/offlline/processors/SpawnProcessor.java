package bahar.window_kill.client.control.states.offlline.processors;

import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.control.states.offlline.processors.spawners.BlackOrbSpawner;
import bahar.window_kill.client.control.states.offlline.processors.spawners.SaveSpawner;
import bahar.window_kill.client.model.entities.generators.shooters.*;
import bahar.window_kill.client.control.states.offlline.processors.spawners.EntitySpawner;
import bahar.window_kill.client.model.SmileyBrain;
import bahar.window_kill.client.model.Watch;
import bahar.window_kill.client.model.entities.Barricados;
import bahar.window_kill.client.model.entities.attackers.Squarantine;
import bahar.window_kill.client.model.entities.attackers.Trigorath;
import bahar.window_kill.client.model.entities.generators.SpawnerArchmire;

import java.util.ArrayList;

public class SpawnProcessor extends GameProcessor {
    EntitySpawner[] entitySpawners = new EntitySpawner[10];
    ArrayList<Watch> currentWatches = new ArrayList<>();
    SmileyBrain smileyBrain;
    public SpawnProcessor(Game game) {
        super(game);
        makeWatches();
    }
    private void makeWatches() {
        int difficulty = 100 / game.users.get(0).settings.getDifficulty() /*/ settings.getDifficulty()*/;
        makeWatch(0, 1250 * difficulty, 5 * difficulty, new Class<?>[]{Trigorath.class, Squarantine.class});
        makeWatch(1, 1000 * difficulty, 5 * difficulty, new Class<?>[]{Trigorath.class, Squarantine.class});
        makeWatch(2, 1500 * difficulty, 5 * difficulty, new Class<?>[]{Trigorath.class, Squarantine.class, Nechropic.class, Omenoct.class});
        makeWatch(3, 1300 * difficulty, 5 * difficulty, new Class<?>[]{Trigorath.class, Squarantine.class, Nechropic.class, Omenoct.class});

        makeWatch(4, 1500 * difficulty, 5 * difficulty, new Class<?>[]{
                Trigorath.class, Squarantine.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(5, 2000 * difficulty, 5 * difficulty, new Class<?>[]{
                Wyrm.class, Squarantine.class, Trigorath.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(6, 1500 * difficulty, 5 * difficulty, new Class<?>[]{
                Wyrm.class, Barricados.class, Squarantine.class, Trigorath.class});
        makeWatch(7, 1500 * difficulty, 5 * difficulty, new Class<?>[]{
                Wyrm.class, Barricados.class, Squarantine.class, Trigorath.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(8, 1500 * difficulty, 5 * difficulty, new Class<?>[]{
                Wyrm.class, Barricados.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(9, 1500 * difficulty, 5 * difficulty, new Class<?>[]{
                Wyrm.class, Barricados.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        currentWatches.add(entitySpawners[game.wave]);
    }
    private void makeWatch(int index, int duration, int cycleCount, Class<?>[] enemyTypes) {
        entitySpawners[index] = new EntitySpawner(duration, cycleCount, enemyTypes, game) {
            @Override
            protected void onEnd() {
                currentWatches.remove(entitySpawners[game.wave]);
                currentWatches.add(makeSaveWatch(index));
            }

            @Override
            protected void onStart() {
                if (index >= 7)
                    currentWatches.add(new BlackOrbSpawner(game) {
                        @Override
                        protected void onEnd() {
                            currentWatches.remove(this);
                        }
                    });
            }
        };
    }
    private SaveSpawner makeSaveWatch(int index) {
        SaveSpawner saveSpawner = new SaveSpawner(game) {
            @Override
            protected void onEnd() {
                super.onEnd();
                currentWatches.remove(this);
                game.wave = index + 1;
                if (index == 9) {
                    SmileyFace face = new SmileyFace(); addEntity(face, game);
                    SmileyHand leftHand = new SmileyHand(true); addEntity(leftHand, game);
                    SmileyHand rightHand = new SmileyHand(false); addEntity(rightHand, game);
                    smileyBrain = new SmileyBrain(game, face, leftHand, rightHand);
                    return;
                }
                currentWatches.add(entitySpawners[game.wave]);
            }
        };
        return saveSpawner;
    }
    @Override
    public void run() {
        if (game.wave == 10 && smileyBrain.getHP() <= 0)
            System.out.println("Game over!!!");
        if (game.isLocked)
            return;
        if (game.wave == 10) {
            smileyBrain.act(game);
            return;
        }
        for (int i = currentWatches.size() - 1; i >= 0; i--)
            currentWatches.get(i).call(game.clock);
    }
}
