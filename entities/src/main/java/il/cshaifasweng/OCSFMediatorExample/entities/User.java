package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "Email")
    private String Email;
    @Column(name = "Password")
    private String Password;
//    @Column(name = "complaints")
//    private ArrayList<Complaint> complaintList;
//    @Column(name = "orders")
//    private ArrayList<Order> ordersList;

    public User(int id, String email,String Password) {
        this.id = id;
        this.Email = email;
        this.Password=Password;
    }

    public User() {

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

}

