package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ExitFullMemberMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ExitParkingMessage;
import javafx.event.ActionEvent;


public class ExitFullParkingLotEvent extends ActionEvent{
    ExitFullMemberMessage message;

    public ExitFullParkingLotEvent(ExitFullMemberMessage message) {
        this.message = message;
    }

    public ExitFullMemberMessage getMessage() {
        return message;
    }

    public void setMessage(ExitFullMemberMessage message) {
        this.message = message;
    }
}
