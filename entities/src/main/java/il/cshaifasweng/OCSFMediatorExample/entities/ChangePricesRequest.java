package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="Requests")
public class ChangePricesRequest implements Serializable {
    @Id
    @Column(name = "RequestNum")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestID;
    @Column(name = "inAdvance")
    private int inAdv;
    @Column(name = "NewInPlace")
    private int inPlace;
    @Column(name = "RegularSingle")
    private int regMemS;
    @Column(name = "RegularMultiple")
    private int regMemM;
    @Column(name = "FullMember")
    private int fullMem;
    @Column(name = "GmApprove")
    private boolean GMapprove=false;
    @Column(name = "MangerID")
    private String ManagerID;

    public String getManagerID() {
        return ManagerID;
    }

    public void setManagerID(String managerID) {
        ManagerID = managerID;
    }

    public boolean isGMapprove() {
        return GMapprove;
    }

    public void setGMapprove(boolean GMapprove) {
        this.GMapprove = GMapprove;
    }

    public ChangePricesRequest(String id,int inAdv, int inPlace, int regMemS, int regMemM, int fullMem) {
        this.ManagerID = id;
        this.inAdv = inAdv;
        this.inPlace = inPlace;
        this.regMemS = regMemS;
        this.regMemM = regMemM;
        this.fullMem = fullMem;
    }

    public ChangePricesRequest() {
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getInAdv() {
        return inAdv;
    }

    public void setInAdv(int inAdv) {
        this.inAdv = inAdv;
    }

    public int getInPlace() {
        return inPlace;
    }

    public void setInPlace(int inPlace) {
        this.inPlace = inPlace;
    }

    public int getRegMemS() {
        return regMemS;
    }

    public void setRegMemS(int regMemS) {
        this.regMemS = regMemS;
    }

    public int getRegMemM() {
        return regMemM;
    }

    public void setRegMemM(int regMemM) {
        this.regMemM = regMemM;
    }

    public int getFullMem() {
        return fullMem;
    }

    public void setFullMem(int fullMem) {
        this.fullMem = fullMem;
    }

    public String getMangerID() { return ManagerID;}

    public void setMangerID(String mangerID) {ManagerID = mangerID;}
}
