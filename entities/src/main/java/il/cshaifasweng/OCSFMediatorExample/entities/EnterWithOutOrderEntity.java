package il.cshaifasweng.OCSFMediatorExample.entities;

public class EnterWithOutOrderEntity extends Order{

    public EnterWithOutOrderEntity(String carNumber, String email, String leavingMinutes,
                                   String leavingDate, String leavingHours){
        super(carNumber,leavingMinutes ,leavingDate, leavingHours);

    }


}
