package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import il.cshaifasweng.OCSFMediatorExample.entities.FullMemberShipEntity;

import java.io.Serializable;

public class PayFullMembershipMessage implements Serializable {
    private String nameOnCard;
    private String cardNumber;
    private String cvv;
    private String year;
    private String month;
    private String carNumber;
    double fee;
    String membershipId;

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    FullMemberShipEntity fullMemberShipEntity;


    boolean result ;


    public PayFullMembershipMessage(String nameOnCard, String cardNumber, FullMemberShipEntity fullMemberShipEntity, String cvv , String year,
                                    String month) {
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.year=year;
        this.month =month ;
        this.fullMemberShipEntity = fullMemberShipEntity;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public FullMemberShipEntity getFullMemberShipEntity() {
        return fullMemberShipEntity;
    }

    public void setFullMemberShipEntity(FullMemberShipEntity fullMemberShipEntity) {
        this.fullMemberShipEntity = fullMemberShipEntity;
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
