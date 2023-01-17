package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetSpotsMessage;

public class GetSpotsToSaveEvent {
    private GetSpotsMessage msg;

    public GetSpotsToSaveEvent(GetSpotsMessage message) {
        this.msg = message;
    }
    public GetSpotsMessage getMsg() {
        return msg;
    }
    public void setMsg(GetSpotsMessage msg) {
        this.msg = msg;
    }

}
