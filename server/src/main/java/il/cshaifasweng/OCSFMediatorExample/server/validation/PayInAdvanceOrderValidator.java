package il.cshaifasweng.OCSFMediatorExample.server.validation;

import java.io.IOException;
import java.text.ParseException;

public class PayInAdvanceOrderValidator {
    String cardNumber ;
    String cvv ;
    String month , year ;

    public PayInAdvanceOrderValidator(String cardNumber, String cvv,  String yearCard, String monthCard) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.year = yearCard;
        this.month = monthCard ;
    }
    public boolean validatePayment()throws ParseException, IOException {
        System.out.println(month+"montheee");
        System.out.println(year+"yeareee");

        if (cvv == null || cardNumber == null) {
            return false;
        }
        try {
            if (cvv.length() != 3)
                return false;
            Integer.parseInt(cvv);
            Integer.parseInt(cardNumber);
            Integer.parseInt(month);
            Integer.parseInt(year);
        } catch (NumberFormatException nfe) {
            return false;
        }
    return true ;
    }


}

