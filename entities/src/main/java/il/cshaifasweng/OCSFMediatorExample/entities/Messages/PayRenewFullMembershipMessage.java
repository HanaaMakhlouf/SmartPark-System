package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class PayRenewFullMembershipMessage implements Serializable {
    private String nameOnCard;
    private String cardNumber;
    private String cvv;
    private String year;
    private String month;
    private String carNumber;
    double fee;
    int membershipId;
    boolean isFullMember ;
     String leavingDate ;
     String memberNumber ;
    boolean result ;

    public boolean isFullMember() {
        return isFullMember;
    }

    public void setFullMember(boolean fullMember) {
        isFullMember = fullMember;
    }

    public PayRenewFullMembershipMessage(String nameOnCard, String cardNumber , String cvv , String year,
                                         String month ,  int membershipId, String carNumber,
                                         String leavingDate , String memberNumber, boolean isFullMember ,double fee ) {
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.year=year;
        this.month =month ;
        this.carNumber =carNumber ;
        this.membershipId = membershipId;
        this.fee= fee ;
        this.memberNumber =memberNumber ;
        this.leavingDate =leavingDate ;
        this.isFullMember = isFullMember ;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
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

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public String getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(String leavingDate) {
        this.leavingDate = leavingDate;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }


}
