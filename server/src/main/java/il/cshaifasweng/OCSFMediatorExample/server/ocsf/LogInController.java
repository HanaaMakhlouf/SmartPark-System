package il.cshaifasweng.OCSFMediatorExample.server.ocsf;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.SubscribedClient;


public class LogInController {
    String userId;
    String userPass;

    public LogInController(String userid, String userpass) {
        this.userId = userid;
        this.userPass = userpass;
    }

    public LogInController() {
    }

    public int validateUserCredentials(List<User> userList, List<Manager> managerList,
                                       List<ParkingLotEmployee> employeeList, List<GeneralManager> gmList,
                                       List<CustomerServiceEmployee> cs_employeeList,List<Subscriber> subList) {
        for(Subscriber s : subList)
            if(s.getId() == Integer.parseInt(userId))
                return 0;


        if ((Integer.parseInt(userId) == 0000  || Integer.parseInt(userId) == 0001) && userPass.equals("Admin"))
        return 6;

        for (CustomerServiceEmployee cs_employee : cs_employeeList) {
            if (Integer.parseInt(userId) == cs_employee.getId() && userPass.equals(cs_employee.getPassword())) {
                return 5;
            }
        }

        for (ParkingLotEmployee employee : employeeList) {
            if (Integer.parseInt(userId) == employee.getId() && userPass.equals(employee.getPassword())) {
                return 3;
            }
        }
            for (Manager manager : managerList) {
                if (Integer.parseInt(userId) == manager.getId() && userPass.equals(manager.getPassword())) {
                    return 2;
                }
            }
                    for (GeneralManager generalManager : gmList) {
                        if (Integer.parseInt(userId) == generalManager.getId() && userPass.equals(generalManager.getPassword())) {
                            return 1;
                        }
                    }
        for (User user : userList) {
            if (Integer.parseInt(userId) == user.getId() && userPass.equals(user.getPassword())) {
                return 4;
            }
        }

                    return 0;
                }
    }

//    private static List<User> getUsers() throws Exception {
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        session.beginTransaction();
//        CriteriaQuery<User> query = builder.createQuery(User.class);
//        query.from(Prices.class);
//        List<User> users = session.createQuery(query).getResultList();
//        return users;
//    }


