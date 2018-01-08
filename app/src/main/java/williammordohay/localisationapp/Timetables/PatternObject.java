package williammordohay.localisationapp.Timetables;

/**
 * Created by William on 08/01/2018.
 */

public class PatternObject {
    private String id, desc, shortDesc;
    private int dir;

    public PatternObject(String id, String desc, String shortDesc, int dir) {
        this.id = id;
        this.desc = desc;
        this.shortDesc = shortDesc;
        this.dir = dir;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
}
