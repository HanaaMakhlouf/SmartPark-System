package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.PayRenewFullMembershipMessage;

public class PayRenewFullMembershipEvent {
    PayRenewFullMembershipMessage message;

    public PayRenewFullMembershipMessage getMessage() {
        return message;
    }

    public void setMessage(PayRenewFullMembershipMessage message) {
        this.message = message;
    }

    public PayRenewFullMembershipEvent(PayRenewFullMembershipMessage message) {
        this.message = message;
    }
}
