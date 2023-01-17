package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.EnterFullMemberMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.EnterWithOutOrderMessage;
import javafx.event.ActionEvent;

public class EnterFullMemberEvent extends ActionEvent{
    EnterFullMemberMessage message;

    public EnterFullMemberEvent(EnterFullMemberMessage message) {
        this.message = message;
    }

    public void setMessage(EnterFullMemberMessage message) {
        this.message = message;
    }

    public EnterFullMemberMessage getMessage() {
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
