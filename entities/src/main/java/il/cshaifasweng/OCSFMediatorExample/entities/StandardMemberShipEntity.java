package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "standardMemberships")
public class StandardMemberShipEntity implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "carNumber")
    private String carNumber;
    @Column(name = "startingDate")
    private String startingDate ;
    @Column(name = "endingDate")
    private String endingDate ;
    @Column(name = "parkingLot")
    private String parkingLot ;
    @Column(name = "membershipID")
    private String membershipID ;
    @Column(name = "hoursLeft")
    private double hoursLeft;
    private String timeEnteredPark;
    private boolean isParked;

    public StandardMemberShipEntity(int id, String carNumber, String startingDate,String parkingLot) {
        this.id = id;
        this.carNumber = carNumber;
        this.startingDate = startingDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTimeArrival = LocalDate.parse(startingDate,formatter).plusDays(28);
        this.endingDate = dateTimeArrival.format(formatter);
        this.parkingLot = parkingLot;
        hoursLeft = 60;
        this.timeEnteredPark="";
        isParked = false;
    }

    public String getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(String membershipID) {
        this.membershipID = membershipID;
    }

    public StandardMemberShipEntity() {
    }

    public String getTimeEnteredPark() {
        return timeEnteredPark;
    }

    public void setTimeEnteredPark(String timeEnteredPark) {
        this.timeEnteredPark = timeEnteredPark;
    }

    public boolean isParked() {
        return isParked;
    }

    public void setParked(boolean parked) {
        isParked = parked;
    }

    public double getHoursLeft() {
        return hoursLeft;
    }

    public void setHoursLeft(double hoursLeft) {
        this.hoursLeft = hoursLeft;
    }

    public String getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
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
