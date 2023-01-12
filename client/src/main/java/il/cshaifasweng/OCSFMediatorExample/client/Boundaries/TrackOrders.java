package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.ShowTrackOrdersEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.logInEvent;
import il.cshaifasweng.OCSFMediatorExample.client.showRefundEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetallOrdersOfClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.OrderToDeleteMsg;
import il.cshaifasweng.OCSFMediatorExample.entities.Prices;
import javafx.animation.FadeTransition;
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

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TrackOrders {

    private String id;
    @FXML
    private TableColumn<InAdvanceOrderEntity, String> arrdate;

    @FXML
    private TableColumn<InAdvanceOrderEntity, String> arrhours;

    @FXML
    private TableColumn<InAdvanceOrderEntity, String> arrmin;

    @FXML
    private TableColumn<InAdvanceOrderEntity, String> carnum;

    @FXML
    private TableColumn<InAdvanceOrderEntity, String> leavdte;

    @FXML
    private TableColumn<InAdvanceOrderEntity, String> leavhours;

    @FXML
    private TableColumn<InAdvanceOrderEntity, String> leavmin;

    @FXML
    private TableColumn<InAdvanceOrderEntity, String> orderid;

    @FXML
    private TableColumn<InAdvanceOrderEntity, String> prklot;

    @FXML
    private TableView<InAdvanceOrderEntity> mytable;

    @FXML
    private ComboBox<String> combobx;

    @FXML
    private Button backbtn;

    @FXML
    private Button cancelbtn;

    @FXML
    private Label labelmsg;

    @FXML
    private Label refundlabel;

    @Subscribe
    public void showRefund(showRefundEvent event) {
        Platform.runLater(new Runnable() {
            public void run() {
                Double balance = event.getBalance();
                refundlabel.setText("You got a Refund!  amount : " + Double.toString(balance));
                refundlabel.setMinWidth(0);
                refundlabel.setPrefWidth(Control.USE_COMPUTED_SIZE);
                refundlabel.setMaxWidth(Double.MAX_VALUE);
                refundlabel.setTextFill(Color.BLUE);
                FadeTransition ft = new FadeTransition(Duration.seconds(10), refundlabel);
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setCycleCount(1);
                ft.play();
            }
        });


    }

    @Subscribe
    public void showTrackofOrders(ShowTrackOrdersEvent event) {
        Platform.runLater(new Runnable() {
            public void run() {
                List<InAdvanceOrderEntity> lst = event.getMsg().getLst();
                System.out.println("we here?");
                System.out.println(lst.get(0).getUserID());
                id = lst.get(0).getUserID();
                arrdate.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
                arrhours.setCellValueFactory(new PropertyValueFactory<>("arrivalHours"));
                arrmin.setCellValueFactory(new PropertyValueFactory<>("arrivalMinutes"));
                carnum.setCellValueFactory(new PropertyValueFactory<>("CarNumber"));
                leavdte.setCellValueFactory(new PropertyValueFactory<>("LeavingDate"));
                leavhours.setCellValueFactory(new PropertyValueFactory<>("LeavingHours"));
                leavmin.setCellValueFactory(new PropertyValueFactory<>("LeavingMinutes"));
                orderid.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
                prklot.setCellValueFactory(new PropertyValueFactory<>("parkingLotName"));

                ObservableList<InAdvanceOrderEntity> oblist = FXCollections.observableArrayList();
// Add ParkingLot objects to the list

                mytable.setItems(oblist);

                for (InAdvanceOrderEntity p : event.getMsg().getLst()) {
                    mytable.getItems().add(p);
                }

                TableColumn<InAdvanceOrderEntity, String> comboColumn = new TableColumn<>("Orders");
                comboColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));

                List<String> orderIds = lst.stream().map(InAdvanceOrderEntity::getOrderID).distinct().collect(Collectors.toList());
                combobx.getItems().addAll(orderIds);

                comboColumn.setCellFactory(col -> {
                    TableCell<InAdvanceOrderEntity, String> cell = new TableCell<InAdvanceOrderEntity, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
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
    void cancelbtn(ActionEvent event) throws IOException {
            String s = combobx.getSelectionModel().getSelectedItem();
            if(s!=null) {
                OrderToDeleteMsg msg = new OrderToDeleteMsg(s);
                SimpleClient.getClient().sendToServer(msg);

                String orderId = s;
                List<InAdvanceOrderEntity> filteredOrder =  mytable.getItems().stream()
                        .filter(o -> o.getOrderID().equals(orderId))
                        .collect(Collectors.toList());

                if (!filteredOrder.isEmpty()) {
                    mytable.getItems().removeAll(filteredOrder);
                } else {
                    System.out.println("No row with Order ID " + orderId + " found in the table.");
                }

                labelmsg.setText("Order Deleted successfully refresh to see change");
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
            else {
                labelmsg.setText("please choose the order's ID to cancel");
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
    void backbtn(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../userBoundary.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        UserBoundaryController inadv = tableViewParent.getController();
        inadv.setUser(id);

    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }
}
