package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class RegisterAsAMember {

    @FXML
    private Button FullMembershipBt;

    @FXML
    private Button backBt2;

    @FXML
    private Button standardMembershipBt;

    @FXML
    void FullMembership(ActionEvent event) throws IOException {
        Navigate.navigate(event, "../fullMembership.fxml");
    }

    @FXML
    void StandardMembership(ActionEvent event) throws IOException {
        Navigate.navigate(event, "../standardMembership.fxml");

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Navigate.navigate(event, "../userBoundary.fxml");
    }

}
