package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import il.cshaifasweng.OCSFMediatorExample.entities.AbsSpot;
import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLotEntitiy;
import il.cshaifasweng.OCSFMediatorExample.entities.Spot;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class GetSpotsMessage implements Serializable {

    private List<AbsSpot> list=new ArrayList<>();
    private int fromWhom = 0;

    public int getFromWhom() {
        return fromWhom;
    }

    public void setFromWhom(int fromWhom) {
        this.fromWhom = fromWhom;
    }



    public GetSpotsMessage(List<AbsSpot> list) {
        this.list = list;
    }
    public GetSpotsMessage() {
    }

    public List<AbsSpot> getList() {
        return list;
    }

    public void setList(List<AbsSpot> list) {
        this.list = list;
    }


}
