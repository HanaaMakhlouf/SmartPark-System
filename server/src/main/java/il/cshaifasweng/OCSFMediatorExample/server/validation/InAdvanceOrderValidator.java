package il.cshaifasweng.OCSFMediatorExample.server.validation;

import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import il.cshaifasweng.OCSFMediatorExample.entities.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class InAdvanceOrderValidator {
    String carNumber;
    String parkingLot;
    String arrivalHours, arrivalDate , arrivalMinutes ;
    String leavingHours, leavingDate , leavingMinutes ;

    public static void  validateInAdvaceOrder(){

    }

    public InAdvanceOrderValidator(String carNumber, String parkingLot, String arrivalHours
            , String arrivalDate, String arrivalMinutes,String leavingHours, String leavingDate, String leavingMinutes) {
        this.carNumber = carNumber;
        this.parkingLot = parkingLot;
        this.arrivalHours = arrivalHours;
        this.arrivalDate = arrivalDate;
        this.arrivalMinutes = arrivalMinutes;
        this.leavingHours = leavingHours;
        this.leavingDate = leavingDate;
        this.leavingMinutes = leavingMinutes;
    }
    public boolean validateOrder() throws ParseException {
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
        return true;
    }
}
