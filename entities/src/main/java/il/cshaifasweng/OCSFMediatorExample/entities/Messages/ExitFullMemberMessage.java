package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class ExitFullMemberMessage implements Serializable {
    private String carNumber;
    private String leavingMinutes ;
    private String leavingDate ;
    private String leavingHours ;
    private String userId;
    private double hoursLeft;

    boolean result;


    public ExitFullMemberMessage(String carNumber, String leavingMinutes, String leavingDate
            , String leavingHours, String userId) {
        this.carNumber = carNumber;
        this.leavingMinutes = leavingMinutes;
        this.leavingDate = leavingDate;
        this.leavingHours = leavingHours;
        this.userId = userId;
        result = false;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public ExitFullMemberMessage() {
    }

    public double getHoursLeft() {
        return hoursLeft;
    }

    public void setHoursLeft(double hoursLeft) {
        this.hoursLeft = hoursLeft;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setLeavingMinutes(String leavingMinutes) {
        this.leavingMinutes = leavingMinutes;
    }

    public void setLeavingDate(String leavingDate) {
        this.leavingDate = leavingDate;
    }

    public void setLeavingHours(String leavingHours) {
        this.leavingHours = leavingHours;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getLeavingMinutes() {
        return leavingMinutes;
    }

    public String getLeavingDate() {
        return leavingDate;
    }

    public String getLeavingHours() {
        return leavingHours;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

}
