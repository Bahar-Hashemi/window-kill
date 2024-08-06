package bahar.window_kill.server.control.game.processors.spawners;

import bahar.window_kill.server.model.Game;
import bahar.window_kill.client.model.entities.Entity;

import java.lang.reflect.Constructor;
import java.util.Random;

public class EntitySpawner extends Spawner {
    private final Class<?>[] enemyTypes;
    public EntitySpawner(int duration, int cycleCount, Class<?>[] enemyTypes, Game game) {
        super(duration, game);
        setCycleCount(cycleCount);
        this.enemyTypes = enemyTypes;
    }
    protected void onCall() {
        Random random = new Random();
        Class<?> type = enemyTypes[random.nextInt(enemyTypes.length)];
        try {
            Constructor<?> constructor = type.getConstructor();
            Entity entity = (Entity) constructor.newInstance();
            addEntity(entity, game);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
