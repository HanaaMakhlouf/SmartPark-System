package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ComplaintsReport")
public class ComplaintsReport {
    @Id
    @Column(name = "id_of_report")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "parkname")
    private String Park;

    @Column(name = "num_of_complaints")
    private int complaintsNum;

    @Column(name = "num_of_unresolved_complaints")
    private int unresolved;

    @Column(name = "num_of_resolved_complaints")
    private int resolved;

    @Column(name = "fromdate")
    LocalDateTime from;

    @Column(name = "untillDate")
    LocalDateTime until;

    public ComplaintsReport(String park, int complaintsNum, int resolved, LocalDateTime from, LocalDateTime until) {
        Park = park;
        this.complaintsNum = complaintsNum;
        this.resolved = resolved;
        this.from = from;
        this.until = until;
        this.unresolved =complaintsNum-resolved;
    }

    public ComplaintsReport() {
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

    public int getComplaintsNum() {
        return complaintsNum;
    }

    public void setComplaintsNum(int complaintsNum) {
        this.complaintsNum = complaintsNum;
    }

    public int getUnresolved() {
        return unresolved;
    }

    public void setUnresolved(int unresolved) {
        this.unresolved = unresolved;
    }

    public int getResolved() {
        return resolved;
    }

    public void setResolved(int resolved) {
        this.resolved = resolved;
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