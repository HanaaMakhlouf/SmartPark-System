package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

public class SendFailedMessage {

    int id;

    public SendFailedMessage(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
