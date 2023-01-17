package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class DisableSpotMessage implements Serializable {
    private int spot_id;
    private boolean ifDis;

    public DisableSpotMessage(int spot_id, boolean ifDis) {
        this.spot_id = spot_id;
        this.ifDis = ifDis;
    }
    public boolean isIfDis() {
        return ifDis;
    }
    public void setIfDis(boolean ifDis) {
        this.ifDis = ifDis;
    }
    public int getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(int spot_id) {
        this.spot_id = spot_id;
    }

}
