package bahar.window_kill.server.control.game.processors.abilities.skills;

import bahar.window_kill.server.model.Game;
import bahar.window_kill.client.model.User;
import bahar.window_kill.client.model.entities.Collectable;
import bahar.window_kill.client.model.entities.Entity;
import bahar.window_kill.client.model.entities.attackers.Bullet;
import bahar.window_kill.server.control.game.processors.abilities.AbilityType;
import bahar.window_kill.server.control.game.processors.abilities.AbilityWatch;

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
