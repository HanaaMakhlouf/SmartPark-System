package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.EnterWithOrderEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.EnterWithOrderMessage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.format.DateTimeFormatter;


public class EnterWithOrder {

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
    private Button enterBtn;

    @FXML
    private Label status;

    @FXML
    private MenuButton parkingLot;
    private Stage currentWindow;
    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        setMenuItemsHour();
        setMenuItemsMin();
    }

    @FXML
    public void enter(ActionEvent actionEvent) throws IOException {
        String carNum = carNumber.getText();
        String park = parkingLot.getText();
        String arrivalDate1= null;
        if(arrivalDate.getValue() != null){
            arrivalDate1 = arrivalDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        String arrivalHour = arrivalHours.getText();
        String arrivalMinute = arrivalMinutes.getText();
        EnterWithOrderMessage message = new EnterWithOrderMessage(carNum,arrivalMinute,arrivalDate1,arrivalHour
                ,park,userId);
        SimpleClient.getClient().sendToServer(message);
//        carNumber.clear();
//        parkingLot.setText("Choose Parking Lot");
//        arrivalDate.cancelEdit();

        currentWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }

    @Subscribe
    public void enterWithOrder(EnterWithOrderEvent event){
        if(event.getMessage().getResult()){
            Platform.runLater(new Runnable() {
                public void run() {
                    status.setText("Car Has Been Parked!");
                    status.setTextFill(Paint.valueOf("#228c22"));
                }
            });
        }
        else {
            status.setText("Error, Car Couldn't Be Parked!");
            status.setTextFill(Paint.valueOf("#FF0000"));
        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../userBoundary.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        UserBoundaryController inadv = tableViewParent.getController();
        inadv.setUser(userId);
    }



    public DatePicker getArrivalDate() {
        return arrivalDate;
    }

    public void HaifaPort(ActionEvent actionEvent) {
        parkingLot.setText("Haifa Port");
    }
    public void Carmel(ActionEvent actionEvent) {
        parkingLot.setText("Carmel");
    }
    public void Central_Station(ActionEvent actionEvent) {
        parkingLot.setText("Central Station");
    }


    private void setMenuItemsHour(){
        MenuItem menuItem0 = new MenuItem("00");
        MenuItem menuItem1 = new MenuItem("01");
        MenuItem menuItem2 = new MenuItem("02");
        MenuItem menuItem3 = new MenuItem("03");
        MenuItem menuItem4 = new MenuItem("04");
        MenuItem menuItem5 = new MenuItem("05");
        MenuItem menuItem6 = new MenuItem("06");
        MenuItem menuItem7 = new MenuItem("07");
        MenuItem menuItem8 = new MenuItem("08");
        MenuItem menuItem9 = new MenuItem("09");
        MenuItem menuItem10 = new MenuItem(String.valueOf(10));
        MenuItem menuItem11 = new MenuItem(String.valueOf(11));
        MenuItem menuItem12 = new MenuItem(String.valueOf(12));
        MenuItem menuItem13 = new MenuItem(String.valueOf(13));
        MenuItem menuItem14 = new MenuItem(String.valueOf(14));
        MenuItem menuItem15 = new MenuItem(String.valueOf(15));
        MenuItem menuItem16 = new MenuItem(String.valueOf(16));
        MenuItem menuItem17 = new MenuItem(String.valueOf(17));
        MenuItem menuItem18 = new MenuItem(String.valueOf(18));
        MenuItem menuItem19 = new MenuItem(String.valueOf(19));
        MenuItem menuItem20 = new MenuItem(String.valueOf(20));
        MenuItem menuItem21 = new MenuItem(String.valueOf(21));
        MenuItem menuItem22 = new MenuItem(String.valueOf(22));
        MenuItem menuItem23 = new MenuItem(String.valueOf(23));
        menuItem0.setOnAction(e -> {
            arrivalHours.setText("00");
        });
        menuItem1.setOnAction(e -> {
            arrivalHours.setText("01");
        });
        menuItem2.setOnAction(e -> {
            arrivalHours.setText("02");
        });
        menuItem3.setOnAction(e -> {
            arrivalHours.setText("03");
        });
        menuItem4.setOnAction(e -> {
            arrivalHours.setText("04");
        });
        menuItem5.setOnAction(e -> {
            arrivalHours.setText("05");
        });
        menuItem6.setOnAction(e -> {
            arrivalHours.setText("06");
        });
        menuItem7.setOnAction(e -> {
            arrivalHours.setText("07");
        });
        menuItem8.setOnAction(e -> {
            arrivalHours.setText("08");
        });
        menuItem9.setOnAction(e -> {
            arrivalHours.setText("09");
        });
        menuItem10.setOnAction(e -> {
            arrivalHours.setText(String.valueOf(10));
        });
        menuItem11.setOnAction(e -> {
            arrivalHours.setText(String.valueOf(11));
        });
        menuItem12.setOnAction(e -> {
            arrivalHours.setText(String.valueOf(12));
        });
        menuItem13.setOnAction(e -> {
            arrivalHours.setText(String.valueOf(13));
        });
        menuItem14.setOnAction(e -> {
            arrivalHours.setText(String.valueOf(14));
        });
        menuItem15.setOnAction(e -> {
            arrivalHours.setText(String.valueOf(15));
        });
        menuItem16.setOnAction(e -> {
            arrivalHours.setText(String.valueOf(16));
        });
        menuItem17.setOnAction(e -> {
            arrivalHours.setText(String.valueOf(17));
        });
        menuItem18.setOnAction(e -> {
            arrivalHours.setText(String.valueOf(18));
        });
        menuItem19.setOnAction(e -> {
            arrivalHours.setText(String.valueOf(19));
        });
        menuItem20.setOnAction(e -> {
            arrivalHours.setText(String.valueOf(20));
        });
        menuItem21.setOnAction(e -> {
            arrivalHours.setText(String.valueOf(21));
        });
        menuItem22.setOnAction(e -> {
            arrivalHours.setText(String.valueOf(22));
        });
        menuItem23.setOnAction(e -> {
            arrivalHours.setText(String.valueOf(23));
        });
        arrivalHours.getItems().addAll(menuItem0,menuItem1,menuItem2,menuItem3,menuItem4,menuItem5,menuItem6,menuItem7,menuItem8
                ,menuItem9,menuItem10,menuItem11,menuItem12,menuItem13,menuItem14,menuItem15,menuItem16,menuItem17,menuItem18,menuItem19
                ,menuItem20,menuItem21,menuItem22,menuItem23);
    }

    private void setMenuItemsMin(){
        MenuItem menuItem0 = new MenuItem("00");
        MenuItem menuItem5 = new MenuItem("05");
        MenuItem menuItem10 = new MenuItem(String.valueOf(10));
        MenuItem menuItem15 = new MenuItem(String.valueOf(15));
        MenuItem menuItem20 = new MenuItem(String.valueOf(20));
        MenuItem menuItem25 = new MenuItem(String.valueOf(25));
        MenuItem menuItem30 = new MenuItem(String.valueOf(30));
        MenuItem menuItem35 = new MenuItem(String.valueOf(35));
        MenuItem menuItem40 = new MenuItem(String.valueOf(40));
        MenuItem menuItem45 = new MenuItem(String.valueOf(45));
        MenuItem menuItem50 = new MenuItem(String.valueOf(50));
        MenuItem menuItem55 = new MenuItem(String.valueOf(55));
        menuItem0.setOnAction(e -> {
            arrivalMinutes.setText("00");
        });
        menuItem5.setOnAction(e -> {
            arrivalMinutes.setText("05");
        });
        menuItem10.setOnAction(e -> {
            arrivalMinutes.setText(String.valueOf(10));
        });
        menuItem15.setOnAction(e -> {
            arrivalMinutes.setText(String.valueOf(15));
        });
        menuItem20.setOnAction(e -> {
            arrivalMinutes.setText(String.valueOf(20));
        });
        menuItem25.setOnAction(e -> {
            arrivalMinutes.setText(String.valueOf(25));
        });
        menuItem30.setOnAction(e -> {
            arrivalMinutes.setText(String.valueOf(30));
        });
        menuItem35.setOnAction(e -> {
            arrivalMinutes.setText(String.valueOf(35));
        });
        menuItem40.setOnAction(e -> {
            arrivalMinutes.setText(String.valueOf(40));
        });
        menuItem45.setOnAction(e -> {
            arrivalMinutes.setText(String.valueOf(45));
        });
        menuItem50.setOnAction(e -> {
            arrivalMinutes.setText(String.valueOf(50));
        });
        menuItem55.setOnAction(e -> {
            arrivalMinutes.setText(String.valueOf(55));
        });
        arrivalMinutes.getItems().addAll(menuItem0,menuItem5,menuItem10,menuItem15,menuItem20,menuItem25,menuItem30,menuItem35
                ,menuItem40,menuItem45,menuItem50,menuItem55);
    }

}
