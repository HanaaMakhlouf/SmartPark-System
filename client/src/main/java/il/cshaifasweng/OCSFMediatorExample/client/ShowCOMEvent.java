package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ShowCOMPrepBYrepID;

public class ShowCOMEvent {
    private ShowCOMPrepBYrepID message;
    public ShowCOMEvent(ShowCOMPrepBYrepID message) {
        this.message=message;
    }

    public ShowCOMEvent() {
    }

    public ShowCOMPrepBYrepID getMessage() {
        return message;
    }

    public void setMessage(ShowCOMPrepBYrepID message) {
        this.message = message;
    }
}
