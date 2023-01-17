/**
 * Sample Skeleton for 'customerServiceEmployeeBoundary.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.ComplaintResponseController;
import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.Navigate;
import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.SaveSpotController;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.CustomerServiceEmployee;

import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetComplaintsMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetSpotsMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetallOrdersOfClient;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.LogoutMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;

public class CustomerServiceEmployeeController {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="goBackBtn"
    private Button goBackBtn; // Value injected by FXMLLoader

    @FXML // fx:id="refundBtn"
    private Button saveSpotBtn; // Value injected by FXMLLoader

    @FXML // fx:id="sendResponseBtn"
    private Button sendResponseBtn; // Value injected by FXMLLoader

    @FXML // fx:id="showComplaintsBtn"
    private Button showComplaintsBtn; // Value injected by FXMLLoader



    @FXML
    void goBack(ActionEvent event) throws IOException {
        LogoutMessage l = new LogoutMessage(Integer.parseInt(id));
        SimpleClient.getClient().sendToServer(l);
        Navigate.navigate(event , "../mainPage.fxml");

    }

    @FXML
    void saveSpot(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("saveSpot.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        SaveSpotController cs_em = tableViewParent.getController();
        cs_em.setCS_employee(getId());
        GetSpotsMessage message = new GetSpotsMessage();
        message.setFromWhom(1);
        SimpleClient.getClient().sendToServer(message);
        currentWindow.show();
    }



    @FXML
    void showComplaints(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("complaintResponse.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        ComplaintResponseController user = tableViewParent.getController();
        user.setId(getId());
        ArrayList<Complaint> list = new ArrayList<>();
        GetComplaintsMessage msg = new GetComplaintsMessage(list,id);
        msg.setGetForWhom(1);
        SimpleClient.getClient().sendToServer(msg);
        currentWindow.show();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert goBackBtn != null : "fx:id=\"goBackBtn\" was not injected: check your FXML file 'customerServiceEmployeeBoundary.fxml'.";
        assert sendResponseBtn != null : "fx:id=\"sendResponseBtn\" was not injected: check your FXML file 'customerServiceEmployeeBoundary.fxml'.";
        assert showComplaintsBtn != null : "fx:id=\"showComplaintsBtn\" was not injected: check your FXML file 'customerServiceEmployeeBoundary.fxml'.";

    }
    private String id;
    public String getId() {
        return id;
    }

    public void setCS_employee(String text) {
        this.id = text;
    }

}
