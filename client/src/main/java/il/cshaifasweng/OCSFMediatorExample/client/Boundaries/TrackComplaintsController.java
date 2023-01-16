package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.*;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetComplaintsMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class TrackComplaintsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Complaint, String > comp;

    @FXML
    private TableColumn<Complaint, String> res;

    @FXML
    private TableView<Complaint> mytable;

    Timeline timeline = new Timeline((new KeyFrame(Duration.seconds(3), event ->{
        new Thread(()->{
            try {
                ArrayList<Complaint> list = new ArrayList<>();
                GetComplaintsMessage msg = new GetComplaintsMessage(list,String.valueOf(this.id));
                msg.setGetForWhom(2);

                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    })));

    @Subscribe
    public void trackComplaints(TrackComplaintEvent event){
        Platform.runLater(new Runnable() {
            public void run() {

                List<Complaint> lst = event.getMsg().getLst();
                comp.setCellValueFactory(new PropertyValueFactory<>("Description"));
                res.setCellValueFactory(new PropertyValueFactory<>("Response"));

                ObservableList<Complaint> oblist = FXCollections.observableArrayList();
                mytable.setItems(oblist);

                for (Complaint p : event.getMsg().getLst()) {
                    if(Integer.valueOf(p.getId()) == getId())
                    {
                        mytable.getItems().add(p);
                    }
                }

            }
        });
    }


    @FXML
    void goBack(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../sendComplaint1.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        SendComplaintController user = tableViewParent.getController();
        user.setSenderId(String.valueOf(getId()));
        timeline.stop();
    }


    private int id;

    public int getId() {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }


    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        assert comp != null : "fx:id=\"comp\" was not injected: check your FXML file 'trackComplaints.fxml'.";
        assert res != null : "fx:id=\"res\" was not injected: check your FXML file 'trackComplaints.fxml'.";

    }

}
