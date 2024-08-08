package bahar.window_kill.communications.model.entities.additional.data;

public class BlackOrbLaserData {
    String terminal1Id, terminal2Id;
    public BlackOrbLaserData(String terminal1Id, String terminal2Id) {
        this.terminal1Id = terminal1Id;
        this.terminal2Id = terminal2Id;
    }

    public String getTerminal1Id() {
        return terminal1Id;
    }

    public void setTerminal1Id(String terminal1Id) {
        this.terminal1Id = terminal1Id;
    }

    public String getTerminal2Id() {
        return terminal2Id;
    }

    public void setTerminal2Id(String terminal2Id) {
        this.terminal2Id = terminal2Id;
    }
}
