
/**
 * Sample Skeleton for 'saveSpot.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.*;
import il.cshaifasweng.OCSFMediatorExample.entities.AbsSpot;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.DisableSpotMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetSpotsMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.SaveSpotMessage;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class DisableSpotController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="saveBtn"
    private Button saveBtn; // Value injected by FXMLLoader

    @FXML // fx:id="unsaveBtn"
    private Button unsaveBtn; // Value injected by FXMLLoader

    @FXML // fx:id="saveLabel"
    private Label saveLabel; // Value injected by FXMLLoader

    @FXML // fx:id="unsaveLabel"
    private Label unsaveLabel; // Value injected by FXMLLoader

    @FXML // fx:id="parkCombo"
    private ComboBox<String> parkCombo; // Value injected by FXMLLoader

    @FXML // fx:id="saveCombo"
    private ComboBox<Integer> saveCombo; // Value injected by FXMLLoader

    @FXML // fx:id="unsaveCombo"
    private ComboBox<Integer> unsaveCombo; // Value injected by FXMLLoader

    String[] parks = {"Haifa Port","Carmel","Central Station"};

    List<AbsSpot> p1_list = new ArrayList<>(), p2_list = new ArrayList<>(), p3_list = new ArrayList<>();


    @Subscribe
    public void getSpots(GetSpotsToDisableEvent event) throws IOException{
        List<AbsSpot> list = event.getMsg().getList();
        // List<AbsSpot> p1_list = new ArrayList<>(), p2_list = new ArrayList<>(), p3_list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPark_id() == 1) {
                p1_list.add(list.get(i));
            } else if (list.get(i).getPark_id() == 2) {
                p2_list.add(list.get(i));
            } else if (list.get(i).getPark_id() == 3) {
                p3_list.add(list.get(i));
            }
        }
        //remove the event handler for the combobox
        //parkCombo.setOnAction(...);
        //Select the "Haifa Port" option in the combobox
        Platform.runLater(() -> {
            if (getPark_id() == 1) {
                parkCombo.getSelectionModel().select("Haifa Port");
                parkCombo.setDisable(true);
            }
            else if (getPark_id() == 2) {
                parkCombo.getSelectionModel().select("Carmel");
                parkCombo.setDisable(true);
            }
            else if (getPark_id() == 3) {
                parkCombo.getSelectionModel().select("Central Station");
                parkCombo.setDisable(true);
            }
        });

    }



    @FXML
    void parkSelected(ActionEvent event) {
        saveCombo.getItems().clear();
        unsaveCombo.getItems().clear();
        if(parkCombo.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        if (parkCombo.getSelectionModel().getSelectedItem().equals("Haifa Port")) {
            setCombo(p1_list);
        }
        if (parkCombo.getSelectionModel().getSelectedItem().equals("Carmel")) {
            setCombo(p2_list);
        }
        if (parkCombo.getSelectionModel().getSelectedItem().equals("Central Station")) {
            setCombo(p3_list);
        }
    }

    private void setCombo(List<AbsSpot> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isAvailable() && !(list.get(i).isSaved()) && !(list.get(i).isDisabled())) {
                saveCombo.getItems().add(list.get(i).getSpot_id());
            }
            else if (list.get(i).isAvailable() && !(list.get(i).isSaved()) && (list.get(i).isDisabled())) {
                unsaveCombo.getItems().add(list.get(i).getSpot_id());
            }
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
            Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("../parkingLotEmployeeBoundary.fxml"));
            Scene tableViewScene = new Scene(tableViewParent.load());
            currentWindow.setScene(tableViewScene);
            currentWindow.show();
            ParkingLotEmployeeController em = tableViewParent.getController();
            em.setEmployee(getId());
            em.setPark_id(getPark_id());

    }

    @FXML
    void saveSpot(ActionEvent event) throws IOException {
        if(saveCombo.getSelectionModel().getSelectedItem() == null){
            saveLabel.setText("Please select a spot to disable.");
            saveLabel.setMinWidth(0);
            saveLabel.setPrefWidth(Control.USE_COMPUTED_SIZE);
            saveLabel.setMaxWidth(Double.MAX_VALUE);
            saveLabel.setTextFill(Color.RED);
            FadeTransition ft = new FadeTransition(Duration.seconds(5), saveLabel);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();
        }
        else {
            DisableSpotMessage message = new DisableSpotMessage(saveCombo.getSelectionModel().getSelectedItem(),true);
            SimpleClient.getClient().sendToServer(message);
            saveLabel.setText("Spot " + saveCombo.getSelectionModel().getSelectedItem() + " disabled successfully");
            saveLabel.setMinWidth(0);
            saveLabel.setPrefWidth(Control.USE_COMPUTED_SIZE);
            saveLabel.setMaxWidth(Double.MAX_VALUE);
            saveLabel.setTextFill(Color.GREEN);
            FadeTransition ft = new FadeTransition(Duration.seconds(5), saveLabel);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();


            p1_list.clear();
            p2_list.clear();
            p3_list.clear();
            GetSpotsMessage ms = new GetSpotsMessage();
            ms.setFromWhom(2);
            SimpleClient.getClient().sendToServer(ms);
            //    parkCombo.getSelectionModel().clearSelection();
            parkCombo.getSelectionModel().select(-1);


        }

    }

    @FXML
    void unsaveSpot(ActionEvent event) throws IOException {
        if(unsaveCombo.getSelectionModel().getSelectedItem() == null){
            unsaveLabel.setText("Please select a spot to enable.");
            unsaveLabel.setMinWidth(0);
            unsaveLabel.setPrefWidth(Control.USE_COMPUTED_SIZE);
            unsaveLabel.setMaxWidth(Double.MAX_VALUE);
            unsaveLabel.setTextFill(Color.RED);
            FadeTransition ft = new FadeTransition(Duration.seconds(5), unsaveLabel);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();
        }
        else {
            DisableSpotMessage message = new DisableSpotMessage(unsaveCombo.getSelectionModel().getSelectedItem(),false);
            SimpleClient.getClient().sendToServer(message);
            unsaveLabel.setText("Spot " + unsaveCombo.getSelectionModel().getSelectedItem() + " enabled successfully");
            unsaveLabel.setMinWidth(0);
            unsaveLabel.setPrefWidth(Control.USE_COMPUTED_SIZE);
            unsaveLabel.setMaxWidth(Double.MAX_VALUE);
            unsaveLabel.setTextFill(Color.GREEN);
            FadeTransition ft = new FadeTransition(Duration.seconds(5), unsaveLabel);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();


            p1_list.clear();
            p2_list.clear();
            p3_list.clear();
            GetSpotsMessage ms = new GetSpotsMessage();
            ms.setFromWhom(2);
            SimpleClient.getClient().sendToServer(ms);
            parkCombo.getSelectionModel().clearSelection();

        }

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        EventBus.getDefault().register(this);
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'saveSpot.fxml'.";
        assert parkCombo != null : "fx:id=\"parkCombo\" was not injected: check your FXML file 'saveSpot.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'saveSpot.fxml'.";
        assert saveCombo != null : "fx:id=\"saveCombo\" was not injected: check your FXML file 'saveSpot.fxml'.";
        assert saveLabel != null : "fx:id=\"saveLabel\" was not injected: check your FXML file 'saveSpot.fxml'.";
        assert unsaveBtn != null : "fx:id=\"unsaveBtn\" was not injected: check your FXML file 'saveSpot.fxml'.";
        assert unsaveCombo != null : "fx:id=\"unsaveCombo\" was not injected: check your FXML file 'saveSpot.fxml'.";
        assert unsaveLabel != null : "fx:id=\"unsaveLabel\" was not injected: check your FXML file 'saveSpot.fxml'.";
        parkCombo.getItems().addAll(parks);
    }
    private int park_id;
    private String id;


    public int getPark_id() {return park_id;}
    public void setPark_id(int park_id) {this.park_id = park_id;}
    public String getId() {return id;}
    public void set_employee(String text) {this.id = text;}

}
