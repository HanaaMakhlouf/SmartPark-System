package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "disabledHistory")
public class DisabledSpotHistory {

    @Id
    @Column(name = "id_of_report")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "parkname")
    private String Park;

    @Column(name = "spotnum")
    private int spot_number;


    @Column(name = "timeofDisabling")
    LocalDateTime time_of_disabling;


    public DisabledSpotHistory(String park, int spot_number) {
        Park = park;
        this.spot_number = spot_number;
        this.time_of_disabling = LocalDateTime.now();
    }

    public DisabledSpotHistory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPark() {
        return Park;
    }

    public void setPark(String park) {
        Park = park;
    }

    public int getSpot_number() {
        return spot_number;
    }

    public void setSpot_number(int spot_number) {
        this.spot_number = spot_number;
    }

    public LocalDateTime getTime_of_disabling() {
        return time_of_disabling;
    }

    public void setTime_of_disabling(LocalDateTime time_of_disabling) {
        this.time_of_disabling = time_of_disabling;
    }
}
