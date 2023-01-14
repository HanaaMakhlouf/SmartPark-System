package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetComplaintsMessage;
import org.greenrobot.eventbus.EventBus;

public class TrackComplaintEvent {
    private GetComplaintsMessage msg;

    public GetComplaintsMessage getMsg() {
        return msg;
    }

    public void setMsg(GetComplaintsMessage msg) {
        this.msg = msg;
    }

    public TrackComplaintEvent(GetComplaintsMessage msg) {
        this.msg = msg;
    }

}
