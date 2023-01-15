package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class EnterWithOrderMessage implements Serializable {
    private String carNumber;
    private String arrivingMinutes ;
    private String arrivingDate ;
    private String arrivingHours ;
    private String parkingLot;
    private String orderId;
    private String userId;

    private double fee;
    boolean result;


    public EnterWithOrderMessage(String carNumber, String arrivingMinutes, String arrivingDate, String arrivingHours
            , String pLot, String userId) {
        this.carNumber = carNumber;
        this.arrivingMinutes = arrivingMinutes;
        this.arrivingDate = arrivingDate;
        this.arrivingHours = arrivingHours;
        this.parkingLot = pLot;
        this.userId = userId;
        fee = 0;
        result = false;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public EnterWithOrderMessage() {
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

    public String getCarNumber() {
        return carNumber;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
