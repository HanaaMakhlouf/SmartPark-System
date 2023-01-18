package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ShowDSBLrepBYrepID;

public class ShowDSBLEvent {
    private ShowDSBLrepBYrepID message;
    public ShowDSBLEvent(ShowDSBLrepBYrepID message) {
        this.message =message;
    }

    public ShowDSBLEvent() {
    }

    public ShowDSBLrepBYrepID getMessage() {
        return message;
    }

    public void setMessage(ShowDSBLrepBYrepID message) {
        this.message = message;
    }
}
