package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Prices;

import java.util.List;

public class showptableEventTwo {
    private List<Prices> plist;

    public showptableEventTwo(List<Prices> plist) {
        this.plist = plist;
    }

    public List<Prices> getPlist() {
        return plist;
    }

    public void setPlist(List<Prices> plist) {
        this.plist = plist;
    }

}
