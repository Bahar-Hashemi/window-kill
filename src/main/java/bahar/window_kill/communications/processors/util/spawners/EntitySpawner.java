package bahar.window_kill.communications.processors.util.spawners;

import bahar.window_kill.communications.util.GameUtil;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.entities.Entity;

import java.lang.reflect.Constructor;
import java.util.Random;

public class EntitySpawner extends Spawner {
    private final Class<?>[] enemyTypes;
    public EntitySpawner(boolean isViewable, int duration, int cycleCount, Class<?>[] enemyTypes, Game game) {
        super(isViewable, duration, game);
        setCycleCount(cycleCount);
        this.enemyTypes = enemyTypes;
    }
    protected void onCall() {
        Random random = new Random();
        Class<?> type = enemyTypes[random.nextInt(enemyTypes.length)];
        try {
            Constructor<?> constructor = type.getConstructor(boolean.class, String.class);
            Entity entity = (Entity) constructor.newInstance(isViewable, GameUtil.generateID());
            addEntity(entity, game);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
