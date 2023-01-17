package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;
import java.time.LocalDate;

public class MemberLogInMessage implements Serializable {
    String memberNumber;
    String carNumber;
    String dateTimeEnd ;
    int result;
    String memberPark;
    int memberId ;
    boolean isFullMembership;
    double fee72 ;

    public double getFee72() {
        return fee72;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public boolean isFullMembership() {
        return isFullMembership;
    }

    public void setFullMembership(boolean fullMembership) {
        isFullMembership = fullMembership;
    }

    public String getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(String dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public MemberLogInMessage(String memberNumber, String carNumber ) {
        this.memberNumber = memberNumber;
        this.carNumber = carNumber;

    }

    public String getMemberPark() {
        return memberPark;
    }

    public void setMemberPark(String memberPark) {
        this.memberPark = memberPark;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setFee72(double fee72) {
    }
}

