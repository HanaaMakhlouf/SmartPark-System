package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
@Table (name= "complaints")
public class Complaint implements Serializable{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int complaintId;
    @Column(name = "sender_id")
    private String id;
    @Column(name = "Date")
    private Date date;
    @Column(name = "Description")
    private String description;
    @Column(name = "park_id")
    private int park_id;
    @Column(name = "Response")
    private String response = "";


    public int getPark_id() {
        return park_id;
    }

    public void setPark_id(int park_id) {
        this.park_id = park_id;
    }

    public Complaint(String id, Date date, String description,int parkId) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.park_id = parkId;
    }

    public Complaint() {

    }

    public String getId() {
        return id;
    }
    public void setId(String id) { this.id = id; }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getResponse() {
        return response;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}

