package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.*;
import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetBalance;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetallOrdersOfClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.LogoutMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Prices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.InflaterInputStream;

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

    public String getId() {
        return id;
    }

    @FXML
    void cancelOrder(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../userbalance.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        GetBalance msg = new GetBalance(id);
        SimpleClient.getClient().sendToServer(msg);
        currentWindow.show();
        Userbalance inadv = tableViewParent.getController();
        System.out.println("user id is "+id);
        inadv.setId(id);

    }

    @FXML
    void enterWithOrder(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../enterWithOrder.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        EnterWithOrder inadv = tableViewParent.getController();
//        System.out.println("user id is "+id);
        inadv.setUserId(id);
    }

    @FXML
    void enterWithOutOrder(ActionEvent event)throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../enterWithOutOrder.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        EnterWithOutOrder inadv = tableViewParent.getController();
        inadv.setId(id);
    }

    @FXML
    void exitParkingLot(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        LogoutMessage l = new LogoutMessage(Integer.parseInt(id));
        SimpleClient.getClient().sendToServer(l);
        Navigate.navigate(event , "../mainPage.fxml");
    }

    @FXML
    void inAdvancedOrder(ActionEvent event)throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../inAdvanceOrder.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        InAdvanceOrder inadv = tableViewParent.getController();
        System.out.println("user id is "+id);
        inadv.setId(id);

    }

    @FXML
    void registerAsAMember(ActionEvent event) throws IOException {

        FXMLLoader tableViewParent = null;
        try {
            tableViewParent = new FXMLLoader(getClass().getResource("../registerAsAMember.fxml"));
            Scene tableViewScene = new Scene(tableViewParent.load());
            Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentWindow.setScene(tableViewScene);
            currentWindow.show();
            RegisterAsAMember registerAsAMember = tableViewParent.getController();
            registerAsAMember.setId(this.id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void sendComplaint(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../sendComplaint1.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        SendComplaintController complaint = tableViewParent.getController();
        complaint.setSenderId(this.id);
    }

    @FXML
    void trackOrder(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../trackorders.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        ArrayList<InAdvanceOrderEntity> list = new ArrayList<>();
        GetallOrdersOfClient msg = new GetallOrdersOfClient();
        msg.setId(id);
        msg.setLst(list);
        SimpleClient.getClient().sendToServer(msg);
        currentWindow.show();

        /*InAdvanceOrder inadv = tableViewParent.getController();
        System.out.println("user id is "+id);
        inadv.setId(id);*/

    }


}


//   MIGHT NEED IN THE FUTURE

//        FXMLLoader tableViewParent = null;
//        try {
//            tableViewParent = new FXMLLoader(getClass().getResource("../inAdvanceOrder.fxml"));
//            Scene tableViewScene = new Scene(tableViewParent.load());
//            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//            window.setScene(tableViewScene);
//            window.show();
//            InAdvanceOrder inAdvanceOrder = tableViewParent.getController();
//            inAdvanceOrder.setUp();
////            user.setUser(idTxt.getText());
//            // System.out.println(idTxt.getText());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }











