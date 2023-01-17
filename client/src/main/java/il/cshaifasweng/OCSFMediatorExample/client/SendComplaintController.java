/**
 * Sample Skeleton for 'sendComplaint1.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.*;
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
    @FXML
    private Label label;

    private String senderId;
    private String[] parkingLots = {"Haifa port" , "Carmel" , "Central Station"};
    public   int flag2 ;
    private String carNumber ;
    private String endDate ;
    String memberNumber ;
    boolean isFullMember ;
    private String memberPark;

    public boolean isFullMember(boolean isFullMember) {
        return this.isFullMember;
    }

    public void setFullMember(boolean fullMember) {
        isFullMember = fullMember;
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
    //   Navigate.navigate(event,"../userBoundary.fxml");
        if(flag2 == 3) {
            Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("userBoundary.fxml"));
            Scene tableViewScene = new Scene(tableViewParent.load());
            currentWindow.setScene(tableViewScene);
            currentWindow.show();
            UserBoundaryController user = tableViewParent.getController();
            user.setUser(this.senderId);
        }else if(flag2 == 4){
            Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("memberPage.fxml"));
            Scene tableViewScene = new Scene(tableViewParent.load());
            currentWindow.setScene(tableViewScene);
            currentWindow.show();
            MemberPage member = tableViewParent.getController();
            member.setIdMember(Integer.parseInt(this.senderId));
            member.setDateTimeEnd(this.endDate);
            member.setFullMember(this.isFullMember);
            member.setMemberNumber(memberNumber);
            member.setMemberPark(memberPark);
            member.setCarNumber(this.carNumber);
            System.out.println("full" + this.isFullMember);

        }
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
        inadv.setId(Integer.valueOf(this.senderId));
        inadv.setFlag2(this.flag2);
        inadv.setEndDate(this.endDate);
        inadv.setFullMember(this.isFullMember);
        inadv.setMemberNumber(this.memberNumber);
        inadv.setMemberPark(this.memberPark);
        inadv.setCarNumber(this.carNumber);
        ArrayList<Complaint> list = new ArrayList<>();
        GetComplaintsMessage msg = new GetComplaintsMessage(list,getSenderId());
        msg.setGetForWhom(2);
        SimpleClient.getClient().sendToServer(msg);
        currentWindow.show();
    }
    public int getFlag2() {
        return flag2;
    }
    public void setFlag2(int flag2) {
        this.flag2 = flag2;
    }
    public String getSenderId() {
        return senderId;
    }
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getMemberPark() {
        return memberPark;
    }

    public void setMemberPark(String memberPark) {
        this.memberPark = memberPark;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'sendComplaint1.fxml'.";
        assert complaintContent != null : "fx:id=\"complaintContent\" was not injected: check your FXML file 'sendComplaint1.fxml'.";
        assert sendComplaintBtn != null : "fx:id=\"sendComplaintBtn\" was not injected: check your FXML file 'sendComplaint1.fxml'.";
        chooseParkingLot.getItems().addAll(parkingLots);
    }


}
