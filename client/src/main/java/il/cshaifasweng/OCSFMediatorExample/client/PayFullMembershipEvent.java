package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.PayFullMembershipMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.PayInAdvanceOrderMessage;

public class PayFullMembershipEvent {
    PayFullMembershipMessage message;

    public PayFullMembershipMessage getMessage() {
        return message;
    }

    public void setMessage(PayFullMembershipMessage message) {
        this.message = message;
    }

    public PayFullMembershipEvent(PayFullMembershipMessage message) {
        this.message = message;
    }
}
