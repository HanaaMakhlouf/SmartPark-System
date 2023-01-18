package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ShowCOMPrepBYrepID;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ShowDSBLrepBYrepID;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ShowORDERSrepBYrepID;
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

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AllpreparedReports {

    @FXML
    private TableColumn<?, ?> COMCOMcompdate;

    @FXML
    private TableColumn<?, ?> COMCOMparkname;

    @FXML
    private TableColumn<?, ?> COMCOMrepid;

    @FXML
    private TableColumn<?, ?> COMCOMstatus;

    @FXML
    private TableView<ComplaintsDataForReport> COMCOMtble;

    @FXML
    private ComboBox<Integer> COMcombo;

    @FXML
    private TableColumn<?, ?> COMcompnum;

    @FXML
    private TableColumn<?, ?> COMfrom;

    @FXML
    private TableColumn<?, ?> COMparkname;

    @FXML
    private TableColumn<?, ?> COMrepid;

    @FXML
    private TableColumn<?, ?> COMresolvednum;

    @FXML
    private TableView<ComplaintsReport> COMtble;

    @FXML
    private TableColumn<?, ?> COMunresolvednum;

    @FXML
    private TableColumn<?, ?> COMuntil;

    @FXML
    private TableColumn<?, ?> DISDISparkname;

    @FXML
    private TableColumn<?, ?> DISDISrepid;

    @FXML
    private TableColumn<?, ?> DISDISspotnum;

    @FXML
    private TableView<DisabledDataReport> DISDIStble;

    @FXML
    private TableColumn<?, ?> DISDIStime;

    @FXML
    private Button DISbtn;

    @FXML
    private ComboBox<Integer> DIScombo;

    @FXML
    private TableColumn<?, ?> dispots;

    @FXML
    private TableColumn<?, ?> DISfrom;

    @FXML
    private TableColumn<?, ?> DISparkname;

    @FXML
    private TableColumn<?, ?> DISrepid;

    @FXML
    private TableView<DisabledSpotReport> DIStble;

    @FXML
    private TableColumn<?, ?> DISuntil;

    @FXML
    private TableColumn<?, ?> ORDORDorderedat;

    @FXML
    private TableColumn<?, ?> ORDORDorderid;

    @FXML
    private TableColumn<?, ?> ORDORDordertype;

    @FXML
    private TableColumn<?, ?> ORDORDparkname;

    @FXML
    private TableColumn<?, ?> ORDORDrepid;

    @FXML
    private TableView<ALLOrdersInTimePeriod> ORDORDtble;

    @FXML
    private Button ORDbtn;

    @FXML
    private ComboBox<Integer> ORDcombo;

    @FXML
    private TableColumn<?, ?> ORDfrom;

    @FXML
    private TableColumn<?, ?> ORDinadv;

    @FXML
    private TableColumn<?, ?> ORDinplace;

    @FXML
    private TableColumn<?, ?> ORDparkname;

    @FXML
    private TableColumn<?, ?> ORDrepid;

    @FXML
    private TableView<OrdersReport> ORDtble;

    @FXML
    private TableColumn<?, ?> ORDuntil;


    @FXML
    private Label labelmsg1;

    @FXML
    private Label labelmsg2;

    @FXML
    private Label labelmsg3;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @FXML
    private Button backbtn;

    @FXML
    void backto(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("reports.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        Reports inadv = tableViewParent.getController();
        inadv.setId(id);
    }


    @FXML
    void showCOM(ActionEvent event) throws IOException {
        Integer repid = COMcombo.getSelectionModel().getSelectedItem();
        if (repid == null) {
            labelmsg2.setText("please choose report id first");
            labelmsg2.setMinWidth(0);
            labelmsg2.setPrefWidth(Control.USE_COMPUTED_SIZE);
            labelmsg2.setMaxWidth(Double.MAX_VALUE);
            labelmsg2.setTextFill(Color.RED);
            FadeTransition ft = new FadeTransition(Duration.seconds(10), labelmsg2);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();
        } else {
            ShowCOMPrepBYrepID Msg = new ShowCOMPrepBYrepID(repid);
            SimpleClient.getClient().sendToServer(Msg);
        }
    }
    @FXML
    void showDIS(ActionEvent event) throws IOException {
        Integer repid = DIScombo.getSelectionModel().getSelectedItem();
        if (repid == null) {
            labelmsg3.setText("please choose report id first");
            labelmsg3.setMinWidth(0);
            labelmsg3.setPrefWidth(Control.USE_COMPUTED_SIZE);
            labelmsg3.setMaxWidth(Double.MAX_VALUE);
            labelmsg3.setTextFill(Color.RED);
            FadeTransition ft = new FadeTransition(Duration.seconds(10), labelmsg3);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();
        } else {
            ShowDSBLrepBYrepID Msg = new ShowDSBLrepBYrepID(repid);
            SimpleClient.getClient().sendToServer(Msg);
        }
    }
    @FXML
    void showORD(ActionEvent event) throws IOException {
        Integer repid = ORDcombo.getSelectionModel().getSelectedItem();
        if(repid == null ) {
            labelmsg1.setText("please choose report id first");
            labelmsg1.setMinWidth(0);
            labelmsg1.setPrefWidth(Control.USE_COMPUTED_SIZE);
            labelmsg1.setMaxWidth(Double.MAX_VALUE);
            labelmsg1.setTextFill(Color.RED);
            FadeTransition ft = new FadeTransition(Duration.seconds(10), labelmsg1);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();
        }
        else {
            ShowORDERSrepBYrepID Msg = new ShowORDERSrepBYrepID(repid);
            SimpleClient.getClient().sendToServer(Msg);
        }
    }

    @Subscribe
    public void showReports(ShowAllReportsEvent event)
    {
        Platform.runLater(new Runnable() {
            public void run() {
                List<OrdersReport> lst = event.getMsg().getOlst();

                ORDrepid.setCellValueFactory(new PropertyValueFactory<>("id"));
                ORDfrom.setCellValueFactory(new PropertyValueFactory<>("from"));
                ORDinadv.setCellValueFactory(new PropertyValueFactory<>("inadvance"));
                ORDinplace.setCellValueFactory(new PropertyValueFactory<>("inplace"));
                ORDuntil.setCellValueFactory(new PropertyValueFactory<>("until"));
                ORDparkname.setCellValueFactory(new PropertyValueFactory<>("Park"));

                ObservableList<OrdersReport> oblist = FXCollections.observableArrayList();
// Add ParkingLot objects to the list

                ORDtble.setItems(oblist);

                for (OrdersReport p : event.getMsg().getOlst()) {
                    ORDtble.getItems().add(p);
                }

                TableColumn<OrdersReport, Integer> comboColumn = new TableColumn<>("Orders");
                comboColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

                List<Integer> orderIds = lst.stream().map(OrdersReport::getId).distinct().collect(Collectors.toList());
                if(ORDcombo.getItems().isEmpty()) {
                    ORDcombo.getItems().addAll(orderIds);

                    comboColumn.setCellFactory(col -> {
                        TableCell<OrdersReport, Integer> cell = new TableCell<OrdersReport, Integer>() {
                            @Override
                            protected void updateItem(Integer item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    ORDcombo.setValue(item);
                                    setGraphic(ORDcombo);
                                }
                            }
                        };
                        return cell;
                    });}






                List<ComplaintsReport> clst = event.getMsg().getClst();

                COMrepid.setCellValueFactory(new PropertyValueFactory<>("id"));
                COMfrom.setCellValueFactory(new PropertyValueFactory<>("from"));
                COMcompnum.setCellValueFactory(new PropertyValueFactory<>("complaintsNum"));
                COMunresolvednum.setCellValueFactory(new PropertyValueFactory<>("unresolved"));
                COMuntil.setCellValueFactory(new PropertyValueFactory<>("until"));
                COMparkname.setCellValueFactory(new PropertyValueFactory<>("Park"));
                COMresolvednum.setCellValueFactory(new PropertyValueFactory<>("resolved"));


                ObservableList<ComplaintsReport> oblistt = FXCollections.observableArrayList();
// Add ParkingLot objects to the list

                COMtble.setItems(oblistt);

                for (ComplaintsReport p : event.getMsg().getClst()) {
                    COMtble.getItems().add(p);
                }

                TableColumn<ComplaintsReport, Integer> comboColumn2 = new TableColumn<>("Orders");
                comboColumn2.setCellValueFactory(new PropertyValueFactory<>("id"));

                List<Integer> comids = clst.stream().map(ComplaintsReport::getId).distinct().collect(Collectors.toList());
                if(COMcombo.getItems().isEmpty()) {
                    COMcombo.getItems().addAll(comids);

                    comboColumn2.setCellFactory(col -> {
                        TableCell<ComplaintsReport, Integer> cell2 = new TableCell<ComplaintsReport, Integer>() {
                            @Override
                            protected void updateItem(Integer item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    COMcombo.setValue(item);
                                    setGraphic(COMcombo);
                                }
                            }
                        };
                        return cell2;
                    });}







                List<DisabledSpotReport> dlst = event.getMsg().getDlst();

                DISrepid.setCellValueFactory(new PropertyValueFactory<>("id"));
                DISfrom.setCellValueFactory(new PropertyValueFactory<>("from"));
                dispots.setCellValueFactory(new PropertyValueFactory<>("disablement"));
                DISuntil.setCellValueFactory(new PropertyValueFactory<>("until"));
                DISparkname.setCellValueFactory(new PropertyValueFactory<>("Park"));


                ObservableList<DisabledSpotReport> oblisttt = FXCollections.observableArrayList();
// Add ParkingLot objects to the list

                DIStble.setItems(oblisttt);

                for (DisabledSpotReport p : event.getMsg().getDlst()) {
                    DIStble.getItems().add(p);
                }

                TableColumn<DisabledSpotReport, Integer> comboColumn3 = new TableColumn<>("Orders");
                comboColumn3.setCellValueFactory(new PropertyValueFactory<>("id"));

                List<Integer> disids = dlst.stream().map(DisabledSpotReport::getId).distinct().collect(Collectors.toList());
                if(DIScombo.getItems().isEmpty()) {
                    DIScombo.getItems().addAll(comids);

                    comboColumn3.setCellFactory(col -> {
                        TableCell<DisabledSpotReport, Integer> cell3 = new TableCell<DisabledSpotReport, Integer>() {
                            @Override
                            protected void updateItem(Integer item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    DIScombo.setValue(item);
                                    setGraphic(DIScombo);
                                }
                            }
                        };
                        return cell3;
                    });}





























            }
        });


    }



    @Subscribe
    public void showord(ShowORDEvent event) {

        Platform.runLater(new Runnable() {
            public void run() {
                List<ALLOrdersInTimePeriod> lst = event.getMessage().getLst();

                ORDORDrepid.setCellValueFactory(new PropertyValueFactory<>("repid"));
                ORDORDorderid.setCellValueFactory(new PropertyValueFactory<>("orderID"));
                ORDORDparkname.setCellValueFactory(new PropertyValueFactory<>("parkingLotName"));
                ORDORDorderedat.setCellValueFactory(new PropertyValueFactory<>("date"));
                ORDORDordertype.setCellValueFactory(new PropertyValueFactory<>("type"));

                ObservableList<ALLOrdersInTimePeriod> oblist = FXCollections.observableArrayList();
// Add ParkingLot objects to the list

                ORDORDtble.setItems(oblist);

                for (ALLOrdersInTimePeriod p : event.getMessage().getLst()) {
                    ORDORDtble.getItems().add(p);
                }


            }



        });

    }


    @Subscribe
    public void showCOM(ShowCOMEvent event)
    {
        Platform.runLater(new Runnable() {
            public void run() {
                List<ComplaintsDataForReport> lst = event.getMessage().getLst();

                COMCOMcompdate.setCellValueFactory(new PropertyValueFactory<>("date"));
                COMCOMparkname.setCellValueFactory(new PropertyValueFactory<>("parkingLotName"));
                COMCOMrepid.setCellValueFactory(new PropertyValueFactory<>("repid"));
                COMCOMstatus.setCellValueFactory(new PropertyValueFactory<>("type"));

                ObservableList<ComplaintsDataForReport> oblist = FXCollections.observableArrayList();
// Add ParkingLot objects to the list

                COMCOMtble.setItems(oblist);

                for (ComplaintsDataForReport p : event.getMessage().getLst()) {
                    COMCOMtble.getItems().add(p);
                }


            }



        });



    }

    @Subscribe
    public void showDIS(ShowDSBLEvent event)
    {

        Platform.runLater(new Runnable() {
            public void run() {
                List<DisabledDataReport> lst = event.getMessage().getLst();

                DISDIStime.setCellValueFactory(new PropertyValueFactory<>("time_of_disabling"));
                DISDISparkname.setCellValueFactory(new PropertyValueFactory<>("Park"));
                DISDISspotnum.setCellValueFactory(new PropertyValueFactory<>("spot_number"));
                DISDISrepid.setCellValueFactory(new PropertyValueFactory<>("repid"));

                ObservableList<DisabledDataReport> oblist = FXCollections.observableArrayList();
// Add ParkingLot objects to the list

                DISDIStble.setItems(oblist);

                for (DisabledDataReport p : event.getMessage().getLst()) {
                    DISDIStble.getItems().add(p);
                }


            }



        });


    }










    @FXML
    void initialize() {
        EventBus.getDefault().register(this);

    }

}
