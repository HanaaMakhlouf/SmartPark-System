package il.cshaifasweng.OCSFMediatorExample.client.ocsf;
import javafx.event.ActionEvent;

public class logInEvent extends ActionEvent{

    int result;

    public logInEvent(int result) {
        this.result = result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }
}
