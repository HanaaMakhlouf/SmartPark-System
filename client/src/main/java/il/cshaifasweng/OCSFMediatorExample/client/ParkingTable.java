package il.cshaifasweng.OCSFMediatorExample.client;

        import il.cshaifasweng.OCSFMediatorExample.entities.Messages.Message;
        import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLots;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.cell.PropertyValueFactory;
        import org.greenrobot.eventbus.EventBus;
        import org.greenrobot.eventbus.Subscribe;
        import java.io.IOException;

        import java.util.List;

public class ParkingTable {

    @FXML
    private TableColumn<ParkingLots, Integer> idpark;

    @FXML
    private TableColumn<ParkingLots, Integer> rowsnum;

    @FXML
    private TableColumn<ParkingLots, Integer> spots;

    @FXML
    private TableView<ParkingLots> tableview;

    @FXML
    private List<ParkingLots> parkingLotsList;
    private int msgId;

    public ParkingTable() {}

    public ParkingTable(List<ParkingLots> parkingLotsList1) {
        parkingLotsList=parkingLotsList1;
    }
    @FXML
    public void initData(List<ParkingLots> parkingLotsList1){

        parkingLotsList=parkingLotsList1;
    }


    @FXML
    public void setMsgId(int msgId1){
        this.msgId = msgId1;
    }

    @FXML
    public void setTable(){
        try {
            Message message = new Message(msgId, "print parking table");
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Subscribe
    public void setTableviewFromServer(showTableEvent event)
    {
        idpark.setCellValueFactory(new PropertyValueFactory<>("id"));
        rowsnum.setCellValueFactory(new PropertyValueFactory<>("num_of_rows"));
        spots.setCellValueFactory(new PropertyValueFactory<>("parking_spots"));

        ObservableList<ParkingLots> parkingLots = FXCollections.observableArrayList();
// Add ParkingLot objects to the list
        tableview.setItems(parkingLots);
        for (ParkingLots p : event.getList()){
            tableview.getItems().add(p);
        }
    }
    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);


    }
}
