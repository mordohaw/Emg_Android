package williammordohay.localisationapp.GoogleMap;

/**
 * Created by William on 16/01/2018.
 */

public class Bounds {
    private LocationMap northeast, southwest;

    public double getPositionLat() {
        return ((northeast.getLat()+southwest.getLat())/2);
    }

    public double getPositionLong() {
        return ((northeast.getLng()+southwest.getLng())/2);
    }
}
