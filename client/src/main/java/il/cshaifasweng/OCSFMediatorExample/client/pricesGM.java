package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Prices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class pricesGM {
    @FXML
    private Button backbt;
    @FXML
    private TableColumn<?, ?> fullmem;

    @FXML
    private TableColumn<?, ?> inadv;

    @FXML
    private TableColumn<?, ?> inplace;

    @FXML
    private TableView<Prices> mytable;

    @FXML
    private TableColumn<?, ?> regmult;

    @FXML
    private TableColumn<?, ?> regsingle;


    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Subscribe
    public void setpTableviewFromServer(showptableEventt event)
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



    @FXML
    public void backto(javafx.event.ActionEvent event) throws IOException {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("generalManageBoundary.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        currentWindow.show();
        GeneralManagerController inadv = tableViewParent.getController();
        inadv.setGM(id);
    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        EventBus.getDefault().register(this);
    }

}
