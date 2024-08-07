package bahar.window_kill.communications.processors.util.abilities.skills;

import bahar.window_kill.communications.processors.util.abilities.AbilityType;
import bahar.window_kill.communications.processors.util.abilities.AbilityWatch;
import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.entities.Collectable;
import bahar.window_kill.communications.model.entities.Entity;
import bahar.window_kill.communications.model.entities.attackers.Bullet;

public class ChironWatch extends AbilityWatch {
    private double enemyHealth;
    public ChironWatch(Game game, User user) {
        super(game, user,30, AbilityType.CHIRON, 900);
    }
    private double getEnemyHealth() {
        double result = 0;
        for (Entity entity: game.entities)
            if (!(entity instanceof Bullet) && !(entity instanceof Collectable) && entity.getHP() < 100)
                result += entity.getHP();
        return result;
    }

    @Override
    protected void onCall() {
        super.onCall();
            double currentEnemyHealth = getEnemyHealth();
            if (currentEnemyHealth < enemyHealth)
                user.getEpsilon().setHP(user.getEpsilon().getHP() + ((enemyHealth - currentEnemyHealth) / 5 * 3));
            enemyHealth = currentEnemyHealth;
    }

    @Override
    protected void onStart() {
        super.onStart();
        enemyHealth = getEnemyHealth();
    }
}
