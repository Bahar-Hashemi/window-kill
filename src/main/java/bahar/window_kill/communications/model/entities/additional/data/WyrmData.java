package bahar.window_kill.communications.model.entities.additional.data;

public class WyrmData {
    double gunDirectionX;
    double gunDirectionY;
    public WyrmData(double gunDirectionX, double gunDirectionY) {
        this.gunDirectionX = gunDirectionX;
        this.gunDirectionY = gunDirectionY;
    }

    public double getGunDirectionX() {
        return gunDirectionX;
    }
    public double getGunDirectionY() {
        return gunDirectionY;
    }
}
