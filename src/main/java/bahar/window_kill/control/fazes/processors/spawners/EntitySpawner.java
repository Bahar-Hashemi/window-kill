package bahar.window_kill.control.fazes.processors.spawners;

import bahar.window_kill.model.entities.Entity;

import java.lang.reflect.Constructor;
import java.util.Random;

public class EntitySpawner extends Spawner {
    boolean spawned = false;
    public EntitySpawner(int duration, int cycleCount, Class<?>[] enemyTypes) {
        super(duration, createRunnable(enemyTypes));
        setCycleCount(cycleCount);
    }
    private static Runnable createRunnable(Class<?>[] enemyTypes) {
        return () -> {
            Random random = new Random();
            Class<?> type = enemyTypes[random.nextInt(enemyTypes.length)];
            try {
                Constructor<?> constructor = type.getConstructor();
                Entity entity = (Entity) constructor.newInstance();
                addEntity(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
