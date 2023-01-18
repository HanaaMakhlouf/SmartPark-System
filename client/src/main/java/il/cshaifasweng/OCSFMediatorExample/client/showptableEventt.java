package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Prices;

import java.util.List;
import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLots;
import il.cshaifasweng.OCSFMediatorExample.entities.Prices;


public class showptableEventt {

    private List<Prices> plist;

    public showptableEventt(List<Prices> plist) {
        this.plist = plist;
    }

    public List<Prices> getPlist() {
        return plist;
    }

    public void setPlist(List<Prices> plist) {
        this.plist = plist;

    }


}


