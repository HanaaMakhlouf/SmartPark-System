package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ChangePricesRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;

import java.util.List;

public class ShowChangePricesRequestEvent {

    List<ChangePricesRequest> lst;

    public ShowChangePricesRequestEvent(List<ChangePricesRequest> lst) {
        this.lst = lst;
    }

    public List<ChangePricesRequest> getLst() {
        return lst;
    }

    public void setLst(List<ChangePricesRequest> lst) {
        this.lst = lst;
    }
}
