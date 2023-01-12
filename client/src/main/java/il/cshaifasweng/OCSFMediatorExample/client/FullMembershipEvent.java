package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.FullMembershipMessage;


import javafx.event.ActionEvent;

public class FullMembershipEvent extends ActionEvent {
    FullMembershipMessage message;

    public FullMembershipEvent(FullMembershipMessage message) {
        this.message = message;
    }

    public void setMessage(FullMembershipMessage message) {
        this.message = message;
    }

    public FullMembershipMessage getMessage() {
        return message;
    }

}

