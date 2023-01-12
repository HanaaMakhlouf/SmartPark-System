package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;


import il.cshaifasweng.OCSFMediatorExample.client.FullMembershipEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.FullMemberShipEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.FullMembershipMessage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class FullMembership {

    @FXML
    private DatePicker arrivalDate;

    @FXML
    private Button backBt;

    @FXML
    private TextField carNumber;

    @FXML
    private Label memberId;

    @FXML
    private Button payBt;
    String id;
    private Stage currentWindow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @FXML
    void Pay(ActionEvent event) throws IOException {
        String arrival = arrivalDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        FullMembershipMessage fullMembershipMessage = new FullMembershipMessage(carNumber.getText()
                ,arrival,id);
        SimpleClient.getClient().sendToServer(fullMembershipMessage);
        currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    }
    @Subscribe
    public void fullMember(FullMembershipEvent event){
        if (event.getMessage().isResult()){
            Platform.runLater(new Runnable() {
                public void run() {
                    FullMemberShipEntity fullMemberShipEntity = event.getMessage().getFullMemberShipEntity();
                    FXMLLoader tableViewParent = null;
                    try {
                        tableViewParent = new FXMLLoader(getClass().getResource("../payFullMembership.fxml"));
                        Scene tableViewScene = new Scene(tableViewParent.load());
                        currentWindow.setScene(tableViewScene);
                        currentWindow.show();
                        PayFullMembership payFullMembership = tableViewParent.getController();
                        payFullMembership.setFullMemberShipEntity(fullMemberShipEntity);
                        payFullMembership.setFee(event.getMessage().getFee());
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
    @FXML
    void back(ActionEvent event) throws IOException {
        Navigate.navigate(event, "../registerAsAMember.fxml");
    }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }

}
