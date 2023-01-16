package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.LogoutMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import il.cshaifasweng.OCSFMediatorExample.client.CustomerServiceEmployeeController;
import il.cshaifasweng.OCSFMediatorExample.client.PricesTable;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetallOrdersOfClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Prices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import il.cshaifasweng.OCSFMediatorExample.client.SendComplaintController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.InflaterInputStream;

public class MemberPage {

    @FXML
    private Button backBtn;

    @FXML
    private Button complaintBt;

    @FXML
    private Button enterParkingLotBt;

    @FXML
    private Button exitBt;

    @FXML
    private Button renewMembershipBt;

    @FXML
    private Button trackBt;

    private String memberNumber ;

    public void setMember(String memberNumber){
        this.memberNumber = memberNumber ;
    }
    public String getMemberNumber() {
        return memberNumber;
    }


    @FXML
    void enterParkingLot(ActionEvent event) {

    }

    @FXML
    void exitParkingLot(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        LogoutMessage l = new LogoutMessage(Integer.parseInt(memberNumber));
        SimpleClient.getClient().sendToServer(l);
        Navigate.navigate(event , "../MainPage.fxml");
    }

    @FXML
    void renewMembership(ActionEvent event) {

    }

    @FXML
    void sendComplaint(ActionEvent event) {

    }

    @FXML
    void trackOrder(ActionEvent event) {

    }

}
