package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class InAdvanceOrder {

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
    private DatePicker leavingDate;

    @FXML
    private MenuButton leavingHours;

    @FXML
    private MenuButton leavingMinutes;

    @FXML
    private MenuButton parkingLot;

    @FXML
    private Button payBt;



    @FXML
    void initialize() {
    //  EventBus.getDefault().register(this);
       // MenuButton
        for(int i = 0 ; i<24 ; i++) {
            arrivalHours.getItems().addAll(new MenuItem(String.valueOf(i)));
            leavingHours.getItems().addAll(new MenuItem(String.valueOf(i)));

        }
        for(int i = 0 ; i<60 ; i+=5) {
            arrivalMinutes.getItems().addAll(new MenuItem(String.valueOf(i)));
            leavingMinutes.getItems().addAll(new MenuItem(String.valueOf(i)));
        }
    }

    @FXML
    void Pay(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Navigate.navigate(event , "../userBoundary.fxml");

    }

}
