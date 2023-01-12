package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CancelOrder {

    @FXML
    private Button backBt;

    @FXML
    private Button cancelOrderBt;

    @FXML
    private Label creditAmount;

    @FXML
    private Button getCreditAmountBt;

    @FXML
    private TextField orderNumber;

    @FXML
    void back(ActionEvent event) throws IOException {
        Navigate.navigate(event , "../userBoundary.fxml");

    }

    @FXML
    void cancelOrder(ActionEvent event) {

    }

    @FXML
    void getCreditAmount(ActionEvent event) {

    }

}
