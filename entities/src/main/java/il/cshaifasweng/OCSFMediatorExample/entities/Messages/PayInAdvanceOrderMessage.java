package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class PayInAdvanceOrderMessage implements Serializable {
    private String nameOnCard;
    private String cardNumber;
    private String cvv;
    private String year;
    private String month;

private String userid;
    private String carNumber;
    private String leavingDate ;
    private String leavingHours ;
    private String leavingMinutes ;
    private String arrivingDate ;
    private String arrivingHours ;
    private String arrivingMinutes ;
    private String parkingLot;
    private String orderId;
    boolean result ;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public PayInAdvanceOrderMessage(String nameOnCard, String userid, String cardNumber, String carNumber, String leavingDate
            , String leavingHours, String leavingMinutes, String arrivingDate, String arrivingHours
            , String arrivingMinutes, String parkingLot, String orderId , String cvv , String year,
                                    String month) {
        this.nameOnCard = nameOnCard;
        this.userid=userid;
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
        this.cvv = cvv;
        this.year=year;
        this.month =month ;
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

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

}
