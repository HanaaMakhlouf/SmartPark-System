package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class PayInAdvanceOrderMessage implements Serializable {
    String nameOnCard;
    String cardNumber;
    private String carNumber;
    private String leavingDate ;
    private String leavingHours ;
    private String leavingMinutes ;
    private String arrivingDate ;
    private String arrivingHours ;
    private String arrivingMinutes ;
    private String parkingLot;
    private String orderId;

    public PayInAdvanceOrderMessage(String nameOnCard, String cardNumber, String carNumber, String leavingDate
            , String leavingHours, String leavingMinutes, String arrivingDate, String arrivingHours
            , String arrivingMinutes, String parkingLot, String orderId) {
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.carNumber = carNumber;
        this.leavingDate = leavingDate;
        this.leavingHours = leavingHours;
        this.leavingMinutes = leavingMinutes;
        this.arrivingDate = arrivingDate;
        this.arrivingHours = arrivingHours;
        this.arrivingMinutes = arrivingMinutes;
        this.parkingLot = parkingLot;
        this.orderId = orderId;
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

    public void setArrivingMinutes(String arrivingMinutes) {
        this.arrivingMinutes = arrivingMinutes;
    }

    public void setArrivingDate(String arrivingDate) {
        this.arrivingDate = arrivingDate;
    }

    public void setArrivingHours(String arrivingHours) {
        this.arrivingHours = arrivingHours;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getArrivingMinutes() {
        return arrivingMinutes;
    }

    public String getArrivingDate() {
        return arrivingDate;
    }

    public String getArrivingHours() {
        return arrivingHours;
    }

    public String getParkingLot() {
        return parkingLot;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
