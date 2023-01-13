/**
 * Sample Skeleton for 'sendComplaint1.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Date;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.ComplaintResponseController;
import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.Navigate;
import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.TrackComplaintsController;
import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.UserBoundaryController;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetComplaintsMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.SendComplaintMsg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SendComplaintController implements Serializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="complaintContent"
    private TextArea complaintContent; // Value injected by FXMLLoader

    @FXML // fx:id="sendComplaintBtn"
    private Button sendComplaintBtn; // Value injected by FXMLLoader

    @FXML // fx:id="chooseParkingLot"
    private ChoiceBox<String> chooseParkingLot; // Value injected by FXMLLoader

    private String[] parkingLots = {"Haifa port" , "Carmel" , "Central Station"};

    @FXML
    void goBack(ActionEvent event) throws IOException {
    //   Navigate.navigate(event,"../userBoundary.fxml");
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("userBoundary.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        UserBoundaryController user = tableViewParent.getController();
        user.setUser(this.senderId);

    }

    @FXML
    private Label label;

    private String senderId;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }


    @FXML
    void sendComplaint(ActionEvent event) {
        String complaint = complaintContent.getText();
        String parkingLotName = chooseParkingLot.getSelectionModel().getSelectedItem();
        int park_id = 0;
        if (parkingLotName.equals("Haifa port")) park_id = 1;
        else  if (parkingLotName.equals("Carmel")) park_id = 2;
        else if(parkingLotName.equals("Central Station")) park_id = 3;
        LocalDateTime currentDate =LocalDateTime.now();
        SendComplaintMsg message = new SendComplaintMsg(complaint,park_id,getSenderId(),currentDate);
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        label.setText("Complaint has been sent.");
        sendComplaintBtn.setDisable(true);
    }

    @FXML
    void trackComplaintsBtn(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("trackComplaints.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        TrackComplaintsController inadv = tableViewParent.getController();
        inadv.setId(Integer.valueOf(getSenderId()));
        ArrayList<Complaint> list = new ArrayList<>();
        GetComplaintsMessage msg = new GetComplaintsMessage(list,getSenderId());
        msg.setGetForWhom(2);
        SimpleClient.getClient().sendToServer(msg);
        currentWindow.show();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'sendComplaint1.fxml'.";
        assert complaintContent != null : "fx:id=\"complaintContent\" was not injected: check your FXML file 'sendComplaint1.fxml'.";
        assert sendComplaintBtn != null : "fx:id=\"sendComplaintBtn\" was not injected: check your FXML file 'sendComplaint1.fxml'.";
        chooseParkingLot.getItems().addAll(parkingLots);
    }

}
