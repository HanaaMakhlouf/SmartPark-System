package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.PayFullMembershipMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.PayStandardMembershipMessage;

public class PayStandardMembershipEvent {
    PayStandardMembershipMessage message;

    public PayStandardMembershipMessage getMessage() {
        return message;
    }

    public void setMessage(PayStandardMembershipMessage message) {
        this.message = message;
    }

    public PayStandardMembershipEvent(PayStandardMembershipMessage message) {
        this.message = message;
    }
}
