package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.SignUpEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.SignUpMessage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class SignUp {
    @FXML
    private Button backbt;

    @FXML
    private TextField emailtxt;

    @FXML
    private TextField idtxt;

    @FXML
    private TextField passwordtxt;

    @FXML
    private Button signUpBtn;
    @FXML
    private Label status;

    @FXML
    void SignUp(ActionEvent event) throws IOException {
        String userId = idtxt.getText();
        String userPass = passwordtxt.getText();
        String userEmail = emailtxt.getText();
        SignUpMessage msg = new SignUpMessage(userId,userPass, userEmail);
        SimpleClient.getClient().sendToServer(msg);
    }

    @Subscribe
    public void SignUpProcess(SignUpEvent event) throws IOException {
        if (event.getResult()){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    status.setText("Sign Up Successful!");
                    status.setTextFill(Paint.valueOf("#228c22"));

                }
            });
        }
        else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Sign Up Failed");
                alert.setTitle("Error!");
                alert.setHeaderText("Error:");
                alert.show();
            });
        }
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Navigate.navigate(event , "../mainPage.fxml");
    }
}
