package bahar.window_kill.client.control.states.processors.spawners;

import bahar.window_kill.client.model.Deck;
import bahar.window_kill.client.model.entities.Entity;

import java.lang.reflect.Constructor;
import java.util.Random;

public class EntitySpawner extends Spawner {
    boolean spawned = false;
    public EntitySpawner(int duration, int cycleCount, Class<?>[] enemyTypes, Deck deck) {
        super(duration, createRunnable(enemyTypes, deck), deck);
        setCycleCount(cycleCount);
    }
    private static Runnable createRunnable(Class<?>[] enemyTypes, Deck deck) {
        return () -> {
            Random random = new Random();
            Class<?> type = enemyTypes[random.nextInt(enemyTypes.length)];
            try {
                Constructor<?> constructor = type.getConstructor();
                Entity entity = (Entity) constructor.newInstance();
                addEntity(entity, deck);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
