package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;


import il.cshaifasweng.OCSFMediatorExample.client.InAdvanceOrderEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SignUpEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.InAdvanceOrderMessage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.format.DateTimeFormatter;

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
    private Label status;
    private int id;

    public DatePicker getArrivalDate() {
        return arrivalDate;
    }

    public MenuButton getArrivalHours() {
        return arrivalHours;
    }

    public MenuButton getArrivalMinutes() {
        return arrivalMinutes;
    }

    public DatePicker getLeavingDate() {
        return leavingDate;
    }

    public MenuButton getLeavingHours() {
        return leavingHours;
    }

    public MenuButton getLeavingMinutes() {
        return leavingMinutes;
    }

    @FXML
    void initialize() {
      EventBus.getDefault().register(this);
       // MenuButton
        for(int i = 0 ; i<24 ; i++) {
//            MenuItem menuItem1 = new MenuItem(String.valueOf(i));
//            MenuItem menuItem2 = new MenuItem(String.valueOf(i));
            arrivalHours.getItems().addAll(new MenuItem(String.valueOf(i)));
//            menuItem1.setOnAction(e -> {
//                arrivalHours.setText(String.valueOf(0));
//            });
            leavingHours.getItems().addAll(new MenuItem(String.valueOf(i)));
        }
        for(int i = 0 ; i<60 ; i+=5) {
//            MenuItem menuItem1 = new MenuItem(String.valueOf(i));
//            MenuItem menuItem2 = new MenuItem(String.valueOf(i));
            arrivalMinutes.getItems().addAll(new MenuItem(String.valueOf(i)));
//            menuItem1.setOnAction(this::setArrivalMin);
            leavingMinutes.getItems().addAll(new MenuItem(String.valueOf(i)));
//            menuItem2.setOnAction(this::setLeavingMin);
        }
    }

    @FXML
    void Pay(ActionEvent event) throws IOException {
        String carNumb = carNumber.getText();
        String pLot = parkingLot.getText();
        String arrivalHour = arrivalHours.getText();
        String arrivalDate1 = arrivalDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String arrivalMinute = arrivalMinutes.getText() ;

        String leavingHour = leavingHours.getText() ;
        String leavingDate1 = leavingDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String leavingMinute = leavingMinutes.getText();
        InAdvanceOrderMessage inAdvanceOrderMessage = new InAdvanceOrderMessage(carNumb,leavingMinute,leavingDate1
        ,leavingHour,arrivalMinute,arrivalDate1,arrivalHour,pLot);
        SimpleClient.getClient().sendToServer(inAdvanceOrderMessage);
    }
    @Subscribe
    public void PayProcess(InAdvanceOrderEvent event) throws IOException {
        if (event.getResult()){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Navigate.navigate(event,"../payInAdvanceOrder.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else {
            status.setText("Could not place order. Please pick another time.");
            status.setTextFill(Paint.valueOf("#df2c14"));
            };
        }


//    @Subscribe
//    public void CalculateFeeAndPay(InAdvanceOrderEvent event) throws IOException{
//        if (event.getResult()){
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//
////                    status.setText("Order");
////                    status.setTextFill(Paint.valueOf("#228c22"));
//
//                }
//            });
//        }
//        else {
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
//            Platform.runLater(() -> {
//                status.setText("Order creation was unsuccessful!");
//                status.setTextFill(Paint.valueOf("#228c22"));
//            });
//        }
//    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Navigate.navigate(event , "../userBoundary.fxml");

    }

}
