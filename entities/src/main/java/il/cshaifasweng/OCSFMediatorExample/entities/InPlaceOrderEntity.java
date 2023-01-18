package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "InPlaceOrders")
public class InPlaceOrderEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name ="Userid")
    private String UserID;
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
    @Column(name = "Email")
    String email ;
    @Column(name = "Ordered_At")
    LocalDateTime date;



    public InPlaceOrderEntity(String carNumber, String clientId, String leavingMinutes, String leavingDate, String leavingHours, String arrivalMinutes,
                              String arrivalDate, String arrivalHours, String parkingLotName,String email) {
        this.carNumber = carNumber;
        this.UserID = clientId;
        this.leavingMinutes = leavingMinutes;
        this.leavingDate = leavingDate;
        this.leavingHours = leavingHours;
        this.arrivalMinutes = arrivalMinutes;
        this.arrivalDate = arrivalDate;
        this.arrivalHours = arrivalHours;
        this.parkingLotName = parkingLotName;
        this.email = email;
        this.date= LocalDateTime.now();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
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

    public InPlaceOrderEntity() {

    }
}
