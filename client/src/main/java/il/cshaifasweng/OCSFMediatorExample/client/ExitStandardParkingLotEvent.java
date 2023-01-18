package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ExitFullMemberMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ExitStandardMemberMessage;
import javafx.event.ActionEvent;


public class ExitStandardParkingLotEvent extends ActionEvent{
    ExitStandardMemberMessage message;

    public ExitStandardParkingLotEvent(ExitStandardMemberMessage message) {
        this.message = message;
    }

    public ExitStandardMemberMessage getMessage() {
        return message;
    }

    public void setMessage(ExitStandardMemberMessage message) {
        this.message = message;
    }
}
