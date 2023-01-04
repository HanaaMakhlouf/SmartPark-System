package il.cshaifasweng.OCSFMediatorExample.client.ocsf;
import javafx.event.ActionEvent;

public class logInEvent extends ActionEvent{

    boolean result;

    public logInEvent(boolean result) {
        this.result = result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }
}
