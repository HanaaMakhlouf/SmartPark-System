package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import il.cshaifasweng.OCSFMediatorExample.entities.FullMemberShipEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.StandardMemberShipEntity;

import java.io.Serializable;

public class PayStandardMembershipMessage implements Serializable {
    private String nameOnCard;
    private String cardNumber;
    private String cvv;
    private String year;
    private String month;
    private String carNumber;
    double fee;
    String membershipId;

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    StandardMemberShipEntity standardMemberShipEntity;


    boolean result ;


    public PayStandardMembershipMessage(String nameOnCard, String cardNumber, StandardMemberShipEntity standardMemberShipEntity, String cvv , String year,
                                        String month) {
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.year=year;
        this.month =month ;
        this.standardMemberShipEntity = standardMemberShipEntity;
    }

    public StandardMemberShipEntity getStandardMemberShipEntity() {
        return standardMemberShipEntity;
    }

    public void setStandardMemberShipEntity(StandardMemberShipEntity standardMemberShipEntity) {
        this.standardMemberShipEntity = standardMemberShipEntity;
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
