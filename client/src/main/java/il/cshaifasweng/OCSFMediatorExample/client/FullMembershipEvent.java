package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.FullMembershipMessage;


public class FullMembershipEvent {
    FullMembershipMessage message;

    public FullMembershipMessage getMessage() {
        return message;
    }

    public void setMessage(FullMembershipMessage message) {
        this.message = message;
    }

    public FullMembershipEvent(FullMembershipMessage message) {
        this.message = message;
    }
}
