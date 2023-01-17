package il.cshaifasweng.OCSFMediatorExample.server.validation;

import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.InPlaceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Spot;
import java.util.List;

public class ExitParkingLotValidator {
    String carNumber;
    String parkingLot;
    String leavingHours, leavingDate, leavingMinutes;
    List<Spot> spots;
    int parkId;

    public ExitParkingLotValidator(String carNumber, String parkingLot, String leavingHours
            , String leavingDate, String leavingMinutes,List<Spot> spots,int parkId) {
        this.carNumber = carNumber;
        this.parkingLot = parkingLot;
        this.leavingHours = leavingHours;
        this.leavingDate = leavingDate;
        this.leavingMinutes = leavingMinutes;
        this.spots = spots;
        this.parkId = parkId;
    }

    public boolean validateOrder() {
        if (leavingDate == null || leavingHours == null || leavingMinutes == null || carNumber == null
                || parkingLot == null) {
            return false;
        }
        for(Spot spot : spots){
            if(!spot.isAvailable() && spot.getCarNum().equals(this.carNumber)){
                return true;
            }
        }
        return false;
    }
}
