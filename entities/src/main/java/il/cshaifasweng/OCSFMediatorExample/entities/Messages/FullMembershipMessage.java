package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import il.cshaifasweng.OCSFMediatorExample.entities.FullMemberShipEntity;

import java.io.Serializable;

public class FullMembershipMessage implements Serializable {
    String carNumber;
    String startDate;
    String id;
    boolean result;
    String membershipId;
    FullMemberShipEntity fullMemberShipEntity;
    double fee;

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public FullMembershipMessage(String carNumber, String startDate, String id) {
        this.carNumber = carNumber;
        this.startDate = startDate;
        this.id = id;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public FullMemberShipEntity getFullMemberShipEntity() {
        return fullMemberShipEntity;
    }

    public void setFullMemberShipEntity(FullMemberShipEntity fullMemberShipEntity) {
        this.fullMemberShipEntity = fullMemberShipEntity;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
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

