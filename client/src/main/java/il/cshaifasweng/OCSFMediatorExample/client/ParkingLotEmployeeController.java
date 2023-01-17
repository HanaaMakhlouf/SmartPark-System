/**
 * Sample Skeleton for 'parkingLotEmployeeBoundary.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.DisableSpotController;
import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.Navigate;
import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.SaveSpotController;
import il.cshaifasweng.OCSFMediatorExample.entities.AbsSpot;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetParkingLotByEmployeeId;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetSpotsMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.LogoutMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.SetUpMessage;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ParkingLotEmployeeController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="registerUnavailableSpotBtn"
    private Button registerUnavailableSpotBtn; // Value injected by FXMLLoader

    @FXML // fx:id="saveSpotBtn"
    private Button saveSpotBtn; // Value injected by FXMLLoader

    @FXML // fx:id="sendToAltBtn"
    private Button sendToAltBtn; // Value injected by FXMLLoader

    @FXML // fx:id="setupBtn"
    private Button setupBtn; // Value injected by FXMLLoader

    @FXML
    private Label status;


    @FXML // fx:id="goBackBtn"
    private Button goBackBtn; // Value injected by FXMLLoader

    @FXML
    void goBack(ActionEvent event) throws IOException {
        LogoutMessage l = new LogoutMessage(Integer.parseInt(id));
        SimpleClient.getClient().sendToServer(l);
        Navigate.navigate(event , "../mainPage.fxml");
    }

    @FXML
    private Label setLabel;

    @FXML
    void registerUnavailableSpot(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("disableSpot.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        DisableSpotController em = tableViewParent.getController();
        em.set_employee(getId());
        em.setPark_id(getPark_id());
        GetSpotsMessage message = new GetSpotsMessage();
        message.setFromWhom(2);
        SimpleClient.getClient().sendToServer(message);
        currentWindow.show();
    }

    @FXML
    void saveParkingSpot(ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("saveSpot.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        SaveSpotController cs_em = tableViewParent.getController();
        cs_em.setCS_employee(getId());
        cs_em.setPark_id(getPark_id());
        GetSpotsMessage message = new GetSpotsMessage();
        message.setFromWhom(1);
        SimpleClient.getClient().sendToServer(message);
        currentWindow.show();
    }

    @FXML
    void sendToAltPark(ActionEvent event) throws IOException {
        GetSpotsMessage message = new GetSpotsMessage();
        message.setFromWhom(3);
        SimpleClient.getClient().sendToServer(message);

    }
    boolean p1 = false;
    boolean p2 = false;
    boolean p3 = false;

    @FXML
    void setup(ActionEvent event) throws IOException {
        if ((getPark_id() == 1 && p1) ||(getPark_id() == 2 && p2) || (getPark_id() == 3 && p3)){
            setLabel.setText("Set up for this park has been done before!");
            setLabel.setMinWidth(0);
            setLabel.setPrefWidth(Control.USE_COMPUTED_SIZE);
            setLabel.setMaxWidth(Double.MAX_VALUE);
            setLabel.setTextFill(Color.RED);
            FadeTransition ft = new FadeTransition(Duration.seconds(20), setLabel);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();
            setupBtn.setDisable(true);
        }
        else {
            SetUpMessage message = new SetUpMessage(getPark_id());
            SimpleClient.getClient().sendToServer(message);
        }
       // setLabel.setText("All parking lots has been installed already!");
    }

    @Subscribe
    public void setUpPark(SetUpEvent event) throws IOException{
        Platform.runLater(() -> {
            String[] parks = {"Haifa Port","Carmel","Central Station"};
            String park = parks[event.getParkId()-1];
            setLabel.setText( park +" Parking Lot setup is done successfully");
            setLabel.setMinWidth(0);
            setLabel.setPrefWidth(Control.USE_COMPUTED_SIZE);
            setLabel.setMaxWidth(Double.MAX_VALUE);
            setLabel.setTextFill(Color.DARKGREEN);
            FadeTransition ft = new FadeTransition(Duration.seconds(20), setLabel);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();
            if(event.getParkId() == 1) p1 = true;
            if(event.getParkId() == 2) p2 = true;
            if(event.getParkId() == 3) p3 = true;

        });
    }

    List<AbsSpot> p1_list = new ArrayList<>(), p2_list = new ArrayList<>(), p3_list = new ArrayList<>();

    @Subscribe
    public void getSpots(GetSpotsToSeeSpaceEvent event) throws IOException {
        List<AbsSpot> list = event.getMsg().getList();

        Platform.runLater(() -> {
            int c1 = 0;
            int c2 = 0;
            int c3 = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getPark_id() == 1) {
                    p1_list.add(list.get(i));
                    if (!list.get(i).isDisabled() && !list.get(i).isSaved() && list.get(i).isAvailable())
                        c1++;
                } else if (list.get(i).getPark_id() == 2) {
                    p2_list.add(list.get(i));
                    if (!list.get(i).isDisabled() && !list.get(i).isSaved() && list.get(i).isAvailable())
                        c2++;
                } else if (list.get(i).getPark_id() == 3) {
                    p3_list.add(list.get(i));
                    if (!list.get(i).isDisabled() && !list.get(i).isSaved() && list.get(i).isAvailable())
                        c3++;
                }
            }
            if(getPark_id() == 1){
            status.setText("There are " + c2 + " empty spots at Carmel Park \n " +
                    "There are "+ c3 + " empty spots at Haifa Station Park");
        }
        else if(getPark_id() == 2){
            status.setText("There are " + c1 + " empty spots at Haifa Port Park \n " +
                    "There are "+ c3 + " empty spots at Haifa Station Park");
        }
        else if(getPark_id() == 3){
            status.setText("There are " + c1 + " empty spots at Haifa Port Park \n " +
                    "There are "+ c2 + " empty spots at  Carmel Park");
        }
            status.setMinWidth(0);
            status.setPrefWidth(Control.USE_COMPUTED_SIZE);
            status.setMaxWidth(Double.MAX_VALUE);
            status.setTextFill(Color.NAVY);
            FadeTransition ft = new FadeTransition(Duration.seconds(15), status);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.play();

        });

            p1_list.clear();
            p2_list.clear();
            p3_list.clear();
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        EventBus.getDefault().register(this);
        assert registerUnavailableSpotBtn != null : "fx:id=\"registerUnavailableSpotBtn\" was not injected: check your FXML file 'parkingLotEmployeeBoundary.fxml'.";
        assert saveSpotBtn != null : "fx:id=\"saveSpotBtn\" was not injected: check your FXML file 'parkingLotEmployeeBoundary.fxml'.";
        assert sendToAltBtn != null : "fx:id=\"sendToAltBtn\" was not injected: check your FXML file 'parkingLotEmployeeBoundary.fxml'.";
        assert setupBtn != null : "fx:id=\"setupBtn\" was not injected: check your FXML file 'parkingLotEmployeeBoundary.fxml'.";

    }


    private String id;
    private int park_id;

    public String getId() {
        return id;
    }

    public int getPark_id() {
        return park_id;
    }

    public void setPark_id(int park_id) {
        this.park_id = park_id;
    }


    public void setEmployee(String text) {
        this.id = text;
    }
}