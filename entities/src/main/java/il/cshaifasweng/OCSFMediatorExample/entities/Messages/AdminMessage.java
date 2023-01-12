package il.cshaifasweng.OCSFMediatorExample.entities.Messages;
import il.cshaifasweng.OCSFMediatorExample.entities.Subscriber;

import java.io.Serializable;
import java.util.ArrayList;

public class AdminMessage implements Serializable {
     private ArrayList<Subscriber> lst;

    public ArrayList<Subscriber> getLst() {
        return lst;
    }

    public void setLst(ArrayList<Subscriber> lst) {
        this.lst = lst;
    }

    public AdminMessage(ArrayList<Subscriber> lst) {
        this.lst = lst;
    }
}
