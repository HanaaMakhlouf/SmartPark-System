package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import il.cshaifasweng.OCSFMediatorExample.entities.ChangePricesRequest;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class ShowRequestForManager implements Serializable {

    private List<ChangePricesRequest> list;
    private String managerid;

    public String getManagerid() {
        return managerid;
    }

    public void setManagerid(String managerid) {
        this.managerid = managerid;
    }

    public ShowRequestForManager() {
    }

    public List<ChangePricesRequest> getList() {
        return list;
    }

    public void setList(List<ChangePricesRequest> list) {
        this.list = list;
    }
}
