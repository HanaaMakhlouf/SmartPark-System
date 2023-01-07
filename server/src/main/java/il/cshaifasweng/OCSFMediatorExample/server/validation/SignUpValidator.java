package il.cshaifasweng.OCSFMediatorExample.server.validation;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.*;


public class SignUpValidator {
    String userId;
    String userPass;
    String userEmail;

    public SignUpValidator(String userid, String userpass, String userEmail) {
        this.userId = userid;
        this.userPass = userpass;
        this.userEmail = userEmail;
    }

    public SignUpValidator() {
    }

    public boolean validateUserCredentials(List<User> userList){
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        try{
            int id = Integer.parseInt(this.userId);
            if(this.userId.length() != 9){
                return false;
            }else if(!this.userEmail.matches(EMAIL_REGEX)){
                return false;
            }
            for(User user : userList) {
                if (user.getId() == Integer.parseInt(this.userId)) {
                    return false;
                }
            }

        }catch(Exception e){
               return false;
            }
        return true;
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
