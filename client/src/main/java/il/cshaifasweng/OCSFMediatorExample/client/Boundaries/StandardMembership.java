package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.io.IOException;

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

    @FXML
    void Pay(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Navigate.navigate(event, "../registerAsAMember.fxml");
    }

}
