package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "ComplaintsDataForReports")
public class ComplaintsDataForReport implements Serializable {


    @Id
    @Column(name = "id_of_report")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Report_id")
    private int repid;

    @Column(name = "Complaint_type")
    String type;

    @Column(name = "parking_lot_name")
    String parkingLotName ;

    @Column(name = "complained_at")
    LocalDateTime date;


    public ComplaintsDataForReport(int repid, String type, String parkingLotName, LocalDateTime date) {
        this.repid = repid;
        this.type = type;
        this.parkingLotName = parkingLotName;
        this.date = date;
    }

    public ComplaintsDataForReport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRepid() {
        return repid;
    }

    public void setRepid(int repid) {
        this.repid = repid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
