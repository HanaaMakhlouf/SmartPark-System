package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class SetUpMessage implements Serializable {
   private int park_num;

    public SetUpMessage(int park_num) {
        this.park_num = park_num;
    }

    public int getPark_num() {
        return park_num;
    }
}
