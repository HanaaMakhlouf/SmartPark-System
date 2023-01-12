package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class OrderToDeleteMsg implements Serializable {
    private String id;
    private Double balance;
    public OrderToDeleteMsg(String s) {
        this.id= s;
    }

    public String getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setId(String id) {
        this.id = id;
    }
}
