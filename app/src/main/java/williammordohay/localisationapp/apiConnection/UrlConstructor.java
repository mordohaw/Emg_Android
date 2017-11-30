package williammordohay.localisationapp.apiConnection;

/**
 * Created by William on 22/11/2017.
 */

public class UrlConstructor {

    private static final String myGoogleApiKey="AIzaSyDeDG09ZXIlnEfkHNkA1zHqHyCb2t7SZks";

    //public static String getGeolocUrl()
    //{
      //  return("https://www.googleapis.com/geolocation/v1/geolocate?key="+myGoogleApiKey);
    //}

    public static String getStopUrl(String longitude, String lattitude, String distance){
        return("http://data.metromobilite.fr/api/linesNear/json?x=" + longitude + "&y=" + lattitude + "&dist=" + distance + "&details=false");

    }
}
