package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetComplaintsMessage;

public class ShowComplaintsEvent {
    private GetComplaintsMessage msg;

    public GetComplaintsMessage getMsg() {
        return msg;
    }

    public void setMsg(GetComplaintsMessage msg) {
        this.msg = msg;
    }

    public ShowComplaintsEvent(GetComplaintsMessage msg) {
        this.msg = msg;
    }

}
