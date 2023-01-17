package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.PayInAdvanceOrderMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.PayInPlaceOrderMessage;

import javafx.event.ActionEvent;


public class PayInPlaceOrderEvent extends ActionEvent {
    PayInPlaceOrderMessage message;

    public PayInPlaceOrderEvent(PayInPlaceOrderMessage message) {
        this.message = message;
    }

    public PayInPlaceOrderMessage getMessage() {
        return message;
    }

    public void setMessage(PayInPlaceOrderMessage message) {
        this.message = message;
    }
}
