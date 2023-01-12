package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "fullMemberships")
public class FullMemberShipEntity implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "CarNumber")
    private String carNumber;
    @Column(name = "StartingDate")
    private String startingDate ;
    @Column(name = "endingDate")
    private String endingDate ;
    @Column(name = "MembershipID")
    private String membershipID ;

    public FullMemberShipEntity(int id, String carNumber, String startingDate) {
        this.id = id;
        this.carNumber = carNumber;
        this.startingDate = startingDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTimeArrival = LocalDate.parse(startingDate,formatter).plusDays(28);
        this.endingDate = dateTimeArrival.toString();
    }

    public FullMemberShipEntity() {
    }

    public String getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(String membershipID) {
        this.membershipID = membershipID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }
}
