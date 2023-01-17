package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.entities.FullMemberShipEntity;
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
    private boolean isFullMember;
    private String carNum;
    private String memberPark;

    public void setMember(String memberNumber){
        this.memberNumber = memberNumber ;
    }
    public String getMemberNumber() {
        return memberNumber;
    }


    @FXML
    void enterParkingLot(ActionEvent event) {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = null;
        if(isFullMember){
            try {
                tableViewParent = new FXMLLoader(getClass().getResource("../enterFullMember.fxml"));
                Scene tableViewScene = new Scene(tableViewParent.load());
                currentWindow.setScene(tableViewScene);
                currentWindow.show();
                EnterFullMember enterFullMember = tableViewParent.getController();
                enterFullMember.setFullMember(isFullMember);
                enterFullMember.setId(memberNumber);
                enterFullMember.setCarNum(carNum);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                System.out.println("standard member here");
                tableViewParent = new FXMLLoader(getClass().getResource("../enterStandardMember.fxml"));
                Scene tableViewScene = new Scene(tableViewParent.load());
                currentWindow.setScene(tableViewScene);
                currentWindow.show();
                EnterStandardMember enterStandardMember = tableViewParent.getController();
                enterStandardMember.setFullMember(isFullMember);
                enterStandardMember.setId(memberNumber);
                enterStandardMember.setParkingLot(memberPark);
                enterStandardMember.setCarNum(carNum);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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

    public String getMemberPark() {
        return memberPark;
    }

    public void setMemberPark(String memberPark) {
        this.memberPark = memberPark;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public boolean isFullMember() {
        return isFullMember;
    }

    public void setFullMember(boolean fullMember) {
        isFullMember = fullMember;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
}
