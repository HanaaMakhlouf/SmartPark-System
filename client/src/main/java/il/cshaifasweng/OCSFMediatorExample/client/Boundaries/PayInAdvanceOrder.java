package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PayInAdvanceOrder {

    @FXML
    private Button backBt;

    @FXML
    private TextField cardNumber;

    @FXML
    private Label cost;

    @FXML
    private TextField cvv;

    @FXML
    private MenuButton monthPayment;

    @FXML
    private TextField nameOnCard;

    @FXML
    private Label orderNumber;

    @FXML
    private Button payBt;

    @FXML
    private MenuButton yearPayment;

    @FXML
    void Pay(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event)throws IOException {
        Navigate.navigate(event , "../userBoundary.fxml");
    }

}
