package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "OrdersReport")
public class OrdersReport {
    @Id
    @Column(name = "id_of_report")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "parkname")
    private String Park;

    @Column(name = "numOfinplace")
    private int inplace;

    @Column(name = "numOfinAdvance")
    private int inadvance;

    @Column(name = "fromdate")
    LocalDateTime from;

    @Column(name = "untillDate")
    LocalDateTime until;

    public OrdersReport(String park, int inplace, int inadvance,LocalDateTime from,LocalDateTime until) {
        Park = park;
        this.inplace = inplace;
        this.inadvance = inadvance;
        this.from=from;
        this.until=until;
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

    public OrdersReport() {
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

    public int getInplace() {
        return inplace;
    }

    public void setInplace(int inplace) {
        this.inplace = inplace;
    }

    public int getInadvance() {
        return inadvance;
    }

    public void setInadvance(int inadvance) {
        this.inadvance = inadvance;
    }
}
