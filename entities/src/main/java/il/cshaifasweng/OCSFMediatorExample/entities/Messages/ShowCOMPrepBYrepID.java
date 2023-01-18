package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import il.cshaifasweng.OCSFMediatorExample.entities.ComplaintsDataForReport;

import java.io.Serializable;
import java.util.List;

public class ShowCOMPrepBYrepID implements Serializable {
    private int repid;
    private List<ComplaintsDataForReport> lst;

    public ShowCOMPrepBYrepID(Integer repid) {
        this.repid = repid;
    }

    public ShowCOMPrepBYrepID() {
    }

    public List<ComplaintsDataForReport> getLst() {
        return lst;
    }

    public void setLst(List<ComplaintsDataForReport> lst) {
        this.lst = lst;
    }

    public int getRepid() {
        return repid;
    }

    public void setRepid(int repid) {
        this.repid = repid;
    }
}
