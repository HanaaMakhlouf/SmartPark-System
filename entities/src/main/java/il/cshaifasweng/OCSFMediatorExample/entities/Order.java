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
@Table(name = "Orders")
public class Order {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "Car Number")
    private String carNumber;
    @Column(name = "Leaving Minutes")
    private String leavingMinutes ;
    @Column(name = "Leaving Date")
    private String leavingDate ;
    @Column(name = "Leaving Hours")
    private String leavingHours ;

    public Order(String carNumber, String leavingMinutes, String leavingDate, String leavingHours) {
        this.carNumber = carNumber;
        this.leavingMinutes = leavingMinutes;
        this.leavingDate = leavingDate;
        this.leavingHours = leavingHours;
    }

    public Order() {

    }
}
