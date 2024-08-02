package bahar.window_kill.client.control.states.processors.spawners;

import bahar.window_kill.client.model.Game;
import bahar.window_kill.client.model.entities.Entity;

import java.lang.reflect.Constructor;
import java.util.Random;

public class EntitySpawner extends Spawner {
    boolean spawned = false;
    public EntitySpawner(int duration, int cycleCount, Class<?>[] enemyTypes, Game game) {
        super(duration, createRunnable(enemyTypes, game), game);
        setCycleCount(cycleCount);
    }
    private static Runnable createRunnable(Class<?>[] enemyTypes, Game game) {
        return () -> {
            Random random = new Random();
            Class<?> type = enemyTypes[random.nextInt(enemyTypes.length)];
            try {
                Constructor<?> constructor = type.getConstructor();
                Entity entity = (Entity) constructor.newInstance();
                addEntity(entity, game);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
