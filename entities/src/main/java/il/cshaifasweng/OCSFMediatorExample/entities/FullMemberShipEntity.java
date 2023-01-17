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
    private String CarNumber;
    @Column(name = "StartingDate")
    private String StartingDate ;
    @Column(name = "endingDate")
    private String endingDate ;
    @Column(name = "MembershipID")
    private String MembershipID ;
    @Column(name = "Hours_Left")
    private double Hours_Left;
    private String timeEnteredPark;
    private boolean isParked;



    public FullMemberShipEntity(int id, String carNumber, String startingDate) {
        this.id = id;
        this.CarNumber = carNumber;
        this.StartingDate = startingDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTimeArrival = LocalDate.parse(startingDate,formatter).plusDays(28);
        this.endingDate = dateTimeArrival.format(formatter);
        this.Hours_Left = 72;
        this.timeEnteredPark="";
        isParked = false;
    }

    public FullMemberShipEntity() {
    }

    public boolean isParked() {
        return isParked;
    }

    public void setParked(boolean parked) {
        isParked = parked;
    }

    public double getHours_Left() {
        return Hours_Left;
    }

    public void setHours_Left(double hours_Left) {
        Hours_Left = hours_Left;
    }

    public String getTimeEnteredPark() {
        return timeEnteredPark;
    }

    public void setTimeEnteredPark(String timeEnteredPark) {
        this.timeEnteredPark = timeEnteredPark;
    }

    public double getHoursLeft() {
        return Hours_Left;
    }

    public void setHoursLeft(double hoursLeft) {
        this.Hours_Left = hoursLeft;
    }

    public String getMembershipID() {
        return MembershipID;
    }

    public void setMembershipID(String membershipID) {
        this.MembershipID = membershipID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNumber() {
        return CarNumber;
    }

    public void setCarNumber(String carNumber) {
        this.CarNumber = carNumber;
    }

    public String getStartingDate() {
        return StartingDate;
    }

    public void setStartingDate(String startingDate) {
        this.StartingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }
}
