package williammordohay.localisationapp.EntryData;

/**
 * Created by William on 22/11/2017.
 */

public class Localisation {
    //{ "location": { "lat": 48.856614, "lng": 2.3522219000000004 }, "accuracy": 314845.0 }

    private Location location;
    private int accuracy;

    public Localisation(Location location, int accuracy) {
        this.location = location;
        this.accuracy = accuracy;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }
}
