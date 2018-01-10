package williammordohay.localisationapp.Timetables;

/**
 * Created by William on 07/01/2018.
 */

public class TimeTable {
    private PatternObject pattern;
    private TimeObject[] times;

    /*public TimeTable(PatternObject pattern, TimeObject[] times) {
        //this.pattern = new PatternObject()
        this.pattern = pattern;
        this.times = times;
    }*/

    public PatternObject getPattern() {
        return pattern;
    }

    /*public void setPattern(PatternObject pattern) {
        this.pattern = pattern;
    }*/

    public TimeObject[] getTimes() {
        return times;
    }

    /*public void setTimes(TimeObject[] times) {
        this.times = times;
    }*/
}
