package il.cshaifasweng.OCSFMediatorExample.server.validation;

import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.InPlaceOrderEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.ExitParkingMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Spot;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class ExitParkingLotService {

    String carNumber;
    boolean result;
    ExitParkingMessage message;
    boolean inPlaceOrder = false;
    List<InAdvanceOrderEntity> inAdvanceOrders;
    List<InPlaceOrderEntity> inPlaceOrders;
    InAdvanceOrderEntity inAdvanceOrder = null;
    InPlaceOrderEntity order = null;


    public ExitParkingLotService(String carNum, boolean result, ExitParkingMessage message,
                                 List<InPlaceOrderEntity> inPlaceOrders, List<InAdvanceOrderEntity> inAdvanceOrders) {
        this.carNumber = carNum;
        this.result = result;
        this.message = message;
        this.message.setResult(result);
        this.inPlaceOrders = inPlaceOrders;
        this.inAdvanceOrders = inAdvanceOrders;

    }

    public boolean getInPlaceOrder(){return this.inPlaceOrder;}
    public ExitParkingMessage getMessage() {

        if(result) {
            for (InAdvanceOrderEntity order : inAdvanceOrders) {
                if ((order.isCarEntered() && order.getCarNumber().equals(carNumber))) {
                    this.message.setIsInPlaceOrder(false);
                    this.inAdvanceOrder = order;
                }
            }
            for (InPlaceOrderEntity order : inPlaceOrders) {
                if (order.getCarNumber().equals(carNumber)) {
                    this.message.setIsInPlaceOrder(true);
                    this.order = order;
                }
            }
        }
        return message;
    }

    public InAdvanceOrderEntity getOrderInAdvance(){
        return inAdvanceOrder;
    }
    public InPlaceOrderEntity getOrderInPlace(){
        return order;
    }
}
