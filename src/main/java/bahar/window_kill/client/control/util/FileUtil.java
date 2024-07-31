package bahar.window_kill.client.control.util;

import bahar.window_kill.client.control.util.reader.DevelopmentUtil;
import bahar.window_kill.client.control.util.reader.SettingsUtil;
import bahar.window_kill.communications.data.Development;
import bahar.window_kill.communications.data.Settings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileUtil {
    private static final String resourcesPath = "src/main/resources";
    private static final String progressPath = resourcesPath + "/log/development.txt";
    private static final String settingsPath = resourcesPath + "/log/settings.txt";
    public static void saveDevelopment(Development development) {
        try {
            FileWriter fileWriter = new FileWriter(progressPath, false);
            String output = new DevelopmentUtil().write(development);
            fileWriter.write(output);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Error in saving progress: " + e.getMessage());
        }
    }

    public static Development readDevelopment() {
        try {
            Scanner sc = new Scanner(new File(progressPath));
            Development development = (Development) (new DevelopmentUtil().read(sc));
            sc.close();
            return development;
        } catch (Exception e) {
            System.out.println("Error in reading progress: ");
            e.printStackTrace();
        }
        return null;
    }
    public static Settings readSettings() {
        try {
            Scanner sc = new Scanner(new File(settingsPath));
            Settings settings = (Settings) (new SettingsUtil().read(sc));
            sc.close();
            return settings;
        } catch (Exception e) {
            System.out.println("Error in reading progress: " + e.getMessage());
        }
        return null;
    }
    public static void saveSettings(Settings settings) {
        try {
            FileWriter fileWriter = new FileWriter(settingsPath, false);
            String output = new SettingsUtil().write(settings);
            fileWriter.write(output);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error in saving settings: " + e.getMessage());
        }
    }
}
