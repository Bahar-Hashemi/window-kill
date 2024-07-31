package bahar.window_kill.client.control.states.processors.abilities.skills;

import bahar.window_kill.client.control.states.processors.abilities.AbilityWatch;

public class ChironWatch extends AbilityWatch {
    static long enemyHealth;
    public ChironWatch() {
        super(30, () -> {
//            long currentEnemyHealth = getEnemyHealth();
//            if (currentEnemyHealth < enemyHealth)
//                user.getEpsilon().setHP(user.getEpsilon().getHP() + ((enemyHealth - currentEnemyHealth) / 5 * 2));
//            enemyHealth = currentEnemyHealth;
        }, "Chiron", 900);
    }
    private static long getEnemyHealth() {
        long result = 0;
//        for (Entity entity: entities)
//            if (!(entity instanceof Bullet) && !(entity instanceof Collectable) && entity.getHP() < 100)
//                result += entity.getHP();
        return result;
    }
    @Override
    protected void onStart() {
        super.onStart();
        enemyHealth = getEnemyHealth();
    }
}
