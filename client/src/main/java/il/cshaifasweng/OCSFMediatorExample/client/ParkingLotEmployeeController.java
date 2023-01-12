/**
 * Sample Skeleton for 'parkingLotEmployeeBoundary.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ParkingLotEmployeeController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="registerUnavailableSpotBtn"
    private Button registerUnavailableSpotBtn; // Value injected by FXMLLoader

    @FXML // fx:id="saveSpotBtn"
    private Button saveSpotBtn; // Value injected by FXMLLoader

    @FXML // fx:id="sendToAltBtn"
    private Button sendToAltBtn; // Value injected by FXMLLoader

    @FXML // fx:id="setupBtn"
    private Button setupBtn; // Value injected by FXMLLoader


    @FXML // fx:id="goBackBtn"
    private Button goBackBtn; // Value injected by FXMLLoader

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Navigate.navigate(event , "../mainPage.fxml");
    }


    @FXML
    void registerUnavailableSpot(ActionEvent event) {

    }

    @FXML
    void saveParkingSpot(ActionEvent event) {

    }

    @FXML
    void sendToAltPark(ActionEvent event) {

    }

    @FXML
    void setup(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert registerUnavailableSpotBtn != null : "fx:id=\"registerUnavailableSpotBtn\" was not injected: check your FXML file 'parkingLotEmployeeBoundary.fxml'.";
        assert saveSpotBtn != null : "fx:id=\"saveSpotBtn\" was not injected: check your FXML file 'parkingLotEmployeeBoundary.fxml'.";
        assert sendToAltBtn != null : "fx:id=\"sendToAltBtn\" was not injected: check your FXML file 'parkingLotEmployeeBoundary.fxml'.";
        assert setupBtn != null : "fx:id=\"setupBtn\" was not injected: check your FXML file 'parkingLotEmployeeBoundary.fxml'.";

    }
    private String id;

    public void setEmployee(String text) {
        this.id = text;
    }
}
