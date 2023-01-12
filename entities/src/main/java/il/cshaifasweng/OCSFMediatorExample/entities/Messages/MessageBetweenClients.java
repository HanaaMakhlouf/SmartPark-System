package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import il.cshaifasweng.OCSFMediatorExample.entities.Manager;

import java.io.Serializable;

public class MessageBetweenClients implements Serializable {
    private int recepientID;
    private String MessageToSend;
    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public MessageBetweenClients(int recepientID, String messageToSend) {
        this.recepientID = recepientID;
        MessageToSend = messageToSend;
    }

    public int getRecepientID() {
        return recepientID;
    }

    public void setRecepientID(int recepientID) {
        this.recepientID = recepientID;
    }

    public String getMessage() {
        return MessageToSend;
    }

    public void setMessage(String message) {
        MessageToSend = message;
    }
}
