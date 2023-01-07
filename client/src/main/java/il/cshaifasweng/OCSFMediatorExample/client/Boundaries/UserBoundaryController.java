package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.PricesTable;
import il.cshaifasweng.OCSFMediatorExample.entities.Prices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class UserBoundaryController {

    @FXML
    private Button backBtn;

    @FXML
    private Button cancelBt;

    @FXML
    private Button complaintBt;

    @FXML
    private Button enterWithOrderBt;

    @FXML
    private Button enterWithOutOrderBt;

    @FXML
    private Button exitBt;

    @FXML
    private Button inAdvancedOrderBt;

    @FXML
    private Button registerAsAMemberBt;

    @FXML
    private Button trackBt;

    private String id ;

    public void setUser(String id){
        this.id = id ;
    }

    @FXML
    void cancelOrder(ActionEvent event) throws IOException{
        Navigate.navigate(event , "../cancelOrder.fxml");
    }

    @FXML
    void enterWithOrder(ActionEvent event) {

    }

    @FXML
    void enterWithOutOrder(ActionEvent event)throws IOException {
        Navigate.navigate(event , "../enterWithOutOrder.fxml");
    }

    @FXML
    void exitParkingLot(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Navigate.navigate(event , "../mainPage.fxml");
    }

    @FXML
    void inAdvancedOrder(ActionEvent event)throws IOException {
        Navigate.navigate(event , "../inAdvanceOrder.fxml");
    }

    @FXML
    void registerAsAMember(ActionEvent event) throws IOException {
        Navigate.navigate(event , "../registerAsAMember.fxml");
    }

    @FXML
    void sendComplaint(ActionEvent event) throws IOException {
        Navigate.navigate(event , "../sendComplaint.fxml");
    }

    @FXML
    void trackOrder(ActionEvent event) {

    }

}















