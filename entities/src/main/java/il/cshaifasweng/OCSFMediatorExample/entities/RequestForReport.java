package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "ReportsRequests")
public class RequestForReport implements Serializable {
    @Id
    @Column(name = "id_of_request")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fromdate")
    private LocalDateTime from;
    @Column(name = "untildate")
    private LocalDateTime until;
    @Column(name = "report_type")
    private String Report_type;
    @Column(name = "requestTime")
    private LocalDateTime time;
    public RequestForReport( LocalDateTime from, LocalDateTime until, String report_type) {

        this.from = from;
        this.until = until;
        Report_type = report_type;
        this.time = LocalDateTime.now();

    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getReport_type() {
        return Report_type;
    }

    public void setReport_type(String report_type) {
        Report_type = report_type;
    }

    public RequestForReport() {

    }

}
