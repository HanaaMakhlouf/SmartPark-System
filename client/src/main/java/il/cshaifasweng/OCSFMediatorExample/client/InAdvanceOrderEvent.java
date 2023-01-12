package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.InAdvanceOrderMessage;
import javafx.event.ActionEvent;

public class InAdvanceOrderEvent extends ActionEvent{
    InAdvanceOrderMessage message;

    public InAdvanceOrderEvent(InAdvanceOrderMessage message) {
        this.message = message;
    }

    public void setMessage(InAdvanceOrderMessage message) {
        this.message = message;
    }

    public InAdvanceOrderMessage getMessage() {
        return message;
    }

    double fee;
    boolean result;

    public InAdvanceOrderEvent(double fee, boolean result) {
        this.fee = fee;
        this.result = result;
    }
    public InAdvanceOrderEvent(boolean result) {
        this.fee = 0;
        this.result = result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
