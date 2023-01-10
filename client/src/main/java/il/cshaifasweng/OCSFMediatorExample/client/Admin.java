package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.Navigate;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.AdminMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.SetUpMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Subscriber;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Admin {
    private ArrayList<Subscriber> list = new ArrayList<>();
    @FXML
    private Button subsbtn;

    @FXML
    private TableView<Subscriber> subscolumn;

    @FXML
    private TableColumn<Subscriber, Integer> subs;

    @FXML
    private Button backBtn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Navigate.navigate(event , "../mainPage.fxml");
    }
    @FXML
    void showAllsubs(ActionEvent event) throws IOException {
        AdminMessage message = new AdminMessage(list);
        SimpleClient.getClient().sendToServer(message);
    }
    @Subscribe
    public void setTableview(showSubsForAdminEvent event)
    {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    subs.setCellValueFactory(new PropertyValueFactory<>("id"));
                    ObservableList<Subscriber> prices = FXCollections.observableArrayList();
                    subscolumn.setItems(prices);
                    for (Subscriber p : event.getSubs()){
                        subscolumn.getItems().add(p);
                    }

                }
            });
        }




    @FXML
    void initialize() {

        EventBus.getDefault().register(this);
    }
}




