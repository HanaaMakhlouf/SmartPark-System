/**
 * Sample Skeleton for 'managerBoundary.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ManagerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

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
        Navigate.navigate(event , "../mainPage.fxml");
    }


    @FXML
    void changePrices(ActionEvent event) {

    }

    @FXML
    void showReports(ActionEvent event) {

    }

    @FXML
    void statusImageReport(ActionEvent event) {

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
