package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Messages.MemberLogInMessage;
import javafx.event.ActionEvent;

public class LogInMemberEvent extends ActionEvent{

    MemberLogInMessage msg1 ;
    public  LogInMemberEvent (MemberLogInMessage msg1){
        this.msg1 = msg1 ;
    }
    public MemberLogInMessage getMsg1() {
        return msg1;
    }

    public void setMsg1(MemberLogInMessage msg1) {
        this.msg1 = msg1;
    }
}
