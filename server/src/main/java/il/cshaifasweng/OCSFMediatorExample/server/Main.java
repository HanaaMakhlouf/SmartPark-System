package il.cshaifasweng.OCSFMediatorExample.server;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.InAdvanceOrder;
import il.cshaifasweng.OCSFMediatorExample.client.StandardMembershipEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
//import il.cshaifasweng.OCSFMediatorExample.
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.LogInController;
import il.cshaifasweng.OCSFMediatorExample.server.validation.*;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Main extends SimpleServer {
private static SimpleServer server;
private static Session session;
private Message serverMSG;
private static SessionFactory sessionFactory = getSessionFactory();
private static List<ParkingLots> data = new ArrayList<>();
private static List<Prices> data2 = new ArrayList<>();
private static ArrayList<SubscribedClient> SubscribersList = new ArrayList<>();

public static ArrayList<Spot> spots_1 = new ArrayList<>();
public static ArrayList<Spot> spots_2 = new ArrayList<>();
public static ArrayList<Spot> spots_3 = new ArrayList<>();


    public Main(int port) {
        super(port);
    }



    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration =new Configuration();
        configuration.addAnnotatedClass(ParkingLots.class);
        configuration.addAnnotatedClass(Prices.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(InAdvanceOrderEntity.class);
        configuration.addAnnotatedClass(ParkingLotEmployee.class);
        configuration.addAnnotatedClass(Manager.class);
        configuration.addAnnotatedClass(GeneralManager.class);
        configuration.addAnnotatedClass(CustomerServiceEmployee.class);
        configuration.addAnnotatedClass(Subscriber.class);
        configuration.addAnnotatedClass(FullMemberShipEntity.class);
        configuration.addAnnotatedClass(StandardMemberShipEntity.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static void initParkingLots(){
        ParkingLots p1 = new ParkingLots(4 ,"Haifa Port" );
        ParkingLots p2 = new ParkingLots(6, "Carmel");
        ParkingLots p3 = new ParkingLots(8, "Central Station");

        session.save(p1);
        session.save(p2);
        session.save(p3);
        session.flush();
        //session.getTransaction().commit();
    }
    private static void initPrices() {
        Prices pr1 = new Prices(20, 30, 40, 50, 60);
        session.save(pr1);
        session.flush();

    }

    private static void initInAdvanceOrders() {
        for(int i=0; i<20; i++) {
            InAdvanceOrderEntity inAdvanceOrder1 = new InAdvanceOrderEntity("1234567", "00", "20/01/2023"
                    , "16", "00", "20/01/2023", "12", "Haifa Port");

            session.save(inAdvanceOrder1);
            session.flush();
            inAdvanceOrder1.setOrderID("10" + String.valueOf(inAdvanceOrder1.getId()));
            session.flush();
        }
//        session.getTransaction().commit();
    }

    private static void initUser(){
        User u1 = new User(208110130,"saed.diab.98@gmail.com","102030");
        User u2 = new User(123456789,"someone@gmail.com","405060");
        User u3 = new User(987654321,"someoneElse@gmail.com","708090");
        session.save(u1);
        session.save(u2);
        session.save(u3);
        session.flush();
        //   session.getTransaction().commit();
    }
    private static void initParkingLotEmployee(){
        ParkingLotEmployee u1 = new ParkingLotEmployee(111222333,"employee1@gmail.com",
                "111222333",1);
        ParkingLotEmployee u2 = new ParkingLotEmployee(444555666,"employee2@gmail.com",
                "444555666",2);
        ParkingLotEmployee u3 = new ParkingLotEmployee(777888999,"employee3@gmail.com",
                "777888999",3);
        session.save(u1);
        session.save(u2);
        session.save(u3);
        session.flush();
       // session.getTransaction().commit();
    }

    private static void initManagers(){
        Manager u1 = new Manager(111111333,"manager1@gmail.com",
                "111111333",1);
        Manager u2 = new Manager(444444666,"manager2@gmail.com",
                "444444666",2);
        Manager u3 = new Manager(777777999,"manager3@gmail.com",
                "777777999",3);
        session.save(u1);
        session.save(u2);
        session.save(u3);
        session.flush();
        // session.getTransaction().commit();
    }

    private static void initGeneralManager(){
        GeneralManager u1 = new GeneralManager(999999999,"bigBoss@gmail.com", "999999999");
        session.save(u1);
        session.flush();
        // session.getTransaction().commit();
    }

    private static void initCustomerServiceEmployee(){
        CustomerServiceEmployee u1 = new CustomerServiceEmployee(111111111,"CSemployee1@gmail.com",
                "111111111");
        CustomerServiceEmployee u2 = new CustomerServiceEmployee(222222222,"CSemployee2@gmail.com",
                "222222222");
        CustomerServiceEmployee u3 = new CustomerServiceEmployee(333333333,"CSemployee3@gmail.com",
                "333333333");
        session.save(u1);
        session.save(u2);
        session.save(u3);
        session.flush();

    }
    private static void initializeData() throws Exception {
        session.beginTransaction();
        FullMemberShipEntity tmp = new FullMemberShipEntity(208110120,"1234568","29/01/2023");
        session.save(tmp);
        session.flush();
        tmp.setMembershipID("10"+tmp.getId());
        StandardMemberShipEntity tmp2 = new StandardMemberShipEntity(208110120,"1234568"
                ,"29/01/2023","Haifa Port");
        session.save(tmp2);
        session.flush();
        tmp2.setMembershipID("20"+tmp2.getId());
//        initParkingLots();
//        initPrices();
//        initUser();
//        initInAdvanceOrders();
//        initParkingLotEmployee();
//        initManagers();
//        initGeneralManager();
//        initCustomerServiceEmployee();
        session.getTransaction().commit();
    }


    /*private static List<ParkingLots> getAllParkinglots() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ParkingLots> query = builder.createQuery(ParkingLots.class);
        query.from(ParkingLots.class);
        List<ParkingLots> data = session.createQuery(query).getResultList();
        return data;
    }
    private static List<Prices> getPrices() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Prices> query = builder.createQuery(Prices.class);
        query.from(Prices.class);
        List<Prices> data = session.createQuery(query).getResultList();
        return data;
    }*/

//    private static List<User> getUsers() throws Exception {
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        session.beginTransaction();
//        CriteriaQuery<User> query = builder.createQuery(User.class);
//        query.from(Prices.class);
//        List<User> users = session.createQuery(query).getResultList();
//        return users;
//    }



    public static <T> List<T> getAll(Class<T> object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
        Root<T> rootEntry = criteriaQuery.from(object);
        CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);
        TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
        return allQuery.getResultList();
    }

    public static <T> T getOfClass(Class<T> object){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        session.beginTransaction();
        CriteriaQuery<T> query = builder.createQuery(object);
        query.from(Prices.class);
        List<T> data = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        return data.get(0);
    }

    public static double calcFee(String startDate,String startHour,String startMin
            ,String endDate,String endHour,String endMin){
        Prices pricesData = getOfClass(Prices.class);
        int perHour = pricesData.getIn_Advance_price();
        String arrivalTimeAndDate = startDate + " " + startHour + ":" + startMin;
        String leavingTimeAndDate = endDate + " " + endHour + ":" + endMin;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //  ??dd/MM/yyyy HH:mm instead??
        LocalDateTime dateTimeStart = LocalDateTime.parse(arrivalTimeAndDate,formatter);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(leavingTimeAndDate,formatter);
        Duration dur = Duration.between(dateTimeStart,dateTimeEnd);
        return  ((dur.toHours()+(double)(dur.toMinutesPart()/60))*perHour);
    }


    void setUpPark(int parkNum)
    {
        int spotsToSetUp = 0;
        if(parkNum == 1) {
            spotsToSetUp = 36;
            fillUp(spotsToSetUp, spots_1);
        }
        else if (parkNum == 2) {
            spotsToSetUp = 54;
            fillUp(spotsToSetUp, spots_2);
        }
        else if (parkNum == 3) {
                spotsToSetUp = 72;
            fillUp(spotsToSetUp, spots_3);
        }
    }

    private void fillUp(int spotsToSetUp, ArrayList<Spot> spots) {
        for (int i = 0 ; i < 3 ; i++)
            for (int j = 0 ; j < 3;j++)
                for (int k = 0 ; k < spotsToSetUp;k++) {
                    Spot s = new Spot(i, j, k, true, false);
                    spots.add(s);
                }
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        System.out.println("handler here");
        try {
            if(msg instanceof logInMessage){
                logInMessage message = (logInMessage) msg;
                List<User> userList = getAll(User.class);
                List<Manager> managerList = getAll(Manager.class);
                List<ParkingLotEmployee> employeeList = getAll(ParkingLotEmployee.class);
                List<GeneralManager> gmList = getAll(GeneralManager.class);
                List<CustomerServiceEmployee> cs_employeeListgetAll= getAll(CustomerServiceEmployee.class);
                LogInController logInCntrl = new LogInController(message.getUserId(),message.getUserPass());
                message.setResult(logInCntrl.validateUserCredentials(userList,managerList,employeeList,gmList,cs_employeeListgetAll));
                client.sendToClient(message);
            }else if(msg instanceof SignUpMessage){
                SignUpMessage message = (SignUpMessage) msg;
                List<User> userList = getAll(User.class);
                SignUpValidator validator = new SignUpValidator(message.getUserId(),message.getUserPass(), message.getUserEmail());
                message.setResult(validator.validateUserCredentials(userList));
                if(message.getResult()){
                    session.beginTransaction();
                    User newUser = new User(Integer.parseInt(message.getUserId()), message.getUserEmail(), message.getUserPass());
                    SubscribedClient connection = new SubscribedClient(client);
                    connection.setClientID(Integer.parseInt(message.getUserId()));
                    Subscriber s = new Subscriber(connection.getClientID());
                    SubscribersList.add(connection);
                    session.save(newUser);
                    session.flush();
                    session.save(s);
                    session.flush();
                    session.getTransaction().commit();
                }
                client.sendToClient(message);
            }
            else if(msg instanceof AdminMessage)
            {
                AdminMessage message = (AdminMessage) msg;
                ArrayList<Subscriber> lst = new ArrayList<>();
                for(SubscribedClient p : SubscribersList) {
                    Subscriber subscriber = new Subscriber(p.getClientID());
                    lst.add(subscriber);

                }
                message.setLst(lst);
                client.sendToClient(message);

            }
            else if(msg instanceof InAdvanceOrderMessage){
                InAdvanceOrderMessage message = (InAdvanceOrderMessage) msg;
//                int tmpId = 208110130;
                String carNum = message.getCarNumber(),parkingLot = message.getParkingLot();
                String leavingDate = message.getLeavingDate(),leavingHours = message.getLeavingHours(),leavingMin = message.getLeavingMinutes();
                String arrivingDate = message.getArrivingDate(),arrivingHours = message.getArrivingHours(),arrivingMin = message.getArrivingMinutes();

                List<ParkingLots> parkingLots = getAll(ParkingLots.class);
                List<InAdvanceOrderEntity> inAdvanceOrders = getAll(InAdvanceOrderEntity.class);
                InAdvanceOrderValidator validator= new InAdvanceOrderValidator(carNum,parkingLot,arrivingHours
                        ,arrivingDate,arrivingMin,leavingHours,leavingDate,leavingMin, parkingLots, inAdvanceOrders);
                message.setResult(validator.validateOrder());
//                if(message.isResult()){
//                    session.beginTransaction();
//                    InAdvanceOrderEntity newInAdvance = new InAdvanceOrderEntity(carNum,leavingMin,leavingDate
//                            ,leavingHours,arrivingMin,arrivingDate, arrivingHours, parkingLot);
//                    session.save(newInAdvance);
//                    session.flush();
//                    session.getTransaction().commit();
//                    message.setFee(calcFee(arrivingDate,arrivingHours,arrivingMin,leavingDate,leavingHours, leavingMin));
//                }
//                System.out.println("about to send msg to client");
//                System.out.println(message.getResult());
                message.setFee(calcFee(arrivingDate,arrivingHours,arrivingMin,leavingDate,leavingHours, leavingMin));
                message.setOrderId(String.valueOf((inAdvanceOrders.get(inAdvanceOrders.size()-1).getId())+1));
//                message.setInAdvanceOrder(newInAdvance);
                client.sendToClient(message);
            }
            else if(msg instanceof PayInAdvanceOrderMessage) {
                PayInAdvanceOrderMessage message = (PayInAdvanceOrderMessage) msg;
                String carNum = message.getCarNumber(),parkingLot = message.getParkingLot();
                String leavingDate = message.getLeavingDate(),leavingHours = message.getLeavingHours(),leavingMin = message.getLeavingMinutes();
                String arrivingDate = message.getArrivingDate(),arrivingHours = message.getArrivingHours(),arrivingMin = message.getArrivingMinutes();
                String cvvCard = message.getCvv() , yearCard = message.getYear() , monthCard = message.getMonth() , cardNum = message.getCardNumber();
                PayInAdvanceOrderValidator validator= new PayInAdvanceOrderValidator(cardNum ,cvvCard,yearCard,monthCard);
                message.setResult(validator.validatePayment());
                if(message.isResult()) {
                    InAdvanceOrderEntity newInAdvance = new InAdvanceOrderEntity(carNum, leavingMin, leavingDate
                            , leavingHours, arrivingMin, arrivingDate, arrivingHours, parkingLot);
                    session.beginTransaction();
                    session.save(newInAdvance);
                    session.flush();
                    session.getTransaction().commit();
                }
                /* needed
                make InAdvanceOrderEntity and add to DB
                validate payment
                 */
            }
            else if(msg instanceof FullMembershipMessage){
                FullMembershipMessage message = (FullMembershipMessage) msg;
                FullMembershipValidator validator = new FullMembershipValidator(message.getCarNumber()
                        , message.getStartDate());
                message.setResult(validator.validateMembership());
                if (message.isResult()){
                    FullMemberShipEntity fullMemberShipEntity = new FullMemberShipEntity(Integer.parseInt(message.getId())
                            ,message.getCarNumber(),message.getStartDate());
                    session.beginTransaction();
                    session.save(fullMemberShipEntity);
                    session.flush();
                    fullMemberShipEntity.setMembershipID("10"+fullMemberShipEntity.getId());
                    message.setMembershipId(fullMemberShipEntity.getMembershipID());
                    session.getTransaction().commit();
                }
                client.sendToClient(message);
            }
            else if(msg instanceof StandardMembershipMessage){
                StandardMembershipMessage message = (StandardMembershipMessage) msg;
                StandardMembershipValidator validator = new StandardMembershipValidator(message.getCarNumber()
                        , message.getStartDate(),message.getParkingLot());
                message.setResult(validator.validateMembership());
                System.out.println(message.isResult());
                if (message.isResult()){
                    StandardMemberShipEntity standardMemberShipEntity = new StandardMemberShipEntity(Integer.parseInt(message.getId())
                            ,message.getCarNumber(),message.getStartDate(),message.getParkingLot());
                    session.beginTransaction();
                    session.save(standardMemberShipEntity);
                    session.flush();
                    standardMemberShipEntity.setMembershipID("20"+standardMemberShipEntity.getId());
                    message.setMembershipId(standardMemberShipEntity.getMembershipID());
                    session.getTransaction().commit();
                }
                client.sendToClient(message);
            }
            else if(msg instanceof GetParkingLotByEmployeeId){
                GetParkingLotByEmployeeId message = (GetParkingLotByEmployeeId) msg;
                List<ParkingLotEmployee> employeeList = getAll(ParkingLotEmployee.class);
                int park_num = 0;
                for (ParkingLotEmployee em:employeeList){
                    if(em.getId() == message.getId()) {
                        park_num = em.getParkingLot();
                    }
                }
                message.setPark_num(park_num);
                client.sendToClient(message);
            }
            else if(msg instanceof SetUpMessage){
                setUpPark(((SetUpMessage) msg).getPark_num());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    if(msg instanceof Message) {
            Message message = (Message) msg;
            String request = message.getMessage();
            String action = message.getAction();
            try {
                //we got an empty message, so we will send back an error message with the error details.
                if (request.isBlank()) {
                    message.setMessage("Error! we got an empty message");
                    client.sendToClient(message);
                }

                else if (request.equals("print parking table")) {

                    System.out.println("print parking table message");
// Connect to the database and retrieve the data from the parkinglots table
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cps-db", "root", "saedrocks98")) {
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM parkinglotss");
                        data.clear();
                        while (rs.next()) {
                            // Add the data to the ObservableList
                            data.add(new ParkingLots(rs.getInt("id"), rs.getInt("num_of_rows"),
                                    rs.getInt("num_of_parking_spots") , rs.getString("Name")));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
// Set the items of the TableView to the ObservableList
                    for (ParkingLots p : data) {
                        System.out.println("id is " + p.getId() + "num of rows is " + p.getNum_of_rows() +
                                "num of spots is" + p.getParking_spots());

                    }
                    System.out.println("we here");
                    message.setList(data);
                    message.setMessage("plzz");
                    client.sendToClient(message);
                }
                //we got a request to add a new client as a subscriber.
//            else if (request.equals("add client")){
//                SubscribedClient connection = new SubscribedClient(client);
//                SubscribersList.add(connection);
//                message.setMessage("client added successfully");
//                client.sendToClient(message);
//            }
                //we got a message from client requesting to echo Hello, so we will send back to client Hello world!
                else if (request.startsWith("print prices table")) {

                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cps-db", "root", "saedrocks98")) {
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM prices");
                        data2.clear();

                        while (rs.next()) {
                            // Add the data to the ObservableList
                            data2.add(new Prices(rs.getInt("id"), rs.getInt("in_advance"),
                                    rs.getInt("in_place"), rs.getInt("regular_membership_single"),
                                    rs.getInt("regular_membership_multiple"), rs.getInt("Full_membership")));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
// Set the items of the TableView to the ObservableList
                    for (Prices p : data2) {
                        System.out.println("id is " + p.getId() + "in advance " + p.getIn_Advance_price() + "in place" +
                                p.getIn_place_price() + "mem reg single " + p.getSingle_car_reg_mem_price() + "mem multiple reg" +
                                p.getMultiple_cars_reg_mem_price() + "full mem " + p.getFull_mem_price());

                    }
                    System.out.println("we here prices");
                    message.setPlist(data2);
                    message.setMessage("prices list is sent");
                    client.sendToClient(message);
                } else if (request.startsWith("attempt to change data")) {

                    int arr[] = message.getChange_prices();
                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    session.beginTransaction();
                    CriteriaQuery<Prices> query = builder.createQuery(Prices.class);
                    query.from(Prices.class);
                    List<Prices> pricesdata = session.createQuery(query).getResultList();
                    Prices price2 = pricesdata.get(0);

// update the entity's fields
                    if (arr[0] != -1 && arr[0] != -0)
                        price2.setFull_mem_price(arr[0]);
                    if (arr[1] != -1 && arr[1] != -0)
                        price2.setIn_Advance_price(arr[1]);
                    if (arr[2] != -1 && arr[2] != -0)
                        price2.setIn_place_price(arr[2]);
                    if (arr[3] != -1 && arr[3] != -0)
                        price2.setMultiple_cars_reg_mem_price(arr[3]);
                    if (arr[4] != -1 && arr[4] != -0)
                        price2.setSingle_car_reg_mem_price(arr[4]);

// save the updated entity
                    session.save(price2);
                    session.flush();
                    session.getTransaction().commit();
                    session.clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws Exception {

        server = new Main(3030);
        server.listen();
        System.out.println("Server says : hi ");
        try {
        session = sessionFactory.openSession();


        initializeData();

        } catch (HibernateException e)
        {
            e.printStackTrace();
        }
    }
}
