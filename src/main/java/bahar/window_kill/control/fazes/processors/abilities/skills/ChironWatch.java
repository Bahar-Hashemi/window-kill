package bahar.window_kill.control.fazes.processors.abilities.skills;

import bahar.window_kill.control.fazes.processors.abilities.AbilityWatch;
import bahar.window_kill.model.entities.Collectable;
import bahar.window_kill.model.entities.Entity;
import bahar.window_kill.model.entities.attackers.Bullet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static bahar.window_kill.control.Deck.*;

public class ChironWatch extends AbilityWatch {
    static long enemyHealth;
    public ChironWatch() {
        super(30, () -> {
            long currentEnemyHealth = getEnemyHealth();
            if (currentEnemyHealth < enemyHealth)
                user.getEpsilon().setHP(user.getEpsilon().getHP() + ((enemyHealth - currentEnemyHealth) / 5 * 2));
            enemyHealth = currentEnemyHealth;
        }, "Chiron", 900);
    }
    private static long getEnemyHealth() {
        long result = 0;
        for (Entity entity: entities)
            if (!(entity instanceof Bullet) && !(entity instanceof Collectable) && entity.getHP() < 100)
                result += entity.getHP();
        return result;
    }
    @Override
    protected void onStart() {
        super.onStart();
        enemyHealth = getEnemyHealth();
    }
}
