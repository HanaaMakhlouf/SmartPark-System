/**
 * Sample Skeleton for 'generalManageBoundary.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GeneralManagerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="confirmNewPricesBtn"
    private Button confirmNewPricesBtn; // Value injected by FXMLLoader

    @FXML // fx:id="orderReportsBtn"
    private Button orderReportsBtn; // Value injected by FXMLLoader

    @FXML // fx:id="showPricesBtn"
    private Button showPricesBtn; // Value injected by FXMLLoader

    @FXML
    private Button goBackBtn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Navigate.navigate(event , "../mainPage.fxml");
    }

    @FXML
    void confirmNewPrices(ActionEvent event) {

    }

    @FXML
    void orderReports(ActionEvent event) {

    }

    @FXML
    void showPrices(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert confirmNewPricesBtn != null : "fx:id=\"confirmNewPricesBtn\" was not injected: check your FXML file 'generalManageBoundary.fxml'.";
        assert orderReportsBtn != null : "fx:id=\"orderReportsBtn\" was not injected: check your FXML file 'generalManageBoundary.fxml'.";
        assert showPricesBtn != null : "fx:id=\"showPricesBtn\" was not injected: check your FXML file 'generalManageBoundary.fxml'.";

    }

    private String id;
    public void setGM(String text) {
        this.id = text;
    }
}
