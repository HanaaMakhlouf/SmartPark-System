package il.cshaifasweng.OCSFMediatorExample.client;

public class SetUpEvent {
    private int parkId;

    public SetUpEvent(int park_num) {
        this.parkId = park_num;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }
}
