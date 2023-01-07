package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;

@Entity
@Table(name = "InAdvanceOrders")
public class InAdvanceOrderEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "CarNumber")
    private String carNumber;
    @Column(name = "LeavingMinutes")
    private String leavingMinutes ;
    @Column(name = "LeavingDate")
    private String leavingDate ;
    @Column(name = "LeavingHours")
    private String leavingHours ;
    @Column(name = "arrivalMinutes")
    String arrivalMinutes;
    @Column(name = "arrivalDate")
    String arrivalDate;
    @Column(name = "arrivalHours")
    String arrivalHours;
    @Column(name = "parking_lot_name")
    String parkingLotName ;

    public InAdvanceOrderEntity(String carNumber, String leavingMinutes, String leavingDate, String leavingHours, String arrivalMinutes,
                                String arrivalDate, String arrivalHours, String parkingLotName) {
        this.carNumber = carNumber;
        this.leavingMinutes = leavingMinutes;
        this.leavingDate = leavingDate;
        this.leavingHours = leavingHours;
        this.arrivalMinutes = arrivalMinutes;
        this.arrivalDate = arrivalDate;
        this.arrivalHours = arrivalHours;
        this.parkingLotName = parkingLotName;
    }

    public InAdvanceOrderEntity() {

    }
}
