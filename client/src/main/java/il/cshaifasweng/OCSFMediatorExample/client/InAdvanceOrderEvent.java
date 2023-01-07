package il.cshaifasweng.OCSFMediatorExample.client;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;

public class InAdvanceOrderEvent extends ActionEvent{
    double fee;
    boolean result;

    public InAdvanceOrderEvent(double fee, boolean result) {
        this.fee = fee;
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
