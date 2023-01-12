package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "Parkings")
public class ParkingLotEntitiy {
    @Id
    @Column(name = "id")
    int id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "parkinglot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Spot> spots=new ArrayList<>();

    public ParkingLotEntitiy(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ParkingLotEntitiy() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }
}
