package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

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

    @FXML
    void Pay(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Navigate.navigate(event, "../registerAsAMember.fxml");
    }

}
