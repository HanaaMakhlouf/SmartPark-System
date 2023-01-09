package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class InAdvanceOrderMessage implements Serializable {
    private String carNumber;
    private String leavingMinutes ;
    private String leavingDate ;
    private String leavingHours ;
    private String arrivingMinutes ;
    private String arrivingDate ;
    private String arrivingHours ;
    private String parkingLot;
    private double fee;
    boolean result;




    public InAdvanceOrderMessage(String carNumber, String leavingMinutes, String leavingDate
            , String leavingHours, String arrivingMinutes, String arrivingDate, String arrivingHours, String pLot) {
        this.carNumber = carNumber;
        this.leavingMinutes = leavingMinutes;
        this.leavingDate = leavingDate;
        this.leavingHours = leavingHours;
        this.arrivingMinutes = arrivingMinutes;
        this.arrivingDate = arrivingDate;
        this.arrivingHours = arrivingHours;
        this.parkingLot = pLot;
        fee = 0;
        result = false;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getFee() {
        return fee;
    }

    public void setArrivingMinutes(String arrivingMinutes) {
        this.arrivingMinutes = arrivingMinutes;
    }

    public void setArrivingDate(String arrivingDate) {
        this.arrivingDate = arrivingDate;
    }

    public void setArrivingHours(String arrivingHours) {
        this.arrivingHours = arrivingHours;
    }

    public String getArrivingMinutes() {
        return arrivingMinutes;
    }

    public String getArrivingDate() {
        return arrivingDate;
    }

    public String getArrivingHours() {
        return arrivingHours;
    }

    public InAdvanceOrderMessage() {
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getParkingLot() {
        return parkingLot;
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
