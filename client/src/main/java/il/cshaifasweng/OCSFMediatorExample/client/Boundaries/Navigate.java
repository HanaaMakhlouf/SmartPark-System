package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigate {

    public  static  void navigate(ActionEvent actionEvent , String path) throws IOException {
        Parent tableViewParent = FXMLLoader.load(Navigate.class.getResource(path));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
