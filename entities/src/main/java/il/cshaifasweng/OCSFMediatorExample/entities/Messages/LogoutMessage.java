package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class LogoutMessage implements Serializable {
    private int id;

    public LogoutMessage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LogoutMessage(int id) {
        this.id=id;
    }
}
