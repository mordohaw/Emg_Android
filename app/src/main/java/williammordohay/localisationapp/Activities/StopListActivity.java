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
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import williammordohay.localisationapp.Stops.Stop;
import williammordohay.localisationapp.R;
import williammordohay.localisationapp.ApiConnection.UrlConstructor;
import williammordohay.localisationapp.Stops.StopAdapter;
import williammordohay.localisationapp.Timetables.TimeTable;

public class StopListActivity extends CommunicationActivity {

    /*

     Liste des lignes de transport : http://data.metromobilite.fr/api/routers/default/index/routes?codes=SEM:12

     Horaires au poteau : https://data.metromobilite.fr/api/routers/default/index/stops/SEM:3207/stoptimes
     */

    //ma clé : AIzaSyDeDG09ZXIlnEfkHNkA1zHqHyCb2t7SZks
    protected double longitudeGPS, latitudeGPS;

    //private static final int REQUEST_LOCATION=1;
    protected LocationManager locationManager;

    private String stopUrl="";
    private ListView vueListe;
    private StopAdapter myStopAdapter;
    private List<Stop> stopList = new ArrayList<>();
    private String positionLat,positionLong;
    private SwipeRefreshLayout refreshView;
    private static int refreshTime = 5;
    private Spinner distanceSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_list);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        distanceSpinner = (Spinner) findViewById(R.id.distanceSpinner);
        populateSpinner();

        //On récupère la valeur
        //Bundle extras = getIntent().getExtras();
        updatePosition();

        vueListe = (ListView) findViewById(R.id.stopListView);
        gson = new Gson();

        populateStopList(vueListe);

        refreshView = (SwipeRefreshLayout)findViewById(R.id.refreshList);
        refreshList();

    }

    public void updatePosition(){
        //if (checkLocation())
        getPosition();
        positionLat = String.valueOf(latitudeGPS);//extras.getString("myLatitude");
        positionLong = String.valueOf(longitudeGPS); //extras.getString("myLongitude");
    }

    public void populateStopList(ListView myListView){
        //populate the StopListView
        stopList = getStops();
        myStopAdapter = new StopAdapter(this,stopList);

        myListView.setAdapter(myStopAdapter);
        myListView.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //TextView lineTextView = (TextView) findViewById(R.id.line_value);
                //String myStr = lineTextView.toString();
                //StopAdapter.StopView myItem = (StopAdapter.StopView) parent.getItemAtPosition(position).getClass();
                Stop selectedStop = getStops().get(position);
                view.setBackgroundColor(0000);


                goToStopActivity(selectedStop);

            }
        });
        //adapteurProduction=new AdapteurProduction(this,listeProduction);
        //vueListe.setAdapter(adapteurProduction);

    }

    public void goToStopActivity(Stop currentStop){
        String selectedStopId = currentStop.getId();
        String[] selectedStopLine = currentStop.getLines();
        Intent StopIntent = new Intent(StopListActivity.this, StopMenuActivity.class);

        //pass the infos about the stop
        passStopInfo(StopIntent, selectedStopId, selectedStopLine);

        startActivity(StopIntent);
    }

    public void passStopInfo(Intent communication, String id, String[] lines)
    {
        Bundle paquetSortant = new Bundle();

        paquetSortant.putString("myId", id);
        paquetSortant.putStringArray("myLines", lines);
        communication.putExtras(paquetSortant);
    }



    public List<Stop> getStops(){
        // "5.7180759" -- "45.1927837--500"     positionLong   positionLat
        stopUrl = UrlConstructor.getStopUrl("5.7180759","45.1927837",distanceSpinner.getSelectedItem().toString());
        inputData = recupereDonnees(stopUrl);

        return (gson.fromJson(inputData, new TypeToken<List<Stop>>(){}.getType()));
    }

    private void refreshList()
    {


        //rafraichirListe long-time task in background thread
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(!isDestroyed())
                {
                    try
                    {
                        if(distanceSpinner.getSelectedItem().toString()=="1000"){
                            refreshTime=10;
                        }else{
                            refreshTime=5;
                        }
                        //dummy delay for "tpsRafraichissement" second
                        Thread.sleep(refreshTime*1000);

                        //update ui on UI thread
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {

                                //set the rafraichirListe
                                vueListe.invalidateViews();
                                refreshView.setRefreshing(true);
                                //set the action on up dating

                                updatePosition();
                                populateStopList(vueListe);


                                //Update the list
                                vueListe.invalidateViews();
                                refreshView.setRefreshing(false);
                            }
                        });
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                }



            }
        }).start();


    }
    public void quitActivity(View v)
    {
        StopListActivity.this.finish();
    }

    public void populateSpinner(){
        String distanceTab[] = {"300", "500", "1000"};



        //populate the spinner
        ArrayAdapter<String> lineNumberAdapter = new ArrayAdapter<>(this, R.layout.element_spinner, distanceTab);
        distanceSpinner.setAdapter(lineNumberAdapter);
        //set default selected value
        distanceSpinner.setSelection(1);
    }


    // LOCALISATION PART

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
