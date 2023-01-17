package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.EnterWithOrderMessage;
import javafx.event.ActionEvent;

public class EnterWithOrderEvent extends ActionEvent{
    EnterWithOrderMessage message;

    public EnterWithOrderEvent(EnterWithOrderMessage message) {
        this.message = message;
    }

    public void setMessage(EnterWithOrderMessage message) {
        this.message = message;
    }

    public EnterWithOrderMessage getMessage() {
        return message;
    }

    boolean result;

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

}
