package il.cshaifasweng.OCSFMediatorExample.server;


import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import javax.persistence.*;
import java.io.Serializable;


public class SubscribedClient implements Serializable {

    private int ClientID;


    private ConnectionToClient client;

    public SubscribedClient() {

    }


    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
        client.setInfo("ID",clientID);
    }

    public SubscribedClient(ConnectionToClient client) {
        this.client = client;
    }

    public ConnectionToClient getClient() {
        return client;
    }

    public void setClient(ConnectionToClient client) {
        this.client = client;
    }
}
