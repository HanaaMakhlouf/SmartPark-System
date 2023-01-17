package il.cshaifasweng.OCSFMediatorExample.client.ocsf;
import javafx.event.ActionEvent;

public class logInEvent extends ActionEvent{

    int result;
    private int park_id;

    public int getPark_id() {
        return park_id;
    }

    public void setPark_id(int park_id) {
        this.park_id = park_id;
    }

    public logInEvent(int result, int parkingLotId) {
        this.result = result;
        this.park_id = parkingLotId;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }
}
