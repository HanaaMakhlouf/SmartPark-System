package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class SendComplaintMsg implements Serializable {
    private String complaint;
    private int park_id;
    private String sender_id;
    private LocalDateTime currDate;

    public SendComplaintMsg(String complaint, int park_id, String id, LocalDateTime currentDate) {
        this.complaint = complaint;
        this.park_id = park_id;
        this.sender_id = id;
        this.currDate = currentDate;
    }

    public LocalDateTime getCurrDate() {
        return currDate;
    }

    public void setCurrDate(LocalDateTime currDate) {
        this.currDate = currDate;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public int getPark_id() {
        return park_id;
    }

    public void setPark_id(int park_id) {
        this.park_id = park_id;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }
}
