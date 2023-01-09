package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "Managers")
public class Manager  implements Serializable {
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



    public Manager(int id, String email, String Password, int parkingLot) {
        this.id = id;
        this.Email = email;
        this.Password=Password;
        this.parkingLot = parkingLot;
    }

    public Manager() {
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

