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
import javax.persistence.CascadeType;

import javax.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "Spots")
public class Spot {
    @Id
    @Column(name = "spotid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int spotid;
    @Column(name = "width1")
    private int width;
    @Column(name = "column_num")
    private int column;
    @Column(name = "row_num")
    private int row;
    @Column(name = "available_1")
    private boolean available;
    @Column(name = "is_saved")
    private boolean saved;
    @Column(name = "Parked_Car_Number")
    private String carNum ;


    @ManyToOne
    @JoinColumn(name = "id_parking")
    private ParkingLotEntitiy parkinglot;

    public ParkingLotEntitiy getParkinglot() {
        return parkinglot;
    }

    public void setParkinglot(ParkingLotEntitiy parkinglot) {
        this.parkinglot = parkinglot;
    }

    public Spot(int i, int j, int k, boolean b, boolean b1,ParkingLotEntitiy p1) {
        this.parkinglot =p1;
        this.width = i;
        this.column = j;
        this.row = k;
        this.available=b;
        this.saved=b1;
        this.carNum = "";
    }


    public Spot() {
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public int getSpotid() {
        return spotid;
    }

    public void setSpotid(int spotid) {
        this.spotid = spotid;
    }
    public int getWidth() {
        return width;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

//    private static final int columns = 3;
//    private static final int rows = 3;
}
