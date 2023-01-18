package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ShowORDERSrepBYrepID;

public class ShowORDEvent {
    private ShowORDERSrepBYrepID message;
    public ShowORDEvent(ShowORDERSrepBYrepID message) {
        this.message=message;
    }

    public ShowORDERSrepBYrepID getMessage() {
        return message;
    }

    public void setMessage(ShowORDERSrepBYrepID message) {
        this.message = message;
    }

    public ShowORDEvent() {
    }
}
