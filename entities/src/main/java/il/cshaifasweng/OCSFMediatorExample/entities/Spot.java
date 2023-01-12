package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import javax.persistence.*;


public class Spot {
    int width;
    int column;
    int row;
    boolean available;
    boolean saved;

    public Spot(int width, int column, int row) {
        this.width = width;
        this.column = column;
        this.row = row;
    }

    public Spot(int i, int j, int k, boolean b, boolean b1) {
        this.width = i;
        this.column = j;
        this.row = k;
        this.available=b;
        this.saved=b1;
    }

    public int getWidth() {
        return width;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

//    private static final int columns = 3;
//    private static final int rows = 3;
}
