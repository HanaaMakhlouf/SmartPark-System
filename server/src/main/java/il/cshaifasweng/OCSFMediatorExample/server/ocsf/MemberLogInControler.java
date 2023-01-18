package il.cshaifasweng.OCSFMediatorExample.server.ocsf;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.fxml.FXML;
import org.greenrobot.eventbus.EventBus;

import java.time.LocalDate;
import java.util.List;

public class MemberLogInControler {

    String memberNumber;
    String carNumber;
    String dateTimeEnd;
    int memberId;
    boolean isFullMembership = true;
    double fee72;

    public double getFee72() {
        return fee72;
    }

    public void setFee72(double fee72) {
        this.fee72 = fee72;
    }

    public MemberLogInControler(String memberNumber, String carNumber) {
        this.memberNumber = memberNumber;
        this.carNumber = carNumber;
    }

    public MemberLogInControler() {
    }

    public int validateMemberCredentials(List<StandardMemberShipEntity> standardMemberShipEntityList,
                                         List<FullMemberShipEntity> fullMemberShipEntityList, List<Subscriber> sublst) {

        for (Subscriber s : sublst)
            if (s.getId() == Integer.parseInt(memberNumber))
                return 0;


        for (StandardMemberShipEntity standardMemberShip : standardMemberShipEntityList) {

            if (memberNumber.equals(standardMemberShip.getMembershipID())
                    && carNumber.equals(standardMemberShip.getCarNumber())) {
                this.dateTimeEnd = standardMemberShip.getEndingDate();
                this.memberId = standardMemberShip.getId();
                this.isFullMembership = false;
                return 1;
            }
        }

        for (FullMemberShipEntity fullMemberShip : fullMemberShipEntityList) {

            if (memberNumber.equals(fullMemberShip.getMembershipID()) && carNumber.equals(fullMemberShip.getCarNumber())) {
                this.dateTimeEnd = fullMemberShip.getEndingDate();
                this.memberId = fullMemberShip.getId();
                this.isFullMembership = true;
                return 2;
            }
        }
        return -1;
    }


    public String getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(String dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
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

