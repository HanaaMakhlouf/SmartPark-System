package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ShowALLreportsMSG;

public class ShowAllReportsEvent {

    private ShowALLreportsMSG msg;
    public ShowAllReportsEvent(ShowALLreportsMSG message) {
        this.msg = message;
    }

    public ShowALLreportsMSG getMsg() {
        return msg;
    }

    public void setMsg(ShowALLreportsMSG msg) {
        this.msg = msg;
    }
}
