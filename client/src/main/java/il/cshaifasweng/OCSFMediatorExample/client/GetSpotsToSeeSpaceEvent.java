package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetSpotsMessage;
public class GetSpotsToSeeSpaceEvent {
    private GetSpotsMessage msg;

    public GetSpotsToSeeSpaceEvent(GetSpotsMessage message) {
        this.msg = message;
    }
    public GetSpotsMessage getMsg() {
        return msg;
    }
    public void setMsg(GetSpotsMessage msg) {
        this.msg = msg;
    }
}
