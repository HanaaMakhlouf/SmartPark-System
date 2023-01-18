package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import il.cshaifasweng.OCSFMediatorExample.entities.ALLOrdersInTimePeriod;
import il.cshaifasweng.OCSFMediatorExample.entities.DisabledDataReport;
import il.cshaifasweng.OCSFMediatorExample.entities.OrdersReport;

import java.io.Serializable;
import java.util.List;

public class ShowORDERSrepBYrepID implements Serializable {
    private int repid;
    private List<ALLOrdersInTimePeriod> lst;
    public ShowORDERSrepBYrepID(Integer repid) {
   this.repid = repid;
    }

    public ShowORDERSrepBYrepID() {
    }

    public int getRepid() {
        return repid;
    }

    public void setRepid(int repid) {
        this.repid = repid;
    }

    public List<ALLOrdersInTimePeriod> getLst() {
        return lst;
    }

    public void setLst(List<ALLOrdersInTimePeriod> lst) {
        this.lst = lst;
    }
}
