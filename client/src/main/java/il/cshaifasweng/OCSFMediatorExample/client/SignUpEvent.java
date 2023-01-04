package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.event.ActionEvent;
import javafx.event.Event;

import java.util.List;

public class SignUpEvent extends ActionEvent{

    boolean result;

    public SignUpEvent(boolean result) {
        this.result = result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }
}
