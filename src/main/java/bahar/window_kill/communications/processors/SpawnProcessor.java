package bahar.window_kill.communications.processors;

import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.processors.util.spawners.BlackOrbSpawner;
import bahar.window_kill.communications.processors.util.spawners.SaveSpawner;
import bahar.window_kill.communications.processors.util.spawners.EntitySpawner;
import bahar.window_kill.communications.model.SmileyBrain;
import bahar.window_kill.communications.model.Watch;
import bahar.window_kill.communications.model.entities.Barricados;
import bahar.window_kill.communications.model.entities.attackers.Squarantine;
import bahar.window_kill.communications.model.entities.attackers.Trigorath;
import bahar.window_kill.communications.model.entities.generators.SpawnerArchmire;
import bahar.window_kill.communications.model.entities.generators.shooters.*;

import java.util.ArrayList;

public class SpawnProcessor extends GameProcessor {
    transient EntitySpawner[] entitySpawners = new EntitySpawner[10];
    transient ArrayList<Watch> currentWatches = new ArrayList<>();
    SmileyBrain smileyBrain;

    public SpawnProcessor(Boolean isViewable, Game game) {
        super(isViewable, game);
        makeWatches();
    }
    private void makeWatches() {
        int difficulty = 1;
        if (game.needsView)
            difficulty = 100 / game.users.get(0).settings.getDifficulty() /*/ settings.getDifficulty()*/;
        makeWatch(isViewable, 0, 2500 / difficulty, 25 * difficulty, new Class<?>[]{Trigorath.class, Squarantine.class});
        makeWatch(isViewable, 1, 2000 / difficulty, 25 * difficulty, new Class<?>[]{Trigorath.class, Squarantine.class});
        makeWatch(isViewable,2, 3000 / difficulty, 30 * difficulty, new Class<?>[]{Trigorath.class, Squarantine.class, Nechropic.class, Omenoct.class});
        makeWatch(isViewable,3, 26000 / difficulty, 25 * difficulty, new Class<?>[]{Trigorath.class, Squarantine.class, Nechropic.class, Omenoct.class});

        makeWatch(isViewable,4, 3000 / difficulty, 30 * difficulty, new Class<?>[]{
                Trigorath.class, Squarantine.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(isViewable,5, 3000 / difficulty, 25 * difficulty, new Class<?>[]{
                Wyrm.class, Squarantine.class, Trigorath.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(isViewable,6, 3000 / difficulty, 30 * difficulty, new Class<?>[]{
                Wyrm.class, Barricados.class, Squarantine.class, Trigorath.class});
        makeWatch(isViewable,7, 2500 / difficulty, 30 * difficulty, new Class<?>[]{
                Wyrm.class, Barricados.class, Squarantine.class, Trigorath.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(isViewable,8, 2500 / difficulty, 50 * difficulty, new Class<?>[]{
                Wyrm.class, Barricados.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        makeWatch(isViewable,9, 2000 / difficulty, 50 * difficulty, new Class<?>[]{
                Wyrm.class, Barricados.class, Nechropic.class, Omenoct.class, SpawnerArchmire.class});
        currentWatches.add(entitySpawners[game.wave]);
    }
    private void makeWatch(boolean isViewable, int index, int duration, int cycleCount, Class<?>[] enemyTypes) {
        entitySpawners[index] = new EntitySpawner(isViewable, duration, cycleCount, enemyTypes, game) {
            @Override
            protected void onEnd() {
                super.onEnd();
                currentWatches.remove(entitySpawners[game.wave]);
                currentWatches.add(makeSaveWatch(index));
            }

            @Override
            protected void onStart() {
                if (index >= 7)
                    currentWatches.add(new BlackOrbSpawner(isViewable, game) {
                        @Override
                        protected void onEnd() {
                            currentWatches.remove(this);
                        }
                    });
            }
        };
    }
    private SaveSpawner makeSaveWatch(int index) {
        SaveSpawner saveSpawner = new SaveSpawner(isViewable, game) {
            @Override
            protected void onEnd() {
                super.onEnd();
                currentWatches.remove(this);
                game.wave = index + 1;
                if (index == 9) {
                    SmileyFace face = new SmileyFace(isViewable, GameUtil.generateID()); addEntity(face, game);
                    SmileyHand leftHand = new SmileyHand(isViewable, GameUtil.generateID(), true); addEntity(leftHand, game);
                    SmileyHand rightHand = new SmileyHand(isViewable, GameUtil.generateID(), false); addEntity(rightHand, game);
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
