package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;

@Entity
@Table(name = "InAdvanceOrders")
public class InAdvanceOrderEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "OrderID")
    private String orderID;
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

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setLeavingMinutes(String leavingMinutes) {
        this.leavingMinutes = leavingMinutes;
    }

    public void setLeavingDate(String leavingDate) {
        this.leavingDate = leavingDate;
    }

    public void setLeavingHours(String leavingHours) {
        this.leavingHours = leavingHours;
    }

    public void setArrivalMinutes(String arrivalMinutes) {
        this.arrivalMinutes = arrivalMinutes;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setArrivalHours(String arrivalHours) {
        this.arrivalHours = arrivalHours;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public int getId() {
        return id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getLeavingMinutes() {
        return leavingMinutes;
    }

    public String getLeavingDate() {
        return leavingDate;
    }

    public String getLeavingHours() {
        return leavingHours;
    }

    public String getArrivalMinutes() {
        return arrivalMinutes;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getArrivalHours() {
        return arrivalHours;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public InAdvanceOrderEntity() {

    }
}
