package il.cshaifasweng.OCSFMediatorExample.server.validation;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.InAdvanceOrder;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.InAdvanceOrderMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.logInMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLots;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.stage.Stage;
import org.w3c.dom.Node;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InAdvanceOrderValidator {
    String carNumber;
    String parkingLot;
    String arrivalHours, arrivalDate , arrivalMinutes ;
    String leavingHours, leavingDate , leavingMinutes ;
    List<ParkingLots> parkingLots;
    List<InAdvanceOrder> orders;

    public static void  validateInAdvaceOrder(){

    }

    public InAdvanceOrderValidator(String carNumber, String parkingLot, String arrivalHours
            , String arrivalDate, String arrivalMinutes, String leavingHours, String leavingDate, String leavingMinutes, List<ParkingLots> parkingLots, List<InAdvanceOrder> orders) {
        this.carNumber = carNumber;
        this.parkingLot = parkingLot;
        this.arrivalHours = arrivalHours;
        this.arrivalDate = arrivalDate;
        this.arrivalMinutes = arrivalMinutes;
        this.leavingHours = leavingHours;
        this.leavingDate = leavingDate;
        this.leavingMinutes = leavingMinutes;
        this.parkingLots = parkingLots;
        this.orders = orders;

    }
    public boolean validateOrder() throws ParseException, IOException {
//        try {
//            int d = Integer.parseInt(order.getCarNumber());
//        } catch (NumberFormatException nfe) {
//            return false;
//        }
//        int d=Integer.parseInt(order.getCarNumber());
//        if(!(email.equals(user.getEmail()))){
//            return false;
//        }
        String arrivalTimeAndDate = arrivalDate + " " + arrivalHours + ":" + arrivalMinutes;
        String leavingTimeAndDate = leavingDate + " " + leavingHours + ":" + leavingMinutes;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //  ??dd/MM/yyyy HH:mm instead??
        LocalDateTime dateTimeArrival = LocalDateTime.parse(arrivalTimeAndDate,formatter);
        if(!(dateTimeArrival.toLocalDate().isAfter(LocalDate.now()))){
            return false;
        }
        LocalDateTime dateTimeLeaving = LocalDateTime.parse(leavingTimeAndDate,formatter);
        if(!dateTimeLeaving.isAfter(dateTimeArrival)){
            return false;
        }
        //check if theres available spots at this time and date
        int capacity = 0;
        for(ParkingLots parking : parkingLots){
            if(parking.getName().equals(parkingLot)){
                capacity = parking.getParking_spots();
            }
        }
        int counter = 0;


        for(InAdvanceOrder order : orders){
            String orderArrivalTime = order.getArrivalDate() + " " + order.getArrivalHours() + ":" + order.getArrivalMinutes();
            String orderLeavingTime = order.getLeavingDate() + " " + order.getLeavingHours() + ":" + order.getLeavingMinutes();
            LocalDateTime orderArrival = LocalDateTime.parse(orderArrivalTime,formatter);
            LocalDateTime orderLeaving = LocalDateTime.parse(orderLeavingTime,formatter);
            if(orderArrival.isBefore(dateTimeLeaving) || dateTimeArrival.isBefore(orderLeaving)){
                counter++;
            }
        }
        if(counter > capacity){
            return false;
        }
        return true;
    }
}
