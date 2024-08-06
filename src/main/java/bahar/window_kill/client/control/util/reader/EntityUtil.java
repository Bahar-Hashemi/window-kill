package bahar.window_kill.client.control.util.reader;

import bahar.window_kill.client.model.entities.Entity;

import java.util.Scanner;

public class EntityUtil extends ModelUtil {
    @Override
    public Entity read(Scanner sc) {
        Entity entity = EntityMaker(sc.next());
        entity.setX(sc.nextDouble());
        entity.setY(sc.nextDouble());
        entity.setHP(sc.nextDouble());
        return entity;
    }
    private Entity EntityMaker(String classType) {
        try {
            Class<?> clazz = Class.forName(classType);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            return (Entity) instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public String write(Object object) {
//        if (object instanceof Bullet bullet)
//            return writeBullet(bullet);
//        if (object instanceof Collectable)
//            return writeCollectable(object);
        StringBuilder sb = new StringBuilder();
        sb.append(object.getClass().getName()).append("\n");
        sb.append(((Entity) object).getLayoutX()).append(" ");
        sb.append(((Entity) object).getLayoutY()).append("\n");
        sb.append(((Entity) object).getHP()).append("\n");
        return sb.toString();
    }
//    private String writeBullet(Bullet bullet) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(bullet.getClass().getName()).append("\n");
//    }
}
