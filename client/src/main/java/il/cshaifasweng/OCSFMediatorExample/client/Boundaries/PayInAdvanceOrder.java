package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.PayInAdvanceOrderEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.PayInAdvanceOrderMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

    private String carNumber;
    private String leavingMinutes ;
    private String leavingDate ;
    private String leavingHours ;
    private String arrivingMinutes ;
    private String arrivingDate ;
    private String arrivingHours ;
    private String parkingLot;
    private String orderId;
    double fee;
    String orderNum;
    int id;


    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
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

    @FXML
    void Pay(ActionEvent event) throws IOException {
        String cardName = nameOnCard.getText();
        String cardNum = cardNumber.getText();
        String cvvNum = cvv.getText();
        String cardYear = yearPayment.getText();
        String cardMonth = monthPayment.getText();
        PayInAdvanceOrderMessage message = new PayInAdvanceOrderMessage(cardName,cardNum,carNumber,leavingDate,leavingHours
                ,leavingMinutes,arrivingDate,arrivingHours,arrivingMinutes,parkingLot,orderId);
        SimpleClient.getClient().sendToServer(message);
    }

    @Subscribe
    public void do_something(PayInAdvanceOrderEvent event){
        System.out.println("im in do_something");
    }

    @FXML
    void back(ActionEvent event)throws IOException {
        Navigate.navigate(event , "../userBoundary.fxml");
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



}
