package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ShowAllReportrequestsMessage;

public class ShowALLreportsReqEvent {

    private ShowAllReportrequestsMessage message;
    public ShowALLreportsReqEvent(ShowAllReportrequestsMessage message) {
        this.message=message;
    }

    public ShowAllReportrequestsMessage getMessage() {
        return message;
    }

    public void setMessage(ShowAllReportrequestsMessage message) {
        this.message = message;
    }
}
