package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "GeneralManager")
public class GeneralManager   implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "Email")
    private String Email;
    @Column(name = "Password")
    private String Password;


    public GeneralManager(int id, String email, String Password) {
        this.id = id;
        this.Email = email;
        this.Password = Password;
    }

    public GeneralManager() {
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
