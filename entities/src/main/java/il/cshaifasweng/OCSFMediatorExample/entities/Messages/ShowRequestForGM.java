package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import il.cshaifasweng.OCSFMediatorExample.entities.ChangePricesRequest;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class ShowRequestForGM implements Serializable {

   private List<ChangePricesRequest> list;

    public ShowRequestForGM() {
    }

    public List<ChangePricesRequest> getList() {
        return list;
    }

    public void setList(List<ChangePricesRequest> list) {
        this.list = list;
    }
}
