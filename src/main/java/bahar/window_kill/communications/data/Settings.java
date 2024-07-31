package bahar.window_kill.communications.data;

import bahar.window_kill.client.model.boards.controller.ControlStrategy;


public class Settings {
    private int speed, difficulty, volume;
    private ControlStrategy controlStrategy;
    public Settings(int speed, int difficulty, int volume, ControlStrategy controlStrategy) {
        this.speed = speed;
        this.difficulty = difficulty;
        this.volume = volume;
        this.controlStrategy = controlStrategy;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public ControlStrategy getControlStrategy() {
        return controlStrategy;
    }

    public void setControlStrategy(ControlStrategy controlStrategy) {
        this.controlStrategy = controlStrategy;
    }
}
