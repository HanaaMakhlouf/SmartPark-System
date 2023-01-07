package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "parkingLotEmployee")
public class ParkingLotEmployee   implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "Email")
    private String Email;
    @Column(name = "Password")
    private String Password;
    //which parkingLot he works at
    @Column(name = "PatkingLot")
    private int  parkingLot;

//    @ManyToOne
//    @JoinColumn(name = "parking_lots_id")
//    private ParkingLots parkingLots;
//
//    public ParkingLotEmployee(int i, String s, String s1, int i1, ParkingLots parkingLots) {
//    }
//
//    public ParkingLots getParkingLots() {
//        return parkingLots;
//    }
//
//    public void setParkingLots(ParkingLots parkingLots) {
//        this.parkingLots = parkingLots;
//    }

    public ParkingLotEmployee(int id, String email, String Password, int parkingLot) {
        this.id = id;
        this.Email = email;
        this.Password=Password;
        this.parkingLot = parkingLot;
    }

    public ParkingLotEmployee() {
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(int parkingLot) {
        this.parkingLot = parkingLot;
    }

}

