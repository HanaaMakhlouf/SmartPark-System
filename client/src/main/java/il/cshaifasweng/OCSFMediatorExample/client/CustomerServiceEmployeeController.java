/**
 * Sample Skeleton for 'customerServiceEmployeeBoundary.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CustomerServiceEmployeeController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="goBackBtn"
    private Button goBackBtn; // Value injected by FXMLLoader

    @FXML // fx:id="refundBtn"
    private Button refundBtn; // Value injected by FXMLLoader

    @FXML // fx:id="sendResponseBtn"
    private Button sendResponseBtn; // Value injected by FXMLLoader

    @FXML // fx:id="showComplaintsBtn"
    private Button showComplaintsBtn; // Value injected by FXMLLoader


    @FXML
    void goBack(ActionEvent event) throws IOException {
        Navigate.navigate(event , "../mainPage.fxml");
    }

    @FXML
    void refund(ActionEvent event) {

    }

    @FXML
    void sendResponse(ActionEvent event) {

    }

    @FXML
    void showComplaints(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert goBackBtn != null : "fx:id=\"goBackBtn\" was not injected: check your FXML file 'customerServiceEmployeeBoundary.fxml'.";
        assert refundBtn != null : "fx:id=\"refundBtn\" was not injected: check your FXML file 'customerServiceEmployeeBoundary.fxml'.";
        assert sendResponseBtn != null : "fx:id=\"sendResponseBtn\" was not injected: check your FXML file 'customerServiceEmployeeBoundary.fxml'.";
        assert showComplaintsBtn != null : "fx:id=\"showComplaintsBtn\" was not injected: check your FXML file 'customerServiceEmployeeBoundary.fxml'.";

    }
    private String id;
    public void setCS_employee(String text) {
        this.id = text;
    }

}
