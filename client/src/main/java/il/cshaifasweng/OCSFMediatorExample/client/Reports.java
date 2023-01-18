package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ShowALLreportsMSG;
import il.cshaifasweng.OCSFMediatorExample.entities.RequestForReport;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.greenrobot.eventbus.Subscribe;

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
    @FXML
    private Label labelmsg1;

    @FXML
    private Label labelmsg2;

    @FXML
    private Label labelmsg3;


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
        labelmsg2.setText("report ordered successfully");
        labelmsg2.setMinWidth(0);
        labelmsg2.setPrefWidth(Control.USE_COMPUTED_SIZE);
        labelmsg2.setMaxWidth(Double.MAX_VALUE);
        labelmsg2.setTextFill(Color.GREEN);
        FadeTransition ft = new FadeTransition(Duration.seconds(10), labelmsg2);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(1);
        ft.play();

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
        labelmsg3.setText("report ordered successfully");
        labelmsg3.setMinWidth(0);
        labelmsg3.setPrefWidth(Control.USE_COMPUTED_SIZE);
        labelmsg3.setMaxWidth(Double.MAX_VALUE);
        labelmsg3.setTextFill(Color.GREEN);
        FadeTransition ft = new FadeTransition(Duration.seconds(10), labelmsg3);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(1);
        ft.play();
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
        labelmsg1.setText("report ordered successfully");
        labelmsg1.setMinWidth(0);
        labelmsg1.setPrefWidth(Control.USE_COMPUTED_SIZE);
        labelmsg1.setMaxWidth(Double.MAX_VALUE);
        labelmsg1.setTextFill(Color.GREEN);
        FadeTransition ft = new FadeTransition(Duration.seconds(10), labelmsg1);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(1);
        ft.play();
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
    void gotoPreparedReports(ActionEvent event) throws IOException {

        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("AllpreparedReports.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);

        ShowALLreportsMSG msg = new ShowALLreportsMSG();
        SimpleClient.getClient().sendToServer(msg);


        currentWindow.show();
        AllpreparedReports inadv = tableViewParent.getController();
        inadv.setId(id);





    }




}
