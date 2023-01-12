package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;


import il.cshaifasweng.OCSFMediatorExample.client.InAdvanceOrderEvent;
import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.PayInAdvanceOrder;
import il.cshaifasweng.OCSFMediatorExample.client.ParkingTable;
import il.cshaifasweng.OCSFMediatorExample.client.SignUpEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.InAdvanceOrderMessage;
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
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
       // MenuButton
//        int x = 0;
//        for(int i = 0 ; i<24 ; i++) {
//            MenuItem menuItem1 = new MenuItem(String.valueOf(i));
//            MenuItem menuItem2 = new MenuItem(String.valueOf(i));
//            arrivalHours.getItems().addAll(new MenuItem(String.valueOf(i)));
//            int finalI = i;
//            int finalI1 = i;
//            menuItem1.setOnAction(e -> {
//                arrivalHours.setText(String.valueOf(x));
//            });
//            leavingHours.getItems().addAll(new MenuItem(String.valueOf(i)));
//            menuItem2.setOnAction(e -> {
//                leavingHours.setText(String.valueOf(finalI));
//            });
//            x++;
//        }
//        MenuItem menuItem1 = new MenuItem(String.valueOf(1));
//        MenuItem menuItem2 = new MenuItem(String.valueOf(5));
//        menuItem1.setOnAction(e -> {
//                arrivalHours.setText(String.valueOf(12));
//            });
//        menuItem2.setOnAction(e -> {
//            arrivalMinutes.setText(String.valueOf(5));
//        });
//        arrivalHours.getItems().addAll(menuItem1);
//        arrivalMinutes.getItems().addAll(menuItem2);
//        for(int i = 0 ; i<60 ; i+=5) {
////            MenuItem menuItem1 = new MenuItem(String.valueOf(i));
////            MenuItem menuItem2 = new MenuItem(String.valueOf(i));
//            arrivalMinutes.getItems().addAll(new MenuItem(String.valueOf(i)));
////            menuItem1.setOnAction(this::setArrivalMin);
//            leavingMinutes.getItems().addAll(new MenuItem(String.valueOf(i)));
////            menuItem2.setOnAction(this::setLeavingMin);
//        }
        setMenuItemsHour();
        setMenuItemsMin();
    }

    @FXML
    void Pay(ActionEvent event) throws IOException {
        String arrivalDate1 = null ;
        String leavingDate1 = null ;
        String carNumb = carNumber.getText();
        String pLot = parkingLot.getText();
        String arrivalHour = arrivalHours.getText();
        String arrivalMinute = arrivalMinutes.getText();

        String leavingHour = leavingHours.getText();
        String leavingMinute = leavingMinutes.getText();
        if(arrivalDate.getValue() != null && leavingDate.getValue() != null) {
           arrivalDate1 = arrivalDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
           leavingDate1 = leavingDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        InAdvanceOrderMessage inAdvanceOrderMessage = new InAdvanceOrderMessage(carNumb,leavingMinute,leavingDate1
        ,leavingHour,arrivalMinute,arrivalDate1,arrivalHour,pLot);
        SimpleClient.getClient().sendToServer(inAdvanceOrderMessage);
    }

    @Subscribe
    public void PayProcess(InAdvanceOrderEvent event) throws IOException {
        //System.out.println(event.getMessage().getResult());
//        System.out.println("im in PayProcess");
//        System.out.println(event.getResult());
        if (event.getMessage().getResult()){
            Platform.runLater(new Runnable() {
                public void run() {
                    status.setText("");
//                    try {
//                        Navigate.navigate(event,"../payInAdvanceOrder.fxml");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../payInAdvanceOrder.fxml"));
                    Stage stage = new Stage();
                    try {
                        stage.setScene(new Scene(loader.load()));
                        PayInAdvanceOrder payInAdvanceOrder = loader.getController();
                        payInAdvanceOrder.setFee(event.getMessage().getFee());
                        payInAdvanceOrder.setOrderNum("10"+event.getMessage().getOrderId());

                        payInAdvanceOrder.setCarNumber(event.getMessage().getCarNumber());
                        payInAdvanceOrder.setArrivingDate(event.getMessage().getArrivingDate());
                        payInAdvanceOrder.setArrivingHours(event.getMessage().getArrivingHours());
                        payInAdvanceOrder.setArrivingMinutes(event.getMessage().getArrivingMinutes());
                        payInAdvanceOrder.setLeavingDate(event.getMessage().getLeavingDate());
                        payInAdvanceOrder.setLeavingHours(event.getMessage().getLeavingHours());
                        payInAdvanceOrder.setLeavingMinutes(event.getMessage().getLeavingMinutes());
                        payInAdvanceOrder.setParkingLot(event.getMessage().getParkingLot());
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    FXMLLoader tableViewParent = null;
//                    try {
//                        tableViewParent = new FXMLLoader(getClass().getResource("../userBoundary.fxml"));
//                        Scene tableViewScene = new Scene(tableViewParent.load());
//
//                        currentWindow.setScene(tableViewScene);
//                        currentWindow.show();
//                        UserBoundaryController user = tableViewParent.getController();
//                        user.setUser(idTxt.getText());
//                        // System.out.println(idTxt.getText());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            });
        }
        else {
            Platform.runLater(new Runnable() {
                public void run() {
                    System.out.println(event.getMessage().getResult());
                    status.setText("Could not place order. Please pick another time.");
                    status.setTextFill(Paint.valueOf("#df2c14"));
                }

             });
        }
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
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../userBoundary.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        UserBoundaryController inadv = tableViewParent.getController();
        inadv.setUser(id);

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

        ////////////////////////////////////////////////////
        MenuItem menuItemL0 = new MenuItem("00");
        MenuItem menuItemL1 = new MenuItem("01");
        MenuItem menuItemL2 = new MenuItem("02");
        MenuItem menuItemL3 = new MenuItem("03");
        MenuItem menuItemL4 = new MenuItem("04");
        MenuItem menuItemL5 = new MenuItem("05");
        MenuItem menuItemL6 = new MenuItem("06");
        MenuItem menuItemL7 = new MenuItem("07");
        MenuItem menuItemL8 = new MenuItem("08");
        MenuItem menuItemL9 = new MenuItem("09");
        MenuItem menuItemL10 = new MenuItem(String.valueOf(10));
        MenuItem menuItemL11 = new MenuItem(String.valueOf(11));
        MenuItem menuItemL12 = new MenuItem(String.valueOf(12));
        MenuItem menuItemL13 = new MenuItem(String.valueOf(13));
        MenuItem menuItemL14 = new MenuItem(String.valueOf(14));
        MenuItem menuItemL15 = new MenuItem(String.valueOf(15));
        MenuItem menuItemL16 = new MenuItem(String.valueOf(16));
        MenuItem menuItemL17 = new MenuItem(String.valueOf(17));
        MenuItem menuItemL18 = new MenuItem(String.valueOf(18));
        MenuItem menuItemL19 = new MenuItem(String.valueOf(19));
        MenuItem menuItemL20 = new MenuItem(String.valueOf(20));
        MenuItem menuItemL21 = new MenuItem(String.valueOf(21));
        MenuItem menuItemL22 = new MenuItem(String.valueOf(22));
        MenuItem menuItemL23 = new MenuItem(String.valueOf(23));
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
        menuItemL10.setOnAction(e -> {
            leavingHours.setText(String.valueOf(10));
        });
        menuItemL11.setOnAction(e -> {
            leavingHours.setText(String.valueOf(11));
        });
        menuItemL12.setOnAction(e -> {
            leavingHours.setText(String.valueOf(12));
        });
        menuItemL13.setOnAction(e -> {
            leavingHours.setText(String.valueOf(13));
        });
        menuItemL14.setOnAction(e -> {
            leavingHours.setText(String.valueOf(14));
        });
        menuItemL15.setOnAction(e -> {
            leavingHours.setText(String.valueOf(15));
        });
        menuItemL16.setOnAction(e -> {
            leavingHours.setText(String.valueOf(16));
        });
        menuItemL17.setOnAction(e -> {
            leavingHours.setText(String.valueOf(17));
        });
        menuItemL18.setOnAction(e -> {
            leavingHours.setText(String.valueOf(18));
        });
        menuItemL19.setOnAction(e -> {
            leavingHours.setText(String.valueOf(19));
        });
        menuItemL20.setOnAction(e -> {
            leavingHours.setText(String.valueOf(20));
        });
        menuItemL21.setOnAction(e -> {
            leavingHours.setText(String.valueOf(21));
        });
        menuItemL22.setOnAction(e -> {
            leavingHours.setText(String.valueOf(22));
        });
        menuItemL23.setOnAction(e -> {
            leavingHours.setText(String.valueOf(23));
        });

        leavingHours.getItems().addAll(menuItemL0,menuItemL1,menuItemL2,menuItemL3,menuItemL4,menuItemL5,menuItemL6
                ,menuItemL7,menuItemL8,menuItemL9,menuItemL10,menuItemL11,menuItemL12,menuItemL13,menuItemL14
                ,menuItemL15,menuItemL16,menuItemL17,menuItemL18,menuItemL19,menuItemL20,menuItemL21,menuItemL22,menuItemL23);
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

        ///////////////////////////////////////////////////////

        MenuItem menuItemL0 = new MenuItem("00");
        MenuItem menuItemL5 = new MenuItem("05");
        MenuItem menuItemL10 = new MenuItem(String.valueOf(10));
        MenuItem menuItemL15 = new MenuItem(String.valueOf(15));
        MenuItem menuItemL20 = new MenuItem(String.valueOf(20));
        MenuItem menuItemL25 = new MenuItem(String.valueOf(25));
        MenuItem menuItemL30 = new MenuItem(String.valueOf(30));
        MenuItem menuItemL35 = new MenuItem(String.valueOf(35));
        MenuItem menuItemL40 = new MenuItem(String.valueOf(40));
        MenuItem menuItemL45 = new MenuItem(String.valueOf(45));
        MenuItem menuItemL50 = new MenuItem(String.valueOf(50));
        MenuItem menuItemL55 = new MenuItem(String.valueOf(55));
        menuItemL0.setOnAction(e -> {
            leavingMinutes.setText("00");
        });
        menuItemL5.setOnAction(e -> {
            leavingMinutes.setText("05");
        });
        menuItemL10.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(10));
        });
        menuItemL15.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(15));
        });
        menuItemL20.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(20));
        });
        menuItemL25.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(25));
        });
        menuItemL30.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(30));
        });
        menuItemL35.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(35));
        });
        menuItemL40.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(40));
        });
        menuItemL45.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(45));
        });
        menuItemL50.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(50));
        });
        menuItemL55.setOnAction(e -> {
            leavingMinutes.setText(String.valueOf(55));
        });
        leavingMinutes.getItems().addAll(menuItemL0,menuItemL5,menuItemL10,menuItemL15,menuItemL20,menuItemL25
                ,menuItemL30,menuItemL35,menuItemL40,menuItemL45,menuItemL50,menuItemL55);
    }
}
