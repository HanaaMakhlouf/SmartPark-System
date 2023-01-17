package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ExitParkingMessage;
import javafx.event.ActionEvent;


public class ExitParkingLotEvent extends ActionEvent{
    ExitParkingMessage message;

    public ExitParkingLotEvent(ExitParkingMessage message) {
        this.message = message;
    }

    public ExitParkingMessage getMessage() {
        return message;
    }

    public void setMessage(ExitParkingMessage message) {
        this.message = message;
    }
}
