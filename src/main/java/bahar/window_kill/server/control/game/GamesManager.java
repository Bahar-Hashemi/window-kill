package bahar.window_kill.server.control.game;

import bahar.window_kill.communications.processors.GameProcessor;

import java.util.ArrayList;

public class GamesManager extends Thread {
    private static final ArrayList<GameProcessor> gameProcessors = new ArrayList<>();
    public GamesManager() {
        super();
    }
    @Override
    public void run() {
        try {
            while (true) {
                super.run();
                for (int i = gameProcessors.size() - 1; i >= 0; i--)
                    gameProcessors.get(i).run();
                this.sleep(30);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void addProcessor(GameProcessor gameProcessor) {
        synchronized (gameProcessors) {
            gameProcessors.add(gameProcessor);
        }
    }
}
