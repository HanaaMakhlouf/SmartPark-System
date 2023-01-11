package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.MessageBetweenClients;

public class ShowMessageFromOthersEvent {
    private MessageBetweenClients msg;
    public ShowMessageFromOthersEvent(MessageBetweenClients msg) {
        this.msg = msg;

    }

    public MessageBetweenClients getMsg() {
        return msg;
    }

    public void setMsg(MessageBetweenClients msg) {
        this.msg = msg;
    }
}
