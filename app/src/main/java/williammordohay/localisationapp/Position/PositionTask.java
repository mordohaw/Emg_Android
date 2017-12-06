package williammordohay.localisationapp.Position;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * Created by William on 06/12/2017.
 */

public class PositionTask extends AsyncTask<Context,String,String[]> {
    double longitudeGPS, latitudeGPS;
    @Override
    protected String[] doInBackground(Context... params) {



        //private static final int REQUEST_LOCATION=1;

        //LocationManager locationManager = (LocationManager) getSystemService(params[0].LOCATION_SERVICE);;

            if (ActivityCompat.checkSelfPermission(params[0], Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(params[0], Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2 * 60 * 1000, 10, locationListenerGPS);

            //getLocation();
            //TextView positionTextView = (TextView) findViewById(R.id.myPositionText);
            //Gson myGson = new Gson();
            //String locUrl= UrlConstructor.getGeolocUrl();
            //String entryString = recupereDonnees(locUrl);
            //get the data from WebService


            //if(entryString != null)
            //{
            //  Localisation myLoc = myGson.fromJson(entryString,Localisation.class);

            //positionTextView.setText();
            //}

        return  null;


}

    private final LocationListener locationListenerGPS = new LocationListener() {
        public void onLocationChanged(final Location location) {
            longitudeGPS = location.getLongitude();
            latitudeGPS = location.getLatitude();

            /*runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "GPS Provider update", Toast.LENGTH_SHORT).show();
                }
            });*/
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

}