package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.FullMembership;
import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.ExitParking;
import il.cshaifasweng.OCSFMediatorExample.client.GeneralManagerController;
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.SendComplaintMsg;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
//import il.cshaifasweng.OCSFMediatorExample.client.showSubsForAdminEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.entities.InAdvanceOrderEntity;
//import il.cshaifasweng.OCSFMediatorExample.
import il.cshaifasweng.OCSFMediatorExample.entities.Messages.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.LogInController;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.MemberLogInControler;
import il.cshaifasweng.OCSFMediatorExample.server.validation.InAdvanceOrderValidator;
import il.cshaifasweng.OCSFMediatorExample.server.validation.PayValidator;
import il.cshaifasweng.OCSFMediatorExample.server.validation.SignUpValidator;
import il.cshaifasweng.OCSFMediatorExample.server.validation.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
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
    private static ArrayList<ConnectionToClient> clientsConn = new ArrayList<>();

    private static ThreadGroup threadGroup = new ThreadGroup("SignedUpclientsThreadGroup");



    public Main(int port) {
        super(port);
    }
    private List<ConnectionToClient> clients=new ArrayList<>();

    public void addClient(ConnectionToClient client) {
        clients.add(client);
    }

    public List<ConnectionToClient> getClients() {
        return clients;
    }


    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration =new Configuration();
        configuration.addAnnotatedClass(ParkingLots.class);
        configuration.addAnnotatedClass(Prices.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(InAdvanceOrderEntity.class);
        configuration.addAnnotatedClass(FullMemberShipEntity.class);
        configuration.addAnnotatedClass(StandardMemberShipEntity.class);
        configuration.addAnnotatedClass(ParkingLotEmployee.class);
        configuration.addAnnotatedClass(Manager.class);
        configuration.addAnnotatedClass(GeneralManager.class);
        configuration.addAnnotatedClass(CustomerServiceEmployee.class);
        configuration.addAnnotatedClass(Subscriber.class);
        configuration.addAnnotatedClass(Complaint.class);
        configuration.addAnnotatedClass(ParkingLotEntitiy.class);
        configuration.addAnnotatedClass(Spot.class);
        configuration.addAnnotatedClass(ChangePricesRequest.class);
        configuration.addAnnotatedClass(OrdersReport.class);
        configuration.addAnnotatedClass(ComplaintsReport.class);
        configuration.addAnnotatedClass(InPlaceOrderEntity.class);
        configuration.addAnnotatedClass(RequestForReport.class);
        configuration.addAnnotatedClass(DisabledSpotReport.class);
        configuration.addAnnotatedClass(DisabledSpotHistory.class);
        configuration.addAnnotatedClass(ALLOrdersInTimePeriod.class);
        configuration.addAnnotatedClass(ComplaintsDataForReport.class);
        configuration.addAnnotatedClass(DisabledDataReport.class);


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
            InAdvanceOrderEntity inAdvanceOrder1 = new InAdvanceOrderEntity("1234567","123454321", "00", "20/01/2023"
                    , "16", "00", "20/01/2023", "12", "Haifa Port");
            session.save(inAdvanceOrder1);
            session.flush();
            inAdvanceOrder1.setOrderID("10" + String.valueOf(inAdvanceOrder1.getId()));
            session.flush();
        }
        for(int i=0;i<5;i++) {
            InAdvanceOrderEntity inAdvanceOrder2 = new InAdvanceOrderEntity("304955","123456789", "00", "20/02/2023"
                    , "16", "05", "20/02/2023", "16", "Carmel");

            session.save(inAdvanceOrder2);
            session.flush();
            inAdvanceOrder2.setOrderID("10" + String.valueOf(inAdvanceOrder2.getId()));
            session.flush();

        }
