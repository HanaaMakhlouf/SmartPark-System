package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;


import il.cshaifasweng.OCSFMediatorExample.client.FullMembershipEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.FullMembershipMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    }
    @Subscribe
    public void fullMember(FullMembershipEvent event){
        if (event.getMessage().isResult()){
            System.out.println(event.getMessage().getMembershipId());
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
