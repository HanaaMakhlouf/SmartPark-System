package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.CustomerServiceEmployeeController;
import il.cshaifasweng.OCSFMediatorExample.client.SendComplaintController;
import il.cshaifasweng.OCSFMediatorExample.client.ShowComplaintsEvent;
import il.cshaifasweng.OCSFMediatorExample.client.TrackComplaintEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
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
        assert comp != null : "fx:id=\"comp\" was not injected: check your FXML file 'trackComplaints.fxml'.";
        assert res != null : "fx:id=\"res\" was not injected: check your FXML file 'trackComplaints.fxml'.";

    }

}
