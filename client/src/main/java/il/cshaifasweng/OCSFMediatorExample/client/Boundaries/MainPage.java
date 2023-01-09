
package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.*;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.logInEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.logInMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLotEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.Subscribe;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;

public class MainPage {

    @FXML // fx:id="idTxt"
    private TextField idTxt; // Value injected by FXMLLoader

    @FXML // fx:id="loginBtn"
    private Button loginBtn; // Value injected by FXMLLoader

    @FXML // fx:id="memberBtn"
    private Button memberBtn; // Value injected by FXMLLoader

    @FXML // fx:id="passTxt"
    private TextField passTxt; // Value injected by FXMLLoader

    @FXML // fx:id="signupBtn"
    private Button signupBtn; // Value injected by FXMLLoader
    private int msgId;
    private Stage currentWindow;

    @FXML
    void login(ActionEvent event) throws IOException {
        String userId = idTxt.getText();
        String userPass = passTxt.getText();
        logInMessage msg = new logInMessage(userId,userPass);
        SimpleClient.getClient().sendToServer(msg);
        currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("userBoundary.fxml"));
//		System.out.println("here1");
//		Stage stage = new Stage();
//		System.out.println("here2");
//		stage.setScene(new Scene(loader.load()));
//		System.out.println("here3");
//		stage.show();
    }

    @Subscribe
    public void logInProcess(logInEvent event) throws IOException {
        if (event.getResult() != 0){
            Platform.runLater(new Runnable() {
                public void run() {
                    FXMLLoader tableViewParent = null;
                    try {
                        if(event.getResult() == 6) {  tableViewParent = new FXMLLoader(getClass().getResource("../admin.fxml"));
                            Scene tableViewScene = new Scene(tableViewParent.load());
                            currentWindow.setScene(tableViewScene);
                            currentWindow.show();
                            CustomerServiceEmployeeController cs_employee = tableViewParent.getController();
                            cs_employee.setCS_employee(idTxt.getText()); }
                        if(event.getResult() == 5) {
                            tableViewParent = new FXMLLoader(getClass().getResource("../customerServiceEmployeeBoundary.fxml"));
                            Scene tableViewScene = new Scene(tableViewParent.load());
                            currentWindow.setScene(tableViewScene);
                            currentWindow.show();
                            CustomerServiceEmployeeController cs_employee = tableViewParent.getController();
                            cs_employee.setCS_employee(idTxt.getText());
                        }
                        if(event.getResult() == 4) {
                            tableViewParent = new FXMLLoader(getClass().getResource("../userBoundary.fxml"));
                            Scene tableViewScene = new Scene(tableViewParent.load());
                            currentWindow.setScene(tableViewScene);
                            currentWindow.show();
                            UserBoundaryController user = tableViewParent.getController();
                            user.setUser(idTxt.getText());
                        }
                        else if(event.getResult() == 3) {
                            tableViewParent = new FXMLLoader(getClass().getResource("../ParkingLotEmployeeBoundary.fxml"));
                            Scene tableViewScene = new Scene(tableViewParent.load());
                            currentWindow.setScene(tableViewScene);
                            currentWindow.show();
                            ParkingLotEmployeeController employee = tableViewParent.getController();
                            employee.setEmployee(idTxt.getText());
                        }
                        else if(event.getResult() == 2) {
                            tableViewParent = new FXMLLoader(getClass().getResource("../managerBoundary.fxml"));
                            Scene tableViewScene = new Scene(tableViewParent.load());
                            currentWindow.setScene(tableViewScene);
                            currentWindow.show();
                            ManagerController manager = tableViewParent.getController();
                            manager.setManager(idTxt.getText());
                        }
                        else if(event.getResult() == 1) {
                            tableViewParent = new FXMLLoader(getClass().getResource("../generalManageBoundary.fxml"));
                            Scene tableViewScene = new Scene(tableViewParent.load());
                            currentWindow.setScene(tableViewScene);
                            currentWindow.show();
                            GeneralManagerController gm = tableViewParent.getController();
                            gm.setGM(idTxt.getText());
                        }

                        System.out.println(idTxt.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Incorrect Id or Password");
                alert.setTitle("Error!");
                alert.setHeaderText("Error:");
                alert.show();
            });
        }
    }

    //		Parent tableViewParent = FXMLLoader.load(getClass().getResource("pricesTable.fxml"));
//		Scene tableViewScene = new Scene(tableViewParent);
//		Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//		window.setScene(tableViewScene);
//		window.show();

    @FXML
    void signinmember(ActionEvent event) throws IOException {
    }

    @FXML
    void signup(ActionEvent event) throws IOException {
        Navigate.navigate(event , "../signUp.fxml");
        //   FXMLLoader loader = new FXMLLoader(getClass().getResource("../signUp.fxml"));
        //  Stage stage = new Stage();
        // stage.setScene(new Scene(loader.load()));
        //  stage.show();
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
// Set the items of the TableView to the ObservableList
        msgId=0;
        try {
            Message message = new Message(msgId++, "add client");
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
