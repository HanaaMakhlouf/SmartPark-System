package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;

import java.io.Serializable;
import java.util.List;

public class GetallOrdersOfClient implements Serializable {
    private  List<InAdvanceOrderEntity> Lst;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GetallOrdersOfClient() {
    }

    public GetallOrdersOfClient(List<InAdvanceOrderEntity> lst, String id) {
        Lst = lst;
        this.id = id;
    }

    public List<InAdvanceOrderEntity> getLst() {
        return Lst;
    }

    public void setLst(List<InAdvanceOrderEntity> lst) {
        this.Lst = lst;
    }
}
