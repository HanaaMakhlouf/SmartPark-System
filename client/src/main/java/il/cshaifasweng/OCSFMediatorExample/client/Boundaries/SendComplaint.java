package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SendComplaint {
    @FXML
    private Button backBt;

    @FXML
    private TextField complaintTxt;

    @FXML
    private Button sendComplaintBt;

    @FXML
    void back(ActionEvent event) throws IOException{
        Navigate.navigate(event , "../userBoundary.fxml");

    }

    @FXML
    void sendComplaint(ActionEvent event) {

    }

}
