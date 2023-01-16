package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.UserBoundaryController;
import il.cshaifasweng.OCSFMediatorExample.entities.ChangePricesRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Prices;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;

public class Newprices {

    @FXML
    private TableColumn<Prices, Integer> fullmem;

    @FXML
    private TextField fulltxt;

    @FXML
    private TableColumn<Prices, Integer> inadv;

    @FXML
    private TextField inadvtxt;

    @FXML
    private TableColumn<Prices, Integer> inplace;

    @FXML
    private TextField inplacetxt;

    @FXML
    private Label labelmsg;

    @FXML
    private TableView<Prices> mytable;

    @FXML
    private TextField regmtxt;

    @FXML
    private TableColumn<Prices,Integer > regmult;

    @FXML
    private TableColumn<Prices, Integer> regsingle;

    @FXML
    private TextField regstxt;

    @FXML
    private Button sendbtn;
    @FXML
    private Button backbtn;

    private String Mangerid;

    public String getMangerid() {
        return Mangerid;
    }

    public void setMangerid(String mangerid) {
        Mangerid = mangerid;
    }
    @FXML
    void addRequest(ActionEvent event) throws IOException {
        if (inadvtxt.getText().matches("^\\d+$") && inplacetxt.getText().matches("^\\d+$") && regstxt.getText().matches("^\\d+$")&& regmtxt.getText().matches("^\\d+$")&& fulltxt.getText().matches("^\\d+$")) {
            ChangePricesRequest newPrices = new ChangePricesRequest(Mangerid,Integer.parseInt(inadvtxt.getText()),Integer.parseInt(inplacetxt.getText()),Integer.parseInt(regstxt.getText()),Integer.parseInt(regmtxt.getText()),Integer.parseInt(fulltxt.getText()));
            SimpleClient.getClient().sendToServer(newPrices);

            labelmsg.setText("Request sent to General Manager");
            labelmsg.setMinWidth(0);
            labelmsg.setPrefWidth(Control.USE_COMPUTED_SIZE);
            labelmsg.setMaxWidth(Double.MAX_VALUE);
            labelmsg.setTextFill(Color.GREEN);
            FadeTransition ft = new FadeTransition(Duration.seconds(10), labelmsg);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();
        } else {
            labelmsg.setText("you have an error in one or more values , please input only positive numbers ");
            labelmsg.setMinWidth(0);
            labelmsg.setPrefWidth(Control.USE_COMPUTED_SIZE);
            labelmsg.setMaxWidth(Double.MAX_VALUE);
            labelmsg.setTextFill(Color.RED);
            FadeTransition ft = new FadeTransition(Duration.seconds(10), labelmsg);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();
        }
    
    }

    @Subscribe
    public void setpTableviewFromServer(showptableEvent event)
    {

        inadv.setCellValueFactory(new PropertyValueFactory<>("in_Advance_price"));
        inplace.setCellValueFactory(new PropertyValueFactory<>("in_place_price"));
        regsingle.setCellValueFactory(new PropertyValueFactory<>("single_car_reg_mem_price"));
        regmult.setCellValueFactory(new PropertyValueFactory<>("multiple_cars_reg_mem_price"));
        fullmem.setCellValueFactory(new PropertyValueFactory<>("full_mem_price"));

        ObservableList<Prices> prices = FXCollections.observableArrayList();
// Add ParkingLot objects to the list

        mytable.setItems(prices);

        for (Prices p : event.getPlist()){
            mytable.getItems().add(p);
        }
    }
void updateTableView() throws IOException {
    Message message = new Message(0, "print prices table");
    SimpleClient.getClient().sendToServer(message);


}


    @FXML
    void backto(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("managerBoundary.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        ManagerController inadv = tableViewParent.getController();
        inadv.setManager(Mangerid);
    }


    public void initialize() {

        EventBus.getDefault().register(this);
    }
}
