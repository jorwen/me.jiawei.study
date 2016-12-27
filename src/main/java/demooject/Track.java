package demooject;

import java.io.Serializable;

/**
 * 曲目
 */
public class Track implements Serializable {
    private static final long serialVersionUID = 1123189738192789L;

    private String name;

    private int time;

    private String type;

    public Track(){}

    public Track(String name, Integer time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
