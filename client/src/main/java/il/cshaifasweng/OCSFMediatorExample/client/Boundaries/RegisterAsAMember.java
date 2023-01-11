package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;


import il.cshaifasweng.OCSFMediatorExample.entities.Messages.FullMembershipMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterAsAMember {

    @FXML
    private Button FullMembershipBt;

    @FXML
    private Button backBt2;

    @FXML
    private Button standardMembershipBt;
    String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @FXML
    void FullMembership(ActionEvent event) throws IOException {

        FXMLLoader tableViewParent = null;
        try {
            tableViewParent = new FXMLLoader(getClass().getResource("../fullMembership.fxml"));
            Scene tableViewScene = new Scene(tableViewParent.load());
            Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentWindow.setScene(tableViewScene);
            currentWindow.show();
            FullMembership fullMembership = tableViewParent.getController();
            fullMembership.setId(this.id);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fullMembership.fxml"));
//        Stage stage = new Stage();
//        try {
//            stage.setScene(new Scene(loader.load()));
//            FullMembership fullMembership = loader.getController();
//            fullMembership.setId(this.id);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Navigate.navigate(event, "../fullMembership.fxml");
    }

    @FXML
    void StandardMembership(ActionEvent event) throws IOException {

        FXMLLoader tableViewParent = null;
        try {
            tableViewParent = new FXMLLoader(getClass().getResource("../standardMembership.fxml"));
            Scene tableViewScene = new Scene(tableViewParent.load());
            Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentWindow.setScene(tableViewScene);
            currentWindow.show();
            StandardMembership standardMembership = tableViewParent.getController();
            standardMembership.setId(this.id);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../standardMembership.fxml"));
//        Stage stage = new Stage();
//        try {
//            stage.setScene(new Scene(loader.load()));
//            StandardMembership standardMembership = loader.getController();
//            standardMembership.setId(this.id);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Navigate.navigate(event, "../standardMembership.fxml");

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Navigate.navigate(event, "../userBoundary.fxml");
    }

}
