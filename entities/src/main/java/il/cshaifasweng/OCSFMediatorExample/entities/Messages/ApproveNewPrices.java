package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class ApproveNewPrices implements Serializable {
    int ReqIDtoApprove;
    boolean approve;

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public int getReqIDtoApprove() {
        return ReqIDtoApprove;
    }

    public void setReqIDtoApprove(int reqIDtoApprove) {
        ReqIDtoApprove = reqIDtoApprove;
    }

    public ApproveNewPrices() {
    }

    public ApproveNewPrices(int reqIDtoApprove) {
        ReqIDtoApprove = reqIDtoApprove;
    }
}
