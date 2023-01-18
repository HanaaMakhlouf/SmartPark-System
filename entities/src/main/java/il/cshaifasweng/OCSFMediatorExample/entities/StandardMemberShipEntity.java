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
    @Column(name = "CarNumber")
    private String CarNumber;
    @Column(name = "StartingDate")
    private String StartingDate ;
    @Column(name = "endingDate")
    private String endingDate ;
    @Column(name = "ParkingLot")
    private String ParkingLot ;
    @Column(name = "MembershipID")
    private String MembershipID ;
    @Column(name = "hours_Left")
    private double hours_Left;
    private String timeEnteredPark;
    private boolean isParked;
    private String parkedLocation;



    public StandardMemberShipEntity(int id, String carNumber, String startingDate,String parkingLot) {
        this.id = id;
        this.CarNumber = carNumber;
        this.StartingDate = startingDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTimeEnd = LocalDate.parse(startingDate,formatter).plusDays(28);
        this.endingDate = dateTimeEnd.format(formatter);
        this.ParkingLot = parkingLot;
        hours_Left = 60;
        this.timeEnteredPark="";
        isParked = false;
        parkedLocation=null;
    }

    public double getHours_Left() {
        return hours_Left;
    }

    public void setHours_Left(double hoursLeft) {
        if(hoursLeft <=0){
            this.hours_Left = 0;
        }
        else{
            this.hours_Left = hoursLeft;
        }
    }

    public String getParkedLocation() {
        return parkedLocation;
    }

    public void setParkedLocation(String parkedLocation) {
        this.parkedLocation = parkedLocation;
    }

    public String getMembershipID() {
        return MembershipID;
    }

    public void setMembershipID(String membershipID) {
        this.MembershipID = membershipID;
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
        return hours_Left;
    }

    public void setHoursLeft(double hoursLeft) {
        this.hours_Left = hoursLeft;
    }

    public String getParkingLot() {
        return ParkingLot;
    }

    public void setParkingLot(String parkingLot) {
        this.ParkingLot = parkingLot;
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
