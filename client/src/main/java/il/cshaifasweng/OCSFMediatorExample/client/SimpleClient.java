package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.logInEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.ChangePricesRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.*;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.*;
import il.cshaifasweng.OCSFMediatorExample.entities.Subscriber;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.*;
import il.cshaifasweng.OCSFMediatorExample.entities.Subscriber;
import jdk.jfr.Event;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;

import java.util.ArrayList;
import java.util.List;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;
	private static String id_of_client;

	public static String getId_of_client() {
		return id_of_client;
	}

	public static void setId_of_client(String id_of_client) {
		SimpleClient.id_of_client = id_of_client;
	}

	public SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		if(msg instanceof logInMessage){
			logInMessage message = (logInMessage) msg;
			EventBus.getDefault().post(new logInEvent(message.getResult()));
		}else if(msg instanceof MemberLogInMessage) {
			MemberLogInMessage message = (MemberLogInMessage) msg;
			EventBus.getDefault().post(new LogInMemberEvent(message));
		}
		else if(msg instanceof SignUpMessage){
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
		else if(msg instanceof GetallOrdersOfClient) {
			GetallOrdersOfClient message = (GetallOrdersOfClient) msg;
			System.out.println("we in clietn side");
			System.out.println(message.getLst().get(0).getUserID());
			EventBus.getDefault().post(new ShowTrackOrdersEvent(message));

		}
		else if(msg instanceof GetBalance)
		{
			GetBalance message = (GetBalance) msg;
			EventBus.getDefault().post(new SetBalanceEvent(message.getUserbalance()));

		}
		else if(msg instanceof GetComplaintsMessage){
			GetComplaintsMessage message = (GetComplaintsMessage) msg;
			//System.out.println(message.getGetForWhom());
			if(message.getGetForWhom() == 1)
			EventBus.getDefault().post(new ShowComplaintsEvent(message));
			else
			EventBus.getDefault().post(new TrackComplaintEvent(message));
		}

		else if(msg instanceof OrderToDeleteMsg)
		{
			OrderToDeleteMsg message = (OrderToDeleteMsg) msg;
			EventBus.getDefault().post(new showRefundEvent(message.getBalance()));
		}


		else if(msg instanceof AdminMessage) {
			AdminMessage message = (AdminMessage) msg;
			ArrayList<Subscriber> lst = message.getLst();
			EventBus.getDefault().post(new showSubsForAdminEvent(lst));
		}
		else if(msg instanceof MessageBetweenClients) {

			MessageBetweenClients message = (MessageBetweenClients) msg;
			EventBus.getDefault().post(new ShowMessageFromOthersEvent(message));


		}
		else if(msg instanceof SendFailedMessage)
		{
			EventBus.getDefault().post(new ShowSendResultevent(0));
		}



		else if(msg instanceof StandardMembershipMessage){
			StandardMembershipMessage message = (StandardMembershipMessage) msg;
			EventBus.getDefault().post(new StandardMembershipEvent(message));
		}
		else if(msg instanceof PayFullMembershipMessage){
			PayFullMembershipMessage message = (PayFullMembershipMessage) msg;
			EventBus.getDefault().post(new PayFullMembershipEvent(message));
		}
		else if(msg instanceof FullMembershipMessage){
			FullMembershipMessage message = (FullMembershipMessage) msg;
			EventBus.getDefault().post(new FullMembershipEvent(message));
		}
		else if(msg instanceof PayStandardMembershipMessage){
			PayStandardMembershipMessage message = (PayStandardMembershipMessage) msg;
			EventBus.getDefault().post(new PayStandardMembershipEvent(message));
		}

		else if(msg instanceof GetParkingLotByEmployeeId){
			GetParkingLotByEmployeeId message = (GetParkingLotByEmployeeId) msg;
			EventBus.getDefault().post(new SendParkNumEvent(message.getPark_num()));
		}
		else if(msg instanceof AdminMessage) {
			AdminMessage message = (AdminMessage) msg;
			ArrayList<Subscriber> lst = message.getLst();
			EventBus.getDefault().post(new showSubsForAdminEvent(lst));
		}
		else if(msg instanceof ShowRequestForGM)
		{
			ShowRequestForGM message = (ShowRequestForGM) msg;
			List<ChangePricesRequest> lst = message.getList();
			EventBus.getDefault().post(new ShowChangePricesRequestEvent(lst));


		}
		else if(msg instanceof ShowRequestForManager)
		{
			ShowRequestForManager message = (ShowRequestForManager) msg;
			List<ChangePricesRequest> lst = message.getList();
			EventBus.getDefault().post(new ShowChangePricesRequestEventTwo(lst));

		}
		else {
			Message message = (Message) msg;
			if (message.getMessage().equals("update submitters IDs")) {
				EventBus.getDefault().post(new UpdateMessageEvent(message));
			} else if (message.getMessage().equals("client added successfully")) {
				EventBus.getDefault().post(new NewSubscriberEvent(message));
			}
			else if(message.getMessage().equals("send success"))
			{
				EventBus.getDefault().post(new ShowSendResultevent(1));

			}

			else if (message.getMessage().equals("Error! we got an empty message")) {
				EventBus.getDefault().post(new ErrorEvent(message));
			} else if (message.getMessage().equals("plzz")) {
				System.out.println("server sebt plzz");
				EventBus.getDefault().post(new showTableEvent(message.getList()));
			} else if (message.getMessage().equals("prices list is sent")) {

				if(message.getId() == 0) EventBus.getDefault().post(new showptableEvent(message.getPlist()));
				if(message.getId() == 1 ) EventBus.getDefault().post(new showptableEventTwo(message.getPlist()));

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
