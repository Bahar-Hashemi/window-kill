package bahar.window_kill.communications.data;

public class TableSquad {
    private String name, owner, history;
    private int vault;
    private int PalioxisState, AdonisState, GefjonState;
    private String enemy;
    public TableSquad(String name, String owner, String enemy, String history, int vault, int PalioxisState, int AdonisState, int GefjonState) {
        this.name = name;
        this.owner = owner;
        this.enemy = enemy;
        this.history = history;
        this.vault = vault;
        this.PalioxisState = PalioxisState;
        this.AdonisState = AdonisState;
        this.GefjonState = GefjonState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public int getVault() {
        return vault;
    }

    public void setVault(int vault) {
        this.vault = vault;
    }

    public int getPalioxisState() {
        return PalioxisState;
    }

    public void setPalioxisState(int palioxisOn) {
        PalioxisState = palioxisOn;
    }

    public int getAdonisState() {
        return AdonisState;
    }

    public void setAdonisState(int adonisOn) {
        AdonisState = adonisOn;
    }

    public int getGefjonState() {
        return GefjonState;
    }

    public void setGefjonState(int gefjonOn) {
        GefjonState = gefjonOn;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }
}
