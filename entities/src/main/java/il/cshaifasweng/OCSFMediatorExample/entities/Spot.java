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
    @Column(name = "width_num")
    private int width_num;
    @Column(name = "height_num")
    private int height_num;
    @Column(name = "depth_num")
    private int depth_num;
    @Column(name = "available_1")
    private boolean available;
    @Column(name = "is_saved")
    private boolean saved;
    @Column(name = "disabled")
    private boolean disabled = false;
    @Column(name = "Parked_Car_Number")
    private String carNum ;
    @Column(name = "Car_Leaving_Time")
    private String  leaving ;

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    public String getLeaving() {
        return leaving;
    }

    public void setLeaving(String leaving) {
        this.leaving = leaving;
    }

    @ManyToOne
    @JoinColumn(name = "id_parking")
    private ParkingLotEntitiy parkinglot;

    public ParkingLotEntitiy getParkinglot() {
        return parkinglot;
    }

    public void setParkinglot(ParkingLotEntitiy parkinglot) {
        this.parkinglot = parkinglot;
    }

    public Spot(int depth, int width, int height, boolean available, boolean saved,ParkingLotEntitiy p1) {
        this.parkinglot =p1;
        this.width_num = width;
        this.height_num = height;
        this.depth_num = depth;
        this.available=available;
        this.saved=saved;
        this.carNum = "";
        this.leaving="";
    }
    public Spot() {
    }


    public Spot(Spot spot, ParkingLotEntitiy p1){
        this.parkinglot = p1;
        this.width_num = spot.getWidth_num();
        this.depth_num = spot.getDepth_num();
        this.height_num = spot.getHeight_num();
        this.available = true;
        this.saved = false;
        this.carNum = spot.getCarNum();

    }

    public int getWidth_num() {
        return width_num;
    }

    public void setWidth_num(int width_num) {
        this.width_num = width_num;
    }

    public int getHeight_num() {
        return height_num;
    }

    public void setHeight_num(int height_num) {
        this.height_num = height_num;
    }

    public int getDepth_num() {
        return depth_num;
    }

    public void setDepth_num(int depth_num) {
        this.depth_num = depth_num;
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

    public boolean isAvailable() {
        return available;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public void resetSpot(){
        this.available=true;
        this.saved=false;
        this.carNum ="";
        this.leaving="";
    }

//    private static final int columns = 3;
//    private static final int rows = 3;
}
