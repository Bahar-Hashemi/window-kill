package bahar.window_kill.control.util.reader;

import bahar.window_kill.model.boards.controller.ControlStrategy;
import bahar.window_kill.model.data.Settings;

import java.util.Scanner;

public class SettingsUtil extends ModelUtil {
    @Override
    public Object read(Scanner sc) {
        int speed = sc.nextInt();
        int difficulty = sc.nextInt();
        int volume = sc.nextInt();
        ControlStrategy controlStrategy = getControlStrategy(sc.next());
        return new Settings(speed, difficulty, volume, controlStrategy);
    }
    private ControlStrategy getControlStrategy(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return (ControlStrategy) clazz.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public String write(Object object) {
        Settings settings = (Settings) object;
        return settings.getSpeed() + "\n" + settings.getDifficulty() + "\n" + settings.getVolume() + "\n" + settings.getControlStrategy().getClass().getName();
    }
}
