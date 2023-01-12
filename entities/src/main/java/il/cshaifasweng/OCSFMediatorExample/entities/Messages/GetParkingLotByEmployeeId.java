package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class GetParkingLotByEmployeeId implements Serializable {
    private int id;
    private int park_num;

    public GetParkingLotByEmployeeId(int id) {
        this.id = id;
    }

    public GetParkingLotByEmployeeId() {

    }
    public int getId(){
        return this.id;
    }
    public int getPark_num(){
        return this.park_num;
    }
    public void setPark_num(int num) {
         this.park_num = num;
    }
}

