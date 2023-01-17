package il.cshaifasweng.OCSFMediatorExample.server.ocsf;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.fxml.FXML;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MemberLogInControler {

    String memberNumber;
    String carNumber;

    public MemberLogInControler(String memberNumber, String carNumber) {
        this.memberNumber = memberNumber;
        this.carNumber = carNumber;
    }

    public MemberLogInControler() {
    }

    public int validateMemberCredentials(List<StandardMemberShipEntity> standardMemberShipEntityList,
                                       List<FullMemberShipEntity> fullMemberShipEntityList,List < Subscriber> sublst ) {

        for(Subscriber s : sublst)
            if(s.getId() == Integer.parseInt(memberNumber))
                return 0;


        for (StandardMemberShipEntity standardMemberShip : standardMemberShipEntityList) {

            if (memberNumber.equals(standardMemberShip.getMembershipID())
                    && carNumber.equals(standardMemberShip.getCarNumber())) {

                return 1;
            }
        }

        for (FullMemberShipEntity fullMemberShip : fullMemberShipEntityList) {

            if (memberNumber.equals(fullMemberShip.getMembershipID()) && carNumber.equals(fullMemberShip.getCarNumber())) {


                return 2;
            }
        }
        return -1;
    }


}


