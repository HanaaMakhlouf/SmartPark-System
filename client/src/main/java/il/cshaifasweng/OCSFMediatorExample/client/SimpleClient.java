package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.logInEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.*;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		if(msg instanceof logInMessage){
			logInMessage message = (logInMessage) msg;
			EventBus.getDefault().post(new logInEvent(message.getResult()));
		}else if(msg instanceof SignUpMessage){
			SignUpMessage message = (SignUpMessage) msg;
			EventBus.getDefault().post(new SignUpEvent(message.getResult()));
		}
		else if(msg instanceof InAdvanceOrderMessage){
			InAdvanceOrderMessage message = (InAdvanceOrderMessage) msg;
			EventBus.getDefault().post(new InAdvanceOrderEvent(message));
		}
		else if(msg instanceof PayInAdvanceOrderMessage){
			PayInAdvanceOrderMessage message = (PayInAdvanceOrderMessage) msg;
			EventBus.getDefault().post(new PayInAdvanceOrderEvent(message));
		}
		else {
			Message message = (Message) msg;
			if (message.getMessage().equals("update submitters IDs")) {
				EventBus.getDefault().post(new UpdateMessageEvent(message));
			} else if (message.getMessage().equals("client added successfully")) {
				EventBus.getDefault().post(new NewSubscriberEvent(message));
			} else if (message.getMessage().equals("Error! we got an empty message")) {
				EventBus.getDefault().post(new ErrorEvent(message));
			} else if (message.getMessage().equals("plzz")) {
				System.out.println("server sebt plzz");
				EventBus.getDefault().post(new showTableEvent(message.getList()));
			} else if (message.getMessage().equals("prices list is sent")) {

				EventBus.getDefault().post(new showptableEvent(message.getPlist()));

			} else {
				EventBus.getDefault().post(new MessageEvent(message));
			}
		}
	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3030);
		}
		return client;
	}

}
