package bahar.window_kill.communications.messages.server;

public class GeneralAnswer extends ServerAnswer {
    private boolean accept;
    private String message;
    public GeneralAnswer(boolean accept, String message) {
        super(ServerAnswerType.GENERAL);
        this.accept = accept;
        this.message = message;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
