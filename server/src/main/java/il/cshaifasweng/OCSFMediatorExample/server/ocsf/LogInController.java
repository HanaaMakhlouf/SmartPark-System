package il.cshaifasweng.OCSFMediatorExample.server.ocsf;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.*;


public class LogInController {
    String userId;
    String userPass;

    public LogInController(String userid, String userpass) {
        this.userId = userid;
        this.userPass = userpass;
    }

    public LogInController() {
    }

    public boolean validateUserCredentials(List<User> userList){
        for (User user:userList){
            if (Integer.parseInt(userId) == user.getId() && userPass.equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

//    private static List<User> getUsers() throws Exception {
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        session.beginTransaction();
//        CriteriaQuery<User> query = builder.createQuery(User.class);
//        query.from(Prices.class);
//        List<User> users = session.createQuery(query).getResultList();
//        return users;
//    }

}
