package williammordohay.localisationapp.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import williammordohay.localisationapp.ApiConnection.WebServiceGetter;
import williammordohay.localisationapp.Position.PositionTask;

/**
 * Created by William on 22/11/2017.
 */

public abstract class CommunicationActivity extends AppCompatActivity{

    protected Gson gson;
    protected String inputData="";


    //ma clé : AIzaSyDeDG09ZXIlnEfkHNkA1zHqHyCb2t7SZks
    protected double longitudeGPS, latitudeGPS;

    //private static final int REQUEST_LOCATION=1;
    protected LocationManager locationManager;


    //API CLIENT PART

    public String recupereDonnees(String urlRequete)
    {
        //réception de données provenants du service Web
        String donneesEntrantes="";
        try
        {

            AsyncTask monAsyncTask = new WebServiceGetter().execute(urlRequete);
            donneesEntrantes = (String) monAsyncTask.get();
            monAsyncTask.cancel(true);
        }
        catch (InterruptedException e)
        {
            //debugActivite();
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            //debugActivite();
            e.printStackTrace();
        }
        return donneesEntrantes;
    }



    // LOCALISATION PART

    public String[] recuperePosition(Context context)
    {
        //réception de données provenants du service Web
        String[] donneesEntrantes= new String[2];
        try
        {

            AsyncTask monAsyncTask = new PositionTask().execute(context);
            donneesEntrantes = (String[]) monAsyncTask.get();
            monAsyncTask.cancel(true);
        }
        catch (InterruptedException e)
        {
            //debugActivite();
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            //debugActivite();
            e.printStackTrace();
        }
        return donneesEntrantes;
    }

    protected boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }


    public void getPosition() {


        if (!checkLocation())
            return;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2 * 60 * 1000, 10, locationListenerGPS);
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
