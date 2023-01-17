package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class MemberLogInMessage implements Serializable {
    String memberNumber;
    String carNumber;
    int result;
    String memberPark;

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
}

