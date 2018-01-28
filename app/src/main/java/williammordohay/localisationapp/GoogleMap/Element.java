package williammordohay.localisationapp.GoogleMap;

/**
 * Created by William on 16/01/2018.
 */

public class Element {
    private AddressComponent[] address_components;
    private String formatted_address, place_id;
    private Geometry geometry;
    private boolean partial_match;
    private String[] types;

    public Geometry getGeometry() {
        return geometry;
    }
}
