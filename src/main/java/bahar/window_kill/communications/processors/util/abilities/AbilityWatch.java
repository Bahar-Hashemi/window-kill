package bahar.window_kill.communications.processors.util.abilities;

import bahar.window_kill.communications.model.Game;
import bahar.window_kill.communications.model.User;
import bahar.window_kill.communications.model.Watch;
import bahar.window_kill.communications.processors.util.abilities.shop.*;
import bahar.window_kill.communications.processors.util.abilities.skills.*;


public class AbilityWatch extends Watch {
    final int price;
    final AbilityType type;
    transient protected final Game game;
    transient protected final User user;
    public AbilityWatch(Game game, User user, int duration, AbilityType type, int price) {
        super(duration);
        this.type = type;
        this.price = price;
        this.game = game;
        this.user = user;
    }
    protected void onEnd() {
        user.abilities.remove(this);
    }
    public AbilityType getType() {
        return type;
    }
    public int getPrice() {
        return price;
    }
    public static AbilityWatch getAbilityByType(Game game, User user, AbilityType type) {
        return switch (type) {
            case MELAMPUS -> new MelampusWatch(game, user);
            case CHIRON -> new ChironWatch(game, user);
            case CERBERUS -> new CerberusWatch(game, user);
            case ATHENA -> new AthenaWatch(game, user);
            case ASTRAPE -> new AstrapeWatch(game, user);
            case ARES -> new AresWatch(game, user);
            case ACESO -> new AcesoWatch(game, user);
            case THUNDER -> new ThunderWatch(game, user);
            case SLUMBER -> new SlumberWatch(game, user);
            case HEAL -> new HealWatch(game, user);
            case EMPOWER -> new EmpowerWatch(game, user);
            case DISMAY -> new DismayWatch(game, user);
            case BANISH -> new BanishWatch(game, user);
        };
    }
}
