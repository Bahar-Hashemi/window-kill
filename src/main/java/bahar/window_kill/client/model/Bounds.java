package bahar.window_kill.client.model;

public class Bounds {
    private double minimumX, minimumY, maximumX, maximumY;

    public Bounds(double minimumX, double minimumY, double maximumX, double maximumY) {
        this.minimumX = minimumX;
        this.minimumY = minimumY;
        this.maximumX = maximumX;
        this.maximumY = maximumY;
    }

    public double getMinimumX() {
        return minimumX;
    }

    public double getMinimumY() {
        return minimumY;
    }

    public double getMaximumX() {
        return maximumX;
    }

    public double getMaximumY() {
        return maximumY;
    }

    public void setMinimumX(double minimumX) {
        this.minimumX = minimumX;
    }

    public void setMinimumY(double minimumY) {
        this.minimumY = minimumY;
    }

    public void setMaximumX(double maximumX) {
        this.maximumX = maximumX;
    }

    public void setMaximumY(double maximumY) {
        this.maximumY = maximumY;
    }
}
