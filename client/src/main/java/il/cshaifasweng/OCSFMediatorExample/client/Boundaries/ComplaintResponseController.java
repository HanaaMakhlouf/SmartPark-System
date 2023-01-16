package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import il.cshaifasweng.OCSFMediatorExample.client.CustomerServiceEmployeeController;
import il.cshaifasweng.OCSFMediatorExample.client.ShowComplaintsEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetComplaintsMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.SetComplaintRespondMessage;
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

public class ComplaintResponseController   {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

//    @FXML
//    private ComboBox<Integer> comboBox;

    @FXML
    private TableColumn<Complaint, Integer> num;

    @FXML
    private TableColumn<Complaint, String > sender_id;

    @FXML
    private TableColumn<Complaint, LocalDateTime> time;

    @FXML
    private TableColumn<Complaint, String > complaint;

    @FXML
    private TableView<Complaint> mytable;

    @FXML
    private TextField refundAmount;

    @FXML
    private TextArea responseTxt;

    @FXML
    private Button sendResponseBtn;

    @FXML
    private Label status;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Subscribe
    public void showComplaints(ShowComplaintsEvent event){
        Platform.runLater(new Runnable() {
            public void run() {
                List<Complaint> lst = event.getMsg().getLst();
                num.setCellValueFactory(new PropertyValueFactory<>("complaintId"));
                sender_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                time.setCellValueFactory(new PropertyValueFactory<>("Date"));
                complaint.setCellValueFactory(new PropertyValueFactory<>("Description"));

                ObservableList<Complaint> oblist = FXCollections.observableArrayList();
                mytable.setItems(oblist);

                for (Complaint p : event.getMsg().getLst()) {
                    if(p.getResponse().isEmpty())
                    {
                        mytable.getItems().add(p);
                      //  comboBox.getItems().add(p.getComplaintId());
                    }
                }
            }
        });
    }

    Timeline timeline = new Timeline((new KeyFrame(Duration.seconds(6), event ->{
        new Thread(()->{
            try {
                ArrayList<Complaint> list = new ArrayList<>();
                GetComplaintsMessage msg = new GetComplaintsMessage(list,id);
                msg.setGetForWhom(1);

                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    })));


    @FXML
    void goBack(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../customerServiceEmployeeBoundary.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        CustomerServiceEmployeeController cs_em = tableViewParent.getController();
        cs_em.setCS_employee(getId());
        timeline.stop();
    }



    @FXML
    void sendResponse(ActionEvent event) throws IOException {
        Integer refund = Integer.valueOf(refundAmount.getText());
     //   Integer compId = comboBox.getSelectionModel().getSelectedItem();
        Integer compId = mytable.getSelectionModel().getSelectedItem().getComplaintId();
        String respond = responseTxt.getText();
        if(compId != null && refund != null && respond != null) {
            SetComplaintRespondMessage msg = new SetComplaintRespondMessage(compId,respond,refund);
            SimpleClient.getClient().sendToServer(msg);
            status.setText("Respond sent successfully");
            status.setMinWidth(0);
            status.setPrefWidth(Control.USE_COMPUTED_SIZE);
            status.setMaxWidth(Double.MAX_VALUE);
            status.setTextFill(Color.GREEN);
            FadeTransition ft = new FadeTransition(Duration.seconds(10), status);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();
         //   comboBox.getItems().remove(comboBox.getSelectionModel().getSelectedItem());
            mytable.getItems().remove(mytable.getSelectionModel().getSelectedItem());
            responseTxt.clear();
            refundAmount.clear();
        }
        else {
            status.setText("please choose the complaint");
            status.setMinWidth(0);
            status.setPrefWidth(Control.USE_COMPUTED_SIZE);
            status.setMaxWidth(Double.MAX_VALUE);
            status.setTextFill(Color.RED);
            FadeTransition ft = new FadeTransition(Duration.seconds(10), status);
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

        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'complaintResponse.fxml'.";
     //   assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'complaintResponse.fxml'.";
        assert complaint != null : "fx:id=\"complaint\" was not injected: check your FXML file 'complaintResponse.fxml'.";
        assert num != null : "fx:id=\"num\" was not injected: check your FXML file 'complaintResponse.fxml'.";
        assert refundAmount != null : "fx:id=\"refundAmount\" was not injected: check your FXML file 'complaintResponse.fxml'.";
        assert responseTxt != null : "fx:id=\"responseTxt\" was not injected: check your FXML file 'complaintResponse.fxml'.";
        assert sendResponseBtn != null : "fx:id=\"sendResponseBtn\" was not injected: check your FXML file 'complaintResponse.fxml'.";
        assert sender_id != null : "fx:id=\"sender_id\" was not injected: check your FXML file 'complaintResponse.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'complaintResponse.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'complaintResponse.fxml'.";

    }

}
