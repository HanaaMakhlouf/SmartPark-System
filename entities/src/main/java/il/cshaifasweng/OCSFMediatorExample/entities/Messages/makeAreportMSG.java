package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class makeAreportMSG implements Serializable {
    private Integer requestid;
    private String request_type;
    private String MangerID;

    public String getMangerID() {
        return MangerID;
    }

    public void setMangerID(String mangerID) {
        MangerID = mangerID;
    }

    public makeAreportMSG(Integer requestid, String request_type,String managerid) {
        this.requestid = requestid;
        this.request_type = request_type;
        this.MangerID = managerid;
    }

    public makeAreportMSG() {
    }

    public Integer getRequestid() {
        return requestid;
    }

    public void setRequestid(Integer requestid) {
        this.requestid = requestid;
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }
}
