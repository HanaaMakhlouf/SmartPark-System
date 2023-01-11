package il.cshaifasweng.OCSFMediatorExample.server.validation;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class StandardMembershipValidator {
    String carNumber;
    String arrivalDate ;
    String parkingLot;

    public static void  validateInAdvaceOrder(){

    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public StandardMembershipValidator(String carNumber, String arrivalDate,String parkingLot) {
        this.carNumber = carNumber;
        this.arrivalDate = arrivalDate;
        this.parkingLot = parkingLot;
    }

    public boolean validateMembership() throws ParseException, IOException {
        if(arrivalDate == null || carNumber == null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime dateTimeArrival = LocalDateTime.parse(arrivalDate,formatter);
            if(!(dateTimeArrival.toLocalDate().isAfter(LocalDate.now()))){
                return false;
            }
        }
        return true;
    }

    public String getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }
}
