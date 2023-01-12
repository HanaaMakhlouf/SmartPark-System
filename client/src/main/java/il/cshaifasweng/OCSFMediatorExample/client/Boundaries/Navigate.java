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

//    public static void navigateWithFunc(){
//        FXMLLoader tableViewParent = null;
//        try {
//            tableViewParent = new FXMLLoader(getClass().getResource("../userBoundary.fxml"));
//            Scene tableViewScene = new Scene(tableViewParent.load());
//
//            currentWindow.setScene(tableViewScene);
//            currentWindow.show();
//            UserBoundaryController user = tableViewParent.getController();
//            user.setUser(idTxt.getText());
//            // System.out.println(idTxt.getText());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
