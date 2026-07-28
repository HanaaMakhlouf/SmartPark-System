package il.cshaifasweng.OCSFMediatorExample.server.validation;

import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;

import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class EnterStandardMemberValidator {
    String carNumber;
    String parkingLot;
    String arrivalHours, arrivalDate , arrivalMinutes ;
    String leavingMinutes ;
    String leavingDate ;
    String leavingHours ;
    List<InAdvanceOrderEntity> orders;

    public static void  validateInAdvaceOrder(){}

    public EnterStandardMemberValidator(String carNumber, String parkingLot, String arrivalHours
            , String arrivalDate, String arrivalMinutes, List<InAdvanceOrderEntity> orders, String leavingMinutes
            , String leavingDate, String leavingHours) {
        this.carNumber = carNumber;
        this.parkingLot = parkingLot;
        this.arrivalHours = arrivalHours;
        this.arrivalDate = arrivalDate;
        this.arrivalMinutes = arrivalMinutes;
        this.leavingMinutes = leavingMinutes;
        this.leavingDate = leavingDate;
        this.leavingHours = leavingHours;
        this.orders = orders;

    }
    public boolean validateOrder(int freeSpots,double hours,boolean isParked) throws ParseException, IOException {
        if(arrivalDate == null || arrivalHours == null  || arrivalMinutes == null  || carNumber == null
                || parkingLot == null || isParked) {
            return false ;
        }
        String arrivalTimeAndDate = arrivalDate + " " + arrivalHours + ":" + arrivalMinutes;
        String leavingTimeAndDate = leavingDate + " " + leavingHours + ":" + leavingMinutes;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //  ??dd/MM/yyyy HH:mm instead??
        LocalDateTime dateTimeArrival = LocalDateTime.parse(arrivalTimeAndDate,formatter);
        LocalDateTime dateTimeLeaving = LocalDateTime.parse(leavingTimeAndDate,formatter);
        int counter = 0;
        for(InAdvanceOrderEntity order : orders){
            String orderArrivalTime = order.getArrivalDate()+ " " + order.getArrivalHours() + ":" + order.getArrivalMinutes();
            String orderLeavingTime = order.getLeavingDate() + " " + order.getLeavingHours() + ":" + order.getLeavingMinutes();
            LocalDateTime orderArrival = LocalDateTime.parse(orderArrivalTime,formatter);
            LocalDateTime orderLeaving = LocalDateTime.parse(orderLeavingTime,formatter);
            if(order.getParkingLotName().equals(parkingLot)){
                if((orderArrival.isBefore(dateTimeLeaving) && !orderLeaving.isBefore(dateTimeArrival))
                        || (dateTimeArrival.isBefore(orderLeaving) && !dateTimeLeaving.isBefore(orderArrival))){
                    if (!order.isCarEntered()){
                        counter++;
                    }
                }
            }
        }
        return ((freeSpots - counter) > 0) && hours-getHoursUsed() > 0;
    }

    public double getHoursUsed(){
        String arrivalTimeAndDate = arrivalDate + " " + arrivalHours + ":" + arrivalMinutes;
        String leavingTimeAndDate = leavingDate + " " + leavingHours + ":" + leavingMinutes;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //  ??dd/MM/yyyy HH:mm instead??
        LocalDateTime dateTimeArrival = LocalDateTime.parse(arrivalTimeAndDate,formatter);
        LocalDateTime dateTimeLeaving = LocalDateTime.parse(leavingTimeAndDate,formatter);
        Duration dur = Duration.between(dateTimeArrival,dateTimeLeaving);
        return dur.toHours()+(double)(dur.toMinutesPart()/60);
    }


}
