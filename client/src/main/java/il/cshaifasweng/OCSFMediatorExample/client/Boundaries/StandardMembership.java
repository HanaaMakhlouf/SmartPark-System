package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;


import il.cshaifasweng.OCSFMediatorExample.client.FullMembershipEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.client.StandardMembershipEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.FullMemberShipEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.FullMembershipMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.StandardMembershipMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.StandardMemberShipEntity;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class StandardMembership {

    @FXML
    private DatePicker arrivalDate;

    @FXML
    private Button backBt;

    @FXML
    private TextField carNumber;

    @FXML
    private MenuButton parkingLot;

    @FXML
    private Button payBt;

    @FXML
    private Label standardMembershipId;
    String id;
    private Stage currentWindow;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @FXML
    void Pay(ActionEvent event) throws IOException {
        String arrival = arrivalDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StandardMembershipMessage standardMembershipMessage = new StandardMembershipMessage(carNumber.getText()
                ,arrival,id,parkingLot.getText());
        SimpleClient.getClient().sendToServer(standardMembershipMessage);
        currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Navigate.navigate(event, "../registerAsAMember.fxml");
    }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);

    }

    @Subscribe
    public void standardMember(StandardMembershipEvent event){
        if (event.getMessage().isResult()){
            Platform.runLater(new Runnable() {
                public void run() {
                    StandardMemberShipEntity standardMemberShipEntity = event.getMessage().getStandardMemberShipEntity();
                    FXMLLoader tableViewParent = null;
                    try {
                        tableViewParent = new FXMLLoader(getClass().getResource("../payStandardMembership.fxml"));
                        Scene tableViewScene = new Scene(tableViewParent.load());
                        currentWindow.setScene(tableViewScene);
                        currentWindow.show();
                        PayStandardMembership payStandardMembership = tableViewParent.getController();
                        payStandardMembership.setStandardMemberShipEntity(standardMemberShipEntity);
                        payStandardMembership.setFee(event.getMessage().getFee());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else {
            System.out.println("failed");
        }
    }

    public void HaifaPort(ActionEvent actionEvent) {
        parkingLot.setText("Haifa Port");
    }
    public void Carmel(ActionEvent actionEvent) {
        parkingLot.setText("Carmel");
    }
    public void Central_Station(ActionEvent actionEvent) {
        parkingLot.setText("Central Station");
    }

}
