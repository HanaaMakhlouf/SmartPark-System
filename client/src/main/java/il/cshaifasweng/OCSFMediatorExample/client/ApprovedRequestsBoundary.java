package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ChangePricesRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.*;
import il.cshaifasweng.OCSFMediatorExample.entities.Prices;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApprovedRequestsBoundary {

    @FXML
    private TableColumn<ChangePricesRequest, Integer> Appfullmem1;

    @FXML
    private TableColumn<ChangePricesRequest, Integer> Appinadv1;

    @FXML
    private TableColumn<ChangePricesRequest, Integer> Appinplace1;

    @FXML
    private TableColumn<ChangePricesRequest, Integer> Appregm;

    @FXML
    private TableColumn<ChangePricesRequest, Integer> Appregs;

    @FXML
    private TableColumn<ChangePricesRequest, Integer> Appreqid;

    @FXML
    private Button changepricesbtn;

    @FXML
    private ComboBox<Integer> combobox;

    @FXML
    private TableColumn<Prices, Integer> fullmem;

    @FXML
    private TableColumn<Prices, Integer> inadv;

    @FXML
    private TableColumn<Prices, Integer> inplace;

    @FXML
    private Label labelmsg;

    @FXML
    private TableView<Prices> mytable;

    @FXML
    private TableView<ChangePricesRequest> mytable1;

    @FXML
    private TableColumn<Prices, Integer> regmult;

    @FXML
    private TableColumn<Prices, Integer> regsingle;
    private String managerID;

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
        new Thread(() -> {
            try {
                Message message = new Message(1, "print prices table");
                SimpleClient.getClient().sendToServer(message);
                ArrayList<ChangePricesRequest> list = new ArrayList<>();
                ShowRequestForManager msg = new ShowRequestForManager();
                msg.setList(list);
                msg.setManagerid(managerID);
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }));
    @Subscribe
    public void setpTableviewFromServer(showptableEventTwo event)
    {

        inadv.setCellValueFactory(new PropertyValueFactory<>("in_Advance_price"));
        inplace.setCellValueFactory(new PropertyValueFactory<>("in_place_price"));
        regsingle.setCellValueFactory(new PropertyValueFactory<>("single_car_reg_mem_price"));
        regmult.setCellValueFactory(new PropertyValueFactory<>("multiple_cars_reg_mem_price"));
        fullmem.setCellValueFactory(new PropertyValueFactory<>("full_mem_price"));

        ObservableList<Prices> prices = FXCollections.observableArrayList();
// Add ParkingLot objects to the list

        mytable.setItems(prices);

        for (Prices p : event.getPlist()){
            mytable.getItems().add(p);
        }
    }

    @Subscribe
    public void showRequests(ShowChangePricesRequestEventTwo event) {

        System.out.println("hi");

        Platform.runLater(new Runnable() {
            public void run() {
                List<ChangePricesRequest> lst = event.getLst();
                System.out.println("we here?");
                Appinadv1.setCellValueFactory(new PropertyValueFactory<>("inAdv"));
                Appinplace1.setCellValueFactory(new PropertyValueFactory<>("inPlace"));
                Appregs.setCellValueFactory(new PropertyValueFactory<>("regMemS"));
                Appregm.setCellValueFactory(new PropertyValueFactory<>("regMemM"));
                Appfullmem1.setCellValueFactory(new PropertyValueFactory<>("fullMem"));
                Appreqid.setCellValueFactory(new PropertyValueFactory<>("requestID"));


                ObservableList<ChangePricesRequest> oblist = FXCollections.observableArrayList();

                mytable1.setItems(oblist);

                for (ChangePricesRequest p : event.getLst()) {
                   if(p.isGMapprove()) mytable1.getItems().add(p);
                }

                TableColumn<ChangePricesRequest, Integer> comboColumn = new TableColumn<>("Requests");
                comboColumn.setCellValueFactory(new PropertyValueFactory<>("requestID"));

                List<Integer> orderIds = lst.stream().filter(p -> p.isGMapprove() == true).map(ChangePricesRequest::getRequestID).distinct().collect(Collectors.toList());
                    combobox.getItems().clear();
                    combobox.getItems().addAll(orderIds);

                    comboColumn.setCellFactory(col -> {
                        TableCell<ChangePricesRequest, Integer> cell = new TableCell<ChangePricesRequest, Integer>() {
                            @Override
                            protected void updateItem(Integer item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    combobox.setValue(item);
                                    setGraphic(combobox);
                                }
                            }
                        };
                        return cell;
                    });
            }
        });

    }

    @FXML
    void ChangePrices(ActionEvent event) throws IOException {
        Integer ApprovedRequest = combobox.getSelectionModel().getSelectedItem();
        if(ApprovedRequest !=null) {
            PricesRequestToApply msg = new PricesRequestToApply(ApprovedRequest);
            SimpleClient.getClient().sendToServer(msg);

            labelmsg.setText("Prices are changed Successfully");
            labelmsg.setMinWidth(0);
            labelmsg.setPrefWidth(Control.USE_COMPUTED_SIZE);
            labelmsg.setMaxWidth(Double.MAX_VALUE);
            labelmsg.setTextFill(Color.GREEN);
            FadeTransition ft = new FadeTransition(Duration.seconds(10), labelmsg);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();
            combobox.getItems().remove(combobox.getSelectionModel().getSelectedItem());


        }
        else {
            labelmsg.setText("please choose the request ID to change prices");
            labelmsg.setMinWidth(0);
            labelmsg.setPrefWidth(Control.USE_COMPUTED_SIZE);
            labelmsg.setMaxWidth(Double.MAX_VALUE);
            labelmsg.setTextFill(Color.RED);
            FadeTransition ft = new FadeTransition(Duration.seconds(10), labelmsg);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();

        }

    }
    @FXML
    void backto(ActionEvent event) throws IOException {
        timeline.stop();
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("managerBoundary.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        ManagerController inadv = tableViewParent.getController();
        inadv.setManager(managerID);
    }


    public void initialize() {

        EventBus.getDefault().register(this);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
