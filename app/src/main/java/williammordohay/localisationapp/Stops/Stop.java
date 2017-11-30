package williammordohay.localisationapp.Stops;

/**
 * Created by William on 30/11/2017.
 */

public class Stop {
    private String id;
    private String name;
    private double lon;
    private double lat;
    private String[] lines;

    public Stop(String id, String name, double lon, double lat, String[] lines) {
        this.id = id;
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.lines = lines;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String[] getLines() {
        return lines;
    }

    public void setLines(String[] lines) {
        this.lines = lines;
    }
}
