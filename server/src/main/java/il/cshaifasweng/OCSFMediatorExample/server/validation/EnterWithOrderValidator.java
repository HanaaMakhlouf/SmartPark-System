package il.cshaifasweng.OCSFMediatorExample.server.validation;

import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLots;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class EnterWithOrderValidator {
    String carNumber;
    String parkingLot;
    String arrivalHours, arrivalDate , arrivalMinutes ;
    List<InAdvanceOrderEntity> orders;

    public static void  validateInAdvaceOrder(){}

    public EnterWithOrderValidator(String carNumber, String parkingLot, String arrivalHours
            , String arrivalDate, String arrivalMinutes, List<InAdvanceOrderEntity> orders) {
        this.carNumber = carNumber;
        this.parkingLot = parkingLot;
        this.arrivalHours = arrivalHours;
        this.arrivalDate = arrivalDate;
        this.arrivalMinutes = arrivalMinutes;

        this.orders = orders;

    }
    public boolean validateOrder() throws ParseException, IOException {
        if(arrivalDate == null || arrivalHours == null  || arrivalMinutes == null  || carNumber == null
                || parkingLot == null ) {
            return false ;
        }
        String arrivalTimeAndDate = arrivalDate + " " + arrivalHours + ":" + arrivalMinutes;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //  ??dd/MM/yyyy HH:mm instead??
        LocalDateTime dateTimeArrival = LocalDateTime.parse(arrivalTimeAndDate,formatter);
        for(InAdvanceOrderEntity order : orders){
            String orderArrivalTime = order.getArrivalDate()+ " " + order.getArrivalHours() + ":" + order.getArrivalMinutes();
            String orderLeavingTime = order.getLeavingDate() + " " + order.getLeavingHours() + ":" + order.getLeavingMinutes();
            LocalDateTime orderArrival = LocalDateTime.parse(orderArrivalTime,formatter);
            LocalDateTime orderLeaving = LocalDateTime.parse(orderLeavingTime,formatter);
            if(order.getCarNumber().equals(carNumber) && ((dateTimeArrival.isAfter(orderArrival) || dateTimeArrival.equals(orderArrival))
                    && dateTimeArrival.isBefore(orderLeaving)) && parkingLot.equals(order.getParkingLotName())){
                return true;
            }
        }
        return false;
    }

    public InAdvanceOrderEntity getOrder(){
        String arrivalTimeAndDate = arrivalDate + " " + arrivalHours + ":" + arrivalMinutes;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //  ??dd/MM/yyyy HH:mm instead??
        LocalDateTime dateTimeArrival = LocalDateTime.parse(arrivalTimeAndDate,formatter);
        for(InAdvanceOrderEntity order : orders){
            String orderArrivalTime = order.getArrivalDate()+ " " + order.getArrivalHours() + ":" + order.getArrivalMinutes();
            String orderLeavingTime = order.getLeavingDate() + " " + order.getLeavingHours() + ":" + order.getLeavingMinutes();
            LocalDateTime orderArrival = LocalDateTime.parse(orderArrivalTime,formatter);
            LocalDateTime orderLeaving = LocalDateTime.parse(orderLeavingTime,formatter);
            if(order.getCarNumber().equals(carNumber) && ((dateTimeArrival.isAfter(orderArrival) || dateTimeArrival.equals(orderArrival))
                    && dateTimeArrival.isBefore(orderLeaving)) && parkingLot.equals(order.getParkingLotName())){
                return order;
            }
        }
        return null;
    }
}
