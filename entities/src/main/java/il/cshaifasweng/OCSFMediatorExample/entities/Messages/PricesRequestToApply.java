package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class PricesRequestToApply implements Serializable {
   private int approvedRequest;

    public int getApprovedRequest() {
        return approvedRequest;
    }

    public void setApprovedRequest(int approvedRequest) {
        this.approvedRequest = approvedRequest;
    }

    public PricesRequestToApply() {
    }

    public PricesRequestToApply(Integer approvedRequest) {
this.approvedRequest = approvedRequest;
    }
}
