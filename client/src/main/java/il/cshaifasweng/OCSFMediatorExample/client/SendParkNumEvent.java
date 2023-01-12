package il.cshaifasweng.OCSFMediatorExample.client;

public class SendParkNumEvent {
    private int park_num;

    public void setPark_num(int park_num) {
        this.park_num = park_num;
    }

    public int getPark_num() {
        return park_num;
    }

    public SendParkNumEvent(int num) {
        this.park_num=num;
    }

}
