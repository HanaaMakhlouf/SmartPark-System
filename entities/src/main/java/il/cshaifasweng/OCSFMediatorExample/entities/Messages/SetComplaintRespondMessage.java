package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class SetComplaintRespondMessage implements Serializable {
    private  int complaint_id;
    private String res;
    private int refundAmount;

    public SetComplaintRespondMessage(Integer complaint_id, String res, Integer refundAmount) {
        this.complaint_id = complaint_id;
        this.res = res;
        this.refundAmount = refundAmount;
    }

    public int getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(int complaint_id) {
        this.complaint_id = complaint_id;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public int getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(int refundAmount) {
        this.refundAmount = refundAmount;
    }


}
