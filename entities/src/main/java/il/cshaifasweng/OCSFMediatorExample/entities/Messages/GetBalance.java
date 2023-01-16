package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class GetBalance implements Serializable {
    private String id;
    private Double userbalance;

    public Double getUserbalance() {
        return userbalance;
    }

    public void setUserbalance(Double userbalance) {
        this.userbalance = userbalance;
    }

    public GetBalance(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public GetBalance() {

    }

    public void setId(String id) {
        this.id = id;
    }
}
