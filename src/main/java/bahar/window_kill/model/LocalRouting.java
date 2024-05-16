package bahar.window_kill.model;

import bahar.window_kill.model.entities.*;
import bahar.window_kill.model.entities.collectables.Collectable;

public class LocalRouting {
    static public void apply(Entity entity, Epsilon epsilon) {
        double x = epsilon.getLayoutX() - entity.getLayoutX();
        double y = epsilon.getLayoutY() - entity.getLayoutY();
        if (entity instanceof Collectable) {
            x = epsilon.getLayoutX() - entity.getView().getLayoutBounds().getCenterX();
            y = epsilon.getLayoutY() - entity.getView().getLayoutBounds().getCenterY();
        }
        double chord = Math.sqrt(x * x + y * y);
        entity.setDeltaX(x / chord);
        entity.setDeltaY(y / chord);
    }
}