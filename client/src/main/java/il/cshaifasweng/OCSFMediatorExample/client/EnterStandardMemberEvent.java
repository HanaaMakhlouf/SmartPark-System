package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.EnterFullMemberMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.EnterStandardMemberMessage;
import javafx.event.ActionEvent;

public class EnterStandardMemberEvent extends ActionEvent{
    EnterStandardMemberMessage message;

    public EnterStandardMemberEvent(EnterStandardMemberMessage message) {
        this.message = message;
    }

    public void setMessage(EnterStandardMemberMessage message) {
        this.message = message;
    }

    public EnterStandardMemberMessage getMessage() {
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
