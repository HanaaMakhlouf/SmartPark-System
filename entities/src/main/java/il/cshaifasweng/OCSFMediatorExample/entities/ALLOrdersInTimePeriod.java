package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "dataForOrdersReport")
public class ALLOrdersInTimePeriod implements Serializable {
    @Id
    @Column(name = "id_of_report")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Report_id")
    private int repid;


    @Column(name = "OrderID")
    private String orderID;


    @Column(name = "parking_lot_name")
    String parkingLotName ;

    @Column(name = "Ordered_At")
    LocalDateTime date;

    @Column(name = "Type_order")
    String type;

    public ALLOrdersInTimePeriod(int repid,String orderID, String parkingLotName, LocalDateTime date,String type) {
        this.repid = repid;
        this.orderID = orderID;
        this.parkingLotName = parkingLotName;
        this.date = date;
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRepid() {
        return repid;
    }

    public void setRepid(int repid) {
        this.repid = repid;
    }

    public ALLOrdersInTimePeriod() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
