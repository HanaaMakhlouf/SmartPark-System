package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Subscriber;

import java.util.ArrayList;

public class showSubsForAdminEvent {
    private ArrayList<Subscriber> subs;

    public ArrayList<Subscriber> getSubs() {
        return subs;
    }

    public void setSubs(ArrayList<Subscriber> subs) {
        this.subs = subs;
    }

    public showSubsForAdminEvent(ArrayList<Subscriber> subs) {
        this.subs = subs;
    }
}
