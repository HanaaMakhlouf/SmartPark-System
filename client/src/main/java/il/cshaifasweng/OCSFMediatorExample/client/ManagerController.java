/**
 * Sample Skeleton for 'managerBoundary.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.InAdvanceOrder;
import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.Navigate;
import il.cshaifasweng.OCSFMediatorExample.entities.ChangePricesRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.*;
import il.cshaifasweng.OCSFMediatorExample.entities.RequestForReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ManagerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML
    private Button approvedreq;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="changePricesBtn"
    private Button changePricesBtn; // Value injected by FXMLLoader

    @FXML // fx:id="showReportsBtn"
    private Button showReportsBtn; // Value injected by FXMLLoader

    @FXML // fx:id="statusImageReportBtn"
    private Button statusImageReportBtn; // Value injected by FXMLLoader

    @FXML // fx:id="goBackBtn"
    private Button goBackBtn; // Value injected by FXMLLoader

    @FXML
    void goBack(ActionEvent event) throws IOException {
        LogoutMessage l = new LogoutMessage(Integer.parseInt(id));
        SimpleClient.getClient().sendToServer(l);
        Navigate.navigate(event , "../mainPage.fxml");
    }


    @FXML
    void changePrices(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("newprices.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);

        Message message = new Message(0, "print prices table");
        SimpleClient.getClient().sendToServer(message);
        Newprices inadv = tableViewParent.getController();
        System.out.println("user id is "+id);
        inadv.setMangerid(id);
        currentWindow.show();
    }

    @FXML
    void showReports(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("makereports.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        List<RequestForReport> Orders = new ArrayList<>();
        List<RequestForReport> Complaints = new ArrayList<>();
        List<RequestForReport> Disabled = new ArrayList<>();

        ShowAllReportrequestsMessage message = new ShowAllReportrequestsMessage(Orders,Complaints,Disabled);
        SimpleClient.getClient().sendToServer(message);
        Makereports inadv = tableViewParent.getController();
        System.out.println("user id is "+id);
        inadv.setManagerID(id);
        currentWindow.show();
    }

    @FXML
    void statusImageReport(ActionEvent event) {

    }
    @FXML
    void openApprovedreq(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("ApprovedRequests.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);

        Message message = new Message(1, "print prices table");
        SimpleClient.getClient().sendToServer(message);
        ArrayList<ChangePricesRequest> list = new ArrayList<>();
        ShowRequestForManager msg = new ShowRequestForManager();
        msg.setList(list);
        msg.setManagerid(id);
        SimpleClient.getClient().sendToServer(msg);
        ApprovedRequestsBoundary inadv = tableViewParent.getController();
        System.out.println("user id is "+id);
        inadv.setManagerID(id);
        currentWindow.show();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert changePricesBtn != null : "fx:id=\"changePricesBtn\" was not injected: check your FXML file 'managerBoundary.fxml'.";
        assert showReportsBtn != null : "fx:id=\"showReportsBtn\" was not injected: check your FXML file 'managerBoundary.fxml'.";
        assert statusImageReportBtn != null : "fx:id=\"statusImageReportBtn\" was not injected: check your FXML file 'managerBoundary.fxml'.";

    }
    private String id;

    public void setManager(String text) {
        this.id = text;
    }
}
