package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;

import java.io.Serializable;
import java.util.List;

public class GetComplaintsMessage implements Serializable {
    private List<Complaint> Lst;
   private String park_id;
    // if 1 we get data for Customer Service employee
    // if 2 we get data for user to track his complaints
   private int getForWhom;

    public int getGetForWhom() {
        return getForWhom;
    }

    public void setGetForWhom(int getForWhom) {
        this.getForWhom = getForWhom;
    }


    public GetComplaintsMessage(List<Complaint> lst, String park_id) {
        Lst = lst;
        this.park_id = park_id;
    }
    public GetComplaintsMessage(List<Complaint> lst) {
        Lst = lst;
    }

    public List<Complaint> getLst() {
        return Lst;
    }

    public void setLst(List<Complaint> lst) {
        Lst = lst;
    }

    public String getPark_id() {
        return park_id;
    }

    public void setPark_id(String park_id) {
        this.park_id = park_id;
    }
}
