package williammordohay.localisationapp.EntryData;

/**
 * Created by William on 23/11/2017.
 */

public class Location {
    private int lat;
    private int lng;

    public Location(int lat, int lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }
}
