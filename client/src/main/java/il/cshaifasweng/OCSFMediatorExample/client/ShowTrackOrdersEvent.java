package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetallOrdersOfClient;

import java.util.List;

public class ShowTrackOrdersEvent {
   private GetallOrdersOfClient msg;

    public GetallOrdersOfClient getMsg() {
        return msg;
    }

    public ShowTrackOrdersEvent(GetallOrdersOfClient msg) {
        this.msg = msg;
    }

    public void setMsg(GetallOrdersOfClient msg) {
        this.msg = msg;
    }
}
