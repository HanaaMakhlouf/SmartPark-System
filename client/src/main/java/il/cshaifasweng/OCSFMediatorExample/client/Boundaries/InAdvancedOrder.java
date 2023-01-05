package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.greenrobot.eventbus.EventBus;

public class InAdvancedOrder {

    @FXML
    private DatePicker arrivalDate;

    @FXML
    private MenuButton arrivalHours;

    @FXML
    private MenuButton arrivalMinutes;

    @FXML
    private Button backBt;

    @FXML
    private TextField carNumber;

    @FXML
    private TextField email;

    @FXML
    private DatePicker livingDate;

    @FXML
    private MenuButton livingHours;

    @FXML
    private MenuButton livingMinutes;

    @FXML
    private MenuButton parkingLot;

    @FXML
    private Button payBt;

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
//        MenuButton
//        parkingLot.getItems().addAll(new MenuItem(
//
//        ))
    }

    @FXML
    void Pay(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {

    }

}
