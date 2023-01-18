package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import il.cshaifasweng.OCSFMediatorExample.entities.RequestForReport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShowAllReportrequestsMessage implements Serializable {




    private  List<RequestForReport> Orders = new ArrayList<>();
    private List<RequestForReport> Complaints = new ArrayList<>();
    private List<RequestForReport> Disabled = new ArrayList<>();



    public ShowAllReportrequestsMessage(List<RequestForReport> orders, List<RequestForReport> complaints, List<RequestForReport> disabled) {
        Orders = orders;
        Complaints = complaints;
        Disabled = disabled;
    }


    public ShowAllReportrequestsMessage() {
    }

    public List<RequestForReport> getOrders() {
        return Orders;
    }

    public void setOrders(List<RequestForReport> orders) {
        Orders = orders;
    }

    public List<RequestForReport> getComplaints() {
        return Complaints;
    }

    public void setComplaints(List<RequestForReport> complaints) {
        Complaints = complaints;
    }

    public List<RequestForReport> getDisabled() {
        return Disabled;
    }

    public void setDisabled(List<RequestForReport> disabled) {
        Disabled = disabled;
    }
}
