package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import il.cshaifasweng.OCSFMediatorExample.entities.Order;

import java.io.Serializable;

public class ExitParkingMessage implements Serializable {
    private String carNumber;
    private String leavingMinutes;
    private String leavingDate;
    private String leavingHours;
    private String parkingLot;
    private String orderId;
    private String userId;
    private boolean isInPlaceOrder;
    private boolean isCarParked;
    private double fee;
    boolean result;


    public ExitParkingMessage(String carNumber, String leavingMinutes, String leavingDate, String leavingHours
            , String pLot, String userId) {
        this.carNumber = carNumber;
        this.leavingMinutes = leavingMinutes;
        this.leavingDate = leavingDate;
        this.leavingHours = leavingHours;
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

    public void setLeavingMinutes(String leavingMinutes) {
        this.leavingMinutes = leavingMinutes;
    }

    public void setLeavingDate(String leavingDate) {
        this.leavingDate = leavingDate;
    }

    public void setLeavingHours(String leavingHours) {
        this.leavingHours = leavingHours;
    }

    public String getLeavingMinutes() {
        return this.leavingMinutes;
    }

    public String getLeavingDate() {
        return this.leavingDate;
    }

    public String getLeavingHours() {
        return this.leavingHours;
    }

    public ExitParkingMessage() {
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

    public void setIsInPlaceOrder(boolean inPlace) {
        this.isInPlaceOrder = inPlace;
    }

    public boolean isInPlaceOrder() {
        return isInPlaceOrder;
    }

    public boolean isCarParked() {
        return isCarParked;
    }

    public void setIsCarParked(boolean isParked){
        this.isCarParked = isParked;
    }
}
