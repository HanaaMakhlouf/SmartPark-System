/**
 * Sample Skeleton for 'generalManageBoundary.fxml' Controller Class
 */
package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.Navigate;
import il.cshaifasweng.OCSFMediatorExample.entities.AbsSpot;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetSpotsMessage;
import javafx.animation.FadeTransition;
import il.cshaifasweng.OCSFMediatorExample.entities.ChangePricesRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.GetallOrdersOfClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.LogoutMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ShowRequestForGM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.util.Duration;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;

public class GeneralManagerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="confirmNewPricesBtn"
    private Button confirmNewPricesBtn; // Value injected by FXMLLoader

    @FXML // fx:id="orderReportsBtn"
    private Button orderReportsBtn; // Value injected by FXMLLoader

    @FXML // fx:id="showPricesBtn"
    private Button showPricesBtn; // Value injected by FXMLLoader

    @FXML
    private Button goBackBtn;

    @FXML
    private Label status;


    @FXML
    void goBack(ActionEvent event) throws IOException {
        LogoutMessage l = new LogoutMessage(Integer.parseInt(id));
        SimpleClient.getClient().sendToServer(l);
        Navigate.navigate(event , "../mainPage.fxml");
    }

    @FXML
    void confirmNewPrices(ActionEvent event) throws IOException {


        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("gmanagerrequests.fxml"));
        Scene tableViewScene = new Scene(tableViewParent.load());
        currentWindow.setScene(tableViewScene);
        GManagerrequests inadv = tableViewParent.getController();
        System.out.println("user id is "+id);
        inadv.setId(id);
        ArrayList<ChangePricesRequest> list = new ArrayList<>();
        ShowRequestForGM msg = new ShowRequestForGM();
        msg.setList(list);
        SimpleClient.getClient().sendToServer(msg);
        currentWindow.show();

    }


    @Subscribe
    public void getSpots(GetSpotsEvent event) throws IOException {
    List<AbsSpot> list = event.getMsg().getList();
    List<AbsSpot> p1_list = new ArrayList<>(),p2_list = new ArrayList<>()  ,p3_list = new ArrayList<>() ;
    for (int i = 0 ; i < list.size();i++){
        if(list.get(i).getPark_id() == 1){
            p1_list.add(list.get(i));
        }
        else if(list.get(i).getPark_id() == 2){
            p2_list.add(list.get(i));
        }
        else if(list.get(i).getPark_id() == 3){
            p3_list.add(list.get(i));
        }
    }

    // Create a new PDF document
    PDDocument doc = new PDDocument();
    PDPage page = new PDPage();
    doc.addPage(page);

    // Create a content stream to add the matrix image to the PDF
    PDPageContentStream contentStream = new PDPageContentStream(doc, page);
    // Draw the matrix of rectangles on the PDF
    float totalWidth = (3 * 50) + (2 * 10);
    float totalHeight = (3 * 50) + (2 * 10);
    float x1 = (page.getMediaBox().getWidth() - totalWidth) / 2;
    float y1 = 50;
    x1 -= 50;
    contentStream.beginText();
    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
    contentStream.setNonStrokingColor(Color.BLACK);
    contentStream.newLineAtOffset(x1 , y1 + totalHeight + 3);
    contentStream.showText("Floor 1");
    contentStream.endText();

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 4; j++) {
            contentStream.addRect((float) (x1 + (j*50) + (j * 10)), (float) (y1 + (i*50) + (i * 10)), 50, 50);
            AbsSpot s = getSpotAt(p1_list,0,i,j);
            if (s.isDisabled()) contentStream.setNonStrokingColor(Color.RED);
            else if(!s.isAvailable()) contentStream.setNonStrokingColor(Color.BLUE);
            else contentStream.setNonStrokingColor(Color.BLACK);
            contentStream.fill();
        }
    }
    float x2 = x1;
    float y2 = y1 + totalHeight + 50;
    contentStream.beginText();
    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
    contentStream.setNonStrokingColor(Color.BLACK);
    contentStream.newLineAtOffset(x2 , y2 + totalHeight + 3);
    contentStream.showText("Floor 2");
    contentStream.endText();
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 4; j++) {
            contentStream.addRect((float) (x2 + (j*50) + (j * 10)), (float) (y2 + (i*50) + (i * 10)), 50, 50);
            AbsSpot s = getSpotAt(p1_list,1,i,j);
            if (s.isDisabled()) contentStream.setNonStrokingColor(Color.RED);
            else if(!s.isAvailable()) contentStream.setNonStrokingColor(Color.BLUE);
            else contentStream.setNonStrokingColor(Color.BLACK);
            contentStream.fill();
        }
    }
    float x3 = x1 ;
    float y3 = y2 + totalHeight + 50;
    contentStream.beginText();
    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
    contentStream.setNonStrokingColor(Color.BLACK);
    contentStream.newLineAtOffset(x3 , y3 + totalHeight + 3);
    contentStream.showText("Floor 3");
    contentStream.endText();

    contentStream.beginText();
    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
    contentStream.setNonStrokingColor(Color.BLACK);
    contentStream.newLineAtOffset(x1 +50 , y3 + totalHeight + 30);
    contentStream.showText("Haifa Port");
    contentStream.endText();

    contentStream.beginText();
    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
    contentStream.setNonStrokingColor(Color.BLACK);
    contentStream.newLineAtOffset(x1-150 , y3 + totalHeight + 60);
    contentStream.showText("BLACK = empty spot");
    contentStream.endText();

    contentStream.beginText();
    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
    contentStream.setNonStrokingColor(Color.BLUE);
    contentStream.newLineAtOffset(x1-150 , y3 + totalHeight + 40);
    contentStream.showText("BLUE = car in spot");
    contentStream.endText();

    contentStream.beginText();
    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
    contentStream.setNonStrokingColor(Color.RED);
    contentStream.newLineAtOffset(x1-150 , y3 + totalHeight + 20 );
    contentStream.showText("RED = spot disabled");
    contentStream.endText();

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 4; j++) {
            contentStream.addRect((float) (x3 + (j*50) + (j * 10)), (float)(y3 + (i*50) + (i * 10)), 50, 50);
            AbsSpot s = getSpotAt(p1_list,2,i,j);
            if (s.isDisabled()) contentStream.setNonStrokingColor(Color.RED);
            else if(!s.isAvailable()) contentStream.setNonStrokingColor(Color.BLUE);
            else contentStream.setNonStrokingColor(Color.BLACK);

            contentStream.fill();
        }
    }

    contentStream.close();
    // Prompt the user to choose where to save the PDF
    // Add new page
    PDPage newPage = new PDPage();
    doc.addPage(newPage);
    PDPageContentStream newContentStream = new PDPageContentStream(doc, newPage);
    // Draw the matrix of rectangles on the PDF
    x1 -= 50;
    x2 -= 50;
    x3 -= 50;
    newContentStream.beginText();
    newContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
    newContentStream.setNonStrokingColor(Color.BLACK);

    newContentStream.newLineAtOffset(x1 , y1 + totalHeight + 3);
    newContentStream.showText("Floor 1");
    newContentStream.endText();
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 6; j++) {
            newContentStream.addRect((float) (x1 + (j*50) + (j * 10)), (float) (y1 + (i*50) + (i * 10)), 50, 50);
            AbsSpot s = getSpotAt(p2_list,0,i,j);
            if (s.isDisabled()) newContentStream.setNonStrokingColor(Color.RED);
            else if(!s.isAvailable()) newContentStream.setNonStrokingColor(Color.BLUE);
            else newContentStream.setNonStrokingColor(Color.BLACK);
            newContentStream.fill();
        }
    }

    newContentStream.beginText();
    newContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
    newContentStream.setNonStrokingColor(Color.BLACK);

    newContentStream.newLineAtOffset(x2 , y2 + totalHeight + 3);
    newContentStream.showText("Floor 2");
    newContentStream.endText();
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 6; j++) {
            newContentStream.addRect((float) (x2 + (j*50) + (j * 10)), (float) (y2 + (i*50) + (i * 10)), 50, 50);
            AbsSpot s = getSpotAt(p2_list,1,i,j);
            if (s.isDisabled()) newContentStream.setNonStrokingColor(Color.RED);
            else if(!s.isAvailable()) newContentStream.setNonStrokingColor(Color.BLUE);
            else newContentStream.setNonStrokingColor(Color.BLACK);
            newContentStream.fill();
        }
    }

    newContentStream.beginText();
    newContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
    newContentStream.setNonStrokingColor(Color.BLACK);
    newContentStream.newLineAtOffset(x3 , y3 + totalHeight + 3);
    newContentStream.showText("Floor 3");
    newContentStream.endText();

    newContentStream.beginText();
    newContentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
    newContentStream.setNonStrokingColor(Color.BLACK);
    newContentStream.newLineAtOffset(x1 + 100 , y3 + totalHeight + 30);
    newContentStream.showText("Carmel");
    newContentStream.endText();

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 6; j++) {
            newContentStream.addRect((float) (x3 + (j*50) + (j * 10)), (float)(y3 + (i*50) + (i * 10)), 50, 50);
            AbsSpot s = getSpotAt(p2_list,2,i,j);
            if (s.isDisabled()) newContentStream.setNonStrokingColor(Color.RED);
            else if(!s.isAvailable()) newContentStream.setNonStrokingColor(Color.BLUE);
            else newContentStream.setNonStrokingColor(Color.BLACK);

            newContentStream.fill();
        }
    }
    newContentStream.close();


    // Prompt the user to choose where to save the PDF
    // Add new page
    PDPage thirdPage = new PDPage();
    doc.addPage(thirdPage);
    PDPageContentStream thirdContentStream = new PDPageContentStream(doc, thirdPage);
    // Draw the matrix of rectangles on the PDF
    x1 -= 50;
    x2 -= 50;
    x3 -= 50;
    thirdContentStream.beginText();
    thirdContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
    thirdContentStream.setNonStrokingColor(Color.BLACK);

    thirdContentStream.newLineAtOffset(x1 , y1 + totalHeight + 3);
    thirdContentStream.showText("Floor 1");
    thirdContentStream.endText();
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 8; j++) {
            thirdContentStream.addRect((float) (x1 + (j*50) + (j * 10)), (float) (y1 + (i*50) + (i * 10)), 50, 50);
            AbsSpot s = getSpotAt(p3_list,0,i,j);
            if (s.isDisabled()) thirdContentStream.setNonStrokingColor(Color.RED);
            else if(!s.isAvailable()) thirdContentStream.setNonStrokingColor(Color.BLUE);
            else thirdContentStream.setNonStrokingColor(Color.BLACK);
            thirdContentStream.fill();
        }
    }

    thirdContentStream.beginText();
    thirdContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
    thirdContentStream.setNonStrokingColor(Color.BLACK);

    thirdContentStream.newLineAtOffset(x2 , y2 + totalHeight + 3);
    thirdContentStream.showText("Floor 2");
    thirdContentStream.endText();
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 8; j++) {
            thirdContentStream.addRect((float) (x2 + (j*50) + (j * 10)), (float) (y2 + (i*50) + (i * 10)), 50, 50);
            AbsSpot s = getSpotAt(p3_list,1,i,j);
            if (s.isDisabled()) thirdContentStream.setNonStrokingColor(Color.RED);
            else if(!s.isAvailable()) thirdContentStream.setNonStrokingColor(Color.BLUE);
            else thirdContentStream.setNonStrokingColor(Color.BLACK);
            thirdContentStream.fill();
        }
    }

    thirdContentStream.beginText();
    thirdContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
    thirdContentStream.setNonStrokingColor(Color.BLACK);
    thirdContentStream.newLineAtOffset(x3 , y3 + totalHeight + 3);
    thirdContentStream.showText("Floor 3");
    thirdContentStream.endText();

    thirdContentStream.beginText();
    thirdContentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
    thirdContentStream.setNonStrokingColor(Color.BLACK);
    thirdContentStream.newLineAtOffset(x1 + 150 , y3 + totalHeight + 30);
    thirdContentStream.showText("Central Station");
    thirdContentStream.endText();

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 8; j++) {
            thirdContentStream.addRect((float) (x3 + (j*50) + (j * 10)), (float)(y3 + (i*50) + (i * 10)), 50, 50);
            AbsSpot s = getSpotAt(p3_list,2,i,j);
            if (s.isDisabled()) thirdContentStream.setNonStrokingColor(Color.RED);
            else if(!s.isAvailable()) thirdContentStream.setNonStrokingColor(Color.BLUE);
            else thirdContentStream.setNonStrokingColor(Color.BLACK);
            thirdContentStream.fill();
        }
    }
    thirdContentStream.close();
    File pdfFile = new File(System.getProperty("user.home") + "/Downloads" + "/ParkingLotsReport.pdf");
    if(pdfFile.exists()){
        pdfFile = new File(System.getProperty("user.home") + "/Downloads" + "/ParkingLotsReport_"+ UUID.randomUUID()+".pdf");
    }
    doc.save(pdfFile);
    doc.close();
}

    public  AbsSpot getSpotAt(List<AbsSpot> list , int height , int depth , int width){
                for (int i = 0 ; i < list.size() ; i++){
                    if(list.get(i).getHeight_num() == height && list.get(i).getDepth_num() == depth && list.get(i).getWidth_num() == width)
                        return list.get(i);
                }
            return null;
        }



