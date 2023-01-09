package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "subscribers")
public class Subscriber implements Serializable {

    @Id
    @Column(name = "ids")
    int id;

    public Subscriber(int id) {
        this.id = id;
    }

    public Subscriber() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
