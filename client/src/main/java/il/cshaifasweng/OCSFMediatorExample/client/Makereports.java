package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.OrderToDeleteMsg;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.makeAreportMSG;
import il.cshaifasweng.OCSFMediatorExample.entities.RequestForReport;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Makereports {

    @FXML
    private TableView<RequestForReport> Orderstable;

    @FXML
    private TableView<RequestForReport> Complaintstable;

    @FXML
    private TableView<RequestForReport> disabledtable;



    @FXML
    private ComboBox<Integer> combxComplaints;

    @FXML
    private ComboBox<Integer> combxDisabled;

    @FXML
    private ComboBox<Integer> combxOrders;

    @FXML
    private TableColumn<?, ?> from1;

    @FXML
    private TableColumn<?, ?> from2;

    @FXML
    private TableColumn<?, ?> from3;

    @FXML
    private TableColumn<?, ?> idreq1;

    @FXML
    private TableColumn<?, ?> idreq2;

    @FXML
    private TableColumn<?, ?> idreq3;

    @FXML
    private Button prepareComplaintsbtn;

    @FXML
    private Button prepareDisabledbtn;

    @FXML
    private Button prepareOrdersbtn;

    @FXML
    private TableColumn<?, ?> until1;

    @FXML
    private TableColumn<?, ?> until2;

    @FXML
    private TableColumn<?, ?> until3;


    @FXML
    private TableColumn<?, ?> time1;

    @FXML
    private TableColumn<?, ?> time2;

    @FXML
    private TableColumn<?, ?> time3;

    @FXML
    private Label labelmsg;

    @FXML
    private Label labelmsg2;

    @FXML
    private Label labelmsg3;

    private String ManagerID;

    public String getManagerID() {
        return ManagerID;
    }

    public void setManagerID(String managerID) {
        ManagerID = managerID;
    }

    @FXML
    void PrepareComplaints(ActionEvent event) throws IOException {
        Integer requestID = combxComplaints.getSelectionModel().getSelectedItem();
        if (requestID != null)
        {
            makeAreportMSG msg = new makeAreportMSG(requestID,"Complaints",ManagerID);
            SimpleClient.getClient().sendToServer(msg);



            labelmsg2.setText("report prepared successfully");
            labelmsg2.setMinWidth(0);
            labelmsg2.setPrefWidth(Control.USE_COMPUTED_SIZE);
            labelmsg2.setMaxWidth(Double.MAX_VALUE);
            labelmsg2.setTextFill(Color.GREEN);
            FadeTransition ft = new FadeTransition(Duration.seconds(10), labelmsg2);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();
            combxComplaints.getItems().remove(combxComplaints.getSelectionModel().getSelectedItem());


        }
        else {
            labelmsg2.setText("please choose the request ID first ");
            labelmsg2.setMinWidth(0);
            labelmsg2.setPrefWidth(Control.USE_COMPUTED_SIZE);
            labelmsg2.setMaxWidth(Double.MAX_VALUE);
            labelmsg2.setTextFill(Color.RED);
            FadeTransition ft = new FadeTransition(Duration.seconds(10), labelmsg2);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();

        }
    }

    @FXML
    void PrepareDisabled(ActionEvent event) {

    }

    @FXML
    void PrepareOrders(ActionEvent event) throws IOException {
        Integer requestID = combxOrders.getSelectionModel().getSelectedItem();
        if (requestID != null)
        {
                makeAreportMSG msg = new makeAreportMSG(requestID,"Orders",ManagerID);
                SimpleClient.getClient().sendToServer(msg);



                labelmsg.setText("report prepared successfully");
                labelmsg.setMinWidth(0);
                labelmsg.setPrefWidth(Control.USE_COMPUTED_SIZE);
                labelmsg.setMaxWidth(Double.MAX_VALUE);
                labelmsg.setTextFill(Color.GREEN);
                FadeTransition ft = new FadeTransition(Duration.seconds(10), labelmsg);
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setCycleCount(1);
                ft.play();
                combxOrders.getItems().remove(combxOrders.getSelectionModel().getSelectedItem());


            }
            else {
                labelmsg.setText("please choose the request ID first ");
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



    @Subscribe
    public void showALLrequests(ShowALLreportsReqEvent event)
    {

        Platform.runLater(new Runnable() {
            public void run() {
                List<RequestForReport> lst = event.getMessage().getOrders();
                System.out.println("we here?");
                idreq1.setCellValueFactory(new PropertyValueFactory<>("id"));
                from1.setCellValueFactory(new PropertyValueFactory<>("from"));
                until1.setCellValueFactory(new PropertyValueFactory<>("until"));
                time1.setCellValueFactory(new PropertyValueFactory<>("time"));


                ObservableList<RequestForReport> oblist = FXCollections.observableArrayList();
// Add ParkingLot objects to the list

                Orderstable.setItems(oblist);

                for (RequestForReport p : event.getMessage().getOrders()) {
                    Orderstable.getItems().add(p);
                }

                TableColumn<RequestForReport, Integer> comboColumn = new TableColumn<>("requests");
                comboColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

                List<Integer> orderIds = lst.stream().filter(p -> p.getReport_type().equals("Orders")).map(RequestForReport::getId).distinct().collect(Collectors.toList());
                if(combxOrders.getItems().isEmpty()) {
                    combxOrders.getItems().addAll(orderIds);

                    comboColumn.setCellFactory(col -> {
                        TableCell<RequestForReport, Integer> cell = new TableCell<RequestForReport, Integer>() {
                            @Override
                            protected void updateItem(Integer item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    combxOrders.setValue(item);
                                    setGraphic(combxOrders);
                                }
                            }
                        };
                        return cell;
                    });}




                List<RequestForReport> clst = event.getMessage().getComplaints();
                System.out.println("we here?");
                idreq2.setCellValueFactory(new PropertyValueFactory<>("id"));
                from2.setCellValueFactory(new PropertyValueFactory<>("from"));
                until2.setCellValueFactory(new PropertyValueFactory<>("until"));
                time2.setCellValueFactory(new PropertyValueFactory<>("time"));


                ObservableList<RequestForReport> oblist2 = FXCollections.observableArrayList();

                Complaintstable.setItems(oblist2);

                for (RequestForReport p : event.getMessage().getComplaints()) {
                    Complaintstable.getItems().add(p);
                }

                TableColumn<RequestForReport, Integer> comboColumn2 = new TableColumn<>("requests");
                comboColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

                List<Integer> complaints = clst.stream().filter(p -> p.getReport_type().equals("Complaints")).map(RequestForReport::getId).distinct().collect(Collectors.toList());
                if(combxComplaints.getItems().isEmpty()) {
                    combxComplaints.getItems().addAll(complaints);

                    comboColumn.setCellFactory(col -> {
                        TableCell<RequestForReport, Integer> cell2 = new TableCell<RequestForReport, Integer>() {
                            @Override
                            protected void updateItem(Integer item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    combxComplaints.setValue(item);
                                    setGraphic(combxComplaints);
                                }
                            }
                        };
                        return cell2;
                    });}


                List<RequestForReport> dlst = event.getMessage().getDisabled();
                System.out.println("we here?");
                idreq3.setCellValueFactory(new PropertyValueFactory<>("id"));
                from3.setCellValueFactory(new PropertyValueFactory<>("from"));
                until3.setCellValueFactory(new PropertyValueFactory<>("until"));
                time3.setCellValueFactory(new PropertyValueFactory<>("time"));

                ObservableList<RequestForReport> oblist3 = FXCollections.observableArrayList();

                disabledtable.setItems(oblist3);

                for (RequestForReport p : event.getMessage().getDisabled()) {
                    disabledtable.getItems().add(p);
                }

                TableColumn<RequestForReport, Integer> comboColumn3 = new TableColumn<>("requests");
                comboColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

                List<Integer> disabled = dlst.stream().filter(p -> p.getReport_type().equals("Disabled")).map(RequestForReport::getId).distinct().collect(Collectors.toList());
                if(combxDisabled.getItems().isEmpty()) {
                    combxDisabled.getItems().addAll(disabled);

                    comboColumn.setCellFactory(col -> {
                        TableCell<RequestForReport, Integer> cell3 = new TableCell<RequestForReport, Integer>() {
                            @Override
                            protected void updateItem(Integer item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    combxDisabled.setValue(item);
                                    setGraphic(combxDisabled);
                                }
                            }
                        };
                        return cell3;
                    });}


            }








        });



    }


    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }

}
