package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

public class CancelOrderMessage {
    private String orderNumber;

    public CancelOrderMessage(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
