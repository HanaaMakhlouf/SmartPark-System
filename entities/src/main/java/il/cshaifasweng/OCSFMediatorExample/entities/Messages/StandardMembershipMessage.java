package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class StandardMembershipMessage implements Serializable {
    String carNumber;
    String startDate;
    String id;
    String parkingLot;
    boolean result;
    String membershipId;

    public StandardMembershipMessage(String carNumber, String startDate, String id,String parkingLot) {
        this.carNumber = carNumber;
        this.startDate = startDate;
        this.id = id;
        this.parkingLot = parkingLot;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public String getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}

