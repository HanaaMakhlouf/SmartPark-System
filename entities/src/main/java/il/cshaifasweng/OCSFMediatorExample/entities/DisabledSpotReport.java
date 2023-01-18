package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name ="disabledspotsreport")
public class DisabledSpotReport implements Serializable {
    @Id
    @Column(name = "id_of_report")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "parkname")
    private String Park;

    @Column(name = "disablement")
    private int disablement =0;

    @Column(name = "fromdate")
    LocalDateTime from;

    @Column(name = "untillDate")
    LocalDateTime until;

    public DisabledSpotReport() {
    }

    public DisabledSpotReport(String park, int disablesnum, LocalDateTime from, LocalDateTime until) {
        Park = park;
        this.disablement = disablesnum;
        this.from = from;
        this.until = until;
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

    public int getDisablement() {
        return disablement;
    }

    public void setDisablement(int disablement) {
        this.disablement = disablement;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getUntil() {
        return until;
    }

    public void setUntil(LocalDateTime until) {
        this.until = until;
    }
}
