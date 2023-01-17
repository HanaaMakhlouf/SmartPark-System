package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Column;
import java.io.Serializable;

public class AbsSpot implements Serializable {
    private int width_num;

    public AbsSpot() {

    }

    public int getWidth_num() {
        return width_num;
    }

    public void setWidth_num(int width_num) {
        this.width_num = width_num;
    }

    public int getHeight_num() {
        return height_num;
    }

    public void setHeight_num(int height_num) {
        this.height_num = height_num;
    }

    public int getDepth_num() {
        return depth_num;
    }

    public void setDepth_num(int depth_num) {
        this.depth_num = depth_num;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public int getPark_id() {
        return park_id;
    }

    public void setPark_id(int park_id) {
        this.park_id = park_id;
    }

    private int height_num;
    private int depth_num;
    private boolean available;
    private boolean saved;
    private boolean disabled;
    private int park_id;

    public AbsSpot(int width_num, int height_num, int depth_num, boolean available, boolean saved, boolean disabled, int park_id) {
        this.width_num = width_num;
        this.height_num = height_num;
        this.depth_num = depth_num;
        this.available = available;
        this.saved = saved;
        this.disabled = disabled;
        this.park_id = park_id;
    }

}
