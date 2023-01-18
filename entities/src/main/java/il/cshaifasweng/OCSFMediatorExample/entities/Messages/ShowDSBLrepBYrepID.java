package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import il.cshaifasweng.OCSFMediatorExample.entities.DisabledDataReport;

import java.io.Serializable;
import java.util.List;

public class ShowDSBLrepBYrepID implements Serializable {

    private int repid;
    private List<DisabledDataReport> lst;
    public ShowDSBLrepBYrepID(Integer repid) {
        this.repid=repid;

    }

    public ShowDSBLrepBYrepID() {
    }

    public int getRepid() {
        return repid;
    }

    public void setRepid(int repid) {
        this.repid = repid;
    }

    public List<DisabledDataReport> getLst() {
        return lst;
    }

    public void setLst(List<DisabledDataReport> lst) {
        this.lst = lst;
    }
}


