package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ChangePricesRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ApproveNewPrices;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetallOrdersOfClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ShowRequestForGM;
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

public class GManagerrequests {


    private String id;
    @FXML
    private Button approvebtn;

    @FXML
    private Button backbtn;

    @FXML
    private TableColumn<ChangePricesRequest, Integer> fullmem;

    @FXML
    private TableColumn<ChangePricesRequest, Integer>  inadv;

    @FXML
    private TableColumn<ChangePricesRequest, Integer> inplace;

    @FXML
    private TableColumn<ChangePricesRequest, String>  managerid;

    @FXML
    private TableColumn<ChangePricesRequest, Integer>  regm;

    @FXML
    private TableColumn<ChangePricesRequest, Integer>  regs;

    @FXML
    private TableColumn<ChangePricesRequest, Integer>  reqid;
    @FXML
    private ComboBox<Integer> combobx;

    @FXML
    private Label labelmsg;
    @FXML
    private TableView<ChangePricesRequest> mytable;


    @FXML
    private Button reject;

    @FXML
    void approve(ActionEvent event) throws IOException {
        Integer s = combobx.getSelectionModel().getSelectedItem();
        if(s !=null) {
            ApproveNewPrices msg = new ApproveNewPrices(s);
            msg.setApprove(true);
            SimpleClient.getClient().sendToServer(msg);

            labelmsg.setText("Request Approved successfully !");
            labelmsg.setMinWidth(0);
            labelmsg.setPrefWidth(Control.USE_COMPUTED_SIZE);
            labelmsg.setMaxWidth(Double.MAX_VALUE);
            labelmsg.setTextFill(Color.GREEN);
            FadeTransition ft = new FadeTransition(Duration.seconds(10), labelmsg);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();
            combobx.getItems().remove(combobx.getSelectionModel().getSelectedItem());


        }
        else  {
            labelmsg.setText("please choose Request ID first");
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

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
        new Thread(() -> {
            try {
                ArrayList<ChangePricesRequest> list = new ArrayList<>();
                ShowRequestForGM msg = new ShowRequestForGM();
                msg.setList(list);
                SimpleClient.getClient().sendToServer(msg);
                } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }));

    @FXML
    void backto(ActionEvent event) throws IOException {
        timeline.stop();
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("generalManageBoundary.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        GeneralManagerController inadv = tableViewParent.getController();
        inadv.setGM(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Subscribe
    public void showRequests(ShowChangePricesRequestEvent event) {

        System.out.println("hi");

        Platform.runLater(new Runnable() {
            public void run() {
                List<ChangePricesRequest> lst = event.getLst();
                System.out.println("we here?");
                inadv.setCellValueFactory(new PropertyValueFactory<>("inAdv"));
                inplace.setCellValueFactory(new PropertyValueFactory<>("inPlace"));
                regs.setCellValueFactory(new PropertyValueFactory<>("regMemS"));
                regm.setCellValueFactory(new PropertyValueFactory<>("regMemM"));
                fullmem.setCellValueFactory(new PropertyValueFactory<>("fullMem"));
                reqid.setCellValueFactory(new PropertyValueFactory<>("requestID"));
                managerid.setCellValueFactory(new PropertyValueFactory<>("ManagerID"));


                ObservableList<ChangePricesRequest> oblist = FXCollections.observableArrayList();

                mytable.setItems(oblist);

                for (ChangePricesRequest p : event.getLst()) {
                    mytable.getItems().add(p);
                }

                TableColumn<ChangePricesRequest, Integer> comboColumn = new TableColumn<>("Requests");
                comboColumn.setCellValueFactory(new PropertyValueFactory<>("requestID"));

                List<Integer> orderIds = lst.stream().map(ChangePricesRequest::getRequestID).distinct().collect(Collectors.toList());
                combobx.getItems().clear();
                combobx.getItems().addAll(orderIds);

                comboColumn.setCellFactory(col -> {
                    TableCell<ChangePricesRequest, Integer> cell = new TableCell<ChangePricesRequest, Integer>() {
                        @Override
                        protected void updateItem(Integer item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                combobx.setValue(item);
                                setGraphic(combobx);
                            }
                        }
                    };
                    return cell;
                });
            }
        });

    }
    @FXML
    void rejectRequest(ActionEvent event) throws IOException {
        Integer s = combobx.getSelectionModel().getSelectedItem();
        if(s !=null) {
            ApproveNewPrices msg = new ApproveNewPrices(s);
            msg.setApprove(false);
            SimpleClient.getClient().sendToServer(msg);

            labelmsg.setText("Request Rejected successfully !");
            labelmsg.setMinWidth(0);
            labelmsg.setPrefWidth(Control.USE_COMPUTED_SIZE);
            labelmsg.setMaxWidth(Double.MAX_VALUE);
            labelmsg.setTextFill(Color.GREEN);
            FadeTransition ft = new FadeTransition(Duration.seconds(10), labelmsg);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();
            combobx.getItems().remove(combobx.getSelectionModel().getSelectedItem());


        }
        else  {
            labelmsg.setText("please choose Request ID first");
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
    void initialize() {
        EventBus.getDefault().register(this);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
}

