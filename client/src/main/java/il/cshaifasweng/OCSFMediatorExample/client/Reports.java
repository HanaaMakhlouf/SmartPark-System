package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.RequestForReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Reports {

    @FXML
    private Button PreparedRequestsbtn;

    @FXML
    private Button backbtn;

    @FXML
    private Button complaintsRepbtn;

    @FXML
    private Button disabledSpotsRepbtn;

    @FXML
    private DatePicker fromdate;

    @FXML
    private Button ordersRepbtn;

    @FXML
    private DatePicker untildate;

    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @FXML
    void ComplintsReport(ActionEvent event) throws IOException {
        LocalDate fromDate = fromdate.getValue();
        LocalTime fromTime = LocalTime.of(0,0);
        LocalDateTime fromDateTime = LocalDateTime.of(fromDate, fromTime);
        LocalDate untilDate = untildate.getValue();
        LocalTime untilTime = LocalTime.of(23,59);
        LocalDateTime untilDateTime = LocalDateTime.of(untilDate, untilTime);
        RequestForReport request = new RequestForReport(fromDateTime,untilDateTime,"Complaints");
        SimpleClient.getClient().sendToServer(request);
    }

    @FXML
    void DisabledReport(ActionEvent event) throws IOException {
        LocalDate fromDate = fromdate.getValue();
        LocalTime fromTime = LocalTime.of(0,0);
        LocalDateTime fromDateTime = LocalDateTime.of(fromDate, fromTime);
        LocalDate untilDate = untildate.getValue();
        LocalTime untilTime = LocalTime.of(23,59);
        LocalDateTime untilDateTime = LocalDateTime.of(untilDate, untilTime);
        RequestForReport request = new RequestForReport(fromDateTime,untilDateTime,"Disabled");
        SimpleClient.getClient().sendToServer(request);
    }

    @FXML
    void OrderReport(ActionEvent event) throws IOException {
        LocalDate fromDate = fromdate.getValue();
        LocalTime fromTime = LocalTime.of(0,0);
        LocalDateTime fromDateTime = LocalDateTime.of(fromDate, fromTime);
        LocalDate untilDate = untildate.getValue();
        LocalTime untilTime = LocalTime.of(23,59);
        LocalDateTime untilDateTime = LocalDateTime.of(untilDate, untilTime);
        RequestForReport request = new RequestForReport(fromDateTime,untilDateTime,"Orders");
        SimpleClient.getClient().sendToServer(request);
    }

    @FXML
    void backto(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("generalManageBoundary.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        GeneralManagerController inadv = tableViewParent.getController();
        inadv.setGM(id);
    }

    @FXML
    void gotoPreparedReports(ActionEvent event) {

    }

}
