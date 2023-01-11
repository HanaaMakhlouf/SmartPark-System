package il.cshaifasweng.OCSFMediatorExample.server.validation;

import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLots;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class FullMembershipValidator {
    String carNumber;
    String arrivalDate ;

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

    public FullMembershipValidator(String carNumber, String arrivalDate) {
        this.carNumber = carNumber;
        this.arrivalDate = arrivalDate;
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
}
