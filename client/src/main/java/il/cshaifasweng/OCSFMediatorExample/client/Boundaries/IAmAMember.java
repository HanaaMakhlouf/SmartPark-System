package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.*;

import il.cshaifasweng.OCSFMediatorExample.client.CustomerServiceEmployeeController;
import il.cshaifasweng.OCSFMediatorExample.client.LogInMemberEvent ;

import il.cshaifasweng.OCSFMediatorExample.client.GeneralManagerController;
import il.cshaifasweng.OCSFMediatorExample.client.ManagerController;
import il.cshaifasweng.OCSFMediatorExample.client.LogInMemberEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.MemberLogInMessage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import il.cshaifasweng.OCSFMediatorExample.client.*;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.logInEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.logInMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLotEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.Subscribe;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;

public class IAmAMember {

    @FXML
    private TextField carNumber;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField memberNumber;

    private Stage currentWindow;

    @FXML
    void login(ActionEvent event) throws IOException {
        String carNumber1 = carNumber.getText();
        String memberNumber1 = memberNumber.getText();
        MemberLogInMessage msg = new MemberLogInMessage(memberNumber1,carNumber1);
        SimpleClient.getClient().sendToServer(msg);
        currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    @Subscribe
    public void memberLogInProcess(LogInMemberEvent event) throws IOException {

        if (event.getMsg1().getResult() != 0){
            Platform.runLater(new Runnable() {
                public void run() {
                    FXMLLoader tableViewParent = null;
                    try {
                        if(event.getMsg1().getResult() == 2) { // means we are FullMembers
                            System.out.println("im full login");
                            tableViewParent = new FXMLLoader(getClass().getResource("../memberPage.fxml"));
                            Scene tableViewScene = new Scene(tableViewParent.load());
                            currentWindow.setScene(tableViewScene);
                            currentWindow.show();
                            MemberPage member = tableViewParent.getController();
                            member.setMember(memberNumber.getText());
                            member.setFullMember(true);
                            member.setCarNum(event.getMsg1().getCarNumber());
                        }
                        else if(event.getMsg1().getResult() == 1){
                            System.out.println("im standard login");
                            tableViewParent = new FXMLLoader(getClass().getResource("../memberPage.fxml"));
                            Scene tableViewScene = new Scene(tableViewParent.load());
                            currentWindow.setScene(tableViewScene);
                            currentWindow.show();
                            MemberPage member = tableViewParent.getController();
                            member.setMember(memberNumber.getText());
                            member.setFullMember(false);
                            member.setCarNum(event.getMsg1().getCarNumber());
                            member.setMemberPark(event.getMsg1().getMemberPark());
                        }

                        System.out.println(memberNumber.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Incorrect Member Number or Car Number");
                alert.setTitle("Error!");
                alert.setHeaderText("Error:");
                alert.show();
            });
        }
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);

    }
}
