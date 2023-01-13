package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.PayInAdvanceOrderEvent;
import il.cshaifasweng.OCSFMediatorExample.client.PayStandardMembershipEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.FullMemberShipEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.PayFullMembershipMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.PayInAdvanceOrderMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.PayStandardMembershipMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.StandardMemberShipEntity;
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

public class PayStandardMembership {

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

    private String carNumber;
    private String leavingMinutes ;
    private String leavingDate ;
    private String leavingHours ;
    private String arrivingMinutes ;
    private String arrivingDate ;
    private String arrivingHours ;
    private String parkingLot;
    private String orderId;
    StandardMemberShipEntity standardMemberShipEntity;
    double fee;
    String orderNum;
    int id;


    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        setMenuItemsMonths();
        setMenuItemsYears();

    }

    @FXML
    void Pay(ActionEvent event) throws IOException {
        String cardName = nameOnCard.getText();
        String cardNum = cardNumber.getText();
        String cvvNum = cvv.getText();
        String cardYear = yearPayment.getText();
        String cardMonth = monthPayment.getText();
        PayStandardMembershipMessage message = new PayStandardMembershipMessage(cardName,cardNum,standardMemberShipEntity
                ,cvvNum, cardYear ,cardMonth);
        SimpleClient.getClient().sendToServer(message);
    }

    public StandardMemberShipEntity getStandardMemberShipEntity() {
        return standardMemberShipEntity;
    }

    public void setStandardMemberShipEntity(StandardMemberShipEntity standardMemberShipEntity) {
        this.standardMemberShipEntity = standardMemberShipEntity;
    }

    public int getId() {
        return id;
    }

    public void setBackBt(Button backBt) {
        this.backBt = backBt;
    }

    public void setFee(double fee) {
        this.fee = fee;
        cost.setText(String.valueOf(fee));
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
        orderNumber.setText(orderNum);
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getFee() {
        return fee;
    }

    public String getOrderNum() {
        return orderNum;
    }


    @Subscribe
    public void payStandardMembership(PayStandardMembershipEvent event){
        Platform.runLater(new Runnable() {
            public void run() {
                orderNumber.setText(event.getMessage().getMembershipId());
                orderNumber.setTextFill(Paint.valueOf("#57cd13"));
            }
        });
    }

    @FXML
    void back(ActionEvent event)throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../userBoundary.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        UserBoundaryController inadv = tableViewParent.getController();
        inadv.setUser(String.valueOf(standardMemberShipEntity.getId()));
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setLeavingMinutes(String leavingMinutes) {
        this.leavingMinutes = leavingMinutes;
    }

    public void setLeavingDate(String leavingDate) {
        this.leavingDate = leavingDate;
    }

    public void setLeavingHours(String leavingHours) {
        this.leavingHours = leavingHours;
    }

    public void setArrivingMinutes(String arrivingMinutes) {
        this.arrivingMinutes = arrivingMinutes;
    }

    public void setArrivingDate(String arrivingDate) {
        this.arrivingDate = arrivingDate;
    }

    public void setArrivingHours(String arrivingHours) {
        this.arrivingHours = arrivingHours;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getLeavingMinutes() {
        return leavingMinutes;
    }

    public String getLeavingDate() {
        return leavingDate;
    }

    public String getLeavingHours() {
        return leavingHours;
    }

    public String getArrivingMinutes() {
        return arrivingMinutes;
    }

    public String getArrivingDate() {
        return arrivingDate;
    }

    public String getArrivingHours() {
        return arrivingHours;
    }

    public String getParkingLot() {
        return parkingLot;
    }

    private void setMenuItemsMonths() {
        MenuItem menuItem1 = new MenuItem("01");
        MenuItem menuItem2 = new MenuItem("02");
        MenuItem menuItem3 = new MenuItem("03");
        MenuItem menuItem4 = new MenuItem("04");
        MenuItem menuItem5 = new MenuItem("05");
        MenuItem menuItem6 = new MenuItem("06");
        MenuItem menuItem7 = new MenuItem("07");
        MenuItem menuItem8 = new MenuItem("08");
        MenuItem menuItem9 = new MenuItem("09");
        MenuItem menuItem10 = new MenuItem("10");
        MenuItem menuItem11 = new MenuItem("11");
        MenuItem menuItem12 = new MenuItem("12");
        menuItem1.setOnAction(e -> {
            monthPayment.setText("01");
        });
        menuItem2.setOnAction(e -> {
            monthPayment.setText("02");
        });
        menuItem3.setOnAction(e -> {
            monthPayment.setText("03");
        });
        menuItem4.setOnAction(e -> {
            monthPayment.setText("04");
        });
        menuItem5.setOnAction(e -> {
            monthPayment.setText("05");
        });
        menuItem6.setOnAction(e -> {
            monthPayment.setText("06");
        });
        menuItem7.setOnAction(e -> {
            monthPayment.setText("07");
        });
        menuItem8.setOnAction(e -> {
            monthPayment.setText("08");
        });
        menuItem9.setOnAction(e -> {
            monthPayment.setText("09");
        });
        menuItem10.setOnAction(e -> {
            monthPayment.setText("10");
        });
        menuItem11.setOnAction(e -> {
            monthPayment.setText(String.valueOf(11));
        });
        menuItem12.setOnAction(e -> {
            monthPayment.setText(String.valueOf(12));
        });
        monthPayment.getItems().addAll(menuItem1, menuItem2, menuItem3,
                menuItem4, menuItem5, menuItem6, menuItem7, menuItem8
                , menuItem9, menuItem10, menuItem11, menuItem12);

    }
    private void setMenuItemsYears() {
        MenuItem item24 = new MenuItem("24");
        MenuItem item25 = new MenuItem("25");
        MenuItem item26 = new MenuItem("26");
        MenuItem item27 = new MenuItem("27");
        MenuItem item28 = new MenuItem("28");

        item24.setOnAction(e -> {
            yearPayment.setText("24");
        });
        item25.setOnAction(e -> {
            yearPayment.setText("25");
        });
        item26.setOnAction(e -> {
            yearPayment.setText("26");
        });
        item27.setOnAction(e -> {
            yearPayment.setText("27");
        });
        item28.setOnAction(e -> {
            yearPayment.setText("28");
        });

        yearPayment.getItems().addAll(item24, item25, item26,
                item27, item28);

    }
}
