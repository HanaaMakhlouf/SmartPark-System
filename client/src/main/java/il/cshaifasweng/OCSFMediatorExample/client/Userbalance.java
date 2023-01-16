package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.UserBoundaryController;
import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetBalance;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetallOrdersOfClient;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;

public class Userbalance {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @FXML
    private Button backbtn;

    @FXML
    private Label balancelabel;

    private String id;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
        new Thread(() -> {
            try {
                    GetBalance msg = new GetBalance(id);
                    SimpleClient.getClient().sendToServer(msg); } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }));


    @FXML
    void backto(ActionEvent event) throws IOException {

        timeline.stop();
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("userBoundary.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        UserBoundaryController inadv = tableViewParent.getController();
        inadv.setUser(id);
    }

    @Subscribe
     public void Balance(SetBalanceEvent event)
    {
        Platform.runLater(new Runnable() {
            public void run() {

                try {
                    balancelabel.setText(": "+Double.toString(event.getBalance())+"₪");
                    balancelabel.setMinWidth(0);
                    balancelabel.setPrefWidth(Control.USE_COMPUTED_SIZE);
                    balancelabel.setMaxWidth(Double.MAX_VALUE);
                    balancelabel.setTextFill(Color.MEDIUMPURPLE);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    });
    }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
