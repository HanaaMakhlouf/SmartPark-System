package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.StandardMembershipMessage;


public class StandardMembershipEvent {
    StandardMembershipMessage message;

    public StandardMembershipMessage getMessage() {
        return message;
    }

    public void setMessage(StandardMembershipMessage message) {
        this.message = message;
    }

    public StandardMembershipEvent(StandardMembershipMessage message) {
        this.message = message;
    }
}
