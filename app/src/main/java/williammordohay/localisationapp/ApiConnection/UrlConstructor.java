package williammordohay.localisationapp.ApiConnection;

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


    public static String getLineInfoUrl(String lineId){
        return("https://data.metromobilite.fr/api/routers/default/index/routes?codes=" + lineId);

    }

    public static String getStopTimeUrl(String stopId){
        return("https://data.metromobilite.fr/api/routers/default/index/stops/" + stopId + "/stoptimes");

    }

    public static String getGoogleMapPos(){
        return("https://maps.googleapis.com/maps/api/geocode/json?address=5%20Rue%20de%20New-York,%20Grenoble&key=AIzaSyDeDG09ZXIlnEfkHNkA1zHqHyCb2t7SZks");

    }

    public static String getGeoCoordonates(String placeType,String placeName){
        return("https://maps.googleapis.com/maps/api/geocode/json?address="+placeType+"+"+placeName+"+"+"Grenoble&key="+myGoogleApiKey);

    }

    //https://maps.googleapis.com/maps/api/geocode/json?address=5,+rue+de+New-York,+Grenoble&key=AIzaSyDeDG09ZXIlnEfkHNkA1zHqHyCb2t7SZks

    //https://maps.googleapis.com/maps/api/geocode/json?address=rue+NewYork+Grenoble&key=AIzaSyDeDG09ZXIlnEfkHNkA1zHqHyCb2t7SZks


}
