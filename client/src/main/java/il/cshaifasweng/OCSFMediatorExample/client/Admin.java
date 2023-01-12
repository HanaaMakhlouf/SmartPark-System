package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.AdminMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.MessageBetweenClients;
import il.cshaifasweng.OCSFMediatorExample.entities.Subscriber;


import il.cshaifasweng.OCSFMediatorExample.entities.Subscriber;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Admin {
    private ArrayList<Subscriber> list = new ArrayList<>();
    @FXML
    private Button subsbtn;
    @FXML
    private TextField idtf;
    @FXML
    private Label status;
    @FXML
    private TextField msgTF;
    @FXML
    private TextField MSGfromOthersTF;
    @FXML
    private Button sendBtn;
    @FXML
    private TableView<Subscriber> subscolumn;

    @FXML
    private TableColumn<Subscriber, Integer> subs;

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
    void sendMessage(ActionEvent event) throws IOException {
        String message = msgTF.getText();
        int id = Integer.parseInt(idtf.getText());
        MessageBetweenClients msg = new MessageBetweenClients(id,message);
        SimpleClient.getClient().sendToServer(msg);
    }

    @Subscribe
    public void showMessage(ShowMessageFromOthersEvent event)
    {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
               String s = "this id "+ Integer.toString(event.getMsg().getRecepientID())+ "sent you this"+ event.getMsg().getMessage();
                MSGfromOthersTF.setText(s);

            }
        });
    }

    @Subscribe
    public void ShowResultLabel(ShowSendResultevent event)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
               if(event.getResult()==1)
               {
                  status.setText("send success");

               }
               else {
                   status.setText("send failed");
               }
            }
        });


    }

    @FXML
    void initialize() {

        EventBus.getDefault().register(this);
    }



    }





