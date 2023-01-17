package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class logInMessage implements Serializable {
    String userId;
    String userPass;
    int result;
    private int parkingLotId = 0;

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }



    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPass() {
        return userPass;
    }

    public logInMessage(String userId, String userPass) {
        this.userId = userId;
        this.userPass = userPass;
    }
}

