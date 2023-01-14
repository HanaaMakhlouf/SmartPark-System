package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class MemberLogInMessage implements Serializable {
    String memberNumber;
    String carNumber;
    int result;

    public MemberLogInMessage(String memberNumber, String carNumber ) {
        this.memberNumber = memberNumber;
        this.carNumber = carNumber;
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

