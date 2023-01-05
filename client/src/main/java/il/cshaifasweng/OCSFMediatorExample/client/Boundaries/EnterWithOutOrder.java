package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class EnterWithOutOrder {

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
    private Button saveOrderBt;

    @FXML
    void initialize() {
        //  EventBus.getDefault().register(this);
        // MenuButton
        for(int i = 0 ; i<24 ; i++) {
            leavingHours.getItems().addAll(new MenuItem(String.valueOf(i)));

        }
        for(int i = 0 ; i<60 ; i+=5) {
            leavingMinutes.getItems().addAll(new MenuItem(String.valueOf(i)));
        }
    }
    @FXML
    void back(ActionEvent event) throws IOException {
            Navigate.navigate(event , "../userBoundary.fxml");

    }

    @FXML
    void saveOrder(ActionEvent event) {

    }

}
