package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class SaveSpotMessage implements Serializable {
    private int spot_id;
    private boolean ifSave;

    public SaveSpotMessage(int spot_id, boolean ifSave) {
        this.spot_id = spot_id;
        this.ifSave = ifSave;
    }

    public int getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(int spot_id) {
        this.spot_id = spot_id;
    }

    public boolean isIfSave() {
        return ifSave;
    }

    public void setIfSave(boolean ifSave) {
        this.ifSave = ifSave;
    }
}
