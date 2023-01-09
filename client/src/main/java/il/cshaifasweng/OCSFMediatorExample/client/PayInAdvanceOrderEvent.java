package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.PayInAdvanceOrderMessage;

public class PayInAdvanceOrderEvent {
    PayInAdvanceOrderMessage message;

    public PayInAdvanceOrderEvent(PayInAdvanceOrderMessage message) {
        this.message = message;
    }
}
