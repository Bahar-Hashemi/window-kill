package bahar.window_kill.control.util.reader;

import bahar.window_kill.control.fazes.processors.abilities.shop.*;
import bahar.window_kill.control.fazes.processors.abilities.skills.*;
import bahar.window_kill.model.Watch;

import java.util.Scanner;

public class WatchUtil extends ModelUtil {
    @Override
    public Object read(Scanner sc) {
        Watch watch = watchBuilder(sc.next());
        watch.setCycleCount(sc.nextInt());
        return watch;
    }
    private Watch watchBuilder(String s) {
        try {
            Class<?> clazz = Class.forName(s);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            return (Watch) instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public String write(Object object) {
        Watch watch = (Watch) object;
        StringBuilder sb = new StringBuilder();
        sb.append(object.getClass().getName()).append("\n");
        sb.append(watch.getCycleCount()).append("\n");
        return sb.toString();
    }
}
