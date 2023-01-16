package il.cshaifasweng.OCSFMediatorExample.client;

public class SetBalanceEvent {
    private Double balance;

    public SetBalanceEvent(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