//        session.getTransaction().commit();
    }

    private static void InitParkings() {
        ParkingLotEntitiy p1 = new ParkingLotEntitiy(1,"Haifa Port");
        List<Spot> lst = new ArrayList<>();
        for (int k = 0 ; k < 3;k++)
              for (int i = 0 ; i < 3 ; i++)
                    for (int j = 0 ; j < 4;j++){
                        Spot s = new Spot(i, j, k, true, false,p1);
                        session.save(s);
                        lst.add(s);
                }

        p1.setDepth(3);
        p1.setWidth(4);
        p1.setHeight(3);
        p1.setSpots(lst);
        session.save(p1);
        session.flush();

        ParkingLotEntitiy p2 = new ParkingLotEntitiy(2,"Carmel");
        List<Spot> lst2 = new ArrayList<>();
        for (int k = 0 ; k < 3;k++)
             for (int i = 0 ; i < 3 ; i++)
                    for (int j = 0 ; j < 6;j++) {
                    Spot s = new Spot(i, j, k, true, false,p2);
                    session.save(s);
                    lst2.add(s);
                }
        p2.setDepth(3);
        p2.setWidth(6);
        p2.setHeight(3);
     //   p2.setSpots(lst);
        p2.setSpots(lst2);
        session.save(p2);
        session.flush();


        ParkingLotEntitiy p3 = new ParkingLotEntitiy(3,"Central Station");
        List<Spot> lst3 = new ArrayList<>();
        for (int k = 0 ; k < 3;k++)
                 for (int i = 0 ; i < 3 ; i++)
                    for (int j = 0 ; j < 8;j++) {
                    Spot s = new Spot(i, j, k, true, false,p3);
                    session.save(s);
                    lst3.add(s);
                }
        p3.setDepth(3);
        p3.setWidth(8);
        p3.setHeight(3);
     //   p3.setSpots(lst);
        p3.setSpots(lst3);
        session.save(p3);
        session.flush();

    }


    private static void initUser(){
        User u1 = new User(208110130,"saed.diab.98@gmail.com","102030");
        User u2 = new User(123456789,"someone@gmail.com","405060");
        User u3 = new User(987654321,"someoneElse@gmail.com","708090");
        User u4 = new User(211516950,"some@gmail.com","123");

        session.save(u1);
        session.save(u2);
        session.save(u3);
        session.save(u4);
        session.flush();
        //   session.getTransaction().commit();
    }
    private static void initParkingLotEmployee() throws IOException {
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

    private static void initCustomerServiceEmployee() throws IOException {
        CustomerServiceEmployee u1 = new CustomerServiceEmployee(111111111,"CSemployee1@gmail.com",
                "111111111");
        /*ConnectionToClient c = new ConnectionToClient(threadGroup,new Socket("localhost",3030),server);
        SubscribedClient connection = new SubscribedClient(c);
        connection.setClientID(111111111);
        Subscriber s = new Subscriber(connection.getClientID());
        SubscribersList.add(connection);*/

        CustomerServiceEmployee u2 = new CustomerServiceEmployee(222222222,"CSemployee2@gmail.com",
                "222222222");
        /*ConnectionToClient cc = new ConnectionToClient(threadGroup,new Socket("localhost",3030),server);
        SubscribedClient connection2 = new SubscribedClient(cc);
        connection2.setClientID(222222222);
        Subscriber ss = new Subscriber(connection2.getClientID());
        SubscribersList.add(connection2);*/
        CustomerServiceEmployee u3 = new CustomerServiceEmployee(333333333,"CSemployee3@gmail.com",
                "333333333");
        session.save(u1);
        session.save(u2);
        session.save(u3);
        session.flush();

    }
    private static void initStandardMembership(){

        StandardMemberShipEntity tmp2 = new StandardMemberShipEntity(321321321,"321321321"
                ,"10/01/2023","Haifa Port");
        session.save(tmp2);
        session.flush();
        tmp2.setMembershipID("0"+tmp2.getId());
        session.flush();
    }
    private static void initFulldMembership(){
        FullMemberShipEntity tmp = new FullMemberShipEntity(123123123,"123123123","20/01/2023");
        session.save(tmp);
        session.flush();
        tmp.setMembershipID("1"+tmp.getId());
        session.flush();
    }
    private static void initializeData() throws Exception {
        session.beginTransaction();
        initParkingLots();

        initPrices();
        initUser();
        //InitParkings();
        initInAdvanceOrders();
        List <Subscriber> lst = getAll(Subscriber.class);
        for(Subscriber s : lst) {
            session.delete(s);
            session.flush();
        }
        initStandardMembership();
        initFulldMembership();
        initInAdvanceOrders();
        initParkingLotEmployee();
        initManagers();
        initGeneralManager();
        initCustomerServiceEmployee();
        session.getTransaction().commit();
    }

    private static ConnectionToClient getConnection(int id) {
        for(ConnectionToClient c : clientsConn) {
            if (c.getIdofClientC() == id) return c;
        }
        return null;
    }

    public static <T, E> List<T> getAllWhereIdEquals(Class<T> object, E id, String field) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
        Root<T> rootEntry = criteriaQuery.from(object);
        criteriaQuery.select(rootEntry).where(builder.equal(rootEntry.get(field), id));
        TypedQuery<T> allQuery = session.createQuery(criteriaQuery);
        return allQuery.getResultList();
    }


    public static <T, E> List<T> getAllforReport(Class<T> object,String OrderDatefield,String ParknameField, LocalDateTime from,LocalDateTime until, E parkname) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
        Root<T> rootEntry = criteriaQuery.from(object);
        Predicate datePredicate = builder.and(
                builder.greaterThanOrEqualTo(rootEntry.get(OrderDatefield), from),
                builder.lessThanOrEqualTo(rootEntry.get(OrderDatefield), until)
        );
        Predicate parknamePredicate = builder.equal(rootEntry.get(ParknameField), parkname);
        Predicate finalPredicate = builder.and(datePredicate, parknamePredicate);
        criteriaQuery.select(rootEntry).where(finalPredicate);
        TypedQuery<T> allQuery = session.createQuery(criteriaQuery);
        return allQuery.getResultList();


    }

    public static <T, E> List<T> getAllforResolvedComplaintsReport(Class<T> object,String OrderDatefield,String ParknameField, LocalDateTime from,LocalDateTime until, E parkname,String respnse) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
        Root<T> rootEntry = criteriaQuery.from(object);
        Predicate datePredicate = builder.and(
                builder.greaterThanOrEqualTo(rootEntry.get(OrderDatefield), from),
                builder.lessThanOrEqualTo(rootEntry.get(OrderDatefield), until)
        );
        Predicate parknamePredicate = builder.equal(rootEntry.get(ParknameField), parkname);
        Predicate responsePredicate = builder.notEqual(rootEntry.get(respnse),"");
        Predicate finalPredicate = builder.and(datePredicate, parknamePredicate,responsePredicate);
        criteriaQuery.select(rootEntry).where(finalPredicate);
        TypedQuery<T> allQuery = session.createQuery(criteriaQuery);
        return allQuery.getResultList();


    }

    public static <T, E> List<T> getAllforUnResolvedComplaintsReport(Class<T> object,String OrderDatefield,String ParknameField, LocalDateTime from,LocalDateTime until, E parkname,String respnse) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
        Root<T> rootEntry = criteriaQuery.from(object);
        Predicate datePredicate = builder.and(
                builder.greaterThanOrEqualTo(rootEntry.get(OrderDatefield), from),
                builder.lessThanOrEqualTo(rootEntry.get(OrderDatefield), until)
        );
        Predicate parknamePredicate = builder.equal(rootEntry.get(ParknameField), parkname);
        Predicate responsePredicate = builder.equal(rootEntry.get(respnse),"");
        Predicate finalPredicate = builder.and(datePredicate, parknamePredicate,responsePredicate);
        criteriaQuery.select(rootEntry).where(finalPredicate);
        TypedQuery<T> allQuery = session.createQuery(criteriaQuery);
        return allQuery.getResultList();


    }


    public static <T> T getWhereIdEquals(Class<T> object,String id,String field) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
        Root<T> rootEntry = criteriaQuery.from(object);
        criteriaQuery.select(rootEntry).where(builder.equal(rootEntry.get(field), id));
        TypedQuery<T> allQuery = session.createQuery(criteriaQuery);
        return allQuery.getSingleResult();
    }
    public static <T> T getWhereIdEquals(Class<T> object,String id1,String field1,String id2,String field2) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
        Root<T> rootEntry = criteriaQuery.from(object);
        criteriaQuery.select(rootEntry).where(builder.equal(rootEntry.get(field1), id1)
                ,builder.equal(rootEntry.get(field2), id2));
        TypedQuery<T> allQuery = session.createQuery(criteriaQuery);
        return allQuery.getSingleResult();
    }

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
            ,String endDate,String endHour,String endMin, boolean isInPlaceOrder){
        Prices pricesData = getOfClass(Prices.class);
        int perHour = isInPlaceOrder ? pricesData.getIn_place_price() : pricesData.getIn_Advance_price();
        String arrivalTimeAndDate = startDate + " " + startHour + ":" + startMin;
        String leavingTimeAndDate = endDate + " " + endHour + ":" + endMin;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //  ??dd/MM/yyyy HH:mm instead??
        LocalDateTime dateTimeStart = LocalDateTime.parse(arrivalTimeAndDate,formatter);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(leavingTimeAndDate,formatter);
        Duration dur = Duration.between(dateTimeStart,dateTimeEnd);
        return  ((dur.toHours()+(double)(dur.toMinutesPart()/60))*perHour);
    }

    public static double calcFeeMembership(String membershipType){
        Prices pricesData = getOfClass(Prices.class);
        if(membershipType.equals("Full")){
            return pricesData.getFull_mem_price();
        }
        else if (membershipType.equals("Standard Single"))
            return pricesData.getSingle_car_reg_mem_price();
        else
            return pricesData.getMultiple_cars_reg_mem_price();
    }


    public static int getParkIdByName(String name){
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<ParkingLotEntitiy> query = builder.createQuery(ParkingLotEntitiy.class);
        Root<ParkingLotEntitiy> root = query.from(ParkingLotEntitiy.class);
        query.select(root);
        query.where(builder.equal(root.get("name"),name));
        return Main.session.createQuery(query).getSingleResult().getId();
    }

    public static Spot getSpotAtCords(int depth,int width,int height,int parkId){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Spot> criteriaQuery = builder.createQuery(Spot.class);
        Root<Spot> rootEntry = criteriaQuery.from(Spot.class);
        criteriaQuery.select(rootEntry);
        criteriaQuery.where(builder.equal(rootEntry.get("depth_num"),depth),builder.equal(rootEntry.get("width_num"),width)
        ,builder.equal(rootEntry.get("height_num"),height)/*,builder.equal(rootEntry.get("id_parking"),parkId)*/);
        List<Spot> spots = Main.session.createQuery(criteriaQuery).getResultList();
        for (Spot spot:spots){
            if(spot.getParkinglot().getId() == parkId){
                return spot;
            }
        }
        return null;
    }

    public static void parkInBestSpot(String carNum,String leavingDate,String park){
        ParkingLotEntitiy parkingLot= getWhereIdEquals(ParkingLotEntitiy.class,park,"name");
        session.beginTransaction();
//        String leavingTimeAndDate = leavingDate + " " + leavingHour + ":" + leavingMin;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime leavingTime = LocalDateTime.parse(leavingDate,formatter);
        for (int i=0;i<parkingLot.getDepth();i++){
            for (int j = 0; j < parkingLot.getWidth(); j++) {
                for (int k = 0; k <parkingLot.getHeight(); k++) {
                    Spot spot = getSpotAtCords(i,j,k,parkingLot.getId());
                    if(spot.isAvailable() && !spot.isSaved()){
                        if(i == 0){
                            spot.setLeaving(leavingDate);
                            spot.setCarNum(carNum);
                            spot.setAvailable(false);
                            session.getTransaction().commit();
                            return;
                        }
                        else{
                            Spot spotAhead = getSpotAtCords(i-1,j,k,parkingLot.getId());
                            System.out.println(spotAhead.getSpotid());
                            String spotLeaving = spotAhead.getLeaving();
                            LocalDateTime spotLeavingTime = LocalDateTime.parse(spotLeaving, formatter);
                            if(leavingTime.isBefore(spotLeavingTime) || spotLeavingTime.equals(leavingTime)){
                                System.out.println("inside the IF");
                                spot.setLeaving(leavingDate);
                                spot.setCarNum(carNum);
                                spot.setAvailable(false);
                                session.getTransaction().commit();
                                return;
                            }
                        }
                    }
                }
            }
        }
        //park first spot we find thats empty if all spots are leaving before us
        List<Spot> spots = getAll(Spot.class);
        for (Spot spot:spots){
            if(spot.getParkinglot().getId()==parkingLot.getId() && spot.isAvailable() && !(spot.isSaved())){
                spot.setLeaving(leavingDate);
                spot.setAvailable(false);
                spot.setCarNum(carNum);
            }
        }
        session.getTransaction().commit();
    }

    public static void addCarToPark(int parkId,String carNum){
        session.beginTransaction();
        List<Spot> spots = getAll(Spot.class);
        for (Spot spot:spots){
            if(spot.getParkinglot().getId()==parkId && spot.isAvailable() && !(spot.isSaved())){
                spot.setAvailable(false);
                spot.setCarNum(carNum);
                session.getTransaction().commit();
                return;
            }
        }
        session.getTransaction().commit();
    }

    static class carData{
        String carNum;
        String leavingDate;
        String park;

        public carData(String carNum, String leavingDate, String park) {
            this.carNum = carNum;
            this.leavingDate = leavingDate;
            this.park = park;
        }
    }
    public static void exitCar(String carNum, int parkId) {
        List<Spot> spots = getAll(Spot.class);
        Spot carSpot = null;
        List<carData> carsToBeMoved = new ArrayList<>();
        ParkingLotEntitiy parking = getWhereIdEquals(ParkingLotEntitiy.class, String.valueOf(parkId), "id");
        for (Spot spot : spots) {
            if (!spot.isAvailable() && spot.getCarNum().equals(carNum)) {
                carSpot = spot;
            }
        }
        for (int i = carSpot.getDepth_num() + 1; i < parking.getDepth(); i++) {
            Spot spot = getSpotAtCords(i, carSpot.getWidth_num(), carSpot.getHeight_num(), parking.getId());
            if(!spot.isAvailable()){
                session.beginTransaction();
                carsToBeMoved.add(new carData(spot.getCarNum(), spot.getLeaving(), parking.getName()));
                spot.resetSpot();
                session.update(spot);
                session.flush();
                session.getTransaction().commit();
            }
        }
        session.beginTransaction();
        carSpot.resetSpot();
        session.update(carSpot);
        session.flush();
        session.getTransaction().commit();
        for (Main.carData carData : carsToBeMoved) {
            parkInBestSpot(carData.carNum, carData.leavingDate, carData.park);
        }

    }

    public static int countFreeSpots(int parkId){
        int count = 0;
        List<Spot> spots = getAll(Spot.class);
        System.out.println("num of total spots is:"+spots.size());
        for (Spot spot:spots){
            if(spot.getParkinglot().getId()==parkId && spot.isAvailable() && !(spot.isSaved())){
                count++;
            }
        }
        return count;
    }



    public boolean sendtoSpecificClient(int clientId, MessageBetweenClients message) throws IOException {
        for(SubscribedClient s : SubscribersList)
        {
            if(s.getClientID()==clientId) {
                s.getClient().sendToClient(message);
                return true;
            }
        }
         return false;
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        try {
            if(msg instanceof logInMessage){
                logInMessage message = (logInMessage) msg;
                List<User> userList = getAll(User.class);
                List<Manager> managerList = getAll(Manager.class);
                List<ParkingLotEmployee> employeeList = getAll(ParkingLotEmployee.class);
                List<GeneralManager> gmList = getAll(GeneralManager.class);
                List<CustomerServiceEmployee> cs_employeeListgetAll = getAll(CustomerServiceEmployee.class);
                List<Subscriber> subs = getAll(Subscriber.class);
                LogInController logInCntrl = new LogInController(message.getUserId(), message.getUserPass());
                message.setResult(logInCntrl.validateUserCredentials(userList, managerList, employeeList, gmList, cs_employeeListgetAll, subs));
                SubscribedClient connection = new SubscribedClient(client);
                connection.setClientID(Integer.parseInt(message.getUserId()));
                SubscribersList.add(connection);
                message.setResult(logInCntrl.validateUserCredentials(userList,managerList,employeeList,gmList,cs_employeeListgetAll,subs));

                Subscriber s = new Subscriber(Integer.parseInt(message.getUserId()));
                if(message.getResult() !=0) {
                 session.beginTransaction();
                 session.save(s);
                session.flush();
                session.getTransaction().commit();  }
                message.setParkingLotId(logInCntrl.getParkingLotId());
                client.sendToClient(message);
            } else if (msg instanceof LogoutMessage) {
                LogoutMessage message = (LogoutMessage) msg;
                session.beginTransaction();
                List<Subscriber> s = getAllWhereIdEquals(Subscriber.class, message.getId(), "id");
                session.delete(s.get(0));
                session.flush();
                session.getTransaction().commit();

            }
            else if(msg instanceof ShowORDERSrepBYrepID)
            {
                ShowORDERSrepBYrepID message = (ShowORDERSrepBYrepID) msg;
                int id = message.getRepid();
                List<ALLOrdersInTimePeriod> lst =getAllWhereIdEquals(ALLOrdersInTimePeriod.class,id,"repid");
                    ShowORDERSrepBYrepID newMSG = new ShowORDERSrepBYrepID(id);
                    newMSG.setLst(lst);
                    client.sendToClient(newMSG);



            }
            else if(msg instanceof ShowCOMPrepBYrepID)
            {
                ShowCOMPrepBYrepID message = (ShowCOMPrepBYrepID) msg;
                int id = message.getRepid();
                List<ComplaintsDataForReport> lst =getAllWhereIdEquals(ComplaintsDataForReport.class,id,"repid");
                ShowCOMPrepBYrepID newMSG = new ShowCOMPrepBYrepID(id);
                newMSG.setLst(lst);
                client.sendToClient(newMSG);



            }
            else if(msg instanceof ShowDSBLrepBYrepID)
            {
                ShowDSBLrepBYrepID message = (ShowDSBLrepBYrepID) msg;
                int id = message.getRepid();
                List<DisabledDataReport> lst =getAllWhereIdEquals(DisabledDataReport.class,id,"repid");
                ShowDSBLrepBYrepID newMSG = new ShowDSBLrepBYrepID(id);
                newMSG.setLst(lst);
                client.sendToClient(newMSG);



            }

            else if(msg instanceof ShowALLreportsMSG)
            {
                ShowALLreportsMSG message = (ShowALLreportsMSG) msg;
                message.setOlst(getAll(OrdersReport.class));
                message.setClst(getAll(ComplaintsReport.class));
                message.setDlst(getAll(DisabledSpotReport.class));
                client.sendToClient(message);




            }
            else if(msg instanceof makeAreportMSG)
            {
                makeAreportMSG message = (makeAreportMSG) msg;
                if(message.getRequest_type().equals("Orders") && message.getMangerID() != null)
                {
                    List<Manager> mangers = getAllWhereIdEquals(Manager.class,Integer.parseInt(message.getMangerID()),"id");
                    int parkname = mangers.get(0).getParkingLot();
                   List<ParkingLotEntitiy> parks = getAllWhereIdEquals(ParkingLotEntitiy.class,parkname,"id");
                    String parkname_string = parks.get(0).getName();
                    List<RequestForReport> requests = getAllWhereIdEquals(RequestForReport.class,message.getRequestid(),"id");
                    List<InAdvanceOrderEntity> inadv = getAllforReport(InAdvanceOrderEntity.class,"date","parkingLotName",requests.get(0).getFrom(),requests.get(0).getUntil(),parkname_string);
                    List<InPlaceOrderEntity> inplace = getAllforReport(InPlaceOrderEntity.class,"date","parkingLotName",requests.get(0).getFrom(),requests.get(0).getUntil(),parkname_string);
                    session.beginTransaction();


                    OrdersReport rep = new OrdersReport(parkname_string,inplace.size(),inadv.size(),requests.get(0).getFrom(),requests.get(0).getUntil());


                    session.save(rep);
                    session.flush();




                    for(InAdvanceOrderEntity i : inadv)
                    {
                        ALLOrdersInTimePeriod o = new ALLOrdersInTimePeriod(rep.getId(),i.getOrderID(),i.getParkingLotName(),i.getDate(),"In advance order");
                        session.save(o);
                        session.flush();

                    }

                    for(InPlaceOrderEntity i : inplace)
                    {
                        ALLOrdersInTimePeriod o = new ALLOrdersInTimePeriod(rep.getId(),i.getOrderID(),i.getParkingLotName(),i.getDate(),"In place order");
                        session.save(o);
                        session.flush();

                    }
                    session.getTransaction().commit();

                }
                if(message.getRequest_type().equals("Complaints") && message.getMangerID() != null)
                {
                    List<Manager> mangers = getAllWhereIdEquals(Manager.class,Integer.parseInt(message.getMangerID()),"id");
                    int parkname = mangers.get(0).getParkingLot();
                    List<ParkingLotEntitiy> parks = getAllWhereIdEquals(ParkingLotEntitiy.class,parkname,"id");
                    String parkname_string = parks.get(0).getName();
                    List<RequestForReport> requests = getAllWhereIdEquals(RequestForReport.class,message.getRequestid(),"id");
                    List<Complaint> complaints = getAllforReport(Complaint.class,"date","park_id",requests.get(0).getFrom(),requests.get(0).getUntil(),parkname);
                    List<Complaint> Resolvedcomplaints = getAllforResolvedComplaintsReport(Complaint.class,"date","park_id",requests.get(0).getFrom(),requests.get(0).getUntil(),parkname,"response");
                    List<Complaint> UNResolvedcomplaints = getAllforUnResolvedComplaintsReport(Complaint.class,"date","park_id",requests.get(0).getFrom(),requests.get(0).getUntil(),parkname,"response");

                    session.beginTransaction();
                    ComplaintsReport rep = new ComplaintsReport(parkname_string,complaints.size(),Resolvedcomplaints.size(),requests.get(0).getFrom(),requests.get(0).getUntil());
                    session.save(rep);
                    session.flush();

                    for(Complaint c : UNResolvedcomplaints)
                    {
                        ComplaintsDataForReport comp = new ComplaintsDataForReport(rep.getId(), "UnResolved Complaint",parkname_string,c.getDate());
                        session.save(comp);
                        session.flush();

                    }
                    for(Complaint c : Resolvedcomplaints)
                    {
                        ComplaintsDataForReport comp = new ComplaintsDataForReport(rep.getId(), "Resolved Complaint",parkname_string,c.getDate());
                        session.save(comp);
                        session.flush();

                    }
                    session.getTransaction().commit();

                }
                if(message.getRequest_type().equals("Disabled") && message.getMangerID() != null)
                {
                    List<Manager> mangers = getAllWhereIdEquals(Manager.class,Integer.parseInt(message.getMangerID()),"id");
                    int parkname = mangers.get(0).getParkingLot();
                    List<ParkingLotEntitiy> parks = getAllWhereIdEquals(ParkingLotEntitiy.class,parkname,"id");
                    String parkname_string = parks.get(0).getName();
                    List<RequestForReport> requests = getAllWhereIdEquals(RequestForReport.class,message.getRequestid(),"id");
                    List<DisabledSpotHistory> Disabled = getAllforReport(DisabledSpotHistory.class,"time_of_disabling","Park",requests.get(0).getFrom(),requests.get(0).getUntil(),parkname_string);
                    int size = Disabled.size();

                    session.beginTransaction();
                    if(Disabled.isEmpty()) size =0;
                    DisabledSpotReport rep = new DisabledSpotReport(parkname_string,size,requests.get(0).getFrom(),requests.get(0).getUntil());
                    session.save(rep);
                    session.flush();

                    for(DisabledSpotHistory h : Disabled) {

                        DisabledDataReport disp = new DisabledDataReport(rep.getId(), parkname_string, h.getSpot_number(), h.getTime_of_disabling());
                        session.save(disp);
                        session.flush();

                    }
                    session.getTransaction().commit();
                }



            }



            else if(msg instanceof RequestForReport)
            {

                RequestForReport message = (RequestForReport) msg;
                session.beginTransaction();
                System.out.println("attempt to save a request");
                RequestForReport newMsg = new RequestForReport(message.getFrom(),message.getUntil(),message.getReport_type());
               session.save(newMsg);
                session.flush();
                session.getTransaction().commit();

            } else if (msg instanceof MemberLogInMessage) {
                MemberLogInMessage message = (MemberLogInMessage) msg;
                List<FullMemberShipEntity> fullmembershipList = getAll(FullMemberShipEntity.class);
                List<StandardMemberShipEntity> standardMemberShipList = getAll(StandardMemberShipEntity.class);
                List<Subscriber> sublst = getAll(Subscriber.class);
                MemberLogInControler memberLogInCntrl = new MemberLogInControler(message.getMemberNumber(), message.getCarNumber());
                message.setResult(memberLogInCntrl.validateMemberCredentials(standardMemberShipList, fullmembershipList, sublst));
                //  message.setResult(memberLogInCntrl.validateMemberCredentials(standardMemberShipList, fullmembershipList));
                message.setDateTimeEnd(memberLogInCntrl.getDateTimeEnd());
                message.setMemberId(memberLogInCntrl.getMemberId());
                message.setFullMembership(memberLogInCntrl.isFullMembership());
                boolean flag72 = memberLogInCntrl.isFullMembership();
                double fee72 ;
                if(flag72 == true)
                    fee72 = calcFeeMembership("Full") ;
                else
                    fee72 = calcFeeMembership("Standard Single") ;
                message.setFee72(fee72) ;
                SubscribedClient connection = new SubscribedClient(client);
                String st = message.getMemberNumber();
                if(message.getResult() == 1){
                    StandardMemberShipEntity standardMemberShip = getWhereIdEquals(StandardMemberShipEntity.class
                    , message.getMemberNumber(),"MembershipID" , message.getCarNumber(), "CarNumber");
                    message.setMemberPark(standardMemberShip.getParkingLot());
                }

                st = message.getMemberNumber();
//                long tmp2 = Long.getLong(st) ;
//                System.out.println(tmp2 +"tmp2");
                connection.setClientID(Integer.parseInt(st));
                Subscriber s = new Subscriber(Integer.parseInt(message.getMemberNumber()));
                if(message.getResult() > 0 ) {
                    session.beginTransaction();
                    session.save(s);
                    session.flush();
                    session.getTransaction().commit();
                }
                SubscribersList.add(connection);
                client.sendToClient(message);


            } else if (msg instanceof ChangePricesRequest) {
                ChangePricesRequest message = (ChangePricesRequest) msg;
                session.beginTransaction();
                ChangePricesRequest newRequest = new ChangePricesRequest(message.getMangerID(), message.getInAdv(), message.getInPlace(), message.getRegMemS(), message.getRegMemM(), message.getFullMem());
                session.save(newRequest);
                session.flush();
                session.getTransaction().commit();


            } else if (msg instanceof OrderToDeleteMsg) {
                OrderToDeleteMsg message = (OrderToDeleteMsg) msg;

                List<InAdvanceOrderEntity> List = getAllWhereIdEquals(InAdvanceOrderEntity.class, message.getId(), "orderID");
                session.beginTransaction();
                for (InAdvanceOrderEntity E : List) {
                    session.delete(E);
                }
                session.flush();
                session.getTransaction().commit();
                session.beginTransaction();
                InAdvanceOrderEntity ent = List.get(0);
                String id_of_client = ent.getUserID();
                Double refund = 0.0;
                LocalDateTime today = LocalDateTime.now();
                int arrmonthdiff = Integer.parseInt(ent.getUserID().substring(3, 5)) - today.getMonthValue();
                int arrdate_diff = Integer.parseInt(ent.getUserID().substring(0, 2)) - today.getDayOfMonth();
                int hours_diff = Integer.parseInt(ent.getArrivalHours()) - today.getHour();
                int min_diff = Integer.parseInt(ent.getArrivalMinutes()) - today.getMinute();
                List<Prices> inadvlst = getAllWhereIdEquals(Prices.class, "1", "id");
                int inadvPrice = inadvlst.get(0).getIn_Advance_price();
                if (arrmonthdiff == 0) {
                    if (arrdate_diff == 0) {
                        int diff = hours_diff * 60 + min_diff;
                        if (hours_diff * 60 + min_diff > 180) refund = 0.9 * inadvPrice;
                        else if (diff <= 180 && diff >= 60) refund = 0.5 * inadvPrice;
                        else refund = 0.0;
                    } else if (arrdate_diff > 0) refund = 0.9 * inadvPrice;
                } else refund = 0.9 * inadvPrice;
                System.out.println(message.getId());
                List<User> lstUsers = getAllWhereIdEquals(User.class, id_of_client, "id");
                User us = lstUsers.get(0);
                System.out.println("refund is ");
                System.out.println(refund);
                us.setBalance(us.getBalance() + refund);
                session.update(us);
                session.getTransaction().commit();
                OrderToDeleteMsg new_msg = new OrderToDeleteMsg(message.getId());
                new_msg.setBalance(refund);
                client.sendToClient(new_msg);

            } else if (msg instanceof GetBalance) {
                GetBalance message = (GetBalance) msg;

                List<User> lst = getAllWhereIdEquals(User.class, message.getId(), "id");
                Double balance = lst.get(0).getBalance();
                GetBalance newMsg = new GetBalance();
                newMsg.setId(message.getId());
                newMsg.setUserbalance(balance);
                client.sendToClient(newMsg);

            }
            else if(msg instanceof ShowAllReportrequestsMessage)
            {
                ShowAllReportrequestsMessage message = (ShowAllReportrequestsMessage) msg;


                List<RequestForReport> Orders = getAllWhereIdEquals(RequestForReport.class,"Orders","Report_type");
                List<RequestForReport> Compliants = getAllWhereIdEquals(RequestForReport.class,"Complaints","Report_type");
                List<RequestForReport> disabled = getAllWhereIdEquals(RequestForReport.class,"Disabled","Report_type");
                ShowAllReportrequestsMessage newMsg = new ShowAllReportrequestsMessage(Orders,Compliants,disabled);
                client.sendToClient(newMsg);



            }
            else if (msg instanceof GetallOrdersOfClient) {
                GetallOrdersOfClient message = (GetallOrdersOfClient) msg;
                List<InAdvanceOrderEntity> List = getAllWhereIdEquals(InAdvanceOrderEntity.class, message.getId(), "UserID");
                System.out.println("attepmpt to print from list ");
                System.out.println(List.get(0).getUserID());
                System.out.println("up?");
                GetallOrdersOfClient new_f = new GetallOrdersOfClient(List, List.get(0).getUserID());
                client.sendToClient(new_f);

            } else if (msg instanceof GetComplaintsMessage) {
                GetComplaintsMessage message = (GetComplaintsMessage) msg;
                List<Complaint> list = getAll(Complaint.class);
                GetComplaintsMessage complaints = new GetComplaintsMessage(list);
                complaints.setGetForWhom(message.getGetForWhom());
                client.sendToClient(complaints);
            }
            else if(msg instanceof GetSpotsMessage){
                GetSpotsMessage message = (GetSpotsMessage)msg;
                List<Spot> lst = getAll(Spot.class);
                List<AbsSpot> abList = new ArrayList<>();
                for (int i=0; i < lst.size();i++){
                    AbsSpot sp = new AbsSpot(lst.get(i).getSpotid(),lst.get(i).getWidth_num(),lst.get(i).getHeight_num(),lst.get(i).getDepth_num(),
                            lst.get(i).isAvailable(),lst.get(i).isSaved(),lst.get(i).isDisabled()
                            ,lst.get(i).getParkinglot().getId());
                    abList.add(sp);
                }
                GetSpotsMessage ms = new GetSpotsMessage(abList);
                ms.setFromWhom(message.getFromWhom());
                client.sendToClient(ms);
            }
            else if(msg instanceof SaveSpotMessage){
                SaveSpotMessage message = (SaveSpotMessage) msg;
                int spot_id = message.getSpot_id();
                boolean newSaved = message.isIfSave();
                session.beginTransaction();
                List<Spot> lstUsers = getAllWhereIdEquals(Spot.class,spot_id,"spotid");
                Spot user = lstUsers.get(0);
                user.setSaved(newSaved);
                session.update(user);
                session.flush();
                session.getTransaction().commit();
            }
            else if(msg instanceof DisableSpotMessage){
                DisableSpotMessage message = (DisableSpotMessage) msg;
                int spot_id = message.getSpot_id();
                boolean newDis = message.isIfDis();
                session.beginTransaction();

                List<Spot> lstUsers = getAllWhereIdEquals(Spot.class,spot_id,"spotid");
                Spot user = lstUsers.get(0);
                if(newDis) {
                    DisabledSpotHistory hist = new DisabledSpotHistory(user.getParkinglot().getName(),user.getSpotid());
                    session.save(hist);
                    session.flush();

                }
                user.setDisabled(newDis);
                session.update(user);
                session.flush();
                session.getTransaction().commit();
            }
            else if (msg instanceof SignUpMessage) {
                SignUpMessage message = (SignUpMessage) msg;
                List<User> userList = getAll(User.class);
                SignUpValidator validator = new SignUpValidator(message.getUserId(), message.getUserPass(), message.getUserEmail());
                message.setResult(validator.validateUserCredentials(userList));
                if (message.getResult()) {
                    session.beginTransaction();
                    User newUser = new User(Integer.parseInt(message.getUserId()), message.getUserEmail(), message.getUserPass());
                    session.save(newUser);
                    session.flush();
                    session.getTransaction().commit();
                }
                client.sendToClient(message);
            }
            else if (msg instanceof MessageBetweenClients) {
                MessageBetweenClients message = (MessageBetweenClients) msg;
                int id = message.getRecepientID();
                /* ConnectionToClient clientToSendTo = getConnection(id);*/
                boolean b = sendtoSpecificClient(id, message);
                if (!b) {
                    message.setResult(0);
                    SendFailedMessage s = new SendFailedMessage(id);
                    client.sendToClient(s);
                }
                else
                {
                    message.setResult(1);
                    client.sendToClient(("send success"));
                    /*clientToSendTo.sendToClient(message);*/
                    sendtoSpecificClient(id, message);
                }
            }
            else if (msg instanceof AdminMessage) {
                AdminMessage message = (AdminMessage) msg;
                ArrayList<Subscriber> lst = new ArrayList<>();
                for (SubscribedClient p : SubscribersList) {
                    Subscriber subscriber = new Subscriber(p.getClientID());
                    lst.add(subscriber);

                }
                message.setLst(lst);
                client.sendToClient(message);
            } else if (msg instanceof InAdvanceOrderMessage) {
                InAdvanceOrderMessage message = (InAdvanceOrderMessage) msg;
                String carNum = message.getCarNumber(), parkingLot = message.getParkingLot();
                String leavingDate = message.getLeavingDate(), leavingHours = message.getLeavingHours(), leavingMin = message.getLeavingMinutes();
                String arrivingDate = message.getArrivingDate(), arrivingHours = message.getArrivingHours(), arrivingMin = message.getArrivingMinutes();

                List<ParkingLots> parkingLots = getAll(ParkingLots.class);
                List<InAdvanceOrderEntity> inAdvanceOrders = getAll(InAdvanceOrderEntity.class);
                InAdvanceOrderValidator validator = new InAdvanceOrderValidator(carNum, parkingLot, arrivingHours
                        , arrivingDate, arrivingMin, leavingHours, leavingDate, leavingMin, parkingLots, inAdvanceOrders);
                message.setResult(validator.validateOrder());
                message.setFee(calcFee(arrivingDate, arrivingHours, arrivingMin, leavingDate, leavingHours, leavingMin, false));
                message.setUserId(message.getUserId());
                client.sendToClient(message);
            } else if (msg instanceof PayInAdvanceOrderMessage) {
                PayInAdvanceOrderMessage message = (PayInAdvanceOrderMessage) msg;
                String carNum = message.getCarNumber(), parkingLot = message.getParkingLot();
                String leavingDate = message.getLeavingDate(), leavingHours = message.getLeavingHours(), leavingMin = message.getLeavingMinutes();
                String arrivingDate = message.getArrivingDate(), arrivingHours = message.getArrivingHours(), arrivingMin = message.getArrivingMinutes();
                String cvvCard = message.getCvv(), yearCard = message.getYear(), monthCard = message.getMonth(), cardNum = message.getCardNumber();
                PayValidator validator = new PayValidator(cardNum, cvvCard, yearCard, monthCard);
                message.setResult(validator.validatePayment());
                if (message.isResult()) {
                    InAdvanceOrderEntity newInAdvance = new InAdvanceOrderEntity(carNum, message.getUserid(), leavingMin, leavingDate
                            , leavingHours, arrivingMin, arrivingDate, arrivingHours, parkingLot);
                    session.beginTransaction();
                    session.save(newInAdvance);
                    session.flush();
                    newInAdvance.setOrderID("10" + String.valueOf(newInAdvance.getId()));
                    session.getTransaction().commit();
                }
                client.sendToClient(message);
            } else if (msg instanceof FullMembershipMessage) {
                FullMembershipMessage message = (FullMembershipMessage) msg;
                FullMembershipValidator validator = new FullMembershipValidator(message.getCarNumber()
                        , message.getStartDate());
                boolean alreadyMember = false;
                List<FullMemberShipEntity> fullMemberShipEntities = getAll(FullMemberShipEntity.class);
                for (FullMemberShipEntity fullmember:fullMemberShipEntities){
                    if (message.getId().equals(String.valueOf(fullmember.getId()))
                            && message.getCarNumber().equals(fullmember.getCarNumber())) {
                        alreadyMember = true;
                        break;
                    }
                }
                message.setResult(validator.validateMembership()&& !alreadyMember);
                if (message.isResult()) {
                    FullMemberShipEntity fullMemberShipEntity = new FullMemberShipEntity(Integer.parseInt(message.getId())
                            , message.getCarNumber(), message.getStartDate());
                    message.setFullMemberShipEntity(fullMemberShipEntity);
                    message.setFee(calcFeeMembership("Full"));
                }
                client.sendToClient(message);
            } else if (msg instanceof PayFullMembershipMessage) {
                PayFullMembershipMessage message = (PayFullMembershipMessage) msg;
                FullMemberShipEntity fullMemberShipEntity = message.getFullMemberShipEntity();
                PayValidator validator = new PayValidator(message.getCardNumber()
                        , message.getCvv(), message.getYear(), message.getMonth());
                message.setResult(validator.validatePayment());
                if (message.isResult()) {
                    session.beginTransaction();
                    session.save(fullMemberShipEntity);
                    session.flush();
                    fullMemberShipEntity.setMembershipID("1" + fullMemberShipEntity.getId());
                    message.setMembershipId(fullMemberShipEntity.getMembershipID());
                    session.flush();
                    session.getTransaction().commit();
                }
                client.sendToClient(message);
            }else if(msg instanceof PayRenewFullMembershipMessage) {
                PayRenewFullMembershipMessage message = (PayRenewFullMembershipMessage) msg;
                boolean flag72 = message.isFullMember();
                double fee72 ;
                // System.out.println("flag72" + flagelse if (msg instanceof StandardMembershipMessage) {72);
                if(flag72 == true)
                    fee72 = calcFeeMembership("Full") ;
                else
                    fee72 = calcFeeMembership("Standard Single") ;
                message.setFee(fee72);
                int memberId = message.getMembershipId();
                System.out.println("mem id" + memberId);
                PayValidator validator = new PayValidator(message.getCardNumber()
                        , message.getCvv(), message.getYear(), message.getMonth());
                message.setResult(validator.validatePayment());
                System.out.println(message.isResult());
                if (message.isResult()){

                    if(flag72 == true) {
                        List<FullMemberShipEntity> list = getAllWhereIdEquals(FullMemberShipEntity.class, String.valueOf(memberId), "id");
                        System.out.println(list);
                        session.beginTransaction();
                        FullMemberShipEntity full = list.get(0);
                        String leavingdate = full.getEndingDate();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate date = LocalDate.parse(leavingdate,formatter).plusDays(28);
                        String newLeavingDate = date.format(formatter) ;
                        full.setEndingDate(newLeavingDate);
                        String memberNumber = full.getMembershipID();
                        message.setMemberNumber(memberNumber);
                        message.setLeavingDate(newLeavingDate);

                        session.update(full);
                        session.flush();
                        session.getTransaction().commit();
                    }else if (flag72 == false) {
                        List<StandardMemberShipEntity> list = getAllWhereIdEquals(StandardMemberShipEntity.class, String.valueOf(memberId), "id");
                        System.out.println(list);
                        session.beginTransaction();
                        StandardMemberShipEntity standard = list.get(0);
                        String leavingdate = standard.getEndingDate();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate date = LocalDate.parse(leavingdate,formatter).plusDays(28);
                        String newLeavingDate = date.format(formatter) ;
                        standard.setEndingDate(newLeavingDate);
                        message.setLeavingDate(newLeavingDate);
                        String memberNumber = standard.getMembershipID();
                        message.setMemberNumber(memberNumber);
                        session.update(standard);
                        session.flush();
                        session.getTransaction().commit();
                    }
                }
                client.sendToClient(message);
            }
            else if(msg instanceof StandardMembershipMessage) {
                StandardMembershipMessage message = (StandardMembershipMessage) msg;
                StandardMembershipValidator validator = new StandardMembershipValidator(message.getCarNumber()
                        , message.getStartDate(), message.getParkingLot());
                System.out.println(message.isResult());
                boolean alreadyMember = false;
                List<StandardMemberShipEntity> standardMemberShipEntities = getAll(StandardMemberShipEntity.class);
                for (StandardMemberShipEntity standardMemberShip:standardMemberShipEntities){
                    if (message.getId().equals(String.valueOf(standardMemberShip.getId()))
                            && message.getCarNumber().equals(standardMemberShip.getCarNumber())) {
                        alreadyMember = true;
                        break;
                    }
                }
                message.setResult(validator.validateMembership() && !alreadyMember);
                if (message.isResult() ) {
                    StandardMemberShipEntity standardMemberShipEntity = new StandardMemberShipEntity(Integer.parseInt(message.getId())
                            , message.getCarNumber(), message.getStartDate(), message.getParkingLot());
                    message.setStandardMemberShipEntity(standardMemberShipEntity);
                    message.setFee(calcFeeMembership("Standard Single"));
                }
                client.sendToClient(message);
            }
            else if (msg instanceof PayStandardMembershipMessage) {
                PayStandardMembershipMessage message = (PayStandardMembershipMessage) msg;
                StandardMemberShipEntity standardMemberShipEntity = message.getStandardMemberShipEntity();
                PayValidator validator = new PayValidator(message.getCardNumber()
                        , message.getCvv(), message.getYear(), message.getMonth());
                message.setResult(validator.validatePayment());
                if (message.isResult()) {
                    session.beginTransaction();
                    session.save(standardMemberShipEntity);
                    session.flush();
                    standardMemberShipEntity.setMembershipID("0" + standardMemberShipEntity.getId());
                    message.setMembershipId(standardMemberShipEntity.getMembershipID());
                    session.flush();
                    session.getTransaction().commit();
                }
                client.sendToClient(message);
            }

            else if(msg instanceof GetParkingLotByEmployeeId){
                GetParkingLotByEmployeeId message = (GetParkingLotByEmployeeId) msg;
                List<ParkingLotEmployee> employeeList = getAll(ParkingLotEmployee.class);
                int park_num = 0;
                for (ParkingLotEmployee em : employeeList) {
                    if (em.getId() == message.getId()) {
                        park_num = em.getParkingLot();
                    }
                }
                message.setPark_num(park_num);
                client.sendToClient(message);
            }
           else if(msg instanceof SetUpMessage){
                SetUpMessage message =(SetUpMessage) msg;
                session.beginTransaction();
                if (message.getPark_num() == 1){
                    ParkingLotEntitiy p1 = new ParkingLotEntitiy(1,"Haifa Port");
                    List<Spot> lst = new ArrayList<>();
                    for (int k = 0 ; k < 3;k++)
                        for (int i = 0 ; i < 3 ; i++)
                            for (int j = 0 ; j < 4;j++){
                                Spot s = new Spot(i, j, k, true, false,p1);
                                session.save(s);
                                lst.add(s);
                            }
                    p1.setDepth(3);
                    p1.setWidth(4);
                    p1.setHeight(3);
                    p1.setSpots(lst);
                    session.save(p1);
                    session.flush();
                }
                else if(message.getPark_num() == 2){
                    ParkingLotEntitiy p2 = new ParkingLotEntitiy(2,"Carmel");
                    List<Spot> lst2 = new ArrayList<>();
                    for (int k = 0 ; k < 3;k++)
                        for (int i = 0 ; i < 3 ; i++)
                            for (int j = 0 ; j < 6;j++) {
                                Spot s = new Spot(i, j, k, true, false,p2);
                                session.save(s);
                                lst2.add(s);
                            }
                    p2.setDepth(3);
                    p2.setWidth(6);
                    p2.setHeight(3);
                    p2.setSpots(lst2);
                    session.save(p2);
                    session.flush();
                }
                else if(message.getPark_num() == 3){
                    ParkingLotEntitiy p3 = new ParkingLotEntitiy(3,"Central Station");
                    List<Spot> lst3 = new ArrayList<>();
                    for (int k = 0 ; k < 3;k++)
                        for (int i = 0 ; i < 3 ; i++)
                            for (int j = 0 ; j < 8;j++) {
                                Spot s = new Spot(i, j, k, true, false,p3);
                                session.save(s);
                                lst3.add(s);
                            }
                    p3.setDepth(3);
                    p3.setWidth(8);
                    p3.setHeight(3);
                    p3.setSpots(lst3);
                    session.save(p3);
                    session.flush();
                }
                session.getTransaction().commit();
                client.sendToClient(message);
            }
            else if (msg instanceof SendComplaintMsg) {
                SendComplaintMsg message = (SendComplaintMsg) msg;
                Complaint complaint = new Complaint(message.getSender_id(), message.getCurrDate()
                        , message.getComplaint(), message.getPark_id());
                System.out.println(message.getComplaint());
                session.beginTransaction();
                session.save(complaint);
                session.flush();
                session.getTransaction().commit();
            }
            else if(msg instanceof SetComplaintRespondMessage){
                SetComplaintRespondMessage message = (SetComplaintRespondMessage) msg;
                List<Complaint> list = getAllWhereIdEquals(Complaint.class, String.valueOf(message.getComplaint_id()), "complaintId");
                session.beginTransaction();
                Complaint comp = list.get(0);
                comp.setResponse(message.getRes());
                session.update(comp);
                session.flush();
                String userId = comp.getId();
                List<User> lstUsers = getAllWhereIdEquals(User.class, userId, "id");
                User user = lstUsers.get(0);
                user.setBalance(user.getBalance() + message.getRefundAmount());
                session.update(user);
                session.getTransaction().commit();
            }
            else if(msg instanceof ShowRequestForGM)
            {
                ShowRequestForGM message = (ShowRequestForGM) msg;

                List<ChangePricesRequest> lst = getAllWhereIdEquals(ChangePricesRequest.class,false,"GMapprove");
                ShowRequestForGM newMsg = new ShowRequestForGM();
                newMsg.setList(lst);
                client.sendToClient(newMsg);

            }
            else if(msg instanceof ShowRequestForManager)
            {
                ShowRequestForManager message = (ShowRequestForManager) msg;
                List<ChangePricesRequest> lst = getAllWhereIdEquals(ChangePricesRequest.class,message.getManagerid(),"ManagerID");
                ShowRequestForManager newMsg = new ShowRequestForManager();
                newMsg.setList(lst);
                newMsg.setManagerid(message.getManagerid());
                client.sendToClient(newMsg);

            }
            else if(msg instanceof PricesRequestToApply)
            {
                PricesRequestToApply message = (PricesRequestToApply) msg;
                List<ChangePricesRequest> Requests = getAllWhereIdEquals(ChangePricesRequest.class,message.getApprovedRequest(),"requestID");
                List<Prices> lst = getAll(Prices.class);
                session.beginTransaction();
                Prices p = lst.get(0);
                p.setIn_Advance_price(Requests.get(0).getInAdv());
                p.setIn_place_price(Requests.get(0).getInPlace());
                p.setSingle_car_reg_mem_price(Requests.get(0).getRegMemS());
                p.setMultiple_cars_reg_mem_price(Requests.get(0).getRegMemM());
                p.setFull_mem_price(Requests.get(0).getFullMem());
                session.update(p);
                session.flush();
                ChangePricesRequest r = Requests.get(0);
                r.setGMapprove(false);
                session.delete(r);
                session.flush();
                session.getTransaction().commit();
            }
            else if(msg instanceof ApproveNewPrices) {
                session.beginTransaction();
                ApproveNewPrices message = (ApproveNewPrices) msg;
                List<ChangePricesRequest> lst = getAllWhereIdEquals(ChangePricesRequest.class,message.getReqIDtoApprove(),"requestID");
                ChangePricesRequest comp = lst.get(0);
               if(message.isApprove()) {
                   comp.setGMapprove(true);
                   session.update(comp);
                   session.flush();
               }
               else if(!message.isApprove())
               {
                   session.delete(comp);
                   session.flush();

               }
                session.getTransaction().commit();

            } else if(msg instanceof EnterWithOrderMessage) {
                EnterWithOrderMessage message = (EnterWithOrderMessage) msg;
                String carNum = message.getCarNumber(), parkingLot = message.getParkingLot();
                String arrivingDate = message.getArrivingDate(), arrivingHours = message.getArrivingHours();
                String arrivingMin = message.getArrivingMinutes();
                int parkId = getParkIdByName(parkingLot);
                List<InAdvanceOrderEntity> inAdvanceOrders = getAll(InAdvanceOrderEntity.class);
                EnterWithOrderValidator validator = new EnterWithOrderValidator(carNum, parkingLot, arrivingHours
                        , arrivingDate, arrivingMin, inAdvanceOrders);
                message.setResult(validator.validateOrder());
                if (message.getResult()) {
                    InAdvanceOrderEntity order = validator.getOrder();
                    order.setCarEntered(true);
                    String leavingTimeAndDate = order.getLeavingDate() + " " + order.getLeavingHours() + ":"
                            + order.getLeavingMinutes();
                    parkInBestSpot(carNum, leavingTimeAndDate, parkingLot);
//                    addCarToPark(parkId,carNum);
                }
                client.sendToClient(message);
            } else if (msg instanceof EnterWithOutOrderMessage) {
                EnterWithOutOrderMessage message = (EnterWithOutOrderMessage) msg;
                String carNum = message.getCarNumber(), parkingLot = message.getParkingLot();
                String arrivingDate = message.getArrivingDate(), arrivingHours = message.getArrivingHours();
                String arrivingMin = message.getArrivingMinutes();
                String leavingDate = message.getLeavingDate(), leavingHours = message.getLeavingHours();
                String leavingMin = message.getLeavingMinutes(), email = message.getEmail();
//                int parkId = getParkIdByName(parkingLot);
                List<InAdvanceOrderEntity> inAdvanceOrders = getAll(InAdvanceOrderEntity.class);
                EnterWithOutOrderValidator validator = new EnterWithOutOrderValidator(carNum, parkingLot, arrivingHours
                        , arrivingDate, arrivingMin, inAdvanceOrders, leavingMin, leavingDate, leavingHours);
                message.setResult(validator.validateOrder(countFreeSpots(getParkIdByName(parkingLot))));
                if (message.getResult()) {
                    String leavingTimeAndDate = leavingDate + " " + leavingHours + ":" + leavingMin;
                    parkInBestSpot(carNum, leavingTimeAndDate, parkingLot);
//                    addCarToPark(parkId,carNum);
                    InPlaceOrderEntity newInPlace = new InPlaceOrderEntity(carNum, message.getUserId(), leavingMin, leavingDate
                            , leavingHours, arrivingMin, arrivingDate, arrivingHours, parkingLot, email);
                    session.beginTransaction();
                    session.save(newInPlace);
                    session.flush();
                    newInPlace.setOrderID("20" + String.valueOf(newInPlace.getId()));
                    session.getTransaction().commit();
                }
                client.sendToClient(message);
            }
            else if(msg instanceof EnterFullMemberMessage) {
                EnterFullMemberMessage message = (EnterFullMemberMessage) msg;
                String carNum = message.getCarNumber(),parkingLot = message.getParkingLot();
                String arrivingDate = message.getArrivingDate(),arrivingHours = message.getArrivingHours();
                String arrivingMin = message.getArrivingMinutes();
                String leavingDate = message.getLeavingDate(),leavingHours = message.getLeavingHours();
                String leavingMin = message.getLeavingMinutes(),memberId=message.getUserId();
                List<InAdvanceOrderEntity> inAdvanceOrders = getAll(InAdvanceOrderEntity.class);
                FullMemberShipEntity fullMemberShip = getWhereIdEquals(FullMemberShipEntity.class,memberId,"MembershipID"
                        ,carNum,"CarNumber");
                EnterFullMemberValidator validator = new EnterFullMemberValidator(carNum,parkingLot,arrivingHours,arrivingDate
                        ,arrivingMin,inAdvanceOrders,leavingMin,leavingDate,leavingHours);
                message.setResult(validator.validateOrder(countFreeSpots(getParkIdByName(parkingLot))
                        ,fullMemberShip.getHoursLeft(),fullMemberShip.isParked()));
                if(message.getResult()){
                    session.beginTransaction();
                    fullMemberShip.setTimeEnteredPark(arrivingDate+" " + arrivingHours + ":" + arrivingMin);
                    fullMemberShip.setParked(true);
                    fullMemberShip.setParkedLocation(parkingLot);
                    session.update(fullMemberShip);
                    session.getTransaction().commit();
                    String leavingTimeAndDate = leavingDate + " " + leavingHours + ":" + leavingMin;
                    parkInBestSpot(carNum, leavingTimeAndDate, parkingLot);
                }
                client.sendToClient(message);
            }
            else if(msg instanceof EnterStandardMemberMessage) {
                EnterStandardMemberMessage message = (EnterStandardMemberMessage) msg;
                String carNum = message.getCarNumber(),parkingLot = message.getParkingLot();
                String arrivingDate = message.getArrivingDate(),arrivingHours = message.getArrivingHours();
                String arrivingMin = message.getArrivingMinutes();
                String leavingDate = message.getLeavingDate(),leavingHours = message.getLeavingHours();
                String leavingMin = message.getLeavingMinutes(),memberId=message.getUserId();
                List<InAdvanceOrderEntity> inAdvanceOrders = getAll(InAdvanceOrderEntity.class);
                StandardMemberShipEntity standardMemberShip = getWhereIdEquals(StandardMemberShipEntity.class,memberId
                        ,"MembershipID",carNum,"CarNumber");
                EnterStandardMemberValidator validator = new EnterStandardMemberValidator(carNum,parkingLot,arrivingHours
                        ,arrivingDate,arrivingMin,inAdvanceOrders,leavingMin,leavingDate,leavingHours);
                message.setResult(validator.validateOrder(countFreeSpots(getParkIdByName(parkingLot))
                        ,standardMemberShip.getHoursLeft(),standardMemberShip.isParked()));
                if(message.getResult()){
                    session.beginTransaction();
                    standardMemberShip.setTimeEnteredPark(arrivingDate+" " + arrivingHours + ":" + arrivingMin);
                    standardMemberShip.setParked(true);
                    standardMemberShip.setParkedLocation(parkingLot);
                    session.update(standardMemberShip);
                    session.getTransaction().commit();
                    String leavingTimeAndDate = leavingDate + " " + leavingHours + ":" + leavingMin;
                    parkInBestSpot(carNum, leavingTimeAndDate, parkingLot);
                }
                client.sendToClient(message);
            } else if (msg instanceof ExitParkingMessage) {
                ExitParkingMessage message = (ExitParkingMessage) msg;
                String carNum = message.getCarNumber(), parkingLot = message.getParkingLot();
                String leavingDate = message.getLeavingDate();
                String leavingHours = message.getLeavingHours();
                String leavingMinutes = message.getLeavingMinutes();
                String id = message.getUserId();
                int parkId = getParkIdByName(parkingLot);
                List<InPlaceOrderEntity> inPlaceOrders = getAll(InPlaceOrderEntity.class);
                List<InAdvanceOrderEntity> inAdvanceOrders = getAll(InAdvanceOrderEntity.class);
                List<Spot> spots = getAll(Spot.class);
                ExitParkingLotValidator validator = new ExitParkingLotValidator(carNum, parkingLot, leavingHours
                        , leavingDate, leavingMinutes, spots, parkId);

                boolean result = validator.validateOrder();
                System.out.println(result);
                ExitParkingLotService service = new ExitParkingLotService(carNum, result, message, inPlaceOrders, inAdvanceOrders);
                ExitParkingMessage message1 = service.getMessage();
                if (result) {
                    double fee = 0;
                    if (message1.isInPlaceOrder()) {
                        // calculate fee
                        InPlaceOrderEntity order = service.getOrderInPlace();
                        fee = calcFee(order.getArrivalDate(), order.getArrivalHours(), order.getArrivalMinutes(),
                                order.getLeavingDate(), order.getLeavingHours(), order.getLeavingMinutes(), true);
                        message1.setFee(fee);
                    } else {
                        // calculate fee and update user balance
                        InAdvanceOrderEntity order = service.getOrderInAdvance();
                        fee = calcFee(order.getArrivalDate(), order.getArrivalHours(), order.getArrivalMinutes(),
                                order.getLeavingDate(), order.getLeavingHours(), order.getLeavingMinutes(), false);
                        double feeAccordingToParkedTime = calcFee(order.getArrivalDate(), order.getArrivalHours(), order.getArrivalMinutes(),
                                leavingDate, leavingHours, leavingMinutes, false);
                        fee -= feeAccordingToParkedTime;
                        message1.setFee(fee);
                        // Update balance
                        List<User> lstUsers = getAllWhereIdEquals(User.class, id, "id");
                        User us = lstUsers.get(0);
                        us.setBalance(us.getBalance() + fee);
                        session.beginTransaction();
                        session.update(us);
                        session.flush();
                        session.getTransaction().commit();
                        // delete order
                        session.beginTransaction();
                        session.delete(order);
                        session.flush();
                        session.getTransaction().commit();
                        exitCar(carNum, parkId);
                    }
                }
                client.sendToClient(message1);
            } else if (msg instanceof PayInPlaceOrderMessage) {
                PayInPlaceOrderMessage message = (PayInPlaceOrderMessage) msg;
                String carNum = message.getCarNumber(), parkingLot = message.getParkingLot();
                String cvvCard = message.getCvv(), yearCard = message.getYear(), monthCard = message.getMonth(), cardNum = message.getCardNumber();
                PayValidator validator = new PayValidator(cardNum, cvvCard, yearCard, monthCard);
                message.setResult(validator.validatePayment());
                List<ParkingLotEntitiy> parkingLots = getAll(ParkingLotEntitiy.class);
                int currParking = 0;
                for (ParkingLotEntitiy park : parkingLots) {
                    if (park.getName().equals(parkingLot)) {
                        currParking = park.getId();
                    }
                }
                List<InPlaceOrderEntity> orders = getAll(InPlaceOrderEntity.class);
                InPlaceOrderEntity myOrder = null;
                for (InPlaceOrderEntity order : orders) {
                    if (order.getCarNumber().equals(carNum)) {
                        myOrder = order;
                        break;
                    }
                }
                if (message.isResult()) {
                    // delete order
                    session.beginTransaction();
                    session.delete(myOrder);
                    session.flush();
                    session.getTransaction().commit();
                    exitCar(carNum, currParking);
                }
                client.sendToClient(message);
            }
            else if (msg instanceof ExitFullMemberMessage) {
                ExitFullMemberMessage message = (ExitFullMemberMessage) msg;
                String carNum = message.getCarNumber();
                String leavingDate = message.getLeavingDate();
                String leavingHours = message.getLeavingHours();
                String leavingMinutes = message.getLeavingMinutes();
                FullMemberShipEntity fullMember = getWhereIdEquals(FullMemberShipEntity.class,carNum,"CarNumber");
                String arrivingDate = fullMember.getTimeEnteredPark();
                int parkId = getParkIdByName(fullMember.getParkedLocation());
                String leavingTimeAndDate = leavingDate + " " + leavingHours + ":" + leavingMinutes;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime dateTimeArrival = LocalDateTime.parse(arrivingDate,formatter);
                LocalDateTime dateTimeLeaving = LocalDateTime.parse(leavingTimeAndDate,formatter);
                message.setResult(!dateTimeLeaving.isBefore(dateTimeArrival) && !dateTimeLeaving.equals(dateTimeArrival));
                if(message.getResult()){
                    Duration dur = Duration.between(dateTimeArrival,dateTimeLeaving);
                    double hoursParked = dur.toHours()+(double)(dur.toMinutesPart());
                    session.beginTransaction();
                    fullMember.setHoursLeft(fullMember.getHours_Left()-hoursParked);
                    fullMember.setParkedLocation(null);
                    fullMember.setParked(false);
                    fullMember.setTimeEnteredPark("");
                    message.setHoursLeft(fullMember.getHours_Left());
                    session.update(fullMember);
                    session.flush();
                    session.getTransaction().commit();
                    exitCar(carNum, parkId);
                }
                client.sendToClient(message);
            }
            else if (msg instanceof ExitStandardMemberMessage) {
                ExitStandardMemberMessage message = (ExitStandardMemberMessage) msg;
                String carNum = message.getCarNumber();
                String leavingDate = message.getLeavingDate();
                String leavingHours = message.getLeavingHours();
                String leavingMinutes = message.getLeavingMinutes();
                StandardMemberShipEntity standardMember = getWhereIdEquals(StandardMemberShipEntity.class,carNum,"CarNumber");
                String arrivingDate = standardMember.getTimeEnteredPark();
                int parkId = getParkIdByName(standardMember.getParkedLocation());
                String leavingTimeAndDate = leavingDate + " " + leavingHours + ":" + leavingMinutes;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime dateTimeArrival = LocalDateTime.parse(arrivingDate,formatter);
                LocalDateTime dateTimeLeaving = LocalDateTime.parse(leavingTimeAndDate,formatter);
                message.setResult(!dateTimeLeaving.isBefore(dateTimeArrival) && !dateTimeLeaving.equals(dateTimeArrival));
                if(message.getResult()){
                    Duration dur = Duration.between(dateTimeArrival,dateTimeLeaving);
                    double hoursParked = dur.toHours()+(double)(dur.toMinutesPart());
                    session.beginTransaction();
                    standardMember.setHours_Left(standardMember.getHours_Left()-hoursParked);
                    standardMember.setParkedLocation(null);
                    standardMember.setParked(false);
                    standardMember.setTimeEnteredPark("");
                    message.setHoursLeft(standardMember.getHours_Left());
                    session.update(standardMember);
                    session.flush();
                    session.getTransaction().commit();
                    exitCar(carNum, parkId);
                }
                client.sendToClient(message);
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
                } else if (request.equals("print parking table")){
                    System.out.println("print parking table message");
// Connect to the database and retrieve the data from the parkinglots table
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cps-db", "root", "Polkmn7220@")) {
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
                //we got a message from client requesting to echo Hello, so we will send back to client Hello world!
                else if (request.startsWith("print prices table")) {
                    List<Prices> lst = getAll(Prices.class);
                    message.setPlist(lst);
                    message.setMessage("prices list is sent");
                    client.sendToClient(message);
                }
                if (request.startsWith("attempt to change data")) {

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
            }catch (Exception e) {
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
