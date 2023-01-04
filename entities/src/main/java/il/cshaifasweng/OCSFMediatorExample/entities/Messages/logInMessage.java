package il.cshaifasweng.OCSFMediatorExample.entities.Messages;

import java.io.Serializable;

public class logInMessage implements Serializable {
    String userId;
    String userPass;
    boolean result;

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPass() {
        return userPass;
    }

    public logInMessage(String userId, String userPass) {
        this.userId = userId;
        this.userPass = userPass;
    }
}