@FXML
void orderReports(ActionEvent event) throws IOException {
        GetSpotsMessage msg = new GetSpotsMessage();
            SimpleClient.getClient().sendToServer(msg);
    status.setText("PDF file has been downloaded to your /Downloads Directory ");
    status.setMinWidth(0);
    status.setPrefWidth(Control.USE_COMPUTED_SIZE);
    status.setMaxWidth(Double.MAX_VALUE);
    status.setTextFill(javafx.scene.paint.Color.NAVY);
    FadeTransition ft = new FadeTransition(Duration.seconds(10), status);
    ft.setFromValue(1.0);
    ft.setToValue(0.0);
    ft.setCycleCount(1);
    ft.play();
    }


    @FXML
    void showPrices(ActionEvent event) {

    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
       EventBus.getDefault().register(this);
        assert confirmNewPricesBtn != null : "fx:id=\"confirmNewPricesBtn\" was not injected: check your FXML file 'generalManageBoundary.fxml'.";
        assert orderReportsBtn != null : "fx:id=\"orderReportsBtn\" was not injected: check your FXML file 'generalManageBoundary.fxml'.";
        assert showPricesBtn != null : "fx:id=\"showPricesBtn\" was not injected: check your FXML file 'generalManageBoundary.fxml'.";

    }

    private String id;
    public void setGM(String text) {
        this.id = text;
    }
}
