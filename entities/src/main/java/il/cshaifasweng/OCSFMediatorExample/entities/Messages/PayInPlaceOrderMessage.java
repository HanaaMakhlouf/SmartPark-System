package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class PayInPlaceOrderMessage implements Serializable {
    private String nameOnCard;
    private String cardNumber;
    private String cvv;
    private String year;
    private String month;
    private int parkId;

    private String userid;
    private String carNumber;

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    private String leavingMinutes ;
    private String parkingLot;
    private String orderId;
    boolean result ;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public PayInPlaceOrderMessage(String nameOnCard, String userid, String cardNumber, String carNumber, String parkingLot , String cvv , String year,
                                  String month) {
        this.nameOnCard = nameOnCard;
        this.userid=userid;
        this.cardNumber = cardNumber;
        this.carNumber = carNumber;
        this.parkingLot = parkingLot;
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

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

}
