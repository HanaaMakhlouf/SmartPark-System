package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.ExitParkingLotEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ExitParkingMessage;
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

public class ExitParking {

    @FXML
    private DatePicker leavingDate;

    @FXML
    private MenuButton leavingHours;

    @FXML
    private MenuButton leavingMinutes;

    @FXML
    private Label status;

    @FXML
    private Button backButton;

    @FXML
    private TextField carNumber;

    @FXML
    private Button exitButton;

    private String userId;
    private Stage currentWindow;

    @FXML
    private MenuButton parkingLot;

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        setMenuItemsHour();
        setMenuItemsMin();
    }

    @FXML
    void Carmel(ActionEvent event) {
        parkingLot.setText("Carmel");

    }

    @FXML
    void Central_Station(ActionEvent event) {
        parkingLot.setText("Central Station");
    }

    @FXML
    void Exit(ActionEvent event) throws IOException {
        String carNum = carNumber.getText();
        String park = parkingLot.getText();
        String leavingDate1 = null;
        if(leavingDate.getValue() != null){
            leavingDate1 = leavingDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        String leavingHour = leavingHours.getText();
        String leavingMinute = leavingMinutes.getText();
        ExitParkingMessage message = new ExitParkingMessage(carNum,leavingMinute,leavingDate1,leavingHour
                ,park,userId);
        SimpleClient.getClient().sendToServer(message);
        currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    @Subscribe
    public void ExitProcess(ExitParkingLotEvent event){
        if(event.getMessage().getResult()) {
            if (!event.getMessage().isInPlaceOrder()) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        status.setText("Please wait few minutes at the front gate for your car.\n Fee: " + event.getMessage().getFee());
                        status.setTextFill(Paint.valueOf("#228c22"));
                    }
                });
            } else if (event.getMessage().isInPlaceOrder()) {
                // move to pay
                Platform.runLater(new Runnable() {
                    public void run() {
                        status.setText("");
                        FXMLLoader tableViewParent = null;
                        try {
                            tableViewParent = new FXMLLoader(getClass().getResource("../payInPlaceOrder.fxml"));
                            Scene tableViewScene = new Scene(tableViewParent.load());
                            currentWindow.setScene(tableViewScene);
                            currentWindow.show();
                            PayInPlaceOrder payInPlaceOrder = tableViewParent.getController();
                            System.out.println(event.getMessage().getFee());
                            System.out.println(event.getMessage().getCarNumber());
                            System.out.println(event.getMessage().getParkingLot());
                            System.out.println(event.getMessage().getUserId());
                            payInPlaceOrder.setFee(event.getMessage().getFee());
                            payInPlaceOrder.setCarNumber(event.getMessage().getCarNumber());
                            payInPlaceOrder.setParkingLot(event.getMessage().getParkingLot());
                            payInPlaceOrder.setId(event.getMessage().getUserId());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }
        }
        else {
            Platform.runLater(new Runnable() {
                public void run() {
                    status.setText("Error, Please verify information.");
                    status.setTextFill(Paint.valueOf("#FF0000"));
                }
            });

        }
//        System.out.println("im in do_something");
    }

    @FXML
    void HaifaPort(ActionEvent event) {
        parkingLot.setText("Haifa Port");

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../userBoundary.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        UserBoundaryController user = tableViewParent.getController();
        user.setUser(this.userId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return userId;
    }

    public void setId(String id) {
        this.userId = id;
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
            leavingHours.setText("00");
        });
        menuItem1.setOnAction(e -> {
            leavingHours.setText("01");
        });
        menuItem2.setOnAction(e -> {
            leavingHours.setText("02");
        });
        menuItem3.setOnAction(e -> {
            leavingHours.setText("03");
        });
        menuItem4.setOnAction(e -> {
            leavingHours.setText("04");
        });
        menuItem5.setOnAction(e -> {
            leavingHours.setText("05");
        });
        menuItem6.setOnAction(e -> {
            leavingHours.setText("06");
        });
        menuItem7.setOnAction(e -> {
            leavingHours.setText("07");
        });
        menuItem8.setOnAction(e -> {
            leavingHours.setText("08");
        });
        menuItem9.setOnAction(e -> {
            leavingHours.setText("09");
        });
        menuItem10.setOnAction(e -> {
            leavingHours.setText(String.valueOf(10));
        });
        menuItem11.setOnAction(e -> {
            leavingHours.setText(String.valueOf(11));
        });
        menuItem12.setOnAction(e -> {
            leavingHours.setText(String.valueOf(12));
        });
        menuItem13.setOnAction(e -> {
            leavingHours.setText(String.valueOf(13));
        });
        menuItem14.setOnAction(e -> {
            leavingHours.setText(String.valueOf(14));
        });
        menuItem15.setOnAction(e -> {
            leavingHours.setText(String.valueOf(15));
        });
        menuItem16.setOnAction(e -> {
            leavingHours.setText(String.valueOf(16));
        });
        menuItem17.setOnAction(e -> {
            leavingHours.setText(String.valueOf(17));
        });
        menuItem18.setOnAction(e -> {
            leavingHours.setText(String.valueOf(18));
        });
        menuItem19.setOnAction(e -> {
            leavingHours.setText(String.valueOf(19));
        });
        menuItem20.setOnAction(e -> {
            leavingHours.setText(String.valueOf(20));
        });
        menuItem21.setOnAction(e -> {
            leavingHours.setText(String.valueOf(21));
        });
        menuItem22.setOnAction(e -> {
            leavingHours.setText(String.valueOf(22));
        });
        menuItem23.setOnAction(e -> {
            leavingHours.setText(String.valueOf(23));
        });
        leavingHours.getItems().addAll(menuItem0,menuItem1,menuItem2,menuItem3,menuItem4,menuItem5,menuItem6,menuItem7,menuItem8
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
            leavingMinutes.setText("00");
        });
        menuItem5.setOnAction(e -> {
            leavingMinutes.setText("05");
        });
        menuItem10.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(10));
        });
        menuItem15.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(15));
        });
        menuItem20.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(20));
        });
        menuItem25.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(25));
        });
        menuItem30.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(30));
        });
        menuItem35.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(35));
        });
        menuItem40.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(40));
        });
        menuItem45.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(45));
        });
        menuItem50.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(50));
        });
        menuItem55.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(55));
        });
        leavingMinutes.getItems().addAll(menuItem0,menuItem5,menuItem10,menuItem15,menuItem20,menuItem25,menuItem30,menuItem35
                ,menuItem40,menuItem45,menuItem50,menuItem55);
    }


}
