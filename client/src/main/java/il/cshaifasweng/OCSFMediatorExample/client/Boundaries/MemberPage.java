package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.entities.FullMemberShipEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.LogoutMessage;
import il.cshaifasweng.OCSFMediatorExample.client.*;
import il.cshaifasweng.OCSFMediatorExample.entities.FullMemberShipEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.FullMembershipMessage;
import il.cshaifasweng.OCSFMediatorExample.client.FullMembershipEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import javafx.scene.paint.Paint;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class MemberPage {

    @FXML
    private Button backBtn;

    @FXML
    private Button complaintBt;

    @FXML
    private Label dayesleft;

    @FXML
    private Button enterParkingLotBt;

    @FXML
    private Button exitBt;

    @FXML
    private Button howLeftBt;

    @FXML
    private Button payTheRenewAmountBt;

    @FXML
    private Button renewMembershipBt;

    @FXML
    private Label statusRenew;

     int idMember ;
     boolean isFullMember ;
     String memberNumber ;
     String dateTimeEnd ;
     String carNumber ;
     private String memberPark;
     double fee72 ;

    @FXML
    void howLeft(ActionEvent event) {
        LocalDate dateTime = LocalDate.now() ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateEnd = LocalDate.parse(dateTimeEnd,formatter);
        if(dateTime.plusDays(8).isAfter(dateEnd)){
            dayesleft.setText("Hi, you have less than 7 days to end your membership! " +
                    "Please Renew Membership!");
            dayesleft.setTextFill(Paint.valueOf("#0ea33d"));
        }
        else{
            dayesleft.setText("Hi, you have more than 7 days to end your membership!");
            dayesleft.setTextFill(Paint.valueOf("#228c22"));
        }
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
                enterFullMember.setMemberNumber(memberNumber);
                enterFullMember.setCarNum(carNumber);
                enterFullMember.setEndMemDate(dateTimeEnd);
                enterFullMember.setId(idMember);
                enterFullMember.setFee(fee72);
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
                enterStandardMember.setMemberNumber(memberNumber);
                enterStandardMember.setParkingLot(memberPark);
                enterStandardMember.setCarNum(carNumber);
                enterStandardMember.setEndMemDate(dateTimeEnd);
                enterStandardMember.setId(idMember);
                enterStandardMember.setFee(fee72);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void exitParkingLot(ActionEvent event) throws IOException {
        if(isFullMember){
            Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../exitFullParking.fxml"));
            Scene tableViewScene = new Scene(tableViewParent.load());
            currentWindow.setScene(tableViewScene);
            currentWindow.show();
            ExitFullParking exitFullParking = tableViewParent.getController();
            exitFullParking.setFullMember(isFullMember);
            exitFullParking.setMemberNumber(memberNumber);
            exitFullParking.setCarNum(carNumber);
            exitFullParking.setEndMemDate(dateTimeEnd);
            exitFullParking.setId(idMember);
            exitFullParking.setFee(fee72);

        }
        else {
            System.out.println("parking lot is:" + memberPark);
            Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../exitStandardParking.fxml"));
            Scene tableViewScene = new Scene(tableViewParent.load());
            currentWindow.setScene(tableViewScene);
            currentWindow.show();
            ExitStandardParking exitStandardParking = tableViewParent.getController();
            exitStandardParking.setFullMember(isFullMember);
            exitStandardParking.setMemberNumber(memberNumber);
            exitStandardParking.setCarNum(carNumber);
            exitStandardParking.setEndMemDate(dateTimeEnd);
            exitStandardParking.setId(idMember);
            exitStandardParking.setFee(fee72);
            exitStandardParking.setParkingLot(memberPark);
        }

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        LogoutMessage l = new LogoutMessage(Integer.parseInt(memberNumber));
        SimpleClient.getClient().sendToServer(l);
        Navigate.navigate(event , "../IAmAMember.fxml");
    }

    @FXML
    void payTheRenewAmount(ActionEvent event) throws IOException {
            Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../PayRenewFullMembership.fxml"));
            Scene tableViewScene = new Scene(tableViewParent.load());
            currentWindow.setScene(tableViewScene);
            currentWindow.show();
            PayRenewFullMembership payRenewFullMembership = tableViewParent.getController();
            payRenewFullMembership.setOrderNum(memberNumber);
            payRenewFullMembership.setMemberNumber(memberNumber);
            payRenewFullMembership.setLeavingDate(dateTimeEnd);
            payRenewFullMembership.setId(idMember);
            payRenewFullMembership.setFee(fee72);
            payRenewFullMembership.setCarNumber(carNumber);
            payRenewFullMembership.setFullMember(this.isFullMember);
            payRenewFullMembership.setMemberPark(memberPark);

    }

    @FXML
    void sendComplaint(ActionEvent event) throws IOException {
            System.out.println("sendComplaint " );
            System.out.println("car " + this.carNumber);
            System.out.println("id" + this.idMember);
            System.out.println("date end " + this.dateTimeEnd);
            System.out.println("is full  " + this.isFullMember);
            Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../sendComplaint1.fxml"));
            Scene tableViewScene = new Scene(tableViewParent.load());
            currentWindow.setScene(tableViewScene);
            currentWindow.show();
            SendComplaintController complaint = tableViewParent.getController();
            complaint.setSenderId(String.valueOf(this.idMember));
            complaint.setMemberNumber(memberNumber);
            complaint.setEndDate(this.dateTimeEnd);
            complaint.setFlag2(4);
            complaint.setFullMember(this.isFullMember);
            complaint.setMemberPark(memberPark);
            complaint.setCarNumber(this.carNumber);
            complaint.setFee(fee72);
    }

    @FXML
    void initialize() {

    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public boolean isFullMember() {
        return isFullMember;
    }

    public void setFullMember(boolean fullMember) {
        isFullMember = fullMember;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(String dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getMemberPark() {
        return memberPark;
    }

    public void setMemberPark(String memberPark) {
        this.memberPark = memberPark;
    }

    public double getFee72() {
        return fee72;
    }

    public void setFee72(double fee72) {
        this.fee72 = fee72;
    }
}
