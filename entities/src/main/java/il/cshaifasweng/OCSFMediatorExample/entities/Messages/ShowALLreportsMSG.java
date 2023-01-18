package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import il.cshaifasweng.OCSFMediatorExample.entities.ComplaintsReport;
import il.cshaifasweng.OCSFMediatorExample.entities.DisabledSpotReport;
import il.cshaifasweng.OCSFMediatorExample.entities.OrdersReport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShowALLreportsMSG implements Serializable {
    private List<OrdersReport> olst = new ArrayList<>();
    private List<ComplaintsReport> clst=new ArrayList<>();
    private List<DisabledSpotReport> dlst=new ArrayList<>();


    public ShowALLreportsMSG() {
    }

    public List<OrdersReport> getOlst() {
        return olst;
    }

    public void setOlst(List<OrdersReport> olst) {
        this.olst = olst;
    }

    public List<ComplaintsReport> getClst() {
        return clst;
    }

    public void setClst(List<ComplaintsReport> clst) {
        this.clst = clst;
    }

    public List<DisabledSpotReport> getDlst() {
        return dlst;
    }

    public void setDlst(List<DisabledSpotReport> dlst) {
        this.dlst = dlst;
    }
}
