package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.EnterWithOrderMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.EnterWithOutOrderMessage;
import javafx.event.ActionEvent;

public class EnterWithOutOrderEvent extends ActionEvent{
    EnterWithOutOrderMessage message;

    public EnterWithOutOrderEvent(EnterWithOutOrderMessage message) {
        this.message = message;
    }

    public void setMessage(EnterWithOutOrderMessage message) {
        this.message = message;
    }

    public EnterWithOutOrderMessage getMessage() {
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
