package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.PayInAdvanceOrderMessage;

public class PayInAdvanceOrderEvent {
    PayInAdvanceOrderMessage message;

    public PayInAdvanceOrderEvent(PayInAdvanceOrderMessage message) {
        this.message = message;
    }

    public PayInAdvanceOrderMessage getMessage() {
        return message;
    }

    public void setMessage(PayInAdvanceOrderMessage message) {
        this.message = message;
    }
}
