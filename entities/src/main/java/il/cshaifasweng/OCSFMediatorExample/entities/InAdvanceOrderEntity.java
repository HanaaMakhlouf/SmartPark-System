package il.cshaifasweng.OCSFMediatorExample.entities;

public class InAdvanceOrderEntity extends Order{
    String arrivalMinutes , arrivalDate, arrivalHours;
    String parkingLotName ;

    public InAdvanceOrderEntity(String carNumber, String email, String leavingMinutes, String leavingDate, String leavingHours, String arrivalMinutes,
                                String arrivalDate, String arrivalHours, String parkingLotName) {
        super(carNumber, email, leavingMinutes, leavingDate, leavingHours);
        this.arrivalMinutes = arrivalMinutes;
        this.arrivalDate = arrivalDate;
        this.arrivalHours = arrivalHours;
        this.parkingLotName = parkingLotName;
    }
}
