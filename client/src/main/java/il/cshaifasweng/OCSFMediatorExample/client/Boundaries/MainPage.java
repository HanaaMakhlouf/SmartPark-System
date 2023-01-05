
package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.PricesTable;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.logInEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.logInMessage;
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
        if (event.getResult()){
            Platform.runLater(new Runnable() {
                public void run() {
                    FXMLLoader tableViewParent = null;
                    try {
                        tableViewParent = new FXMLLoader(getClass().getResource("../userBoundary.fxml"));
                        Scene tableViewScene = new Scene(tableViewParent.load());

                        currentWindow.setScene(tableViewScene);
                        currentWindow.show();
                        UserBoundaryController user = tableViewParent.getController();
                        user.setUser(idTxt.getText());
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
